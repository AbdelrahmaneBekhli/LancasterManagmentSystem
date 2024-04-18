package com.example.lancastermanagmentsystem;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


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

    public static ArrayList<Ingredient> retrieveSupplierStock() {
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        String query = "SELECT " +
                "Ingredients.id, " +
                "Ingredients.name, " +
                "Ingredients.price, " +
                "Ingredients.vegan, " +
                "Ingredients.allergens, " +
                "Ingredients.type, " +
                "Supplier.quantity " +
                "FROM Supplier " +
                "JOIN Ingredients " +
                "ON Supplier.ingredientID = Ingredients.id";

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");
                boolean vegan = resultSet.getBoolean("vegan");
                String allergens = resultSet.getString("allergens");
                String type = resultSet.getString("type");
                int quantity = resultSet.getInt("quantity");

                Ingredient ingredient = new Ingredient(id, name, quantity, price, vegan, allergens, type);
                ingredientsList.add(ingredient);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ingredientsList;
    }

    public static ArrayList<Order> retrieveOrder() {
        ArrayList<Order> orderList = new ArrayList<>();
        String query = "SELECT " +
                "oi.id AS orderId, " +
                "i.name AS ingredient_name, " +
                "oi.amount AS quantity, " +
                "(i.price * oi.amount) AS total_price, " +
                "i.type, " +
                "oi.delivered " +
                "FROM " +
                "OrderedItem oi " +
                "JOIN " +
                "Ingredients i ON oi.ingredientId = i.id " +
                "JOIN " +
                "Orders o ON oi.orderId = o.id ";

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            // Iterate over the result set and print each row
            while (resultSet.next()) {
                int id = resultSet.getInt("orderId");
                String name = resultSet.getString("ingredient_name");
                int quantity = resultSet.getInt("quantity");
                float phoneNumber = resultSet.getFloat("total_price");
                String type = resultSet.getString("type");
                boolean delivered = resultSet.getBoolean("delivered");

                Order order = new Order(id, name, quantity, phoneNumber, type, delivered);
                orderList.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }

    public static ArrayList<Order> searchOrder(String date) {
        ArrayList<Order> orderList = new ArrayList<>();
        String query = "SELECT " +
                "oi.id AS orderId, " +
                "i.name AS ingredient_name, " +
                "oi.amount AS quantity, " +
                "(i.price * oi.amount) AS total_price, " +
                "i.type, " +
                "oi.delivered " +
                "FROM " +
                "OrderedItem oi " +
                "JOIN " +
                "Ingredients i ON oi.ingredientId = i.id " +
                "JOIN " +
                "Orders o ON oi.orderId = o.id " +
                "WHERE o.orderDate = + ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, date);
            resultSet = statement.executeQuery();

            // Iterate over the result set and print each row
            while (resultSet.next()) {
                int id = resultSet.getInt("orderId");
                String name = resultSet.getString("ingredient_name");
                int quantity = resultSet.getInt("quantity");
                float phoneNumber = resultSet.getFloat("total_price");
                String type = resultSet.getString("type");
                boolean delivered = resultSet.getBoolean("delivered");

                Order order = new Order(id, name, quantity, phoneNumber, type, delivered);
                orderList.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
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

        if (staff.getPassword() != null) {
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

    public static void addStaff(Staff staff) {
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
        if (staff.getPassword() != null && StaffController.admins.contains(staff.getRoles().get(0))) {
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

    public static void insertOrder(ObservableList<Ingredient> list) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = now.format(formatter);
        int orderId = 0;
        String sqlOrderInsert = "INSERT INTO Orders(orderDate, deliveryDate, placed, price) " +
                "VALUES (?, ?, 1, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlOrderInsert, Statement.RETURN_GENERATED_KEYS)) {
            LocalDateTime tomorrow = now.plusDays(1);
            String tomorrowDate = tomorrow.format(formatter);

            double total = 0;
            for (Ingredient i : list) {
                total = total + (i.getPrice() * i.getOrdered());
            }

            insertStatement.setString(1, todayDate);
            insertStatement.setString(2, tomorrowDate);
            insertStatement.setDouble(3, total);

            insertStatement.executeUpdate();

            try {
                String sqlInsertIngredients = "SELECT id FROM Orders WHERE orderDate = ?";
                try {
                    PreparedStatement ingredientStatement = connection.prepareStatement(sqlInsertIngredients);
                    ingredientStatement.setString(1, todayDate);
                    ResultSet resultSet = ingredientStatement.executeQuery();
                    if (resultSet.next()) {
                        orderId = resultSet.getInt("id");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String sqlInsertIngredients = "INSERT INTO OrderedItem(orderId, ingredientId, amount, delivered) " +
                        "VALUES (?, ?, ?, 0)";
                try (PreparedStatement ingredientStatement = connection.prepareStatement(sqlInsertIngredients)) {
                    for (Ingredient ingredient : list) {
                        ingredientStatement.setInt(1, orderId);
                        ingredientStatement.setInt(2, ingredient.getID());
                        ingredientStatement.setInt(3, ingredient.getOrdered());
                        ingredientStatement.executeUpdate();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void deleteStaff(Staff staff) {
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

    public static ArrayList<ArrayList<Integer>> bookingsStats() {
        String sqlBookingsStat = "SELECT walkIns, onlineBookings, phoneBookings FROM Sales";
        ArrayList<ArrayList<Integer>> bookingsStats = new ArrayList<>();
        try (Statement earningsStatement = connection.createStatement()) {
            earningsStatement.execute(sqlBookingsStat);
            ResultSet resultSet = earningsStatement.getResultSet();
            while (resultSet.next()) {
                ArrayList<Integer> bookings = new ArrayList<>();
                bookings.add(resultSet.getInt("walkIns"));
                bookings.add(resultSet.getInt("onlineBookings"));
                bookings.add(resultSet.getInt("phoneBookings"));
                bookingsStats.add(bookings);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingsStats;
    }

    ;

    public static Map<String, Integer> getEarnings(String startDate, String endDate) {
        Map<String, Integer> earnings = new LinkedHashMap<>();

        String sqlEarningsStat = "SELECT date, totalEarning FROM Sales WHERE date BETWEEN ? AND ?";
        try (PreparedStatement earningsStatement = connection.prepareStatement(sqlEarningsStat)) {

            earningsStatement.setString(1, startDate);
            earningsStatement.setString(2, endDate);

            try (ResultSet resultSet = earningsStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dateStr = resultSet.getString("date");
                    System.out.println(earningsStatement);
                    int quantity = resultSet.getInt("totalEarning");
                    earnings.put(dateStr, quantity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return earnings;
    }

    public static Map<String, Integer> getDishSold(String startDate, String endDate) {
        Map<String, Integer> earnings = new LinkedHashMap<>();


        String sqlEarningsStat = "SELECT date, dishQuantity FROM Sales WHERE date BETWEEN ? AND ?";
        try (PreparedStatement earningsStatement = connection.prepareStatement(sqlEarningsStat)) {

            earningsStatement.setString(1, startDate);
            earningsStatement.setString(2, endDate);

            try (ResultSet resultSet = earningsStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dateStr = resultSet.getString("date");
                    System.out.println(earningsStatement);
                    int quantity = resultSet.getInt("dishQuantity");
                    earnings.put(dateStr, quantity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return earnings;
    }

    public static Map<String, Integer> getWineSold(String startDate, String endDate) {
        Map<String, Integer> earnings = new LinkedHashMap<>();

        String sqlEarningsStat = "SELECT date, wineQuantity FROM Sales WHERE date BETWEEN ? AND ?";
        try (PreparedStatement earningsStatement = connection.prepareStatement(sqlEarningsStat)) {

            earningsStatement.setString(1, startDate);
            earningsStatement.setString(2, endDate);

            try (ResultSet resultSet = earningsStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dateStr = resultSet.getString("date");
                    System.out.println(earningsStatement);
                    int quantity = resultSet.getInt("wineQuantity");
                    earnings.put(dateStr, quantity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return earnings;
    }

    public static Map<String, Integer> getwalkIns(String startDate, String endDate) {
        Map<String, Integer> earnings = new LinkedHashMap<>();

        String sqlEarningsStat = "SELECT date, walkIns FROM Sales WHERE date BETWEEN ? AND ?";
        try (PreparedStatement earningsStatement = connection.prepareStatement(sqlEarningsStat)) {

            earningsStatement.setString(1, startDate);
            earningsStatement.setString(2, endDate);

            try (ResultSet resultSet = earningsStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dateStr = resultSet.getString("date");
                    System.out.println(earningsStatement);
                    int quantity = resultSet.getInt("walkIns");
                    earnings.put(dateStr, quantity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return earnings;
    }

    public static Map<String, Integer> getOnlineBookings(String startDate, String endDate) {
        Map<String, Integer> earnings = new LinkedHashMap<>();

        String sqlEarningsStat = "SELECT date, onlineBookings FROM Sales WHERE date BETWEEN ? AND ?";
        try (PreparedStatement earningsStatement = connection.prepareStatement(sqlEarningsStat)) {

            earningsStatement.setString(1, startDate);
            earningsStatement.setString(2, endDate);

            try (ResultSet resultSet = earningsStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dateStr = resultSet.getString("date");
                    System.out.println(earningsStatement);
                    int quantity = resultSet.getInt("onlineBookings");
                    earnings.put(dateStr, quantity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return earnings;
    }

    public static Map<String, Integer> getPhoneBookings(String startDate, String endDate) {
        Map<String, Integer> earnings = new LinkedHashMap<>();

        String sqlEarningsStat = "SELECT date, phoneBookings FROM Sales WHERE date BETWEEN ? AND ?";
        try (PreparedStatement earningsStatement = connection.prepareStatement(sqlEarningsStat)) {

            earningsStatement.setString(1, startDate);
            earningsStatement.setString(2, endDate);

            try (ResultSet resultSet = earningsStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dateStr = resultSet.getString("date");
                    System.out.println(earningsStatement);
                    int quantity = resultSet.getInt("phoneBookings");
                    earnings.put(dateStr, quantity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return earnings;
    }

    public static Map<String, Integer> getTotalCustomers(String startDate, String endDate) {
        Map<String, Integer> earnings = new LinkedHashMap<>();

        String sqlEarningsStat = "SELECT date, totalCustomers FROM Sales WHERE date BETWEEN ? AND ?";
        try (PreparedStatement earningsStatement = connection.prepareStatement(sqlEarningsStat)) {

            earningsStatement.setString(1, startDate);
            earningsStatement.setString(2, endDate);

            try (ResultSet resultSet = earningsStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dateStr = resultSet.getString("date");
                    int quantity = resultSet.getInt("totalCustomers");
                    earnings.put(dateStr, quantity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return earnings;
    }

    public static void deleteIngredient(Ingredient ingredient) {
        String sqldelete = "DELETE FROM OrderedItem WHERE ingredientId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqldelete)) {
            statement.setInt(1, ingredient.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


