package models;
 
public class Notes extends BaseModel{
	 
	private Long id;
	private Long idetudiant;
	private Long idmatiere;
	private Double note;
	private String anneedeb;
	private String anneefin;
	private Integer mention;
	
	public Integer getMention() {
		return mention;
	}
	public void setMention(Integer mention) {
		this.mention = mention;
	}
	public Notes( ) { 
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdetudiant() {
		return idetudiant;
	}
	public void setIdetudiant(Long idetudiant) {
		this.idetudiant = idetudiant;
	}
	public Long getIdmatiere() {
		return idmatiere;
	}
	public void setIdmatiere(Long idmatiere) {
		this.idmatiere = idmatiere;
	}
	public Double getNote() {
		return note;
	}
	public void setNote(Double note) {
		this.note = note;
	}
	public String getAnneedeb() {
		return anneedeb;
	}
	public void setAnneedeb(String anneedeb) {
		this.anneedeb = anneedeb;
	}
	public String getAnneefin() {
		return anneefin;
	}
	public void setAnneefin(String anneefin) {
		this.anneefin = anneefin;
	}
	public Notes(Long id, String table, Long idetudiant, Long idmatiere, Double note, String anneedeb,
			String anneefin) {
		super(id, table);
		this.idetudiant = idetudiant;
		this.idmatiere = idmatiere;
		this.note = note;
		this.anneedeb = anneedeb;
		this.anneefin = anneefin;
	}
	public Notes(Long id, String table) {
		super(id, table);
	}
	 
	
}
