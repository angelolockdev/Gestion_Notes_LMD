package models;


public class Etudiant extends BaseModel{
	public static final String PRETORIAT = "ETU";
	 
	private Long id;
	private String numeromatricule;
	private String nom;
	private String prenom;
	private String dateNaissance;

	 
	public Etudiant(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	public Etudiant(){}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeromatricule() {
		return numeromatricule;
	}
	public void setNumeromatricule(String numeromatricule) {
		this.numeromatricule = numeromatricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	} 
}