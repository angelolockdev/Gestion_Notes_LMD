package models;


public class Categorie extends BaseModel{
	private String nom;
	private String image;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Categorie(Long id, String table, String nom) {
		super(id, table);
		this.nom = nom;
	}

	public Categorie(String nom) {
		super();
		this.nom = nom;
	}
	public Categorie(){}
}
