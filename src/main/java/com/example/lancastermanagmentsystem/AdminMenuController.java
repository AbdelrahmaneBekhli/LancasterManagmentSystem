package com.example.lancastermanagmentsystem;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    @FXML
    Button menu, graph, supplier, staff, logout;

    @FXML
    Text user, dateTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(user.getText() + " " + LoginController.user);
        ButtonImage menuButton = new ButtonImage(menu, "Main Menu");
        ButtonImage graphButton = new ButtonImage(graph, "Graph");
        ButtonImage supplierButton = new ButtonImage(supplier, "Supplier");
        ButtonImage staffButton = new ButtonImage(staff, "Staff");
        ButtonImage logOutButton = new ButtonImage(logout, "Logout");

        menuButton.setGraphics();
        graphButton.setGraphics();
        supplierButton.setGraphics();
        staffButton.setGraphics();
        logOutButton.setGraphics();


        menu.setOnAction(e -> {
            try{
                LoginPage.setMenusMenu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        supplier.setOnAction(e ->{
            try{
                LoginPage.setSupplierPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        logout.setOnAction(e -> {
            // Load the login.fxml file
            try {
                LoginPage.setLoginPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime.setText(now.format(formatter));

        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(1), event -> LoginPage.updateDateTime(dateTime))
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        timeline.play();
    }
}
