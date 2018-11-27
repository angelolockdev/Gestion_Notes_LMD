package metiers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import models.Notes;
import models.ResultatExamen;  

public class NotesMetier {
 
	public static Map<String, Object> getNotes(Connection connection,
			long idetudiant, long idmatiere) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String where = "";
		List<Notes> ret = new ArrayList<Notes>() ; 
		try {
			
			if(idetudiant!=0 && idmatiere==0) {
				where = "idetudiant=?";
				ret = BaseDao.select(connection,
						"notes", Notes.class, where,
						"efficacite", "desc", 0, 1, idetudiant);
			}else if(idetudiant!=0 && idmatiere!=0) {
				where = "idetudiant = ? AND idmatiere = ? " ;
				ret = BaseDao.select(connection,
						"notes", Notes.class, where,
						null, null, 0, 1, idetudiant, idmatiere);
			} 
			result.put("listeNotes", ret);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public static void insertNotes(Connection connection, long idetudiant, long idmatiere, double note, String anneedeb, String anneefin, int mention) throws Exception {
		Notes insert = new Notes();
		
		insert.setIdetudiant(idetudiant); 
		insert.setIdmatiere(idmatiere); 
		insert.setNote(note);
		insert.setAnneedeb(anneedeb);
		insert.setAnneefin(anneefin);
		insert.setMention(mention);
		try {
		
			BaseDao.insert(connection, insert);
		
		} catch(SQLException e) {
			if(e.getSQLState().equals("23000"))
				throw new Exception("Vous avez déjà inserer note a celle-ci!");
			else
				throw e;
		}
	}
	public static void deleteNotes(Connection connection, long idnotes ) throws Exception{
		List<ResultatExamen> results = BaseDao.select(connection, "resultatExamen", ResultatExamen.class, "idnotes = ?", null, null, 0, 1, idnotes); 
		if(results.size()!=0) {
			throw new Exception("Une resultat d'examen est associé a celle-ci!");
		}
		try { 
			BaseDao.delete(connection, "notes", idnotes); 
			  
		}catch(SQLException e) {
			throw e;
		}
	}
}