package metiers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao; 
import models.Examen;  
public class ExamenMetier {
 
	public static  List<Examen> listeExamen(Connection connection, long idniveau, int limit, int page) throws SQLException {
		 Map<String, Object> result = new HashMap<>();
		 
		List<Examen> ret = new ArrayList<Examen>() ; 
		try { 
			ret = BaseDao.select(connection, "examenview2", Examen.class, "idniveau=?", "idniveau, dateexamen, semestre", "DESC", limit, page ,idniveau ); 
			result.put("listeExamens", ret);
		} catch (SQLException e) {
			throw e;
		}
		return ret; 
	}
	public static  List<Examen> listeExamen2(Connection connection,int limit, int page) throws SQLException {
		 Map<String, Object> result = new HashMap<>();
		 
		List<Examen> ret = new ArrayList<Examen>() ; 
		try { 
			ret = BaseDao.select(connection, "examenview1", Examen.class, null, null, "DESC", limit, page );
			
			result.put("listeExamens", ret);
		} catch (SQLException e) {
			throw e;
		}
		return ret;
	}
	
	public static Examen listeExamenBy(Connection connection, Long id) throws SQLException {
		//Map<String, Object> result = new HashMap<>();
		 
		Examen  ret = new Examen () ; 
		try {
			System.out.println("Test long = "+id);
			ret = BaseDao.findById(connection, "examenview1", Examen.class, id) ;
		 
			//result.put("listeNotes", ret);
		} catch (SQLException e) {
			throw e;	
		}
		return ret;
	}

	public static void insertExamen(Connection connection, long idniveau, Date dateExam) throws Exception {
		Examen insert = new Examen();
		
		System.out.println("Date examen = "+dateExam );
		
		insert.setIdniveau(idniveau); 
		insert.setDateexamen(dateExam); 
		try {
		
			BaseDao.insert(connection, insert);
		
		} catch(SQLException e) {
			if(e.getSQLState().equals("23000"))
				throw new Exception("Vous avez déjà inserer Examen d'étude a celle-ci!");
			else
				throw e;
		}
	}
	public static void deleteExamen(Connection connection, long id ) throws Exception{  
		
		try { 
			BaseDao.delete(connection, "Examen", id); 
			  
		}catch(SQLException e) {
			throw e;
		}
	}
}