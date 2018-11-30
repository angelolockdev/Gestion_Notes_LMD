package models;

import java.sql.Date;
 

public class Examen extends BaseModel{ 
	 
	private Niveau niveau;
	private Date dateexamen;
	

	private transient Integer semestre;
	
	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
 
	
	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
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

	public Examen(Long id, String table, Niveau niveau, Date dateexamen, Integer semestre) {
		super(id, table);
		this.niveau = niveau;
		this.dateexamen = dateexamen;
		this.semestre = semestre;
	}

	public Examen() {
		super();
	}  
	
	 
}
