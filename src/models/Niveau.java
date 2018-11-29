package models;


public class Niveau extends BaseModel{
	   
	private String intitule;
	private String semestre;
	 
	
	public Niveau() {
		 
	}
	public Niveau(Long id, String table) {
		super(id, table);
	}
	public Niveau(Long id, String table , String intitule, String semestre) {
		super(id, table); 
		this.intitule = intitule;
		this.semestre = semestre;
	}
	 
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	} 

	 
	 
}
