package models;
 
public class Matiere extends BaseModel{
	  
	private Long idniveau;
	private Integer optionM;
	private String designation;
	private String abreviation;
	private Integer coefficient;
	private Long equivalent;
	
	private  String intitule;
	private  Integer semestre;
	
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Long getEquivalent() {
		return equivalent;
	}

	public void setEquivalent(Long equivalent) {
		this.equivalent = equivalent;
	}

	public Matiere() {
		super();
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
	public Matiere(Long id, String table, Long idniveau, Integer optionM, String designation, String abreviation, Integer coefficient, Long equivalent) {
		super(id, table);
		this.idniveau = idniveau;
		this.optionM = optionM;
		this.designation = designation;
		this.abreviation = abreviation;
		this.coefficient = coefficient;
		this.equivalent = equivalent;
	}
	
	public Integer getOptionM() {
		return optionM;
	}

	public void setOptionM(Integer optionM) {
		this.optionM = optionM;
	}

	public Matiere(Long id, String table) {
		super(id, table);
	} 
	 
}
