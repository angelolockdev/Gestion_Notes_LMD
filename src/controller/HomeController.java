package controller;

import java.sql.Connection;
import java.sql.SQLException; 
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.UtilDb;
import metiers.EtudiantsMetier; 
import models.Categorie;
import models.Etudiant; 

@Controller
public class HomeController {
	@RequestMapping(value = {"/", "/index"} )
	public String index( ModelMap map ) { 
		Connection connection = null;
		try {
			connection = UtilDb.getConnection();
			Map<String, Object> result = EtudiantsMetier.listeEtudiants(connection); 
			List<Etudiant> liste = (List<Etudiant>) result.get("listeEtudiants");
			System.out.println("Size Etudiants = "+liste.size());
			for(Etudiant temp : liste) {   
				System.out.println(" Etudiant : "+temp.getNumeromatricule()+" , "+temp.getNom()+" , "+temp.getPrenom() +" , "+temp.getDatenaissance());
			} 
			map.put("listeEtudiant", liste); 
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "error");
			map.put("message", e.getMessage());
		} finally {
			closeConnection(connection);
		}
		
		return "index"; // Nom du fichier .jsp dans /WEB-INF/views/
	}
	 
	
	@RequestMapping(value = "/{model}-details/{id}", method = RequestMethod.GET)
	public String testParameter(
			ModelMap map, // Pour passer des parametres vers la vue => injecter automatiquement par Spring
			HttpServletRequest request, //Non requise mais parfois requise => injecter automatiquement par Spring
			// ou model est  {model} du lien, par exemple si le lien est /film-details/2 
			/// donc le parametre model de cette fonction sera "film"
			@PathVariable("model") String model,
			@PathVariable("id") long id,
			@RequestParam(value = "param1", required = false, defaultValue = "1") int param1,
			@RequestParam(value = "param2") String param2) {
		/**
		 * param1 et param2 peuvent aussi etre recuperer via le HttpServletRequest:
		 * request.getParameter("param1") comme java web
		 */
		Categorie c = new Categorie();
		c.setNom("Un nom de categorie");
		map.put("model", model);
		map.put("id", id);
		map.put("parametre1", param1);
		map.put("param2", param2);
		map.put("categorie", c);
		return "test";
	}
	
	
	
	@RequestMapping(value = "/redirection-test")
	public String redirectionTest() {
		return "redirect:/redirection-link";
	}
	
	@RequestMapping("redirection-link")
	public String redirectionCible() {
		return "redirection-page";
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