package controller;

import java.sql.Connection;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.sql.Date; 
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UtilDb;
import helpers.DateHelper;
import metiers.EtudiantsMetier;
import metiers.ExamenDetailMetier;
import metiers.ExamenMetier;
import metiers.MatiereMetier;
import metiers.NiveauMetier;
import models.Categorie;
import models.Etudiant;
import models.ExamTemp;
import models.Examen;
import models.ExamenDetail;
import models.Matiere;
import models.Niveau; 
import utils.MentionUtils; 

@Controller
public class HomeController {
	@RequestMapping(value = {"/", "/index"} )
	public String index( ModelMap map ) { 
		Connection connection = null;
		try {
			connection = UtilDb.getConnection();
			List<Etudiant> result = EtudiantsMetier.listeEtudiants(connection); 
			@SuppressWarnings("unchecked")
			List<Etudiant> liste = result;
			System.out.println("Size Etudiants = "+liste.size());
			for(Etudiant temp : liste) {   
				System.out.println(" Etudiant : "+temp.getNumeromatricule()+" , "+temp.getNom()+" , "+temp.getPrenom() +" , "+temp.getLieunaissance()+" , "+temp.getDatenaissance());
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/etudiant-{numeromatricule}/{id}", method = RequestMethod.GET)
	public String getEtudiant( ModelMap map ,
			@PathVariable("numeromatricule") String numeromatricule,
			@PathVariable("id") long id) { 
		Connection connection = null;
		try {
			connection = UtilDb.getConnection();
			Map<String, Object> result = EtudiantsMetier.getEtutiantById(connection, id);
			Map<String, Object> matieres = MatiereMetier.listeMatiere(connection); 
			Etudiant etudiant = (Etudiant) result.get("etudiant");
			List<Matiere> listeMatiere = (List<Matiere>) matieres.get("listeMatieres");
			for(Matiere temp : listeMatiere) {
				System.out.println("GEGE = "+temp.getDesignation()+" "+temp.getAbreviation());
			}
			map.put("options", MentionUtils.getOptions());
			map.put("etudiant", etudiant); 
			map.put("listeMatieres", listeMatiere); 
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "error");
			map.put("message", e.getMessage());
		} finally {
			closeConnection(connection);
		}
		
		return "detail-etudiant"; // Nom du fichier .jsp dans /WEB-INF/views/
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
	@RequestMapping(value = "/matiere/{optionM}", method = RequestMethod.GET)
	public @ResponseBody List<Matiere> matieres(ModelMap map ,
			@PathVariable("optionM") Integer optionM) {
		Connection connection = null;
		List<Matiere> result = new ArrayList<Matiere>();
		try {
			connection = UtilDb.getConnection();
			result = MatiereMetier.getOption(connection, optionM);
			for(Matiere m : result) { 
				System.out.println("m = "+ m.getId()+", "+m.getDesignation()+", "+m.getAbreviation()+", "+m.getCoefficient()+", "+m.getEquivalent());
			}
		}  catch (Exception e) {
			e.printStackTrace();
			map.put("status", "error");
			map.put("message", e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	@RequestMapping(value = {"/newExam", "/newExam/error-{type}"})
	public String nouvelleExam(Model model,
		@PathVariable(value = "type") Optional<Integer> type) {
		Connection connection = null;
		List<Examen> result = new ArrayList<Examen>();
		List<Niveau> niveaux = new ArrayList<Niveau>();
		try {
			connection = UtilDb.getConnection();
			result = ExamenMetier.listeExamen2(connection, 15, 1);
			niveaux = NiveauMetier.listeNiveau(connection) ;
			if(type.isPresent()) {
				model.addAttribute("error", type.get());
			}
			
			model.addAttribute("listeExamen", result);
			model.addAttribute("niveaux", niveaux);
		}  catch (Exception e) {
			e.printStackTrace();
			model.addAttribute( "status", "error");
			model.addAttribute( "message", e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return "newExam";
	}
	@RequestMapping(value = "/saveExam", method = RequestMethod.POST)
	public String saveExam(Model model,
		@RequestParam(value = "idniveau") long idniveau,
		@RequestParam(value = "dateexamen") Date dateexamen ,
		HttpServletRequest request) {
		Connection connection = null;  
		Niveau niv = null;
		Date dat = null;
		try {
			connection = UtilDb.getConnection();
			niv = NiveauMetier.listeNiveauBy(connection, idniveau);
			
			ExamenMetier.insertExamen(connection, idniveau, dateexamen);  
		}  catch (Exception e) {
			e.printStackTrace();
			model.addAttribute( "status", "error");
			model.addAttribute( "message", e.getMessage());
		} finally {
			closeConnection(connection);
		}
		if(niv==null) {
			return "redirect:/newExam/error-1";
		} 
		return "redirect:/newExam";
	}
	
	@RequestMapping(value = "/saveexamDetail-S{semestre}/{id}", method = RequestMethod.GET)
	public String saveExam(Model model,
		@PathVariable(value = "semestre") String semestre,
		@PathVariable(value = "id") String id,
		HttpServletRequest request) {
		Connection connection = null;  
		List<Examen> exam = null;
		List<ExamenDetail> examDet = null;
		List<Etudiant> etudiants = null;
		List<ExamTemp> examtemp = new ArrayList<ExamTemp>();
		Examen examen = null;
		try {
			connection = UtilDb.getConnection();
			exam = ExamenMetier.listeExamen(connection, Long.valueOf(semestre), 15, 1);
			etudiants = EtudiantsMetier.listeEtudiants(connection);
			examDet = ExamenDetailMetier.listeExamenDetail(connection, 15, 1);
			examen = ExamenMetier.listeExamenBy(connection, Long.valueOf(id));
			//ExamenMetier.insertExamen(connection, idniveau, dateexamen);   
			for(int i=0;i<exam.size();i++) {
				examtemp.add(new ExamTemp(exam.get(i), examDet.get(i)));
			}
			model.addAttribute( "etudiants", etudiants);
			model.addAttribute( "examen", examen);
			model.addAttribute( "semestre", semestre);
			model.addAttribute( "examtemp", examtemp);
			
		}  catch (Exception e) {
			e.printStackTrace();
			model.addAttribute( "status", "error");
			e.printStackTrace();
			model.addAttribute( "message", e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return "newExamDetail";
	} 
	@RequestMapping(value = "/saveEtudiantExamDetail", method = RequestMethod.POST)
	public String saveEtudiantExamDetail(Model model,
		@RequestParam(value = "idexamen") String idexamen,
		@RequestParam(value = "idetudiant") String idetudiant ,
		HttpServletRequest request) {
		Connection connection = null;  
		System.out.println("Test1 = "+idexamen);
		System.out.println("Test2 = "+idetudiant);
		 Examen examen = null;
		try {
			connection = UtilDb.getConnection();
			examen = ExamenMetier.listeExamenBy(connection, Long.parseLong(idexamen));
			
			System.out.println("exam = "+examen.getDateexamen());
			ExamenDetailMetier.insertExamenDetail(connection, Long.parseLong(idexamen), Long.parseLong(idetudiant));   
		}  catch (Exception e) {
			e.printStackTrace();
			model.addAttribute( "status", "error");
			model.addAttribute( "message", e.getMessage());
		} finally {
			closeConnection(connection);
		} 
		return "redirect:/saveexamDetail-S"+examen.getSemestre()+"/"+idexamen;
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