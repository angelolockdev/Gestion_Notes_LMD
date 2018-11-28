/*SELECT*/
/*Selection : matiereEquivalenteBy(idniveau, referencematiere, id)*/	
	select * from matiere where  referencematiere IS NOT NULL 
	and referencematiere 
	IN(
		select id as referencematiere from matiere 
		where referencematiere IS NULL and idniveau = 5     
	); 	
