package javaCode;

import java.util.*;
import java.io.*;
import java.sql.*;


public class DBCon {
	 public static Connection getmyConnection()
	 {
		Connection conn= null;
		try {
			// ����̹��� �ε�
			Class.forName("com.mysql.jdbc.Driver"); 

			System.out.println("after forName");
			
			// ���� 
			String url = "jdbc:mysql://localhost:3306/testdb";

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "tls1697926");

			System.out.println("DB ������ ����Ǿ����ϴ�.");
		
	
		} 
		catch(ClassNotFoundException e){
            System.out.println("Driver loading failed!");
        }
        catch(SQLException e){
            System.out.println("error: " + e);
        }
		 return conn;

	 }
}
