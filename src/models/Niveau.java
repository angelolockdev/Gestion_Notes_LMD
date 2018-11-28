package models;


public class Niveau extends BaseModel{
	  
	private Long id;
	private String filiere;
	private String intitule;
	private String semestre;
	 
	
	public Niveau() {
		 
	}
	public Niveau(Long id, String table) {
		super(id, table);
	}
	public Niveau(Long id, String table, String filiere, String intitule, String semestre) {
		super(id, table);
		this.filiere = filiere;
		this.intitule = intitule;
		this.semestre = semestre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
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
