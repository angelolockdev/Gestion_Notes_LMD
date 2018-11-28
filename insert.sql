insert into etudiant (numeromatricule, nom, prenom, datenaissance) 
	values	("ETU000200", "Angelo", "lock", "2000-06-12"),
			("ETU000222", "Tef", "Jean", "1994-03-20"),
			("ETU000232", "Rakoto", "lock", "1994-05-21");
			

insert into niveau (filiere, intitule, semestre) 
	values	("Informatique", "Licence", 1),
			("Informatique", "Licence", 2),
			("Informatique", "Licence", 3),
			("Informatique", "Licence", 4),
			("Informatique", "Licence", 5),
			("Informatique", "Licence", 6),
			("Informatique", "Master", 7),
			("Informatique", "Master", 8),
			("Informatique", "Master", 9),
			("Informatique", "Master", 10);

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


/*Notes*/
insert into notes (idetudiant, idmatiere , note, noterepechage, anneedeb, anneefin, mention) 
	values	(1, 15, 10.50, 0, "2017", "2018", 10),    
			(1, 16, 8.50, 0, "2017", "2018", null),
			(1, 17, 12, 0, "2017", "2018", 10),
			(1, 18, 11.50, 0, "2017", "2018", 10),
			(1, 19, 9.75, 0, "2017", "2018", null),
			(1, 20, 6.50, 0, "2017", "2018", null);
			
			
			
			
			