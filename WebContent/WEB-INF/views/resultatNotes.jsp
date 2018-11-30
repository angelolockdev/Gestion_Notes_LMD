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
      	<h1 class="text-center">RELEVE DE NOTES ET RESULTATS</h1>
        <div class=""> 
        	<div class="row">  
		        <div class="col-md-5">
		        	<table>
		        		<tr>
		        			<td>Nom</td>
		        			<td><c:out value="${etudiant.nom}"></c:out></td>
		        		</tr>
		        		<tr>
		        			<td>Prénom</td>
		        			<td><c:out value="${etudiant.prenom}"></c:out></td>
		        		</tr>  
		        		<tr>
		        			<td>Né(e) le</td>
		        			<td><c:out value="${etudiant.datenaissance} à ${etudiant.lieunaissance}"></c:out></td>
		        		</tr>    
		        		<tr>
		        			<td>N° d'inscription</td>
		        			<td><c:out value="${numeromatricule}"></c:out></td>
		        		</tr>
		        		<tr>
		        			<td>Inscrit(e) en</td>
		        			<td><c:out value="${filiere}"></c:out></td>
		        		</tr>  
		        	</table>
		        	a obtenu les notes suivantes:
		        </div>
	        </div>
          	 <hr/>
	      	 <h3> <c:out value="Semestre ${examen.semestre}" /> : <c:out value="${examen.dateexamen}"/> </h3>
		     <div class="row">  
		        <div class="col-md-8">
				  <table class="table table-bordered">
				     <thead>
					    <tr>
					      <th scope="col">UE</th>  
					      <th scope="col">Intitulé </th>
				      	  <th scope="col">Crédits</th>	 
				      	  <th scope="col">Note/20</th>
				      	  <th scope="col">Mention</th>	  	
					    </tr>
					  </thead>
					  <tbody>
					    <c:forEach var="entry" items="${resultat}">
					    <tr>
					      <th scope="row"><a href="#" ><c:out value=" ${entry.matiere.abreviation}"/></a></th>
					      <td> <c:out value="${entry.matiere.designation}"/> </td>
					      <td> <c:out value="${entry.matiere.coefficient}"/> </td>
					      <td> <c:out value="${entry.resultatnotes.note}"/> </td>
					      <td> <c:out value="${entry.resultatnotes.mention}"/> </td>  
					   
					    </tr> 
					    </c:forEach>
				    	<tr>
					      <th scope="col"> </th>  
					      <th scope="col"><c:out value="${niveau.semestre}" /></th>
				      	  <th scope="col">30</th>	 
				      	  <th scope="col"><c:out value="${moyenne}"/></th>
				      	  <th scope="col"><c:out value="${mention}"/></th>	  
					    </tr>
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