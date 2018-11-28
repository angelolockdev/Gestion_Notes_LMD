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