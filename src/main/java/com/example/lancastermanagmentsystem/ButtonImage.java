package com.example.lancastermanagmentsystem;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.InputStream;

public class ButtonImage {
    private final Button button;
    private final ImageView butttonImage;

    private final Image orgButtonImage;
    private final Image hoverButtonImage;

    private final Label buttonLabel;


    public ButtonImage(Button button, String label) {
        this.button = button;
        this.buttonLabel = new Label(label);

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/fonts/Dishcek.otf"), 20);
        buttonLabel.setFont(customFont);

        InputStream buttonImageLoc = getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/images/button.png");
        InputStream hoverImageLoc = getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/images/buttonHover.png");

        orgButtonImage = new Image(buttonImageLoc);
        hoverButtonImage = new Image(hoverImageLoc);

        butttonImage = new ImageView(orgButtonImage);

    }

    private void setBackground(){
        button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        butttonImage.setFitWidth(button.getPrefWidth());
        butttonImage.setFitHeight(button.getPrefHeight());
    }

    private void setAnimation(){
        button.setOnMouseEntered(event -> butttonImage.setImage(hoverButtonImage));
        button.setOnMouseExited(event -> butttonImage.setImage(orgButtonImage));
    }

    public void setGraphics(){
        setBackground();
        setAnimation();
        button.setGraphic(new StackPane(butttonImage, buttonLabel));
    }
}
