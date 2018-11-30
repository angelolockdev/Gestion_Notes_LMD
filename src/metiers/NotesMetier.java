package metiers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import models.Etudiant;
import models.ExamenDetail;
import models.Notes;
import models.ResultatExamen;
import utils.MentionUtils;  

public class NotesMetier {
 
	public static Map<String, Object>  getNotes(Connection connection,
			long idetudiant, long idmatiere) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String where = "";
		List<Notes> ret = new ArrayList<Notes>() ; 
		try {
			
			if(idetudiant!=0 && idmatiere==0) {
				where = "idetudiant=?";
				ret = BaseDao.select(connection,
						"notes", Notes.class, where,
						null, "desc", 0, 1, idetudiant);
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

	public static void insertNotes(Connection connection, long idetudiant, long[] idmatiere, long idexamen, double[] notes) throws Exception {
	 
		try { 
			List<ExamenDetail> listeEtude = BaseDao.select(connection, "examendetail", ExamenDetail.class, "idetudiant = ? ", null, null, 0, 1, idetudiant);
			ExamenDetail detail = new ExamenDetail();
			for(ExamenDetail i : listeEtude) {
				if(i.getIdexamen()==idexamen) {
					detail = i;
				}
			} 
			System.out.println("exDet === "+detail.getId());
			System.out.println(" === "+detail.getId());
			Notes temp = new Notes();
			temp.setIdmatiere(idmatiere[0]);
			temp.setIdexamendetail(detail.getId()); 
			temp.setNote(notes[0]);
			temp.setNoterepechage(Double.valueOf("0"));
			temp.setMention(MentionUtils.checkMention((int)notes[0]));
			 
			System.out.println("print === "+temp.print());
			
			//BaseDao.insert(connection, temp);
			/*for(int i=0 ; i<notes.length;i++) {
				
				Notes temp = new Notes();
				temp.setIdetudiant(idetudiant);
				temp.setIdmatiere(idmatiere[i]);
				temp.setIdexamen(idexamen); 
				temp.setNote(notes[i]);
				temp.setNoterepechage(Double.valueOf("0"));
				temp.setMention(MentionUtils.checkMention((int)notes[i]));
				
				
				System.out.println("print === "+temp.print());
				BaseDao.insert(connection, temp);
				System.out.println("INSERTION Notes");
			} */
		
		} catch(SQLException e) {
			if(e.getSQLState().equals("23000"))
				throw new Exception("Vous avez déjà inserer note a celle-ci!");
			else
				e.printStackTrace();
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