package models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import utils.DateSimple;

public class Etudiant extends BaseModel{
	public static final String PRETORIAT = "ETU";
	  
	private Long iduniversite; 
	private String nom;
	private String prenom; 
	private String lieunaissance;
	private DateSimple datenaissance; 
	
	private String numeromatricule;
	
	private transient String filiere;
	
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public String getLieunaissance() {
		return lieunaissance;
	}
	public void setLieunaissance(String lieunaissance) {
		this.lieunaissance = lieunaissance;
	}

	public Long getIduniversite() {
		return iduniversite;
	}
	public void setIduniversite(Long iduniversite) {
		this.iduniversite = iduniversite;
	}
	public Etudiant(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	public Etudiant(){}
	 
	public String getNumeromatricule() {
		NumberFormat nf = new DecimalFormat("000000"); 
		if(numeromatricule==null)
			numeromatricule = PRETORIAT+""+nf.format(this.getId());
		return numeromatricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) throws Exception {
	    if(nom.equals("") || nom.equals("null") || nom.equals("NULL")){
            throw new Exception("Ce Champ ne doit pas être vide");
        }
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) throws Exception {
		if(prenom.equals("") || prenom.equals("null") || prenom.equals("NULL")){
            throw new Exception("Ce Champ ne doit pas être vide");
        }
		this.prenom = prenom;
	}
	public DateSimple getDatenaissance() {
		return datenaissance;
	}
	public void setDatenaissance(DateSimple datenaissance) {
		this.datenaissance = datenaissance;
	} 
}
