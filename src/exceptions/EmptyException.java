package exceptions;

import java.util.List;

public class EmptyException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<String> columns;
	
	@Override
	public String getMessage(){
		if(columns == null || columns.size() == 0)
			return "";
		String m = "";
		for(String str : columns)
			m += str + ", ";
		m = m.substring(0, m.length()-2);
		if(columns.size()==1)
			m = "Le champ " + m + " ne doit pas être vide!";
		else
			m = "Les champs " + m + " ne doivent pas être vide!";
		return m;
	}
	
	public EmptyException(List<String> columns){
		this.columns = columns;
	}
}