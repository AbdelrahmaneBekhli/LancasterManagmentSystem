package com.example.lancastermanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseConnectivity {
    private static Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    private static String host = "smcse-stuproj00.city.ac.uk";
    private static int port = 3306;
    private static String dbName = "in2033t15";
    private static String username = "in2033t15_a";
    private static String password = "TYZVS1GIk-Y";

    public static boolean startConnection() {
        connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, username, password);

            if (connection != null) {
                System.out.println("Connected to the database");
                return true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
        return false;
    }

    public static List<String[]> retrieveLogin() {
        List<String[]> userList = new ArrayList<>();
        String sqlQuery = "SELECT " +
                "s.id AS id, " +
                "s.firstName AS FirstName, " +
                "s.secondName AS SecondName, " +
                "r.name AS Role " +
                "FROM " +
                "Staff s " +
                "JOIN " +
                "StaffRoles sr ON s.id = sr.staffId " +
                "JOIN " +
                "Role r ON sr.roleId = r.id " +
                "JOIN " +
                "Login l ON s.id = l.staffId " +
                "GROUP BY " +
                "s.id, r.name";
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String name = resultSet.getString("FirstName") + " " + resultSet.getString("SecondName");
                String[] user = {resultSet.getString("id") + " " + name, resultSet.getString("Role")};
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public static boolean checkPassword(String id, String password) {
        String sqlQuery = "SELECT " +
                "l.password AS password " + // Added space after the column name
                "FROM " +
                "Login l " +
                "WHERE l.staffId = '" + id + "'"; // Concatenated id properly and added single quotes around id value
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            // If the query returns no results, return false
            if (!resultSet.next()) {
                return false;
            }

            // Compare the password from the query with the provided password
            return resultSet.getString("password").equals(password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Staff> retrieveStaff() {
        ArrayList<Staff> staffList = new ArrayList<>();
        String sqlQuery = "SELECT Staff.id, Staff.firstName, Staff.secondName, Staff.phone, " +
                "Staff.email, Staff.dob, Staff.holiday, Staff.remainingHolidays, " +
                "Role.name AS roleName " +
                "FROM Staff " +
                "JOIN StaffRoles ON Staff.id = StaffRoles.staffId " +
                "JOIN Role ON StaffRoles.roleId = Role.id";

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            // Iterate over the result set and print each row
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("firstName");
                String surname = resultSet.getString("secondName");
                String phoneNumber = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String dob = resultSet.getString("dob");
                int holiday = Integer.parseInt(resultSet.getString("holiday"));
                int remark = Integer.parseInt(resultSet.getString("remainingHolidays"));
                String role = resultSet.getString("roleName");
                boolean containsId = false;
                Staff temp = null;
                for (Staff s : staffList) {
                    if (s.getID() == id) {
                        temp = s;
                        containsId = true;
                        break;
                    }
                }
                if (containsId) {
                    temp.addRole(role);
                } else {
                    ArrayList<String> roleList = new ArrayList<>();
                    roleList.add(role);
                    Staff staff = new Staff(id, name, surname, phoneNumber, email, dob, roleList, holiday, remark);
                    staffList.add(staff);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staffList;
    }

    public static ArrayList<String> retrieveRoles() {
        ArrayList<String> roles = new ArrayList<>();
        String sqlQuery = "SELECT " +
                "name " +
                "FROM " +
                "Role";
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                roles.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }

    public static void updateStaffTable(Staff staff) {
        String sqlQuery = "UPDATE Staff SET " +
                "firstName = ?, " +
                "secondName = ?, " +
                "phone = ?, " +
                "email = ?, " +
                "dob = ?, " +
                "holiday = ?, " +
                "remainingHolidays = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getSurname());
            statement.setString(3, staff.getPhoneNumber());
            statement.setString(4, staff.getEmail());
            statement.setDate(5, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(staff.getDateOfBirth()).getTime()));
            statement.setInt(6, staff.getHoliday());
            statement.setInt(7, staff.getRemainingHoliday());
            statement.setInt(8, staff.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(staff.getPassword() != null);
        if(staff.getPassword() != null){
            String SqlupdatePassword = "UPDATE Login SET password = ? WHERE staffId = ?";
            try {
                PreparedStatement statement = connection.prepareStatement(SqlupdatePassword);
                statement.setString(1, staff.getPassword());
                statement.setInt(2, staff.getID());
                statement.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void addStaff(Staff staff){
        String sqlQueryInsert = "INSERT INTO Staff (firstName, secondName, phone, email, dob, holiday, remainingHolidays)" +
                "VALUES (?, ?, ?, ?, ?, 0, ?)";
        String sqlAddRole = "INSERT INTO StaffRoles (staffId, roleId) " +
                "VALUES (" +
                "(SELECT id FROM Staff WHERE email = ?), " +
                "(SELECT id FROM Role WHERE name = ?))";
        try (PreparedStatement insertStatement = connection.prepareStatement(sqlQueryInsert)) {
            insertStatement.setString(1, staff.getName());
            insertStatement.setString(2, staff.getSurname());
            insertStatement.setString(3, staff.getPhoneNumber());
            insertStatement.setString(4, staff.getEmail());
            insertStatement.setString(5, staff.getDateOfBirth());
            insertStatement.setInt(6, staff.getRemainingHoliday());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert new staff", e);
        }

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlAddRole)) {
            insertStatement.setString(1, staff.getEmail());
            insertStatement.setString(2, staff.getRoles().get(0));
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert new staff", e);
        }
        if(staff.getPassword() != null && StaffController.admins.contains(staff.getRoles().get(0))){
            String sqlAddLogin = "INSERT INTO Login (staffId, roleId, password) " +
                    "VALUES (" +
                    "(SELECT id FROM Staff WHERE email = ?), " +
                    "(SELECT id FROM Role WHERE name = ?), " +
                    "?" +
                    ")";
            try (PreparedStatement insertStatement = connection.prepareStatement(sqlAddLogin)) {
                insertStatement.setString(1, staff.getEmail());
                insertStatement.setString(2, staff.getRoles().get(0));
                insertStatement.setString(3, staff.getPassword());
                insertStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to insert new staff", e);
            }
        }
    }

    public static int getAvailableStaff() {
        int num = 0;
        String sqlgetAvailableStaff = "SELECT COUNT(*) AS count_staff_with_zero_holiday " +
                "FROM Staff " +
                "WHERE holiday = 0";

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlgetAvailableStaff);
            if (resultSet.next()) {
                num = resultSet.getInt("count_staff_with_zero_holiday");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get available staff", e);
        }
        return num;
    }

    public static void deleteStaff(Staff staff){
        try (Statement disableStatement = connection.createStatement()) {
            disableStatement.executeUpdate("SET FOREIGN_KEY_CHECKS=0");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to disable foreign key constraint", e);
        }

        String sqlDeleteStaffRole = "DELETE FROM StaffRoles WHERE staffId = ?";
        String sqlDeleteStaffLogin = "DELETE FROM Login WHERE staffId = ?";
        String sqlDeleteStaff = "DELETE FROM Staff WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlDeleteStaffRole)) {
            statement.setInt(1, staff.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete new staff", e);
        }
        try (PreparedStatement statement = connection.prepareStatement(sqlDeleteStaffLogin)) {
            statement.setInt(1, staff.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete new staff", e);
        }
        try (PreparedStatement statement = connection.prepareStatement(sqlDeleteStaff)) {
            statement.setInt(1, staff.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete new staff", e);
        }
        try (Statement disableStatement = connection.createStatement()) {
            disableStatement.executeUpdate("SET FOREIGN_KEY_CHECKS=1");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to disable foreign key constraint", e);
        }
    }

    public static void updateStaffRole(Staff staff, String initialRole, String newRole, String password, boolean removeLogin, boolean addLogin) {
        String Password = password;

        // Disable the foreign key constraint
        try (Statement disableStatement = connection.createStatement()) {
            disableStatement.executeUpdate("SET FOREIGN_KEY_CHECKS=0");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to disable foreign key constraint", e);
        }

        // Delete the existing login record
        if (removeLogin) {
            String getPassword = "SELECT password FROM Login WHERE staffId = ? AND roleId = (SELECT id FROM Role WHERE name = ?)";
            try (PreparedStatement statement = connection.prepareStatement(getPassword)) {
                statement.setInt(1, staff.getID());
                statement.setString(2, initialRole);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Password = resultSet.getString("password");
                }
            } catch (SQLException e) {
                System.out.println("Error retrieving password: " + e.getMessage());
            }

            String sqlQueryDeleteLogin = "DELETE FROM Login WHERE staffId = ? AND roleId = (SELECT id FROM Role WHERE name = ?)";
            try (PreparedStatement statement = connection.prepareStatement(sqlQueryDeleteLogin)) {
                statement.setInt(1, staff.getID());
                statement.setString(2, initialRole);
                System.out.println(sqlQueryDeleteLogin);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Delete the existing staff role
        String sqlQueryDelete = "DELETE FROM StaffRoles WHERE staffId = ? AND roleId = (SELECT id FROM Role WHERE name = ?)";
        try (PreparedStatement deleteStatement = connection.prepareStatement(sqlQueryDelete)) {
            deleteStatement.setInt(1, staff.getID());
            deleteStatement.setString(2, initialRole);
            System.out.println(sqlQueryDelete);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete staff role", e);
        }

        // Re-enable the foreign key constraint
        try (Statement enableStatement = connection.createStatement()) {
            enableStatement.executeUpdate("SET FOREIGN_KEY_CHECKS=1");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to re-enable foreign key constraint", e);
        }

        // Insert the new staff role
        String sqlQueryInsert = "INSERT INTO StaffRoles (staffId, roleId) VALUES (?, (SELECT id FROM Role WHERE name = ?))";
        try (PreparedStatement insertStatement = connection.prepareStatement(sqlQueryInsert)) {
            insertStatement.setInt(1, staff.getID());
            insertStatement.setString(2, newRole);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert new staff role", e);
        }

        if (addLogin) {
            // Insert the new login record
            String sqlQueryInsertLogin = "INSERT INTO Login (staffId, roleId, password) VALUES (?, (SELECT id FROM Role WHERE name = ?), ?)";
            try (PreparedStatement statement = connection.prepareStatement(sqlQueryInsertLogin)) {
                statement.setInt(1, staff.getID());
                statement.setString(2, newRole);
                statement.setString(3, Password);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to insert new login record", e);
            }
        }
    }
}


