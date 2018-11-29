package models;
 

public class Universite extends BaseModel{ 
	private Long id;
	private String filiere;
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
	public Universite(Long id, String filiere) {
		super();
		this.id = id;
		this.filiere = filiere;
	}  
	
	 
}
