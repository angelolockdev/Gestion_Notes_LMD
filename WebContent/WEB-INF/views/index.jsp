<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html> 
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico"> 
    <title>Notes-LMD</title> 
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/assets/css/bootstrap.min.css" />" rel="stylesheet"> 
    <!-- Custom styles for this template -->
    <link href="<c:url value="/assets/css/navbar.css" />" rel="stylesheet">
</head> 

<body>
	<div class="container">
      <!-- HEADER -->
      <%@include file="include/header.jsp"%> 
      <!-- HEADER -->
      <main role="main">
        <div class=""> 
	      	 <h3>Liste des étudiants</h3>
		     <div class="row">  
		        <div class="col-md-8">
				  <table class="table table-bordered">
				     <thead>
					    <tr>
					      <th scope="col">N° d'inscription</th>  
					      <th scope="col">Nom </th>
				      	  <th scope="col">Prénom(s) </th>	
					      <th scope="col">Né(e) le</th> 
					      <th scope="col">Inscrit(e) en </th>
					    </tr>
					  </thead>
					  <tbody>
					    <c:forEach var="entry" items="${listeEtudiant}">
					    <tr>
					      <th scope="row"><a href="<c:url value="etudiant-${entry.numeromatricule}/${entry.id}" />"><c:out value="${entry.numeromatricule}"/></a></th>
					      <td><a href="<c:url value="etudiant-${entry.numeromatricule}/${entry.id}" />"><c:out value="${entry.nom}"/></a></td>
					      <td><a href="<c:url value="etudiant-${entry.numeromatricule}/${entry.id}" />"><c:out value="${entry.prenom}"/></a></td>
					      <td><a href="<c:url value="etudiant-${entry.numeromatricule}/${entry.id}" />"><c:out value="${entry.datenaissance}"/> &agrave; <c:out value="${entry.lieunaissance}"/></a></td> 
					      <td><c:out value="${entry.filiere}"/></td> 
					    </tr> 
					    </c:forEach>
					  </tbody>
				  </table>
				</div>
     		 </div>
		  <!-- FOOTER -->
	      <%@include file="include/footer.jsp"%> 
	      <!-- FOOTER -->
        </div>
      </main>
    </div> 
  </body>
  <!-- JAVASCRIPT -->
  <%@include file="include/js.jsp"%> 
  <!-- JAVASCRIPT -->
</html>