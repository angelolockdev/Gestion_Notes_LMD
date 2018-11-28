package metiers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao; 
import models.ResultatExamen; 

public class ResultatExamenMetier {
 
	public static  Map<String, Object> listeResultatExam(Connection connection) throws SQLException {
		 Map<String, Object> result = new HashMap<>();
		 
		List<ResultatExamen> ret = new ArrayList<ResultatExamen>() ; 
		try { 
			ret = BaseDao.select(connection, "resultatexamen", ResultatExamen.class, null, null, null, 0, 1); 
			result.put("listeResultatExamens", ret);
		} catch (SQLException e) {
			throw e;
		}
		return result;
	}
	public static ResultatExamen listeResultatExamenBy(Connection connection, long id) throws SQLException {
		//Map<String, Object> result = new HashMap<>();
		 
		ResultatExamen  ret = new ResultatExamen () ; 
		try {
			
			ret = BaseDao.findById(connection, "resultatexamen", ResultatExamen.class, id) ;
		 
			//result.put("listeNotes", ret);
		} catch (SQLException e) {
			throw e;	
		}
		return ret;
	}

	public static void insertResultatExamen(Connection connection, long idnotes, double moyenne, int statusadmis, String mention) throws Exception {
		ResultatExamen insert = new ResultatExamen();
		
		insert.setIdnotes(idnotes);  
		insert.setMoyenne(moyenne); 
		insert.setStatusadmis(statusadmis); 
		insert.setMention(mention);
		try {
		
			BaseDao.insert(connection, insert);
		
		} catch(SQLException e) {
			if(e.getSQLState().equals("23000"))
				throw new Exception("Vous avez déjà inserer niveau d'étude a celle-ci!");
			else
				throw e;
		}
	}
	public static void deleteResultatExamen(Connection connection, long id ) throws Exception{  
		
		try { 
			BaseDao.delete(connection, "resultatexamen", id); 
			  
		}catch(SQLException e) {
			throw e;
		}
	}
}