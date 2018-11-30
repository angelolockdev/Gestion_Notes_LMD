package models;

import utils.DateSimple;

public class ExamenDetail extends BaseModel{ 
	 
	private Long idexamen;
	private Long idetudiant;
	 
	private transient String nom;
	private transient String prenom; 
	private transient String lieunaissance;
	private transient DateSimple datenaissance; 
	
	private transient String numeromatricule;
	
	public Long getIdexamen() {
		return idexamen;
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

	public void setIdexamen(Long idexamen) {
		this.idexamen = idexamen;
	}
 
	public Long getIdetudiant() {
		return idetudiant;
	}
	
	public void setIdetudiant(Long idetudiant) {
		this.idetudiant = idetudiant;
	}
 
	public ExamenDetail(Long id, String table) {
		super(id, table);
	}

	public ExamenDetail(Long id, String table, long idexamen, long idetudiant) {
		super(id, table);
		this.idexamen = idexamen;
		this.idetudiant = idetudiant;
	}

	public ExamenDetail(Long idexamen, Long idetudiant) {
		super();
		this.idexamen = idexamen;
		this.idetudiant = idetudiant;
	}

	public ExamenDetail() {
		super();
	}  
	 
}
