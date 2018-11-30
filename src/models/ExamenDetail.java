package models;

import utils.DateSimple;

public class ExamenDetail extends BaseModel{ 
	 
	private Examen examen;
	private Etudiant etudiant;
	 
	private transient String nom;
	private transient String prenom; 
	private transient String lieunaissance;
	private transient DateSimple datenaissance; 
	
	private transient String numeromatricule;
	
	 
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

	public String getLieunaissance() {
		return lieunaissance;
	}

	public void setLieunaissance(String lieunaissance) {
		this.lieunaissance = lieunaissance;
	}

	public DateSimple getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(DateSimple datenaissance) {
		this.datenaissance = datenaissance;
	}

	public String getNumeromatricule() {
		return numeromatricule;
	}

	public void setNumeromatricule(String numeromatricule) {
		this.numeromatricule = numeromatricule;
	}

	 
 
	public ExamenDetail(Long id, String table) {
		super(id, table);
	}
 

	public ExamenDetail(Examen examen, Etudiant etudiant, String nom, String prenom, String lieunaissance,
			DateSimple datenaissance, String numeromatricule) {
		super();
		this.examen = examen;
		this.etudiant = etudiant;
		this.nom = nom;
		this.prenom = prenom;
		this.lieunaissance = lieunaissance;
		this.datenaissance = datenaissance;
		this.numeromatricule = numeromatricule;
	}

	public ExamenDetail(Long id, String table, Examen examen, Etudiant etudiant, String nom, String prenom,
			String lieunaissance, DateSimple datenaissance, String numeromatricule) {
		super(id, table);
		this.examen = examen;
		this.etudiant = etudiant;
		this.nom = nom;
		this.prenom = prenom;
		this.lieunaissance = lieunaissance;
		this.datenaissance = datenaissance;
		this.numeromatricule = numeromatricule;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public ExamenDetail() {
	}  
	 
}
