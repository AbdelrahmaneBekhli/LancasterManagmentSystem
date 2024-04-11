package com.example.lancastermanagmentsystem;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class PDFWriter {
    private final PdfDocument pdf;
    private final Canvas documentCanvas;

    private final float courseFontSize = 18;
    public PDFWriter() throws IOException {
        byte[] imageBytes = Files.readAllBytes(Paths.get("src/main/resources/com/example/lancastermanagmentsystem/images/Banner.png"));
        Image image = new Image(ImageDataFactory.create(imageBytes));
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        Paragraph title = new Paragraph(new Text("Lancaster's Menu").setFont(boldFont).setFontColor(new DeviceRgb(43, 51, 54))).setTextAlignment(TextAlignment.CENTER).setFontSize(30);

        String dest = "Menu.pdf"; // Output file path
        PdfWriter writer2 = new PdfWriter(dest);
        pdf = new PdfDocument(writer2);
        PageSize pageSize = PageSize.A3; // Set the page size

        // Add a new page to the PDF
        pdf.addNewPage(pageSize);

        // Create a PdfCanvas instance
        PdfCanvas canvas = new PdfCanvas(pdf.getFirstPage());
        canvas.rectangle(0, 0, pageSize.getWidth(), pageSize.getHeight());

        // Set the background color
        canvas.setFillColor(ColorConstants.LIGHT_GRAY).fill();

        // Set Border
        canvas.setLineWidth(20);
        canvas.setStrokeColor(ColorConstants.GRAY);
        canvas.rectangle(0, 0, pageSize.getWidth(), pageSize.getHeight());
        canvas.stroke();

        documentCanvas = new Canvas(canvas, pageSize, true);
        documentCanvas.add(image.setMarginLeft(-8));
        documentCanvas.add(title);
    }

    public void addFirstCourse(ArrayList<DishInfo> dishes){
        Paragraph header = new Paragraph(new Text("First Course").setFontColor(new DeviceRgb(198, 150, 78))).setFontSize(courseFontSize).setMarginLeft(30);
        documentCanvas.add(header);
        documentCanvas.add(createCourse(dishes));
    }

    public void addSecondCourse(ArrayList<DishInfo> dishes){
        Paragraph header = new Paragraph(new Text("Second Course").setFontColor(new DeviceRgb(198, 150, 78))).setFontSize(courseFontSize).setMarginLeft(30);
        documentCanvas.add(header);
        documentCanvas.add(createCourse(dishes));
    }

    public void addThirdCourse(ArrayList<DishInfo> dishes){
        Paragraph header = new Paragraph(new Text("Third Course").setFontColor(new DeviceRgb(198, 150, 78))).setFontSize(courseFontSize).setMarginLeft(30);
        documentCanvas.add(header);
        documentCanvas.add(createCourse(dishes));
    }

    private Table createCourse(ArrayList<DishInfo> dishes){
        Table table = new Table(3);
        float dishFontSize = 16;
        for(DishInfo dish : dishes){
            float price = dish.getPrice();
            String formattedPrice = String.format("%.2f", price);
            table.addCell(new Cell().add(new Paragraph(dish.getName()).setMarginLeft(70).setFontColor(ColorConstants.BLACK).setFontSize(dishFontSize)).setWidth(250).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph(dish.getRecommendedWine()).setMarginLeft(70).setFontColor(ColorConstants.BLACK).setFontSize(dishFontSize)).setWidth(450).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add(new Paragraph("£"+formattedPrice).setMarginRight(70).setFontColor(ColorConstants.BLACK).setFontSize(dishFontSize)).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(0, 3).add(new Paragraph("ingredients").setMarginLeft(70).setFontColor(ColorConstants.BLACK).setFontSize(dishFontSize - 5)).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(0, 3).add(new Paragraph(dish.getDescription()).setMarginLeft(70).setFontColor(ColorConstants.BLACK).setFontSize(dishFontSize - 5)).setBorder(Border.NO_BORDER));
        }
        return table;
    }


    public void generatePdf(){
        documentCanvas.close();
        pdf.close();
    }


}
