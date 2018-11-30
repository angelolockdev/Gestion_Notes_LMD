package models;

import utils.DateSimple;

public class ExamenDetail extends BaseModel{ 
	  
	private long idexamen;
	private long idetudiant; 
	 
	private transient String nom;
	private transient String prenom; 
	private transient String lieunaissance;
	private transient DateSimple datenaissance; 
	
	private transient String numeromatricule;

	public long getIdexamen() {
		return idexamen;
	}

	public void setIdexamen(long idexamen) {
		this.idexamen = idexamen;
	}

	public long getIdetudiant() {
		return idetudiant;
	}

	public void setIdetudiant(long idetudiant) {
		this.idetudiant = idetudiant;
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

	public ExamenDetail(Long id, String table, long idexamen, long idetudiant, String nom, String prenom,
			String lieunaissance, DateSimple datenaissance, String numeromatricule) {
		super(id, table);
		this.idexamen = idexamen;
		this.idetudiant = idetudiant;
		this.nom = nom;
		this.prenom = prenom;
		this.lieunaissance = lieunaissance;
		this.datenaissance = datenaissance;
		this.numeromatricule = numeromatricule;
	}

	public ExamenDetail(long idexamen, long idetudiant, String nom, String prenom, String lieunaissance,
			DateSimple datenaissance, String numeromatricule) {
		super();
		this.idexamen = idexamen;
		this.idetudiant = idetudiant;
		this.nom = nom;
		this.prenom = prenom;
		this.lieunaissance = lieunaissance;
		this.datenaissance = datenaissance;
		this.numeromatricule = numeromatricule;
	}

	public ExamenDetail() {
	}
	
 
	 
}
