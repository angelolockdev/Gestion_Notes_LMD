package models;


public class Produit extends BaseModel{
	//id, nom, prix, qte, image, etat, categorie (boisson, autre)
	private String nom;
	private Double prix;
	private Integer quantite;
	private String image;
	private Integer etat;
	private Integer categorie;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public Integer getQuantite() {
		return quantite;
	}
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getEtat() {
		return etat;
	}
	public void setEtat(Integer etat) {
		this.etat = etat;
	}
	public Integer getCategorie() {
		return categorie;
	}
	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}
	public Produit(){}
	public Produit(String nom, Double prix, Integer quantite, String image, Integer etat, Integer categorie) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.image = image;
		this.etat = etat;
		this.categorie = categorie;
	}
	public Produit(Long id, String table, String nom, Double prix, Integer quantite, String image, Integer etat,
			Integer categorie) {
		super(id, table);
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.image = image;
		this.etat = etat;
		this.categorie = categorie;
	}
	
}
