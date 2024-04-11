package com.example.lancastermanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class DatabaseConnectivity {
    private static Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    private static String host = "smcse-stuproj00.city.ac.uk";
    private static int port = 3306;
    private static String dbName = "in2033t15";
    private static String username = "in2033t15_d";
    private static String password = "PH5HsLF6q2o";

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
                "Role r ON s.role = r.name " +
                "JOIN " +
                "Login l ON s.id = l.staffId";
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                String name = resultSet.getString("FirstName") + " " + resultSet.getString("SecondName");
                String[] user = {resultSet.getString("id")+ " " + name, resultSet.getString("Role")};
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

}


