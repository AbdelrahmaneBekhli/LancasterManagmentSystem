package com.example.lancastermanagmentsystem;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
        Parent loginFxml = new FXMLLoader(getClass().getResource("Login.fxml")).load();
        Scene scene = new Scene(loginFxml, 1280, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/Lancasters-logo.png"))));
        stage.setTitle("Lancaster");
        if(LoginController.launch) {
            stage.setMaximized(true);
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

    public static void setDashboardPage() throws IOException {
        Parent dashboardFxml = new FXMLLoader(LoginPage.class.getResource("Dashboard.fxml")).load();
        Scene dashboardScene = new Scene(dashboardFxml, get_width(), get_height());
        dashboardScene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("styles.css")).toExternalForm());
        stage.setScene(dashboardScene);
    }

    public static void setMenusMenu() throws IOException {
        Parent menuFxml = new FXMLLoader(LoginPage.class.getResource("MenusMenu.fxml")).load();
        Scene menuScene = new Scene(menuFxml, get_width(), get_height());
        menuScene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("styles.css")).toExternalForm());
        stage.setScene(menuScene);
    }

    public static void setStatisticsPage() throws IOException {
        Parent statisticsFxml = new FXMLLoader(LoginPage.class.getResource("statistics.fxml")).load();
        Scene statisticsScene = new Scene(statisticsFxml, get_width(), get_height());
        statisticsScene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("styles.css")).toExternalForm());
        stage.setScene(statisticsScene);
    }

    public static void setSupplierPage() throws IOException {
        Parent supplierFxml = new FXMLLoader(LoginPage.class.getResource("SupplierMenu.fxml")).load();
        Scene supplierScene = new Scene(supplierFxml, get_width(), get_height());
        supplierScene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("styles.css")).toExternalForm());
        stage.setScene(supplierScene);
    }

    public static void setStaffPage() throws IOException {
        Parent supplierFxml = new FXMLLoader(LoginPage.class.getResource("Staff.fxml")).load();
        Scene supplierScene = new Scene(supplierFxml, get_width(), get_height());
        supplierScene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("styles.css")).toExternalForm());
        stage.setScene(supplierScene);
    }

    public static Text setTime(Text time){
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        time.setText(now.format(formatter));

        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(1), event -> LoginPage.updateDateTime(time))
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        timeline.play();
        return time;
    }

    // Method to update the date and time
    private static Text updateDateTime(Text dateTime) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime.setText(now.format(formatter));
        return dateTime;
    }


}
