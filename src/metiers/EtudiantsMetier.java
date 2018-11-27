package metiers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import models.Etudiant;
import models.Notes;
import models.ResultatExamen;  

public class EtudiantsMetier {
 
	public static  Map<String, Object> listeEtudiants(Connection connection) throws SQLException {
		 Map<String, Object> result = new HashMap<>();
		 
		List<Etudiant> ret = new ArrayList<Etudiant>() ; 
		try { 
			ret = BaseDao.select(connection, "etudiant", Etudiant.class, null, null, null, 0, 1); 
			result.put("listeEtudiants", ret);
		} catch (SQLException e) {
			throw e;
		}
		return result;
	}
	public static Etudiant listeEtudiantsBy(Connection connection, long id) throws SQLException {
		//Map<String, Object> result = new HashMap<>();
		 
		 Etudiant  ret = new Etudiant () ; 
		try {
			
			ret = BaseDao.findById(connection, "etudiant", Etudiant.class, id) ;
			System.out.println("Etudiant = "+ret.getNom()+" , "+ret.getPrenom());
			//result.put("listeNotes", ret);
		} catch (SQLException e) {
			throw e;	
		}
		return ret;
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