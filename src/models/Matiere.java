package models;
 
public class Matiere extends BaseModel{
	 
	private Long id;
	private Long idniveau;
	private String designation;
	private String abreviation;
	private Integer coefficient;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdniveau() {
		return idniveau;
	}
	public void setIdniveau(Long idniveau) {
		this.idniveau = idniveau;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getAbreviation() {
		return abreviation;
	}
	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}
	public Integer getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Integer coefficient) {
		this.coefficient = coefficient;
	}
	public Matiere(Long id, String table, Long idniveau, String designation, String abreviation, Integer coefficient) {
		super(id, table);
		this.idniveau = idniveau;
		this.designation = designation;
		this.abreviation = abreviation;
		this.coefficient = coefficient;
	}
	
	public Matiere(Long id, String table) {
		super(id, table);
	} 
	 
}
