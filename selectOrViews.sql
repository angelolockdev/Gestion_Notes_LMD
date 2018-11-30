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

create or replace view examenview1 as select 
  ex.*,
  n.semestre 
FROM examen ex 
JOIN niveau n ON n.id = ex.idniveau ; 

create or replace view examenview2 as select ex.*, n.semestre 
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

create or replace view matierebyniveauview as select 
  mt.id ,
  niv.id as idniveau,
  niv.intitule ,
  niv.semestre ,
  mt.optionM,
  mt.designation,
  mt.abreviation,
  mt.coefficient,
  mt.equivalent
FROM niveau niv 
JOIN matiere mt ON mt.idniveau = niv.id;

create or replace view getresultats as select 
  calculMoyenne(ex.idetudiant, nt.idexamendetail)
FROM examendetail ex 
JOIN notes nt ON nt.idexamendetail = ex.id;


create or replace view notesexamendetailview as select 
  nt.id as idnotes,
  ex.idexamen ,
  ex.idetudiant ,
  nt.idmatiere,
  nt.note,
  nt.noterepechage,
  nt.mention  
FROM examendetail ex 
JOIN notes nt ON ex.id = nt.idexamendetail 

;

