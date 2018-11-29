package models;
 

public class Universite extends BaseModel{ 
	 
	private String filiere;
	private String nom;
	
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public Universite(Long id, String filiere) {
		super();
		this.filiere = filiere;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Universite(Long id, String table, String filiere, String nom) {
		super(id, table);
		this.filiere = filiere;
		this.nom = nom;
	}
	public Universite() {
		super();
	}  
	
	 
}
