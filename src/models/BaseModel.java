package models;

public class BaseModel {
	private Long id;
	private transient String table;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTable() {
		if(table==null)
			return getClass().getSimpleName().toLowerCase();
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public BaseModel(Long id, String table) {
		super();
		this.id = id;
		this.table = table;
	}
	public BaseModel(){}
}
