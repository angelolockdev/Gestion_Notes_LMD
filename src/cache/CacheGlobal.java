package cache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import utils.Column;

public class CacheGlobal {
	public static HashMap<String, List<Column>> columns;
	
	public static List<Column> getTableColumns(Connection connection, String table) throws SQLException{
		List<Column> list = null;
		if(columns==null)
			columns = new HashMap<>();
		if(columns.containsKey(table.toLowerCase()))
			return columns.get(table.toLowerCase());
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		boolean debug = bundle.getString("debug") == null ? true : Boolean.parseBoolean(bundle.getString("debug"));
		String sql = "select column_name, data_type from information_schema.columns where upper(table_name)=? AND table_schema=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, table.toUpperCase());
		preparedStatement.setString(2, connection.getCatalog());
		if(debug)
			System.out.println(preparedStatement);
		ResultSet rSet = preparedStatement.executeQuery();
		list = new ArrayList<>();
		while(rSet.next()){
			Column col = new Column(rSet.getString("column_name"), rSet.getString("data_type"));
			list.add(col);			
		}
		columns.put(table.toLowerCase(), list);
		return list;
	}
}
