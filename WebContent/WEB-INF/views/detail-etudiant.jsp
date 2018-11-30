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
    <script src="<c:url value="/assets/js/jquery-3.3.1.min.js"/>"></script>
</head> 

<body>
	<div class="container">
      <!-- HEADER -->
      <%@include file="include/header.jsp"%> 
      <!-- HEADER -->
      <main role="main">
        <div class=""> 
	      	 <h3>Saisi de note etudiant : </h3>
		     <div class="row">  
		        <form class="needs-validation" novalidate>
		            <div class="row">
	             		<div class="col-md-6 mb-3">
			               	<label for="option">Choisir option:</label>
			                <select class="custom-select d-block w-100" id="choixOption" onchange="choseOption()" name="option">
			                  <option value="100" selected> - </option>
			                  <c:forEach var="entry" items="${options}">
			                  	<option value="<c:out value="${entry.key}" />"><c:out value="${entry.value}" /></option>
			                  </c:forEach>
			                </select>
			                <div class="invalid-feedback">
			                   Svp, veuillez informer le champ Option. 
			                </div> 
		              	</div>
		              	
		              	<div class="col-md-6 mb-3">
			               	<label for="matiere">Matieres</label>
		                	 <select id="changeable" class="custom-select d-block w-100" id="matiere" name="matiere" required>
		                  		<option value=""></option>
				                
				                <!--  <c:forEach var="entry" items="${listeMatieres}">
			                  	<option value="<c:out value="${entry.id }" />"><c:out value="${entry.designation}" /> | UE: <c:out value="${entry.abreviation}" /> | Coeff: <c:out value="${entry.coefficient}" /></option>
				                  </c:forEach>-->
		                		</div>
		                	</select> 
			                
			                <div class="invalid-feedback">
			                   Svp, veuillez informer le champ matiere. 
			                </div> 
			              	
		              	</div>
		               
		            </div>
		
		            <div class="mb-3">
		              <label for="username">Note</label>
		              <div class="input-group">
		                <div class="input-group-prepend">
		                  <span class="input-group-text">##/20</span>
		                </div>
		                <input type="text" class="form-control" id="username" placeholder="Username" required>
		                <div class="invalid-feedback" style="width: 100%;">
		                  Your username is required.
		                </div>
		              </div>
		            </div>
		            <hr class="mb-4"> 
		            <button class="btn btn-primary btn-lg btn-block" type="submit">Valider</button> 
		          </form>
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
  <script>
  	function getHtml(msg){
		var html = "";
		//"<select class=\"custom-select d-block w-100\" id=\"matiere\" name=\"matiere\" required>";
		html += "";
		
		for(var i = 0 ; i < msg.length ; i++ ){
			html += "<option value=\""+msg[i].optionM+"\">"+msg[i].designation+" | UE: "+msg[i].abreviation+" | Coeff: "+msg[i].coefficient+"</option>"; //</select>
	    } 
		return html;
	}
  	function choseOption(){
  		var option = $('#choixOption'); 
  		console.log("option = "+option.val());
  		$.ajax({
			  method: "GET",
			  url: "http://localhost:8080/EmptyProject/matiere/"+option.val(),
			  data: { }
			}).done(function( msg ) {
				console.log(getHtml(msg));
				//$('#outputlisteDiplome').html(getHtml(msg,"setIdDiplome"));
				$('#changeable').html(getHtml(msg));
				//alert( "Data Saved: " + msg); 
			   
  		}); 
  		 /*$.ajax({
             url : 'http://loclhost:8080/EmptyProject/matiere/'+option.val(),
             method : 'GET',
             data : params,
             dataType : 'html',
             success: function(resultat){
                 if(resultat.status == 'success'){
 					window.location.href = '<?php echo base_url() ?>/index.php/categorie/getDetailJob?id=' + resultat.values.annonce.id;
                 }
 				else{
 					alert(resultat.message);
 					$(element).attr('disabled', 'disabled');
 				}
             }
         });*/
  	}
  </script>
  <!-- JAVASCRIPT -->
</html>