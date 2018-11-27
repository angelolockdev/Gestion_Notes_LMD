package dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import cache.CacheGlobal;
import models.BaseModel;
import utils.Column;
import utils.DateSimple;
import utils.DateTime;
import utils.Functions;

public class BaseDao {
	private static final ResourceBundle bundle = ResourceBundle
			.getBundle("config");

	public static void insert(Connection connection, BaseModel model)
			throws SQLException {
		boolean debug = bundle.getString("debug") == null
				? true
				: Boolean.parseBoolean(bundle.getString("debug"));
		try {
			List<Column> columns = CacheGlobal.getTableColumns(connection,
					model.getTable());
			String join = Functions.join(columns);
			String sql = "INSERT INTO " + model.getTable() + " (" + join
					+ ") VALUES (";
			for (int i = 0; i < columns.size(); i++)
				sql += "?,";
			sql = sql.substring(0, sql.length() - 1) + ")";
			PreparedStatement statement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			int i = 1;
			for (Column col : columns) {
				Method get = model.getClass().getMethod(
						"get" + Functions.firstUpper(col.getColumnName()));
				if (get.getReturnType().equals(Date.class)) {
					Date dUtil = (Date) get.invoke(model);
					if (dUtil == null)
						statement.setObject(i, null);
					else {
						java.sql.Date d = new java.sql.Date(dUtil.getTime());
						statement.setDate(i, d);
					}
				} else if (get.getReturnType().equals(DateTime.class)) {
					DateTime dUtil = (DateTime) get.invoke(model);
					if (dUtil == null)
						statement.setObject(i, null);
					else {
						java.sql.Timestamp d = new java.sql.Timestamp(
								dUtil.getTime());
						statement.setTimestamp(i, d);
					}
				} else {
					statement.setObject(i, get.invoke(model));
				}
				i++;
			}
			if (debug)
				System.out.println(statement);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				model.setId(resultSet.getLong(1));
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void delete(Connection connection, String table, long id)
			throws SQLException {
		BaseDao.delete(connection, table, new String[]{"id"}, id);
	}

	public static void delete(Connection connection, String table,
			String[] columns, Object... values) throws SQLException {
		boolean debug = bundle.getString("debug") == null
				? true
				: Boolean.parseBoolean(bundle.getString("debug"));
		String sql = "DELETE FROM " + table + " WHERE ";
		int l = sql.length();
		for (int i = 0; i < columns.length; i++)
			sql += columns[i] + "=? AND ";
		if (sql.length() > l)
			sql = sql.substring(0, sql.length() - 4);
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null && values[i].getClass().equals(Date.class)) {
				java.sql.Date d = new java.sql.Date(
						((Date) values[i]).getTime());
				statement.setDate(i + 1, d);
			}
			if (values[i] != null
					&& values[i].getClass().equals(DateTime.class)) {
				java.sql.Timestamp d = new java.sql.Timestamp(
						((Date) values[i]).getTime());
				statement.setTimestamp(i + 1, d);
			} else
				statement.setObject(i + 1, values[i]);
		}
		if (debug)
			System.out.println(statement);
		statement.executeUpdate();
	}

	public static void update(Connection connection, BaseModel model,
			boolean setNull, boolean whereNull, String[] columns,
			String[] orAnd) throws SQLException {
		boolean debug = bundle.getString("debug") == null
				? true
				: Boolean.parseBoolean(bundle.getString("debug"));
		try {
			List<Column> columnsName = CacheGlobal.getTableColumns(connection,
					model.getTable());
			List<Object> setValues = new ArrayList<>(),
					whereValues = new ArrayList<>();
			String setStr = "", whereStr = "", orA = "";
			int j = 0;
			for (Column col : columnsName) {
				Method get = model.getClass().getMethod(
						"get" + Functions.firstUpper(col.getColumnName()));
				Object obj = get.invoke(model);
				if (Functions.inArray(col.getColumnName().toLowerCase(),
						(Object[]) columns) != -1) {
					if (!whereNull && obj == null)
						continue;
					whereStr += col.getColumnName() + "=? ";
					whereValues.add(obj);
					if (j < orAnd.length && columns.length > 1) {
						whereStr += orAnd[j] + " ";
						orA = orAnd[j];
						j++;
					}
				} else {
					if (!setNull && obj == null)
						continue;
					setStr += col.getColumnName() + "=?, ";
					setValues.add(obj);
				}
			}
			if (!setStr.equals(""))
				setStr = setStr.substring(0, setStr.length() - 2);
			if (!whereStr.equals(""))
				whereStr = whereStr.substring(0,
						whereStr.length() - orA.length() - 1);
			String sql = "UPDATE " + model.getTable() + " SET " + setStr
					+ " WHERE ";
			if (whereValues.size() > 0)
				sql += whereStr;
			else
				sql += "1<2";
			PreparedStatement statement = connection.prepareStatement(sql);
			for (int i = 0; i < setValues.size(); i++) {
				Object obj = setValues.get(i);
				if (obj != null && obj.getClass().equals(Date.class)) {
					java.sql.Date d = new java.sql.Date(((Date) obj).getTime());
					statement.setDate(i + 1, d);
				} else if (obj != null
						&& obj.getClass().equals(DateTime.class)) {
					java.sql.Timestamp d = new java.sql.Timestamp(
							((DateTime) obj).getTime());
					statement.setTimestamp(i + 1, d);
				} else
					statement.setObject(i + 1, obj);
			}
			for (int i = 0, k = setValues.size(); i < whereValues
					.size(); i++, k++) {
				Object obj = whereValues.get(i);
				if (obj != null && obj.getClass().equals(Date.class)) {
					java.sql.Date d = new java.sql.Date(((Date) obj).getTime());
					statement.setDate(k + 1, d);
				} else
					statement.setObject(k + 1, obj);
			}
			if (debug)
				System.out.println(statement);
			statement.executeUpdate();
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void update(Connection connection, BaseModel model,
			boolean setNull) throws SQLException {
		BaseDao.update(connection, model, setNull, false, new String[]{"id"},
				new String[]{});
	}

	public static <T extends BaseModel> List<T> select(Connection connection,
			String table, Class<T> classe, String condition, String column,
			String order, int limit, int page, Object... values)
			throws SQLException {
		List<T> result = new ArrayList<>();
		List<Column> columnsName = CacheGlobal.getTableColumns(connection,
				table);
		boolean test = false;
		for (Column item : columnsName) {
			if (item.getColumnName().equalsIgnoreCase(column)) {
				test = true;
				break;
			}
		}
		String join = Functions.join(columnsName);
		String sql = "SELECT " + join + " FROM " + table + " WHERE ";
		if (condition == null)
			sql += "1<2";
		else
			sql += " " + condition;
		if (test) {
			String tri = order != null && order.equalsIgnoreCase("DESC")
					? "DESC"
					: "ASC";
			sql += " ORDER BY " + column + " " + tri;
		}
		if (!test && column != null && column.equalsIgnoreCase("random"))
			sql += " ORDER BY rand() ";
		if (page <= 0)
			page = 1;
		if (limit > 0) {
			int offset = (page - 1) * limit;
			sql += " LIMIT " + offset + ", " + limit;
		}
		result = executeRequests(connection, sql, classe, page, limit, values);
		return result;
	}

	public static <T extends BaseModel> T findOne(Connection connection,
			String table, Class<T> classe, String condition, Object... values)
			throws SQLException {
		List<T> lst = select(connection, table, classe, condition, null, null,
				1, 1, values);
		if (lst.size() == 0)
			return null;
		return lst.get(0);
	}

	public static <T extends BaseModel> T findById(Connection connection,
			String table, Class<T> classe, Long id) throws SQLException {
		T result = null;
		List<T> l = BaseDao.select(connection, table, classe, "id=?", null,
				null, 1, 1, id);
		if (l.size() != 0)
			return l.get(0);
		return result;
	}

	public static <T extends BaseModel> List<T> executeRequests(
			Connection connection, String sql, Class<T> classe, int page,
			int limit, Object... values) throws SQLException {
		boolean debug = bundle.getString("debug") == null
				? true
				: Boolean.parseBoolean(bundle.getString("debug"));
		if (page <= 0)
			page = 1;
		if (limit > 0) {
			int offset = (page - 1) * limit;
			sql += " LIMIT " + offset + ", " + limit;
		}
		PreparedStatement statement = connection.prepareStatement(sql);
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null && values[i].getClass().equals(Date.class)) {
				java.sql.Date d = new java.sql.Date(
						((Date) values[i]).getTime());
				statement.setDate(i + 1, d);
			} else if (values[i] != null
					&& values[i].getClass().equals(DateTime.class)) {
				java.sql.Timestamp d = new java.sql.Timestamp(
						((DateTime) values[i]).getTime());
				statement.setTimestamp(i + 1, d);
			} else
				statement.setObject(i + 1, values[i]);
		}
		if (debug)
			System.out.println(statement);
		ResultSet rSet = statement.executeQuery();
		Constructor<T> constructor;
		try {
			constructor = classe.getConstructor();
			ResultSetMetaData rsmd = rSet.getMetaData();
			List<String> columnsName = new ArrayList<String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
				columnsName.add(rsmd.getColumnLabel(i));
			while (rSet.next()) {
				T obj = constructor.newInstance();
				for (String col : columnsName) {
					if (rSet.getObject(col) == null)
						continue;
					Method get = classe
							.getMethod("get" + Functions.firstUpper(col));
					Method set = classe.getMethod(
							"set" + Functions.firstUpper(col),
							get.getReturnType());
					if (get.getReturnType().equals(Date.class)) {
						java.sql.Date d = rSet.getDate(col);
						set.invoke(obj, new Date(d.getTime()));
					} else if (get.getReturnType().equals(DateTime.class)) {
						java.sql.Timestamp d = rSet.getTimestamp(col);
						set.invoke(obj, new DateTime(d.getTime()));
					} else if (get.getReturnType().equals(DateSimple.class)) {
						java.sql.Timestamp d = rSet.getTimestamp(col);
						set.invoke(obj, new DateSimple(d.getTime()));
					} else {
						set.invoke(obj, rSet.getObject(col));
					}
				}
				result.add(obj);
			}
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int compteExecuteRequests(Connection connection, String sql,
			Object... values) throws SQLException {
		boolean debug = bundle.getString("debug") == null
				? true
				: Boolean.parseBoolean(bundle.getString("debug"));
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null && values[i].getClass().equals(Date.class)) {
				java.sql.Date d = new java.sql.Date(
						((Date) values[i]).getTime());
				statement.setDate(i + 1, d);
			} else if (values[i] != null
					&& values[i].getClass().equals(DateTime.class)) {
				java.sql.Timestamp d = new java.sql.Timestamp(
						((DateTime) values[i]).getTime());
				statement.setTimestamp(i + 1, d);
			} else
				statement.setObject(i + 1, values[i]);
		}
		if (debug)
			System.out.println(statement);
		ResultSet rSet = statement.executeQuery();
		rSet.next();
		if (rSet.isFirst())
			return rSet.getInt(1);
		else
			return 0;
	}

	public static int compteExecuteWithModelRequests(Connection connection,
			String sql, BaseModel model, boolean andConnector, boolean strict,
			Object... values) throws SQLException {
		List<String> columns = Functions.getFields(model.getClass());
		String c = "";
		List<Object> obj = new ArrayList<>();
		String connector = andConnector ? " AND " : " OR ";
		for (Object o : values)
			obj.add(o);
		for (String str : columns) {
			try {
				Method get = model.getClass()
						.getMethod("get" + Functions.firstUpper(str));
				Object o = get.invoke(model);
				if (o != null && strict) {
					c += str + "=? " + connector;
					obj.add(o);
				} else if (o != null && !strict) {
					c += "LOWER(" + str + ") LIKE ? " + connector;
					obj.add("%" + o.toString().toLowerCase() + "%");
				}
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		if (c.equalsIgnoreCase(""))
			c = "1<2";
		else
			c = c.substring(0, c.length() - connector.length());
		if (sql.toLowerCase().contains("where"))
			sql += " AND " + c;
		else
			sql += " WHERE " + c;
		Object[] valuesTotal = new Object[obj.size()];
		obj.toArray(valuesTotal);
		boolean debug = bundle.getString("debug") == null
				? true
				: Boolean.parseBoolean(bundle.getString("debug"));
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < valuesTotal.length; i++) {
			if (valuesTotal[i] != null
					&& valuesTotal[i].getClass().equals(Date.class)) {
				java.sql.Date d = new java.sql.Date(
						((Date) valuesTotal[i]).getTime());
				statement.setDate(i + 1, d);
			} else if (valuesTotal[i] != null
					&& valuesTotal[i].getClass().equals(DateTime.class)) {
				java.sql.Timestamp d = new java.sql.Timestamp(
						((DateTime) valuesTotal[i]).getTime());
				statement.setTimestamp(i + 1, d);
			} else
				statement.setObject(i + 1, valuesTotal[i]);
		}
		if (debug)
			System.out.println(statement);
		ResultSet rSet = statement.executeQuery();
		rSet.next();
		if (rSet.isFirst())
			return rSet.getInt(1);
		else
			return 0;
	}

	public static <T extends BaseModel> List<T> findByFields(
			Connection connection, T model, Class<T> classe, String column,
			String order, int limit, int page, boolean andConnector,
			boolean strict) throws SQLException {
		return BaseDao.findByFieldsWithCondition(connection, model, classe,
				null, column, order, limit, page, andConnector, strict);
	}

	public static <T extends BaseModel> List<T> findByFieldsWithCondition(
			Connection connection, T model, Class<T> classe, String condition,
			String columnOrder, String order, int limit, int page,
			boolean andConnector, boolean strict, Object... values)
			throws SQLException {
		SimpleDateFormat formatTime = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
		List<String> columns = Functions.getFields(model.getClass());
		String c = "";
		List<Object> obj = new ArrayList<>();
		String connector = andConnector ? " AND " : " OR ";
		for (String str : columns) {
			try {
				Method get = model.getClass()
						.getMethod("get" + Functions.firstUpper(str));
				Object o = get.invoke(model);
				if (o != null && strict) {
					c += str + "=? " + connector;
					obj.add(o);
				} else if (o != null && !strict) {
					c += "LOWER(" + str + ") LIKE ? " + connector;
					if (!o.getClass().equals(Date.class))
						obj.add("%" + o.toString().toLowerCase() + "%");
					else {
						Date d = (Date) o;
						if (d.getTime() % 10800000 == 0)
							obj.add("%" + formatDate.format(d) + "%");
						else
							obj.add("%" + formatTime.format(d) + "%");
					}
				}
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		for (Object o : values)
			obj.add(o);
		if (c.equalsIgnoreCase(""))
			c = "1<2";
		else
			c = c.substring(0, c.length() - connector.length());
		if (condition != null)
			c += " AND " + condition;
		Object[] valuesTotal = new Object[obj.size()];
		obj.toArray(valuesTotal);
		return BaseDao.select(connection, model.getTable(), classe, c,
				columnOrder, order, limit, page, valuesTotal);
	}

	public static int countAll(Connection connection, String table,
			String condition, Object... values) throws SQLException {
		boolean debug = bundle.getString("debug") == null
				? true
				: Boolean.parseBoolean(bundle.getString("debug"));
		String sql = "SELECT count(*) FROM " + table;
		if (condition != null)
			sql += " WHERE " + condition;
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null && values[i].getClass().equals(Date.class)) {
				java.sql.Date d = new java.sql.Date(
						((Date) values[i]).getTime());
				statement.setDate(i + 1, d);
			}
			if (values[i] != null
					&& values[i].getClass().equals(DateTime.class)) {
				java.sql.Timestamp d = new java.sql.Timestamp(
						((Date) values[i]).getTime());
				statement.setTimestamp(i + 1, d);
			} else
				statement.setObject(i + 1, values[i]);
		}
		if (debug)
			System.out.println(statement);
		ResultSet rSet = statement.executeQuery();
		rSet.next();
		return rSet.getInt(1);
	}

	public static int countByFields(Connection connection, BaseModel model,
			boolean andConnector, boolean strict) throws SQLException {
		return BaseDao.countByFieldsWithCondition(connection, model, null,
				andConnector, strict);
	}

	public static int countByFieldsWithCondition(Connection connection,
			BaseModel model, String condition, boolean andConnector,
			boolean strict, Object... values) throws SQLException {
		List<String> columns = Functions.getFields(model.getClass());
		String c = "";
		List<Object> obj = new ArrayList<>();
		String connector = andConnector ? " AND " : " OR ";
		for (String str : columns) {
			try {
				Method get = model.getClass()
						.getMethod("get" + Functions.firstUpper(str));
				Object o = get.invoke(model);
				if (o != null && strict) {
					c += str + "=? " + connector;
					obj.add(o);
				} else if (o != null && !strict) {
					c += "LOWER(" + str + ") LIKE ? " + connector;
					obj.add("%" + o.toString().toLowerCase() + "%");
				}
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		for (Object o : values)
			obj.add(o);
		if (c.equalsIgnoreCase(""))
			c = "1<2";
		else
			c = c.substring(0, c.length() - connector.length());
		if (condition != null)
			c += " AND " + condition;
		Object[] valuesTotal = new Object[obj.size()];
		obj.toArray(valuesTotal);
		return BaseDao.countAll(connection, model.getTable(), c, valuesTotal);
	}

	public static void executeRequest(Connection connection, String query,
			Object... values) throws SQLException {
		boolean debug = bundle.getString("debug") == null
				? true
				: Boolean.parseBoolean(bundle.getString("debug"));
		PreparedStatement statement = connection.prepareStatement(query);
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null && values[i].getClass().equals(Date.class)) {
				java.sql.Date d = new java.sql.Date(
						((Date) values[i]).getTime());
				statement.setDate(i + 1, d);
			}
			if (values[i] != null
					&& values[i].getClass().equals(DateTime.class)) {
				java.sql.Timestamp d = new java.sql.Timestamp(
						((DateTime) values[i]).getTime());
				statement.setTimestamp(i + 1, d);
			} else
				statement.setObject(i + 1, values[i]);
		}
		if (debug)
			System.out.println(statement);
		statement.executeUpdate();
	}
}