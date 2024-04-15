package com.example.lancastermanagmentsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SupplierMenuController implements Initializable {

    @FXML
    TableView<Order> orderTable;

    @FXML
    TableView<Ingredient> newOrderTable;

    @FXML
    TableColumn<Order, String> ingredient_name, type;

    @FXML
    TableColumn<Ingredient, String> newTableIngredient;

    @FXML
    TableColumn<Order, Integer> quantity;

    @FXML
    TableColumn<Ingredient, Integer> newTableQuantity;

    @FXML
    TableColumn<Order, Float> price;

    @FXML
    TableColumn<Ingredient, Float> newTableSinglePrice, newTableTotalPrice;

    @FXML
    TableColumn<Order, Boolean> delivered;

    @FXML
    TableColumn<Ingredient, Boolean> newTableAllergens, newTableVegan;
    @FXML
    Text user, dateTime;

    @FXML
    DatePicker datePicker;

    @FXML
    Button dashboard, menu, statistics, supplier, staff, logout, searchButton, backButton, saveNew, deleteNew, createOrder, submitNewOrder;

    @FXML
    TextField id, ingredientField, quantityField, priceField, typeField, newID, newPrice, newType;

    @FXML
    Spinner<Integer> newQuantity;

    @FXML
    AnchorPane newOrderPane, searchOrderPane;

    @FXML
    ComboBox<String> newIngredients;

    private ObservableList<Order> orderList;

    ArrayList<Ingredient> list = new ArrayList<>();
    Ingredient selectedIngredient = null;

    ObservableList<Ingredient> selectedIngredientsList = FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(LoginController.user);
        LoginPage.setTime(dateTime);

        ButtonImage dashboardButtonImage = new ButtonImage(dashboard, "Dashboard");
        ButtonImage menuButtonImage = new ButtonImage(menu, "Menu");
        ButtonImage graphButtonImage = new ButtonImage(statistics, "statistics");
        ButtonImage supplierButtonImage = new ButtonImage(supplier, "Supplier", true);
        ButtonImage staffButtonImage = new ButtonImage(staff, "Staff");
        ButtonImage logoutButtonImage = new ButtonImage(logout, "Logout");

        dashboardButtonImage.setGraphics();
        menuButtonImage.setGraphics();
        graphButtonImage.setGraphics();
        supplierButtonImage.setGraphics();
        staffButtonImage.setGraphics();
        logoutButtonImage.setGraphics();

        newTableIngredient.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
        newTableQuantity.setCellValueFactory(new PropertyValueFactory<Ingredient, Integer>("ordered"));
        newTableSinglePrice.setCellValueFactory(new PropertyValueFactory<Ingredient, Float>("price"));
        newTableVegan.setCellValueFactory(new PropertyValueFactory<Ingredient, Boolean>("vegan"));
        newTableAllergens.setCellValueFactory(new PropertyValueFactory<Ingredient, Boolean>("allergen"));
        newTableTotalPrice.setCellValueFactory(new PropertyValueFactory<Ingredient, Float>("price"));
        newTableTotalPrice.setCellValueFactory(new PropertyValueFactory<Ingredient, Float>("totalPrice"));


        list = DatabaseConnectivity.retrieveSupplierStock();

        ArrayList<String> nameList = new ArrayList<>();

        for (Ingredient i : list) {
            nameList.add(i.getName());
        }

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        newQuantity.setValueFactory(valueFactory);
        newIngredients.getItems().setAll(nameList);

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

        dashboard.setOnAction(e ->{
            try {
                LoginPage.setDashboardPage();
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



        orderList =  FXCollections.observableArrayList(DatabaseConnectivity.retrieveOrder());

        ingredient_name.setCellValueFactory(new PropertyValueFactory<Order, String>("ingredient_name"));
        quantity.setCellValueFactory(new PropertyValueFactory<Order, Integer>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<Order, Float>("total_price"));
        type.setCellValueFactory(new PropertyValueFactory<Order, String>("type"));
        delivered.setCellValueFactory(new PropertyValueFactory<Order, Boolean>("delivered"));

        orderTable.setItems(orderList);


    }

    @FXML
    void selected(MouseEvent event){
        Order order = orderTable.getSelectionModel().getSelectedItem();
        id.setText( String.valueOf(order.getID()));
        ingredientField.setText(order.getIngredient_name());
        quantityField.setText(String.valueOf(order.getQuantity()));
        priceField.setText(String.valueOf(order.getTotal_price()));
        typeField.setText(order.getType());

    }
    @FXML
    void searchMenu(){
        String date = datePicker.getValue().toString();
        orderList =  FXCollections.observableArrayList(DatabaseConnectivity.searchOrder(date));
        orderTable.setItems(orderList);
    }

    @FXML
    void backToSearch(){
        newOrderPane.setVisible(false);
        searchOrderPane.setVisible(true);
    }

    @FXML
    void setNewOrder(){
        newOrderPane.setVisible(true);
        searchOrderPane.setVisible(false);
    }
    @FXML
    void loadMax(){
        String ingredientName = newIngredients.getValue();

        for(Ingredient i : list){
            if(Objects.equals(i.getName(), ingredientName)){
                selectedIngredient = i;
            }
        }
        assert selectedIngredient != null;
        newID.setText(String.valueOf(selectedIngredient.getID()));
        newPrice.setText("£"+ selectedIngredient.getPrice());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, selectedIngredient.getQuantity());
        newQuantity.setValueFactory(valueFactory);
        newType.setText(selectedIngredient.getType());
    }

    @FXML
    void updateNewTable(){
        selectedIngredient.setOrdered(newQuantity.getValue());
        selectedIngredient.setTotalPrice(newQuantity.getValue() * selectedIngredient.getPrice());
        selectedIngredientsList.add(selectedIngredient);
        newOrderTable.setItems(selectedIngredientsList);
    }

    @FXML
    void removeNewIngredient(){
        selectedIngredient = newOrderTable.getSelectionModel().getSelectedItem();
        newOrderTable.getItems().remove(selectedIngredient);
    }

    @FXML
    void storeOrder(){
        searchOrderPane.setVisible(true);
        newOrderPane.setVisible(false);
        ObservableList <Ingredient> order = newOrderTable.getItems();
        DatabaseConnectivity.insertOrder(order);
    }
}
