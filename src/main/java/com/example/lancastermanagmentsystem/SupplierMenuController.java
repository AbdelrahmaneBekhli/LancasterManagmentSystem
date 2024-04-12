package com.example.lancastermanagmentsystem;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SupplierMenuController implements Initializable {

    @FXML
    Button mainMenu, createOrder, editOrder, placeOrder;

    @FXML
    Text user, dateTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        user.setText(user.getText() + " " + LoginController.user);

        ButtonImage menuButton = new ButtonImage(mainMenu, "Main Menu");
        ButtonImage createOrderButton = new ButtonImage(createOrder, "Create Order");
        ButtonImage editOrderButton = new ButtonImage(editOrder, "Edit Order");
        ButtonImage placeOrderButton = new ButtonImage(placeOrder, "Place Order");

        menuButton.setGraphics();
        createOrderButton.setGraphics();
        editOrderButton.setGraphics();
        placeOrderButton.setGraphics();

        mainMenu.setOnAction(e -> {
            try {
                LoginPage.setAdminPage();
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
