package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import models.Categorie;

@Controller
public class HomeController {
	@RequestMapping(value = {"/", "/index"} )
	public String index() {
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
}