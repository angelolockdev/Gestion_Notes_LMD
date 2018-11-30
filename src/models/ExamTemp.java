package models;


public class ExamTemp extends BaseModel{
	private Examen examen;
	private ExamenDetail examendetail;

	
	public ExamTemp(Examen examen, ExamenDetail examendetail) {
		super();
		this.examen = examen;
		this.examendetail = examendetail;
	}


	public Examen getExamen() {
		return examen;
	}


	public void setExamen(Examen examen) {
		this.examen = examen;
	}


	public ExamenDetail getExamendetail() {
		return examendetail;
	}


	public void setExamendetail(ExamenDetail examendetail) {
		this.examendetail = examendetail;
	}


	public ExamTemp(){}
}
