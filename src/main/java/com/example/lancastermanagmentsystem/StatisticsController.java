package com.example.lancastermanagmentsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class StatisticsController implements Initializable {

    @FXML
    Button dashboard, menu, statistics, supplier, staff, logout, confirm, undo;

    @FXML
    DatePicker startDate, endDate;

    @FXML
    ComboBox<String> dataSelection;

    @FXML
    Text user, dateTime;

    @FXML
    LineChart<String, Number> earningsGraph;

    ArrayList<XYChart.Series<String, Number>> stack = new ArrayList<>();

    /**
     * Initializes Statistics page including all its buttons and components.
     */
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


        String[] dataType = {"Dishes Sold", "Wines sold", "total earnings", "walkins", "online bookings", "phone bookings", "total customers"};
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

        confirm.setOnAction(e ->{
            if(startDate.getValue() == null || endDate.getValue() == null || dataSelection.getValue() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Select Values");
                alert.setHeaderText(null);
                alert.setContentText("Missing data, enter all values first");
                alert.showAndWait();
            } else {
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                Map<String, Integer> map;
                switch (dataSelection.getValue()) {
                    case "Dishes Sold":
                        map = DatabaseConnectivity.getDishSold(String.valueOf(startDate.getValue()), String.valueOf(endDate.getValue()));
                        break; 
                    case "Wines sold":
                        map = DatabaseConnectivity.getWineSold(String.valueOf(startDate.getValue()), String.valueOf(endDate.getValue()));
                        break;
                    case "total earnings":
                        map = DatabaseConnectivity.getEarnings(String.valueOf(startDate.getValue()), String.valueOf(endDate.getValue()));
                        break;
                    case "walkins":
                        map = DatabaseConnectivity.getwalkIns(String.valueOf(startDate.getValue()), String.valueOf(endDate.getValue()));
                        break;
                    case "online bookings":
                        map = DatabaseConnectivity.getOnlineBookings(String.valueOf(startDate.getValue()), String.valueOf(endDate.getValue()));
                        break;
                    case "phone bookings":
                        map = DatabaseConnectivity.getPhoneBookings(String.valueOf(startDate.getValue()), String.valueOf(endDate.getValue()));
                        break;
                    default:
                        map = DatabaseConnectivity.getTotalCustomers(String.valueOf(startDate.getValue()), String.valueOf(endDate.getValue()));
                        break;
                }
                if (!map.isEmpty())
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        series1.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                    }
                earningsGraph.setTitle("Earnings Graph");
                series1.setName(dataSelection.getValue());
                stack.add(series1);
                addData(series1);
            }
        });

        undo.setOnAction(e ->{
            earningsGraph.getData().remove(stack.get(stack.size() - 1));
            stack.remove(stack.size() - 1);
        });

    }
    @SafeVarargs
    final void addData(XYChart.Series<String, Number>... series) {
        earningsGraph.getData().addAll(series);
    }
}
