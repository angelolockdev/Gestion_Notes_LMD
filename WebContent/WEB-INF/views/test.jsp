<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test parametre</title>
</head>
<body>
	<h1>Test parametre</h1>
	<div>
		<ul>
			<li>Model : ${model }</li>
			<li>Id : ${id }</li>
			<li>Param1 : ${parametre1 }</li>
			<li>Param2 : ${param1 }</li>
			<li>Categorie : ${categorie.nom }</li>
		</ul>
	</div>
	<p><a href="<c:url value="/redirection-test"/>">Tester redirection</a></p>
</body>
</html>