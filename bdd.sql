drop database lmddb;

create table universite(
	id bigint AUTO_INCREMENT PRIMARY KEY,
	filiere varchar(100)
)Engine=InnoDB;

create table etudiant(
	id bigint AUTO_INCREMENT PRIMARY KEY,
	iduniversite bigint,
	nom varchar(50),
	prenom varchar(50),
	lieunaissance varchar(50),
	datenaissance date,
	foreign key (iduniversite) references universite(id))Engine=InnoDB;

create table niveau(
	id bigint AUTO_INCREMENT PRIMARY KEY,
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
	equivalent bigint null,
	foreign key (equivalent) references matiere(id) ,
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
	


 

			 
			
			
			
	
	
	