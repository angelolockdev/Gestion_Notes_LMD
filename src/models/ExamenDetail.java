package models;

import utils.DateSimple;

public class ExamenDetail extends BaseModel{ 
	 
<<<<<<< HEAD
<<<<<<< HEAD
	private Examen examen;
	private Etudiant etudiant;
=======
	private long idexamen;
	private long idetudiant;
>>>>>>> parent of 5e4e073... Too much : NullPointer Exception
=======
	private long idexamen;
	private long idetudiant;
>>>>>>> parent of 5e4e073... Too much : NullPointer Exception
	 
	private transient String nom;
	private transient String prenom; 
	private transient String lieunaissance;
	private transient DateSimple datenaissance; 
	
	private transient String numeromatricule;
	
<<<<<<< HEAD
<<<<<<< HEAD
	 
=======
=======
>>>>>>> parent of 5e4e073... Too much : NullPointer Exception
	public long getIdexamen() {
		return idexamen;
	}
 
>>>>>>> parent of 5e4e073... Too much : NullPointer Exception
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

<<<<<<< HEAD
<<<<<<< HEAD
	 
=======
=======
>>>>>>> parent of 5e4e073... Too much : NullPointer Exception
	public void setIdexamen(long idexamen) {
		this.idexamen = idexamen;
	}
 
	public long getIdetudiant() {
		return idetudiant;
	}
	
	public void setIdetudiant(long idetudiant) {
		this.idetudiant = idetudiant;
	}
>>>>>>> parent of 5e4e073... Too much : NullPointer Exception
 
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

<<<<<<< HEAD
<<<<<<< HEAD
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

=======
>>>>>>> parent of 5e4e073... Too much : NullPointer Exception
=======
>>>>>>> parent of 5e4e073... Too much : NullPointer Exception
	public ExamenDetail() {
	}  
	 
}
