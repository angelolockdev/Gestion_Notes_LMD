package metiers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao; 
import models.Niveau; 
public class NiveauMetier {
 
	public static  List<Niveau>  listeNiveau(Connection connection) throws SQLException {
		 Map<String, Object> result = new HashMap<>();
		 
		List<Niveau> ret = new ArrayList<Niveau>() ; 
		try { 
			ret = BaseDao.select(connection, "niveau", Niveau.class, null, null, null, 0, 1); 
			result.put("listeNiveaux", ret);
		} catch (SQLException e) {
			throw e;
		}
		return ret;
	}
	public static Niveau listeNiveauBy(Connection connection, long id) throws SQLException {
		//Map<String, Object> result = new HashMap<>();
		 
		Niveau  ret = new Niveau () ; 
		try {
			
			ret = BaseDao.findById(connection, "niveau", Niveau.class, id) ;
		 
			//result.put("listeNotes", ret);
		} catch (SQLException e) {
			throw e;	
		}
		return ret;
	}

	public static void insertNiveau(Connection connection, String intitule, Integer semestre) throws Exception {
		Niveau insert = new Niveau();
		
		insert.setIntitule(intitule); 
		insert.setSemestre(semestre); 
		try {
		
			BaseDao.insert(connection, insert);
		
		} catch(SQLException e) {
			if(e.getSQLState().equals("23000"))
				throw new Exception("Vous avez déjà inserer niveau d'étude a celle-ci!");
			else
				throw e;
		}
	}
	public static void deleteNiveau(Connection connection, long id ) throws Exception{  
		
		try { 
			BaseDao.delete(connection, "niveau", id); 
			  
		}catch(SQLException e) {
			throw e;
		}
	}
}