package com.example.lancastermanagmentsystem;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    @FXML
    Button menu, graph, supplier, staff, logout;

    @FXML
    Text user;

    @FXML
    Text dateTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(user.getText() + " " + LoginController.user);
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/fonts/Dishcek.otf"), 20);

        Label menuLabel = new Label("Menu");
        Label graphLabel = new Label("Graph");
        Label supplierLabel = new Label("Supplier");
        Label staffLabel = new Label("Staff");
        Label logoutLabel = new Label("Logout");

        menuLabel.setFont(customFont);
        graphLabel.setFont(customFont);
        supplierLabel.setFont(customFont);
        staffLabel.setFont(customFont);
        logoutLabel.setFont(customFont);

        InputStream buttonImageLoc = getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/images/button.png");
        InputStream hoverImageLoc = getClass().getResourceAsStream("/com/example/lancastermanagmentsystem/images/buttonHover.png");

        Image image = new Image(buttonImageLoc);
        Image hoverImage = new Image(hoverImageLoc);

        ImageView menuImage = new ImageView(image);
        ImageView graphImage = new ImageView(image);
        ImageView supplierImage = new ImageView(image);
        ImageView staffImage = new ImageView(image);
        ImageView logoutImage = new ImageView(image);

        menu.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY))); // Make the button background transparent
        graph.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY))); // Make the button background transparent
        supplier.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY))); // Make the button background transparent
        staff.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY))); // Make the button background transparent
        logout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY))); // Make the button background transparent

        menu.setOnMouseEntered(event -> menuImage.setImage(hoverImage));
        menu.setOnMouseExited(event -> menuImage.setImage(image));
        graph.setOnMouseEntered(event -> graphImage.setImage(hoverImage));
        graph.setOnMouseExited(event -> graphImage.setImage(image));
        supplier.setOnMouseEntered(event -> supplierImage.setImage(hoverImage));
        supplier.setOnMouseExited(event -> supplierImage.setImage(image));
        staff.setOnMouseEntered(event -> staffImage.setImage(hoverImage));
        staff.setOnMouseExited(event -> staffImage.setImage(image));
        logout.setOnMouseEntered(event -> logoutImage.setImage(hoverImage));
        logout.setOnMouseExited(event -> logoutImage.setImage(image));


        // Set the fit width and height to resize the image
        menuImage.setFitWidth(menu.getPrefWidth());
        menuImage.setFitHeight(menu.getPrefHeight());
        graphImage.setFitWidth(graph.getPrefWidth());
        graphImage.setFitHeight(graph.getPrefHeight());
        supplierImage.setFitWidth(supplier.getPrefWidth());
        supplierImage.setFitHeight(supplier.getPrefHeight());
        staffImage.setFitWidth(supplier.getPrefWidth());
        staffImage.setFitHeight(supplier.getPrefHeight());
        logoutImage.setFitWidth(supplier.getPrefWidth());
        logoutImage.setFitHeight(supplier.getPrefHeight());


        menu.setGraphic(new StackPane(menuImage, menuLabel));
        graph.setGraphic(new StackPane(graphImage, graphLabel));
        supplier.setGraphic(new StackPane(supplierImage, supplierLabel));
        staff.setGraphic(new StackPane(staffImage, staffLabel));
        logout.setGraphic(new StackPane(logoutImage, logoutLabel));

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
                new KeyFrame(javafx.util.Duration.seconds(1), event -> updateDateTime())
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        timeline.play();
    }

    // Method to update the date and time
    private void updateDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime.setText(now.format(formatter));
    }
}
