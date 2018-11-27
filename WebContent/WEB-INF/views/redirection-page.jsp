<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test redirection</title>
</head>
<body>
	<h1>Test redirection</h1>
	<p><a href="<c:url value="/autre/index"/>">Vers un autre controleur</a></p>
</body>
</html>