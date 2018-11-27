package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.UtilDb;
import metiers.NotesMetier; 
import models.Notes;

@Controller
public class NotesController {
	 
	@RequestMapping(value = "/{nomcomplet}-{niveau}-{anneedeb}-note/{id}", method = RequestMethod.GET)
	public String listeNotes(
			ModelMap map, 
			HttpServletRequest request, 
			@PathVariable("nomcomplet") String nomcomplet,
			@PathVariable("niveau") String niveau,
			@PathVariable("anneedeb") String anneedeb,
			@PathVariable("id") long id,
			@RequestParam(value = "idetudiant", required = false) Integer idetudiant,
			@RequestParam(value = "idmatiere", required = false) Integer idmatiere ) {
		/**
		 * param1 et param2 peuvent aussi etre recuperer via le HttpServletRequest:
		 * request.getParameter("param1") comme java web
		 */
		Connection connection = null;
		try {
			connection = UtilDb.getConnection();
			List<Notes> listeNotes = NotesMetier.getNotes(connection, idetudiant, idmatiere) ;
			map.put("listeNotes", listeNotes);
		 
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "error");
			map.put("message", e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return "test";
	}
	 
	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}