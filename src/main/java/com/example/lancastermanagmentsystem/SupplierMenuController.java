package com.example.lancastermanagmentsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierMenuController implements Initializable {

    @FXML
    Button mainMenu;

    @FXML
    Text user, dateTime;

    @FXML
    Button dashboard, menu, statistics, supplier, staff, logout;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(LoginController.user);


        ButtonImage dashboardButtonImage = new ButtonImage(dashboard, "Dashboard");
        ButtonImage menuButtonImage = new ButtonImage(menu, "Menu");
        ButtonImage graphButtonImage = new ButtonImage(statistics, "statistics");
        ButtonImage supplierButtonImage = new ButtonImage(supplier, "Supplier", true);
        ButtonImage staffButtonImage = new ButtonImage(staff, "Staff");
        ButtonImage logoutButtonImage = new ButtonImage(logout, "logout");

        dashboardButtonImage.setGraphics();
        menuButtonImage.setGraphics();
        graphButtonImage.setGraphics();
        supplierButtonImage.setGraphics();
        staffButtonImage.setGraphics();
        logoutButtonImage.setGraphics();

        mainMenu.setOnAction(e -> {
            try {
                LoginPage.setDashboardPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        LoginPage.setTime(dateTime);

    }
}
