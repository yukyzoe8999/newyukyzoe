package com.bbs.web.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

//connection 관리
public class ConnectionProvider {
    public static Connection getConnection() throws ClassNotFoundException {
	Connection conn = null;
		
	try { 
		 String url = "jdbc:mysql://13.125.166.164:3306/P_Project";
		 String id = "zoe";
		 String pw = "woojin7929!M";
		 String driver = "com.mysql.jdbc.Driver";
		 Class.forName(driver);
		 conn = DriverManager.getConnection(url, id, pw);

	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return conn;
    }
}