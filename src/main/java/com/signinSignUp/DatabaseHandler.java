package com.signinSignUp;

import java.sql.*;


public class DatabaseHandler {
	
	public Connection connection;

	public DatabaseHandler() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "root");
		} catch (SQLException e) {
			System.out.println("connection error");
		}
	}

	public boolean createUser(String userName, String email, int age, String gender, int weight, int hight) {
		try {
			String query = "INSERT INTO users (userName, email, age, gender, weight, hight) VALUES (?, ?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, userName);
				preparedStatement.setString(2, email);
				preparedStatement.setInt(3, age);
				preparedStatement.setString(4, gender);
				preparedStatement.setInt(5, weight);
				preparedStatement.setInt(6, hight);

				preparedStatement.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	  public boolean userExists(String username) {
	        // Check if the connection is null (optional, for safety)
	        if (this.connection == null) {
	            // Handle the situation where the connection is not properly initialized
	            return false;
	        }

	        PreparedStatement preparedStatement = null;

	        try {
	            // Use the connection to create a prepared statement
	            String query = "SELECT * FROM users WHERE username = ?";
	            preparedStatement = this.connection.prepareStatement(query);
	            preparedStatement.setString(1, username);
	           
	            return preparedStatement.executeQuery().next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {	           
	            try {
	                if (preparedStatement != null) {
	                    preparedStatement.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }}

/*
 * public ResultSet getAllUsers() { try { Statement statement =
 * connection.createStatement(); String query = "SELECT * FROM users"; return
 * statement.executeQuery(query); } catch (SQLException e) {
 * e.printStackTrace(); return null; } } }
 */
