package com.example.lancastermanagmentsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenusMenuController implements Initializable {
    @FXML
    Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnAction(e -> {
            try {
                LoginPage.setAdminPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
