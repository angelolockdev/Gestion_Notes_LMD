package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/autre")
public class AutreController {
	@RequestMapping("/index") // =>  localhost:port/Nom_Projet/autre/index
	public String index() {
		return "/autre/index"; //Dans le dossier /WEB-INF/views/autre/index.jsp
	}
}
