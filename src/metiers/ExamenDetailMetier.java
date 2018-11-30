package metiers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao; 
import models.ExamenDetail;
public class ExamenDetailMetier {
 
	public static List<ExamenDetail> listeExamenDetail(Connection connection, int limit, int page) throws SQLException {
		 Map<String, Object> result = new HashMap<>();
		 
		List<ExamenDetail> ret = new ArrayList<ExamenDetail>() ; 
		try { 
			ret = BaseDao.select(connection, "examendetailetudiantview", ExamenDetail.class, null, null, "DESC", limit, page); 
			result.put("listeExamenDetails", ret);
		} catch (SQLException e) {
			throw e;
		}
		return ret;
	}
	public static List<ExamenDetail> listeExamenDetailBy(Connection connection, long id) throws SQLException {
		//Map<String, Object> result = new HashMap<>();
		List<ExamenDetail> ret = new ArrayList<ExamenDetail>() ; 
	 
		try {
			
			ret = BaseDao.select(connection, "examendetail", ExamenDetail.class, "idetudiant = ?", null, null, 0, 1, id);
		 
			//result.put("listeNotes", ret);
		} catch (SQLException e) {
			throw e;	
		}
		return ret;
	}

	public static void insertExamenDetail(Connection connection, long idexamen, long idetudiant) throws Exception {
		ExamenDetail insert = new ExamenDetail();
		
		insert.setIdexamen(idexamen);  
		insert.setIdetudiant(idetudiant);  
		try {
		
			BaseDao.insert(connection, insert);
		
		} catch(SQLException e) {
			if(e.getSQLState().equals("23000"))
				throw new Exception("Vous avez déjà inserer ExamenDetail d'étude a celle-ci!");
			else
				throw e;
		}
	}
	public static void deleteExamenDetail(Connection connection, long id ) throws Exception{  
		
		try { 
			BaseDao.delete(connection, "examendetail", id); 
			  
		}catch(SQLException e) {
			throw e;
		}
	}
}