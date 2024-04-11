package com.example.lancastermanagmentsystem;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LoginController implements Initializable {
    @FXML
    ComboBox<String> loginSelection;

    @FXML
    Text dateTime;

    @FXML
    Button one, two, three, four, five, six, seven, eight, nine, zero, erase, confirm;

    @FXML
    PasswordField password;

    public static boolean launch;

    private String[] users = {"Owner", "Manager", "Admin", "Sommelier"};;

    public static String user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String[]> users = new ArrayList<>();
        if(DatabaseConnectivity.startConnection()){
            users = DatabaseConnectivity.retrieveLogin();
            launch = true;
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Connection Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to connect to database, check correct VPN is used.");
            alert.showAndWait();
            launch = false;
        }
        ArrayList<String> logins = new ArrayList<>();

        for (String[] user : users) {
            String name = user[0];
            String role = user[1];
            logins.add(name + " - " + role);
        }
        loginSelection.getItems().addAll(logins);
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime.setText(now.format(formatter));

        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(1), event -> updateDateTime())
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        timeline.play();

        // Set event handlers for each button
        one.setOnAction(e -> appendCharacter("1"));
        two.setOnAction(e -> appendCharacter("2"));
        three.setOnAction(e -> appendCharacter("3"));
        four.setOnAction(e -> appendCharacter("4"));
        five.setOnAction(e -> appendCharacter("5"));
        six.setOnAction(e -> appendCharacter("6"));
        seven.setOnAction(e -> appendCharacter("7"));
        eight.setOnAction(e -> appendCharacter("8"));
        nine.setOnAction(e -> appendCharacter("9"));
        zero.setOnAction(e -> appendCharacter("0"));

        erase.setOnAction(e -> {
            String currentPassword = password.getText();
            if (!currentPassword.isEmpty()) {
                password.setText(currentPassword.substring(0, currentPassword.length() - 1));
            }
        });

        password.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!isNumeric(event.getCharacter())) {
                event.consume(); // Consume the event to prevent non-numeric characters from being entered
            }
        });

        confirm.setOnAction(e -> {
            String enteredPassword = password.getText();
            // Clear the password field after confirmation if needed
            password.clear();
            user = loginSelection.getValue();
            if(user == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Select User");
                alert.setHeaderText(null);
                alert.setContentText("No user selected. Please select a user.");
                alert.showAndWait();
            }
            else if(!DatabaseConnectivity.checkPassword(user.split(" ")[0], enteredPassword)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Password");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect password entered. Please try again.");
                alert.showAndWait();
            } else{
                try {
                    user = user.split(" ")[1] + " " + user.split(" ")[2];
                    LoginPage.setAdminPage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private void appendCharacter(String character) {
        password.appendText(character);
    }

    // Method to update the date and time
    private void updateDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime.setText(now.format(formatter));
    }
}
