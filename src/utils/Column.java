package utils;

public class Column {
	private String columnName;
	private String type;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Column(String column, String type){
		setColumnName(column);
		setType(type);
	}
}
