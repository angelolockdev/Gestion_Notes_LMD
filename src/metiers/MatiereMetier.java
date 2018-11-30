package metiers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import models.Matiere;

public class MatiereMetier {
 
	public static  List<Matiere> getOption(Connection connection, int option) throws SQLException {
		Map<String, Object> result = new HashMap<>();
		 
		List<Matiere> ret = new ArrayList<Matiere>() ; 
		try { 
			ret = BaseDao.select(connection, "matiere", Matiere.class, "optionM = ?", null, null, 0, 1, option); 
			result.put("listeMatieres", ret);
		} catch (SQLException e) {
			throw e;
		}
		return ret;
	}
	
	public static  Map<String, Object> listeMatiere(Connection connection) throws SQLException {
		 Map<String, Object> result = new HashMap<>();
		 
		List<Matiere> ret = new ArrayList<Matiere>() ; 
		try { 
			ret = BaseDao.select(connection, "matiereniveauview", Matiere.class, null, null, null, 0, 1); 
			result.put("listeMatieres", ret);
		} catch (SQLException e) {
			throw e;
		}
		return result;
	}
	public static  List<Matiere> getListeMatiereBySemestre(Connection connection, Integer semestre) throws SQLException {
		List<Matiere> ret = new ArrayList<Matiere>() ;  
		try { 
			ret = BaseDao.select(connection, "matierebyniveauview", Matiere.class, "semestre = ?", null, null, 0, 1, semestre); 
			 System.out.println("========> ret.size = "+ret.size());
			 for(Matiere m : ret){
					System.out.println(" "+m.getAbreviation()+" "+m.getDesignation()+" "+m.getIntitule());
				}
		} catch (SQLException e) {
			throw e;
		}
		return ret;
	}
	public static Matiere listeMatiereBy(Connection connection, long id) throws SQLException {
		//Map<String, Object> result = new HashMap<>();
		 
		Matiere  ret = new Matiere() ; 
		try {
			
			ret = BaseDao.findById(connection, "matiere", Matiere.class, id) ;
		 
			//result.put("listeNotes", ret);
		} catch (SQLException e) {
			throw e;	
		}
		return ret;
	}

	public static void insertMatiere(Connection connection, long idniveau, String designation, String abreviation, int coeff) throws Exception {
		Matiere insert = new Matiere();
		
		insert.setIdniveau(idniveau); 
		insert.setDesignation(designation);  
		insert.setAbreviation(abreviation);  
		insert.setCoefficient(coeff);
		try {
		
			BaseDao.insert(connection, insert);
		
		} catch(SQLException e) {
			if(e.getSQLState().equals("23000"))
				throw new Exception("Vous avez déjà inserer matière a celle-ci!");
			else
				throw e;
		}
	}
	public static void deleteMatiere(Connection connection, long id ) throws Exception{  
		
		try { 
			BaseDao.delete(connection, "matiere", id); 
			  
		}catch(SQLException e) {
			throw e;
		}
	}
}