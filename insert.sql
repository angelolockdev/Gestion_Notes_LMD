insert into universite (filiere) 
	values	("Informatique");

insert into etudiant (iduniversite, nom, prenom, lieunaissance, datenaissance) 
	values	(1, "Angelo", "lock", "Tulear", "2000-06-12"),
			(1, "Tef", "Jean", "Fianarantsoa", "1994-03-20"),
			(1, "Rakoto", "lock", "Mahajanga","1994-05-21");
			

insert into niveau (intitule, semestre) 
	values	("Licence", 1),
			("Licence", 2),
			("Licence", 3),
			("Licence", 4),
			("Licence", 5),
			("Licence", 6),
			("Master", 7),
			("Master", 8),
			("Master", 9),
			("Master", 10);

/*Matiere communs de: optionM =  (idniveau*10) */
insert into matiere (idniveau, optionM ,designation, abreviation, coefficient, equivalent) 
	values	(3, 100, "Base de Gestion", "ORG201", 3, null),
			(3, 10, "Programmation Objet", "INF201", 6, null), 
			(3, 10, "Programmation Système", "INF203", 4, null), 
			(3, 20, "Base de donneé Objet", "INF202", 4, null),
			(3, 30, "Réseaux", "INF208", 4, null),
			(3, 20, "Html Web", "INF104", 3, null),
			
			(4, 100, "Interfaces Homme Machine", "INF501", 3, null),
			(4, 100, "Système d'Information", "INF502", 3, 7), 
			(4, 100, "Système d'Information Géographique", "INF503", 3, 7), 
			
			(4, 100, "Géometrie", "MATH501", 3, null), 
			(4, 100, "Equation Différentiel", "MATH502", 3, 10),  
			(4, 100, "Optimisation", "MATH503", 3, 10),
			(4, 100, "Analyse de Donnée", "INF202", 3, 10),
			(4, 10, "Algorythme", "INF504", 5, null),
			
			(5, 100, "Gestion d'entreprise", "ORG301", 5, null),    
			(5, 100, "Gestion de projet", "ORG302", 4, null),
			(5, 100, "Anglais pour les affaires", "ORG303", 3, null),
			
			(5, 10, "Architecture logicielle", "INF301", 6, null),
			(5, 10, "Développement pour mobiles", "INF304", 6, null),
			(5, 10, "Conception en modèle orienté objet", "INF307", 6, null), 
			
			(5, 20, "Multimédia", "INF303", 6, null),
			(5, 20, "Introduction du cloud", "INF306", 6, null),
			(5, 20, "Web avancé", "INF309", 6, null),
			
			(5, 30, "Technologies d'accès aux réseaux", "INF302", 6, null),
			(5, 30, "Gestion de domaines, clustering", "INF305", 6, null),
			(5, 30, "Conception en modèle relationnel", "INF308", 6, null);


/*Examen*/
insert into examen (idniveau, dateexamen) 
	values (5, "2018-02-15");

/*Notes*/
insert into notes (idexamen, idetudiant, idmatiere , note, noterepechage, anneedeb, mention) 
	values	(1, 1, 15, 10.50, 0, "2017", 10),    
			(1, 1, 16, 8.50, 0, "2017", null),
			(1, 1, 17, 12, 0, "2017", 10),
			(1, 1, 18, 11.50, 0, "2017", 10),
			(1, 1, 19, 9.75, 0, "2017", null),
			(1, 1, 20, 6.50, 0, "2017", null);
			
			
			
			
			