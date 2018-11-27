package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UtilDb {
	
	public static Connection getConnection() throws SQLException
	{
		Connection connection = null;
		InputStream configFile = null;
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			configFile = loader.getResourceAsStream("config.properties");
			Properties prop = new Properties();
			prop.load(configFile);
			String host = prop.getProperty("host");
			String port = prop.getProperty("port", "3306");
			String user = prop.getProperty("dbuser");
			String dbname = prop.getProperty("dbname");
			String password = prop.getProperty("password", "");
			String className = prop.getProperty("driverClassName", "com.mysql.jdbc.Driver");
			Class.forName(className);
			connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbname+"?useSSL=true&useUnicode=true&characterEncoding=UTF-8", user, password);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			if(configFile!=null)
				try {
					configFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return connection;
	}
}