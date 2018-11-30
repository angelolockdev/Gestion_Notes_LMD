package models;

import java.sql.Date;
 

public class Examen extends BaseModel{ 
	 
	private Long idniveau;
	private Date dateexamen;
	

	private transient Integer semestre;
	
	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
 
	
	 
	public Long getIdniveau() {
		return idniveau;
	}

	public void setIdniveau(Long idniveau) {
		this.idniveau = idniveau;
	}

	public Date getDateexamen() {
		return dateexamen;
	} 

	public void setDateexamen(Date dateexamen) {
		this.dateexamen = dateexamen;
	} 
	
	public Examen(Long id, String table) {
		super(id, table);
	}
 

	public Examen() {
		super();
	}  
	
	 
}
