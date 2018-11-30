package models;
 

public class ExamenDetail extends BaseModel{ 
	 
	private Long idexamen;
	private Long idetudiant;
	 
	 
	public Long getIdexamen() {
		return idexamen;
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
 
	public ExamenDetail() {
		super();
	}  
	 
}
