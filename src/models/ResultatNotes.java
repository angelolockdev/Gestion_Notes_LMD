package models;

public class ResultatNotes extends BaseModel{
	private Long idnotes;
	private Long idexamen;
	private Long idetudiant;
	private Long idmatiere;
	private Double note;
	private Double noterepechage;
	private Integer mention;
	public Long getIdnotes() {
		return idnotes;
	}
	public void setIdnotes(Long idnotes) {
		this.idnotes = idnotes;
	}
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
	public ResultatNotes(Long idnotes, Long idexamen, Long idetudiant, Long idmatiere, Double note,
			Double noterepechage, Integer mention) {
		super();
		this.idnotes = idnotes;
		this.idexamen = idexamen;
		this.idetudiant = idetudiant;
		this.idmatiere = idmatiere;
		this.note = note;
		this.noterepechage = noterepechage;
		this.mention = mention;
	}
	public ResultatNotes(Long id, String table, Long idnotes, Long idexamen, Long idetudiant, Long idmatiere,
			Double note, Double noterepechage, Integer mention) {
		super(id, table);
		this.idnotes = idnotes;
		this.idexamen = idexamen;
		this.idetudiant = idetudiant;
		this.idmatiere = idmatiere;
		this.note = note;
		this.noterepechage = noterepechage;
		this.mention = mention;
	}
	public ResultatNotes() { 
	}
	 
	
}
