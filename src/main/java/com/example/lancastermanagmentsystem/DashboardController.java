package com.example.lancastermanagmentsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author      abdelrahmane, bekhli, abdelrahmane.bekhli@city.ac.uk
 */
public class    DashboardController implements Initializable {

    @FXML
    PieChart pie;

    @FXML
    Button dashboard, menu, statistics, supplier, staff, logout;

    @FXML
    Text user, dateTime, earnings;

    @FXML
    LineChart<String, Number> earningsGraph;

    /**
     * Initializes Statistics page including all its buttons and components.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(LoginController.user);
        LoginPage.setTime(dateTime);
        earnings.setText(earnings.getText() + " £" + "50,000");



        menu.setOnAction(e ->{
            try {
                LoginPage.setMenusMenu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        statistics.setOnAction(e ->{
            try {
                LoginPage.setStatisticsPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        supplier.setOnAction(e ->{
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

        logout.setOnAction(e ->{
            try {
                LoginPage.setLoginPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int walkIns = 0;
        int onlineBookings = 0;
        int phoneBookings = 0;
        ArrayList<ArrayList<Integer>> data = DatabaseConnectivity.bookingsStats();
        for (ArrayList<Integer> innerList : data) {
            walkIns += innerList.get(0);
            onlineBookings += innerList.get(1);
            phoneBookings += innerList.get(2);
        }


        // Create an observable list to store the data for the pie chart
       ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
              new PieChart.Data("WalkIns", walkIns),
               new PieChart.Data("Online Bookings", onlineBookings),
               new PieChart.Data("Phone Bookings", phoneBookings)
       );

       // Add the data to the pie chart
       pie.setData(pieChartData);

        ButtonImage dashboardButtonImage = new ButtonImage(dashboard, "Dashboard", true);
        ButtonImage menuButtonImage = new ButtonImage(menu, "Menu");
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

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>("Category 1", 30));
        series1.getData().add(new XYChart.Data<>("Category 2", 20));
        series1.getData().add(new XYChart.Data<>("Category 3", 40));
        series1.getData().add(new XYChart.Data<>("Category 4", 50)) ;
        series1.getData().add(new XYChart.Data<>("Category 5", 35));
        series1.getData().add(new XYChart.Data<>("Category 6", 45));
        series1.getData().add(new XYChart.Data<>("Category 7", 55));
        series1.getData().add(new XYChart.Data<>("Category 8", 60));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.getData().add(new XYChart.Data<>("Category 1", 50));
        series2.getData().add(new XYChart.Data<>("Category 2", 10));
        series2.getData().add(new XYChart.Data<>("Category 3", 20));
        series2.getData().add(new XYChart.Data<>("Category 4", 60));
        series2.getData().add(new XYChart.Data<>("Category 5", 30));
        series2.getData().add(new XYChart.Data<>("Category 6", 50));
        series2.getData().add(new XYChart.Data<>("Category 7", 40));
        series2.getData().add(new XYChart.Data<>("Category 8", 70));

        series1.setName("Total Customers");
        series2.setName("Total Earnings");
        addData(series1, series2);
    }

    @SafeVarargs
    final void addData(XYChart.Series<String, Number>... series) {
        earningsGraph.getData().addAll(series);
    }
}
