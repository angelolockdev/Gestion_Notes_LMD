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
        <div class="">  
        	<form action="<c:url value="/saveNotes/${idetudiant}"/>" method="POST" class="needs-validation" novalidate>
	            <div class="row">
	             	<input type="hidden" name="idetudiant" value="<c:out value="${idetudiant}"/>" />
		          	<input type="hidden" name="idexamen" value="<c:out value="${idexamen}"/>" />
		          	
	              <c:forEach var="entry" items="${matieres}">
	              <input type="hidden" name="idmatieres" value="<c:out value="${entry.id}"/>" />
		              <div class="col-md-2 mb-3">
		                <label for="ref">Réf</label>
		                <input type="text" class="form-control" id="abreviation" value="<c:out value="${entry.abreviation}"/>" name="abreviation" disabled >
		                <div class="invalid-feedback">
		                  Veuillez informer matière.
		                </div>
		              </div>
		              <div class="col-md-4 mb-3">
		                <label for="lastName">Matière</label>
		                <input type="text" class="form-control" id="idmatiere" value="<c:out value="${entry.designation}"/>" name="matiere" disabled >
		                <div class="invalid-feedback">
		                  Veuillez informer matière.
		                </div>
		              </div>
		              <div class="col-md-2 mb-3">
		                <label for="firstName">Note Ex.</label> 
	                 	<input type="number" min="0" max="20"  class="form-control" id="notes" value="" name="notes" placeholder="00.0" >
		                <div class="invalid-feedback">
		                  Veuillez informer matière.
		                </div>  
		                <div class="invalid-feedback">
		                  Veuillez remplir ce champ.
		                </div>
		              </div>
		              <div class="col-md-2 mb-3">
		                <label for="lastName">Note Rep.</label>
		                <input type="number" min="0" max="20" class="form-control" id="noterepechages" value="" name="noterepechages" placeholder="00.0" <c:out value="disabled" /> />
		                <div class="invalid-feedback">
		                  Veuillez informer ce champ.
		                </div>
		              </div>
		              <div class="col-md-2 mb-3">
		                <label for="lastName">Mention</label>
	                  	<input type="text"  class="form-control" id="mention" value="" name="mention" placeholder="Mention obtenu" <c:out value="disabled" /> />
		                 
		              </div>
	              </c:forEach>
                  <div class="col-md-2 mb-3">
                  	<label for="lastName"></label>
	              	<button class="btn btn-primary btn-lg btn-block" type="submit">Valider</button>
	              </div>
	            </div>
		 
	            <hr class="mb-4">
	            
          	</form> 
	      	 <h3>Liste des étudiants de l'examen du <c:out value="Semestre ${examen.semestre}" /> : <c:out value="${examen.dateexamen}"/> </h3>
		     <div class="row">  
		        <div class="col-md-8">
				  <table class="table table-bordered">
				     <thead>
					    <tr>
					      <th scope="col">#</th>  
					      <th scope="col">Semestre </th>
				      	  <th scope="col">nom et prénom</th>	 
					    </tr>
					  </thead>
					  <tbody>
					    <c:forEach var="entry" items="${examtemp}">
					    <tr>
					      <th scope="row"><a href="<c:url value="/exam-S${entry.examen.semestre}/${entry.examendetail.idetudiant}" />"><c:out value=" ${entry.examendetail.id}"/></a></th>
					      <td><a href="<c:url value="/exam-S${entry.examen.semestre}/${entry.examendetail.idetudiant}" />"><c:out value="Semestre ${entry.examen.semestre}"/></a></td>
					      <td><a href="<c:url value="/exam-S${entry.examen.semestre}/${entry.examendetail.idetudiant}" />"><c:out value="${entry.examendetail.nom} ${entry.examendetail.prenom }"/></a></td>
					     
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