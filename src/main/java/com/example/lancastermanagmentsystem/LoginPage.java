package com.example.lancastermanagmentsystem;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginPage extends Application {

    public static Stage stage;
    public static DatabaseConnectivity database = new DatabaseConnectivity();

    @Override
    public void start(Stage stage) throws Exception {
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

    public static void setAdminPage() throws IOException {
        Parent adminFxml = new FXMLLoader(LoginPage.class.getResource("AdminMenu.fxml")).load();
        Scene adminScene = new Scene(adminFxml, get_width(), get_height());
        adminScene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("styles.css")).toExternalForm());
        stage.setScene(adminScene);
    }

}
