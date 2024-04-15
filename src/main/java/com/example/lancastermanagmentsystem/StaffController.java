package com.example.lancastermanagmentsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class StaffController implements Initializable {

    @FXML
    private TableColumn<Staff, String>Name, Surname, DateOfBirth, PhoneNumber, Email;
    @FXML
    private TableColumn<Staff, Integer> ID;
    @FXML
    private TextField editName, editSurname, editNumber, editEmail, editRemainingHolidays;

    @FXML
    private PasswordField setPasswordField, passwordField;
    @FXML
    private ComboBox<String> rolesSelection, existingRoleSelection;

    @FXML
    private CheckBox editHolidays;

    @FXML
    private DatePicker editDob;

    @FXML
    private TableColumn<Staff, Float> onHoliday, RemainingHoliday;

    @FXML
    private Button deleteStaff, createStaff, passwordCreate, dashboard, menu, supplier, statistics, staff, logout, editCallButton, editCancel, saveButton, cancelButton, continueButton;

    @FXML
    private Text dateTime, user, availableStaff;

    @FXML
    private AnchorPane setPasswordPane, staffTablePane, sideButtons, selectRolePane;

    @FXML
    private GridPane staffDetailsPane;

    @FXML
    private TableView<Staff> staffTable;


    private boolean creatingStaff;
    private Staff selectedStaff;
    private ObservableList<Staff> staffList;
    private String initialRole;
    private int roleIndex = 0;
    String password = "";
    static ArrayList<String> admins = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        dashboard.setOnAction(e -> {
            try {
                LoginPage.setDashboardPage();
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


        ArrayList<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        user.setText(LoginController.user);
        LoginPage.setTime(dateTime);
        admins.add("owner");
        admins.add("admin");
        admins.add("manager");
        admins.add("sommelier");

        staffList =  FXCollections.observableArrayList(DatabaseConnectivity.retrieveStaff());
        rolesSelection.getItems().addAll(DatabaseConnectivity.retrieveRoles());


        ID.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<Staff, String>("Name"));
        Surname.setCellValueFactory(new PropertyValueFactory<Staff, String>("Surname"));
        DateOfBirth.setCellValueFactory(new PropertyValueFactory<Staff, String>("DateOfBirth"));
        PhoneNumber.setCellValueFactory(new PropertyValueFactory<Staff, String>("PhoneNumber"));
        Email.setCellValueFactory(new PropertyValueFactory<Staff, String>("Email"));


        onHoliday.setCellValueFactory(new PropertyValueFactory<Staff, Float>("holiday"));
        RemainingHoliday.setCellValueFactory(new PropertyValueFactory<Staff, Float>("remainingHoliday"));


        staffTable.setItems(staffList);
        availableStaff.setText("Available Staff: " + DatabaseConnectivity.getAvailableStaff());



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

        editCallButton.setOnAction(e ->{
            creatingStaff = false;
            editHolidays.setVisible(true);
            selectedStaff = staffTable.getSelectionModel().getSelectedItem();
            editName.setText(selectedStaff.getName());
            editSurname.setText(selectedStaff.getSurname());
            editNumber.setText(selectedStaff.getPhoneNumber());
            editEmail.setText(selectedStaff.getEmail());
           if(selectedStaff.getHoliday() == 1){
               editHolidays.setSelected(true);
            } else if (selectedStaff.getHoliday() == 0) {
               editHolidays.setSelected(false);
           }
            editRemainingHolidays.setText(String.valueOf(selectedStaff.getRemainingHoliday()));
            editDob.setValue(LOCAL_DATE(selectedStaff.getDateOfBirth()));

            if(selectedStaff.getRoles().size() > 1){
                existingRoleSelection.getItems().setAll(selectedStaff.getRoles());
                staffTablePane.setDisable(true);
                selectRolePane.setVisible(true);
            } else{
                initialRole = selectedStaff.getRoles().get(0);
                roleIndex = 0;
                rolesSelection.setValue(initialRole);
                sideButtons.setVisible(false);
                staffTablePane.setVisible(false);
                staffDetailsPane.setVisible(true);
                if(!admins.contains(initialRole)){
                    passwordField.setDisable(true);
                } else{
                    passwordField.setDisable(false);
                }
            }
        });

        passwordCreate.setOnAction(e-> {
            setPasswordPane.setVisible(false);
            staffTablePane.setVisible(true);
            password = setPasswordField.getText();
            DatabaseConnectivity.updateStaffRole(selectedStaff, initialRole, selectedStaff.getRoles().get(roleIndex), password, false, true);

        });

        deleteStaff.setOnAction(e ->{
            selectedStaff = staffTable.getSelectionModel().getSelectedItem();
            if(selectedStaff == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Select Staff");
                alert.setHeaderText(null);
                alert.setContentText("Staff not Selected, Please try again.");
                alert.showAndWait();
            } else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deletion confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete staff?");

                ButtonType buttonTypeYes = new ButtonType("Yes");
                ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes) {
                    DatabaseConnectivity.deleteStaff(selectedStaff);
                    staffList = FXCollections.observableArrayList(DatabaseConnectivity.retrieveStaff());
                    staffTable.setItems(staffList);
                    availableStaff.setText("Available Staff: " + DatabaseConnectivity.getAvailableStaff());

                }
            }
        });

        continueButton.setOnAction(e->{
            rolesSelection.setValue(existingRoleSelection.getValue());
            roleIndex = selectedStaff.getRoles().indexOf(rolesSelection.getValue());
            initialRole = existingRoleSelection.getValue();
            selectRolePane.setVisible(false);
            staffTablePane.setDisable(false);
            sideButtons.setVisible(false);
            staffTablePane.setVisible(false);
            staffDetailsPane.setVisible(true);
            if(!admins.contains(initialRole)){
                passwordField.setDisable(true);
            } else{
                passwordField.setDisable(false);
            }
        });

        cancelButton.setOnAction(e->{
            staffTablePane.setDisable(false);
            selectRolePane.setVisible(false);
        });

        saveButton.setOnAction(this::handle);

        editCancel.setOnAction(e ->{
            sideButtons.setVisible(true);
            staffTablePane.setVisible(true);
            staffDetailsPane.setVisible(false);
        });

        createStaff.setOnAction(e ->{
            creatingStaff = true;
            sideButtons.setVisible(false);
            staffTablePane.setVisible(false);
            staffDetailsPane.setVisible(true);
            editHolidays.setVisible(false);
            editName.setText("");
            editSurname.setText("");
            editNumber.setText("");
            editEmail.setText("");
            editRemainingHolidays.setText("");
            passwordField.setText("");
            passwordField.setDisable(false);
            editDob.setValue(null);
            rolesSelection.setValue(null);

        });


    }

    public static LocalDate LOCAL_DATE(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

    private boolean checkComplete(){
        if  (!(editName.getText().isEmpty() ||
                editSurname.getText().isEmpty() ||
                editNumber.getText().isEmpty() ||
                editEmail.getText().isEmpty() ||
                editRemainingHolidays.getText().isEmpty() ||
                editDob.getValue() == null ||
                rolesSelection.getValue() == null)){
            if(creatingStaff){
                if(admins.contains(rolesSelection.getValue())){
                    return !(passwordField.getText().isEmpty());
                }
                return true;
            }
            return true;
        };
        return false;
    }

    private void handle(ActionEvent e) {
        if(checkComplete()) {
            if (creatingStaff) {
                ArrayList<String> roles = new ArrayList<>();
                roles.add(rolesSelection.getValue());
                selectedStaff = new Staff(0, editName.getText(), editSurname.getText(), editNumber.getText(), editEmail.getText(), editDob.getValue().toString(), roles, 0, Integer.parseInt(editRemainingHolidays.getText()));
                selectedStaff.setPassword(passwordField.getText());
            } else {
                selectedStaff.setName(editName.getText());
                selectedStaff.setSurname(editSurname.getText());
                selectedStaff.setPhoneNumber(editNumber.getText());
                selectedStaff.setEmail(editEmail.getText());
                selectedStaff.setDateOfBirth(String.valueOf(editDob.getValue()));
                if (editHolidays.isSelected()) {
                    selectedStaff.setHoliday(1);
                } else {
                    selectedStaff.setHoliday(0);
                }

                selectedStaff.setRemainingHoliday(Integer.parseInt(editRemainingHolidays.getText()));
                selectedStaff.getRoles().set(roleIndex, rolesSelection.getValue());
                if (!Objects.equals(passwordField.getText(), "")) {
                    selectedStaff.setPassword(passwordField.getText());
                }
            }
            if(creatingStaff){
                DatabaseConnectivity.addStaff(selectedStaff);
            } else {
                DatabaseConnectivity.updateStaffTable(selectedStaff);
            }
            boolean back = true;


            if (!creatingStaff) {
                if (!Objects.equals(initialRole, selectedStaff.getRoles().get(roleIndex))) {
                    boolean removeLogin = false;
                    boolean reloadLogin = false;
                    if (admins.contains(selectedStaff.getRoles().get(roleIndex)) && !admins.contains(initialRole)) {
                        back = false;
                        staffDetailsPane.setVisible(false);
                        staffTablePane.setVisible(false);
                        setPasswordPane.setVisible(true);
                        sideButtons.setVisible(true);
                    } else {
                        if (admins.contains(selectedStaff.getRoles().get(roleIndex)) && admins.contains(initialRole)) {
                            reloadLogin = true;
                        } else if (admins.contains(initialRole)) {
                            removeLogin = true;
                        }
                        DatabaseConnectivity.updateStaffRole(selectedStaff, initialRole, selectedStaff.getRoles().get(roleIndex), password, removeLogin, reloadLogin);
                    }
                }


            }

            staffList = FXCollections.observableArrayList(DatabaseConnectivity.retrieveStaff());
            staffTable.setItems(staffList);
            availableStaff.setText("Available Staff: " + DatabaseConnectivity.getAvailableStaff());
            if (back) {
                staffDetailsPane.setVisible(false);
                staffTablePane.setVisible(true);
                sideButtons.setVisible(true);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Complete all boxes");
            alert.setHeaderText(null);
            alert.setContentText("Missing Fields. Please try again.");
            alert.showAndWait();
        }


    }
}
