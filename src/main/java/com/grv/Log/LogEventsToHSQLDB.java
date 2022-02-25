package com.grv.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogEventsToHSQLDB {

	static Connection connection;
	static String connectionString = "jdbc:hsqldb:file:hsqldb/demodatabase";
	static String CreateTabel="CREATE TABLE if not exists LogEvents "
			+ "(EVENT_ID VARCHAR(50) NOT NULL, "
			+ "EVENT_STATE VARCHAR(20) NOT NULL,"
			+ "EVENT_TYPE VARCHAR(20),"
			+ "EVENT_HOST VARCHAR(20), "
			+ "EVENT_DUR INT NOT NULL, "
			+ "EVENT_FLAG VARCHAR(10));";

	public static void WritetoDb(List<LogFile> logfiles) throws Exception {

		Statement stmt;
		Logger databaseLogger = Logger.getLogger("demodb.db");
		databaseLogger.setUseParentHandlers(false);
		databaseLogger.setLevel(Level.WARNING);
		databaseLogger.addHandler(new FileHandler("myapp.log"));

		try {
			//Registering the HSQLDB JDBC driver
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			//Creating the connection with HSQLDB
			connection = DriverManager.getConnection(connectionString, "SA", "");
			if (connection!= null){
				System.out.println("Records inserted successfully");
				stmt = connection.createStatement();
				stmt.executeUpdate(CreateTabel);
				//int logCounter = 0;
				String SQLStatement = "INSERT INTO LogEvents VALUES (?, ?, ?, ?, ?,?)";
				PreparedStatement ps = connection.prepareStatement(SQLStatement);
				for(LogFile log:logfiles) {
				      if(log.getState().equals("STARTED")) {
						  ps.setString(1,log.getId());
						  ps.setString(2,log.getState());
						  ps.setString(3,log.getType());
						  ps.setString(4, log.getHost());
			              ps.setInt(5, log.getProcessedTime());
			              ps.setString(6, log.getAlertFlag());
			              ps.executeUpdate();
				      }
				
				}
			}else{
				System.out.println("Problem with creating connection");
			}

		}  catch (Exception e) {
			e.printStackTrace(System.out);
		}finally {
            if (connection != null) {
                connection.close();
            }
		}
	}

}
