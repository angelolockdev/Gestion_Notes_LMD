<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
	<h1>Accueil... </h1>
	<p><a href="<c:url value="/categorie-details/1?param1=22&param2=Test" />">Cliquez ici pour les demos parametres</a></p>
	
	<ul>
		<c:forEach var="entry" items="${listeEtudiant}">
			<li>N° Matricule : 	<c:out value="${entry.value.numeromatricule}" /></li>
			<li>Nom : 			<c:out value="${entry.value.nom}"/></li>
			<li>Prénom : 		<c:out value="${entry.value.prenom}"/></li>
			<li>Date de naissance : <c:out value="${entry.value.datenaissance}"/></li> 
		</c:forEach>
	</ul>
</body>
</html>