package models;

import utils.DateSimple;

public class Examen extends BaseModel{ 
	 
	private String idniveau;
	private DateSimple dateexamen;
	
  
	public String getIdniveau() {
		return idniveau;
	} 

	public void setIdniveau(String idniveau) {
		this.idniveau = idniveau;
	} 
	
	public DateSimple getDateexamen() {
		return dateexamen;
	} 

	public void setDateexamen(DateSimple dateexamen) {
		this.dateexamen = dateexamen;
	} 
	
	public Examen() {
		super();
	}  
	
	 
}
