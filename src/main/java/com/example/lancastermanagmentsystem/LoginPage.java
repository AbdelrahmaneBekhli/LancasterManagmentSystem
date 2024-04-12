package com.example.lancastermanagmentsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LoginPage extends Application {

    public static Stage stage;
    public static DatabaseConnectivity database = new DatabaseConnectivity();

    @Override
    public void start(Stage stage) throws Exception {
//
//        ArrayList<DishInfo> firstCourse = new ArrayList<>();
//        ArrayList<DishInfo> secondCourse = new ArrayList<>();
//        ArrayList<DishInfo> thirdCourse = new ArrayList<>();
//
//        for (int i = 0; i < 4; i++) {
//            DishInfo dish = new DishInfo("Dish name", 12, "Description", "recommended wine");
//            firstCourse.add(dish);
//        }
//        for (int i = 0; i < 4; i++) {
//            DishInfo dish = new DishInfo("Dish name", 12, "Description", "recommended wine");
//            secondCourse.add(dish);
//        }
//        for (int i = 0; i < 4; i++) {
//            DishInfo dish = new DishInfo("Dish name", 12, "Description", "recommended wine");
//            thirdCourse.add(dish);
//        }
//
//        PDFWriter p = new PDFWriter();
//        p.addFirstCourse(firstCourse);
//        p.addSecondCourse(secondCourse);
//        p.addThirdCourse(thirdCourse);
//        p.generatePdf();

        LoginPage.stage = stage;
        Parent loginFxml = new FXMLLoader(getClass().getResource("login.fxml")).load();
        Scene scene = new Scene(loginFxml, 1280, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/Lancasters-logo.png"))));
        stage.setTitle("Lancaster");
        if(LoginController.launch) {
            stage.setScene(scene);
            stage.show();
        }
    }

    public static double get_width(){
        return stage.getScene().getWindow().getScene().getRoot().getBoundsInLocal().getWidth();
    }

    public static double get_height(){
        return stage.getScene().getWindow().getScene().getRoot().getBoundsInLocal().getHeight();
    }

    public static void main(String[] args) {
            launch();
    }

    public static void setLoginPage() throws IOException {
        Parent loginFxml = new FXMLLoader(LoginPage.class.getResource("login.fxml")).load();
        Scene loginScene = new Scene(loginFxml, get_width(), get_height());
        loginScene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("styles.css")).toExternalForm());
        stage.setScene(loginScene);
    }

    public static void setMenusMenu() throws IOException {
        Parent loginFxml = new FXMLLoader(LoginPage.class.getResource("MenusMenu.fxml")).load();
        Scene loginScene = new Scene(loginFxml, get_width(), get_height());
        loginScene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("styles.css")).toExternalForm());
        stage.setScene(loginScene);
    }

    public static void setAdminPage() throws IOException {
        Parent adminFxml = new FXMLLoader(LoginPage.class.getResource("AdminMenu.fxml")).load();
        Scene adminScene = new Scene(adminFxml, get_width(), get_height());
        adminScene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("styles.css")).toExternalForm());
        stage.setScene(adminScene);
    }

    public static void setSupplierPage() throws IOException {
        Parent adminFxml = new FXMLLoader(LoginPage.class.getResource("SupplierMenu.fxml")).load();
        Scene adminScene = new Scene(adminFxml, get_width(), get_height());
        adminScene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("styles.css")).toExternalForm());
        stage.setScene(adminScene);
    }

    // Method to update the date and time
    static Text updateDateTime(Text dateTime) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime.setText(now.format(formatter));
        return dateTime;
    }


}
