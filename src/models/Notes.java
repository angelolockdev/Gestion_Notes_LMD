package models;
 
public class Notes extends BaseModel{

	private Long idmatiere;
	private Long idexamendetail;
	private Double note;
	private Double noterepechage;
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
	
	 
	public Long getIdexamendetail() {
		return idexamendetail;
	}
	public void setIdexamendetail(Long idexamendetail) {
		this.idexamendetail = idexamendetail;
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

	 
	public Notes( Long idetudiant, Long idmatiere, Double note, Double noterepechage, Integer mention) {
		super(); 
		this.idmatiere = idmatiere;
		this.note = note;
		this.noterepechage = noterepechage;
		
		this.mention = mention;
	}
	public Notes(Long id, String table) {
		super(id, table);
	}
	
	public String print() {
		String ret = ""; 
		ret += " idExamen: "+this.getIdexamendetail() ;
		ret += " idMatiere: "+this.getIdmatiere() ;
		ret += " notes: "+this.getNote() ;
		ret += " getNoterepechage: "+this.getNoterepechage() ;
		ret += " getMention: "+this.getMention() ;
		return ret;
	}
	 
	
}
