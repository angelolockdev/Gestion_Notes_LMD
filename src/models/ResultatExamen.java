package models;
 
public class ResultatExamen extends BaseModel{
	  
	private Long idnotes;
	private Integer statusadmis;
	private Double moyenne;
	private String mention;
	
	
	public ResultatExamen() {
		super();
	}
	
	public Long getIdnotes() {
		return idnotes;
	}
	public void setIdnotes(Long idnotes) {
		this.idnotes = idnotes;
	}
	public Integer getStatusadmis() {
		return statusadmis;
	}
	public void setStatusadmis(Integer statusadmis) {
		this.statusadmis = statusadmis;
	}
	public Double getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(Double moyenne) {
		this.moyenne = moyenne;
	}
	public String getMention() {
		return mention;
	}
	public void setMention(String mention) {
		this.mention = mention;
	}
	public ResultatExamen(Long id, String table, Long id2, Long idnotes, Integer statusadmis, Double moyenne,
			String mention) {
		super(id, table);
		id = id2;
		this.idnotes = idnotes;
		this.statusadmis = statusadmis;
		this.moyenne = moyenne;
		this.mention = mention;
	}
	 
	
	
}
