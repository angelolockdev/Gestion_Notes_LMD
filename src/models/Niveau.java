package models;


public class Niveau extends BaseModel{
	   
	private String intitule;
	private Integer semestre;
	 
	
	public Niveau() {
		 
	}
	public Niveau(Long id, String table) {
		super(id, table);
	}
	public Niveau(Long id, String table , String intitule, Integer semestre) {
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
	public Integer getSemestre() {
		return semestre;
	}
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	} 

	 
	 
}
