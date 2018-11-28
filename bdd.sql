drop database lmddb;
 

create table etudiant(
	id bigint AUTO_INCREMENT PRIMARY KEY,
	numeromatricule varchar(50),
	nom varchar(50),
	prenom varchar(50),
	datenaissance date
)Engine=InnoDB;

create table niveau(
	id bigint AUTO_INCREMENT PRIMARY KEY,
	filiere varchar(50),
	intitule varchar(50),
	semestre integer
)Engine=InnoDB;

create table matiere(
	id bigint AUTO_INCREMENT PRIMARY KEY,
	idniveau bigint,
	optionM integer,
	designation varchar(50),
	abreviation varchar(10),
	coefficient integer, 
	foreign key (idniveau) references niveau(id))Engine=InnoDB;
	
create table notes(
	id bigint AUTO_INCREMENT PRIMARY KEY,
	idetudiant bigint,
	idmatiere bigint,
	note double precision,
	noterepechage double precision,
	anneedeb varchar(4),
	anneefin varchar(4),
	mention integer null,
	foreign key (idetudiant) references etudiant(id),
	foreign key (idmatiere) references matiere(id))Engine=InnoDB;
	
/*	
create table resultatexamen(
	id bigint AUTO_INCREMENT PRIMARY KEY,
	idnotes bigint,
	statusadmis integer,
	moyenne double precision,
	mention integer null, 
	foreign key (idnotes) references notes(id))Engine=InnoDB;
*/	
	
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
insert into matiere2 (idniveau, optionM ,designation, abreviation, coefficient, referencematiere) 
	values	(5, 50, "IHM", "ORG301", 5, null),
			(5, 50, "SIG", "ORG303", 3, 1), 
			(5, 50, "Analyse de donnée", "ORG302", null),
			(5, 50, "Equation differentiel", "ORG303", 3, 3),
			(5, 50, "Algo", "ORG303", 3, 3);
/*	

	
	select * from matiere2 where  referencematiere IS NOT NULL 
	and referencematiere 
	IN(
		select id as referencematiere from matiere2 
		where referencematiere IS NULL and idniveau = 5     
	);
*/	

insert into matiere (idniveau, optionM ,designation, abreviation, coefficient) 
	values	(5, 50, "Gestion d'entreprise", "ORG301", 5),    
			(5, 50, "Gestion de projet", "ORG302", 4),
			(5, 50, "Anglais pour les affaires", "ORG303", 3),
			
			(5, 10, "Architecture logicielle", "INF301", 6),
			(5, 10, "Développement pour mobiles", "INF304", 6),
			(5, 10, "Conception en modèle orienté objet", "INF307", 6), 
			
			(5, 20, "Multimédia", "INF303", 6),
			(5, 20, "Introduction du cloud", "INF306", 6),
			(5, 20, "Web avancé", "INF309", 6),
			
			(5, 30, "Technologies d'accès aux réseaux", "INF302", 6),
			(5, 30, "Gestion de domaines, clustering", "INF305", 6),
			(5, 30, "Conception en modèle relationnel", "INF308", 6);
		
			
/*Notes  */
insert into notes (idetudiant, idmatiere , note, anneedeb, anneefin, mention) 
	values	(1, 1, 10.50, "2017", "2018", 10),    
			(1, 2, 8.50, "2017", "2018", null),
			(1, 3, 12, "2017", "2018", 10),
			(1, 4, 11.50, "2017", "2018", 10),
			(1, 5, 9.75, "2017", "2018", null),
			(1, 6, 6.50, "2017", "2018", null);
			
/*Résultat examen  ==> VUE de résultat*/
			 
create view resultatexamenview as Select 
	ROW_NUMBER(),
	CONCAT(e.nom, " ", e.prenom),
	
	from notes n
	join etudiant e
	on e.id = n.idetudiant;
	
	
	
/*FUNCTION*/
/*calculMoyenne(idEtudiant bigint )*/
DELIMITER $$
CREATE FUNCTION calculMoyenne(idEtudiant bigint ) RETURNS double precision
BEGIN
	declare moyenne double precision;
	select sum(nt.note*getCoeff(nt.idmatiere))/sum(getCoeff(nt.idmatiere)) into moyenne 
		from matiere m
		join notes nt
		where nt.idetudiant = idEtudiant;
	return moyenne;
END$$
DELIMITER ;	

/*getCoeff (idmatiere bigint)*/ 
DELIMITER $$
CREATE FUNCTION getCoeff (idmatiere bigint) RETURNS integer
BEGIN 
  DECLARE ret integer;
  select coefficient into ret
		from matiere 
		where id = idmatiere LIMIT 1 ;
  RETURN ret;
END$$
DELIMITER ;	
			 
			
			
			
	
	
	