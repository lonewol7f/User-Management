package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf_auth", "paf_user", "PAF_user");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String firstName, String lastName, String email, String role, String password) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";

			}
			// create a prepared statement

			String query = " insert into users(`id`,`firstName`,`lastName`,`email`,`role`,`password`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, firstName);
			preparedStmt.setString(3, lastName);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, role);
			preparedStmt.setString(6, password);

			preparedStmt.execute();
			con.close();

			User userMgmt = new User();
			output = "{\"status\":\"success\",\"data\":\"" + userMgmt + "\"}";
		}

		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the User.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" class=\"table\"><tr><th>firstName</th>" + "<th>lastName</th>"
					+ "<th>email</th>" + "<th>role</th>" + "<th>password</th>" + "<th>Update</th>"
					+ "<th>Remove</th></tr>";

			String query = "select * from users";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String role = rs.getString("role");
				String password = rs.getString("password");
				// Add into the html table
				output += "<tr><td><input id='hidIDUpdate' name='hidIDUpdate' type='hidden' value='" + id + "'>"
						+ firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + role + "</td>";
				output += "<td>" + password + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-iD='" + id + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-iD='" + id + "'></td></tr>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String id, String firstName, String lastName, String email, String role, String password) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE users SET firstName=?,lastName=?,email=?,role=?,password=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, role);
			preparedStmt.setString(5, password);
			preparedStmt.setInt(6, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			User userMgmt = new User();
			output = "{\"status\":\"success\",\"data\":\"" + userMgmt + "\"}";

		}

		catch (Exception e) {

			output = "{\"status\":\"error\",\"data\":\"Error while updating the User.\"}";

			System.err.println(e.getMessage());

		}

		return output;
	}

	public String deleteUser(String id) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from users where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			User userMgmt = new User();
			output = "{\"status\":\"success\",\"data\":\"" + userMgmt + "\"}";

		}

		catch (Exception e) {
			output = "{\"status\":\"error\",\"data\":\"Error while deleting the User.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
