package models;
 
public class Notes extends BaseModel{
	  
	private Long idetudiant;
	private Long idmatiere;
	private Long idexamen;
	private Double note;
	private Double noterepechage;
	private String anneedeb;
	private String anneefin;
	private Integer mention;
	
	
	public Double getNoterepechage() {
		return noterepechage;
	}
	public void setNoterepechage(Double noterepechage) {
		this.noterepechage = noterepechage;
	}
	public Integer getMention() {
		return mention;
	}
	public void setMention(Integer mention) {
		this.mention = mention;
	}
	public Notes( ) { 
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
	 
	public Notes( Long idetudiant, Long idmatiere, Double note, Double noterepechage, String anneedeb,
			String anneefin, Integer mention) {
		super();
		this.idetudiant = idetudiant;
		this.idmatiere = idmatiere;
		this.note = note;
		this.noterepechage = noterepechage;
		this.anneedeb = anneedeb;
		this.anneefin = anneefin;
		this.mention = mention;
	}
	public Notes(Long id, String table) {
		super(id, table);
	}
	 
	
}
