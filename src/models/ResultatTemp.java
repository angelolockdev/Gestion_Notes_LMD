package models;

public class ResultatTemp extends BaseModel{
	private ResultatNotes resultatnotes;
	private Matiere matiere;
	 
	public ResultatNotes getResultatnotes() {
		return resultatnotes;
	} 
	public void setResultatnotes(ResultatNotes resultatnotes) {
		this.resultatnotes = resultatnotes;
	} 
	public Matiere getMatiere() {
		return matiere;
	} 
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	} 

	public ResultatTemp(ResultatNotes resultatnotes, Matiere matiere) {
		super();
		this.resultatnotes = resultatnotes;
		this.matiere = matiere;
	}
	public ResultatTemp(Long id, String table, ResultatNotes resultatnotes, Matiere matiere) {
		super(id, table);
		this.resultatnotes = resultatnotes;
		this.matiere = matiere;
	}
	public ResultatTemp() { 
	
	}
	
}
