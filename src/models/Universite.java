package models;
 

public class Universite extends BaseModel{ 
	 
	private String filiere;
	
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
	
	 
}
