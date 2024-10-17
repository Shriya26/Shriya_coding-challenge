package com.hexaware.ordersystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getDBConnection() {

		Connection conn = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oms", "root", "Shriy@2603");
		} catch (SQLException e) {

		}

		return conn;

	}


}
