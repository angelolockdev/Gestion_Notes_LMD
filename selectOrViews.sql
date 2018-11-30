/*SELECT*/
/*Selection : matiereEquivalenteBy(idniveau, referencematiere, id)*/	
	select * from matiere where  referencematiere IS NOT NULL 
	and referencematiere 
	IN(
		select id as referencematiere from matiere 
		where referencematiere IS NULL and idniveau = 5     
	); 	

	
/*VIEWS*/
create or replace view etudiantinscritview as select 
  e.*,
  u.filiere
FROM etudiant e 
JOIN universite u ON u.id = e.iduniversite; 

create or replace view matiereniveauview as select 
  m.*,
  n.intitule,
  n.semestre 
FROM matiere m 
JOIN niveau n ON m.idniveau = n.id; 

create or replace view examenview as select 
  ex.*,
  n.semestre 
FROM examen ex 
JOIN niveau n ON n.id = ex.idniveau
JOIN examendetail exdet 
ON exdet.idexamen = ex.id; 

create or replace view examendetailetudiantview as select 
  ex.*,
  et.nom,
  et.prenom,
  et.lieunaissance,
  et.datenaissance
FROM examendetail ex 
JOIN etudiant et ON et.id = ex.idetudiant; 