package com.example.lancastermanagmentsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    Button dashboard, menu, statistics, supplier, staff, logout, confirm, undo;

    @FXML
    ComboBox<String> dataSelection;

    @FXML
    Text user, dateTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(LoginController.user);
        ButtonImage dashboardButton = new ButtonImage(dashboard, "Dashboard");
        ButtonImage menuButton = new ButtonImage(menu, "Menu");
        ButtonImage statisticsButton = new ButtonImage(statistics, "Statistics", true);
        ButtonImage supplierButton = new ButtonImage(supplier, "Supplier");
        ButtonImage staffButton = new ButtonImage(staff, "Staff");
        ButtonImage logoutButton = new ButtonImage(logout, "Logout");

        dashboardButton.setGraphics();
        menuButton.setGraphics();
        statisticsButton.setGraphics();
        supplierButton.setGraphics();
        staffButton.setGraphics();
        logoutButton.setGraphics();

        LoginPage.setTime(dateTime);


        String[] dataType = {"Earnings", "Dishes Sold", "Booking Quantity"};
        dataSelection.getItems().addAll(dataType);




        dashboard.setOnAction(e -> {
            try {
                LoginPage.setDashboardPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        menu.setOnAction(e -> {
            try {
                LoginPage.setMenusMenu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        supplier.setOnAction(e -> {
            try {
                LoginPage.setSupplierPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        staff.setOnAction(e -> {
            try {
                LoginPage.setStaffPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        logout.setOnAction(e -> {
            try {
                LoginPage.setLoginPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}
