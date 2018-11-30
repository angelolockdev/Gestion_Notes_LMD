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
        	<form action="<c:url value="/saveExam" />" method="POST" class="needs-validation" novalidate>
	            <div class="row">
	            <c:if test="${error == 1}">
	           		<span class="text-red">Niveau d'etude introuvable.Recommencez svp!</span>
	           	</c:if>
	              <div class="col-md-5 mb-3">
	                <label for="firstName">Semestre</label> 
	                <select class="custom-select d-block w-100" id="idniveau"  name="idniveau">
	                  	<c:forEach var="entry" items="${niveaux}">
		                  	<option value="<c:out value="${entry.id}" />"><c:out value="Semestre ${entry.semestre}" /></option>
	                  	</c:forEach> 
                	</select> 
	                <div class="invalid-feedback">
	                  Veuillez remplir ce champ.
	                </div>
	              </div>
	              <div class="col-md-5 mb-3">
	                <label for="lastName">Session</label>
	                <input type="date" class="form-control" id="dateexamen" name="dateexamen"   required>
	                <div class="invalid-feedback">
	                  Veuillez informer une date d'examen.
	                </div>
	              </div>
                  <div class="col-md-2 mb-3">
                  	<label for="lastName"></label>
	              	<button class="btn btn-primary btn-lg btn-block" type="submit">Créer</button>
	              </div>
	            </div>
		 
	            <hr class="mb-4">
	            
          	</form> 
	      	 <h3>Liste des Examens</h3>
		     <div class="row">  
		        <div class="col-md-8">
				  <table class="table table-bordered">
				     <thead>
					    <tr>
					      <th scope="col">#</th>  
					      <th scope="col">Semestre </th>
				      	  <th scope="col">Session </th>	 
					    </tr>
					  </thead>
					  <tbody>
					    <c:forEach var="entry" items="${listeExamen}">
					    <tr>
					      <th scope="row"><a href="<c:url value="saveexamDetail-S${entry.semestre}/${entry.id}" />"><c:out value=" ${entry.id}"/></a></th>
					      <td><a href="<c:url value="saveexamDetail-S${entry.semestre}/${entry.id}" />"><c:out value="Semestre ${entry.semestre}"/></a></td>
					      <td><a href="<c:url value="saveexamDetail-S${entry.semestre}/${entry.id}" />"><c:out value="${entry.dateexamen}"/></a></td>
					     
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