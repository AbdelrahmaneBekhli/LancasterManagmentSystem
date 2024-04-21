package com.example.lancastermanagmentsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class AdminMenusMenuController implements Initializable {
    @FXML
    Button dashboard, menu, statistics, supplier, staff, logout;

    @FXML
    Text user, dateTime;

    /**
     * Initializes Statistics page including all its buttons and components.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(LoginController.user);
        LoginPage.setTime(dateTime);

        ButtonImage dashboardButtonImage = new ButtonImage(dashboard, "Dashboard");
        ButtonImage menuButtonImage = new ButtonImage(menu, "Menu", true);
        ButtonImage graphButtonImage = new ButtonImage(statistics, "Statistics");
        ButtonImage supplierButtonImage = new ButtonImage(supplier, "Supplier");
        ButtonImage staffButtonImage = new ButtonImage(staff, "Staff");
        ButtonImage logoutButtonImage = new ButtonImage(logout, "Logout");

        dashboardButtonImage.setGraphics();
        menuButtonImage.setGraphics();
        graphButtonImage.setGraphics();
        supplierButtonImage.setGraphics();
        staffButtonImage.setGraphics();
        logoutButtonImage.setGraphics();



        dashboard.setOnAction(e -> {
            try {
                LoginPage.setDashboardPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        statistics.setOnAction(e -> {
            try {
                LoginPage.setStatisticsPage();
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
