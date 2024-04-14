package com.example.lancastermanagmentsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffController implements Initializable {

    @FXML
    Text dateTime, user;

    @FXML
    Button dashboard, menu, statistics, supplier, staff, logout;

    @FXML
    TableView<Staff> staffTable;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(LoginController.user);
        LoginPage.setTime(dateTime);


        ButtonImage dashboardButtonImage = new ButtonImage(dashboard, "Dashboard");
        ButtonImage menuButtonImage = new ButtonImage(menu, "Menu");
        ButtonImage graphButtonImage = new ButtonImage(statistics, "statistics");
        ButtonImage supplierButtonImage = new ButtonImage(supplier, "Supplier");
        ButtonImage staffButtonImage = new ButtonImage(staff, "Staff", true);
        ButtonImage logoutButtonImage = new ButtonImage(logout, "logout");

        dashboardButtonImage.setGraphics();
        menuButtonImage.setGraphics();
        graphButtonImage.setGraphics();
        supplierButtonImage.setGraphics();
        staffButtonImage.setGraphics();
        logoutButtonImage.setGraphics();

    }
}
