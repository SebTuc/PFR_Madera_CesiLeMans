<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>list utilisateur</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>
<jsp:include page="/jsp/common/navbar.jsp" />

<a href="/Modularis/Annuaire" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container">

<br>
<br>
<div class="card card-edition">

	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="Edition" class="table table-edition table-striped table-bordered"cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important;text-align: center; "
				column-defs='[{"data": "id","title": "","type": "hidden","visible": false},
				{"data":"nomUtilisateur","title": "Nom"},
				{"data":"prenomUtilisateur","title": "Prenom"},
				{"data":"adresseUtilisateur","title": "Adresse"},
				{"data":"codePostalUtilisateur","title": "Code postal"},
				{"data":"telephoneUtilisateur","title": "Telephone"},
				{"data":"emailUtilisateur","title": "Email"},
				{"data":"metierUtilisateur","type": "disabled","title": "Metier"},
				{"data":"loginUtilisateur","title": "Login"},
				{"data":"passwordUtilisateur","type": "password","title": "Password"}]' 
				
				data-set='[<c:forEach var="Utilisateur" items="${ListUtilisateur}">{"id":"${fn:escapeXml(Utilisateur.utilisateurId)}",
				"nomUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.nom)}",
				"prenomUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.prenom)}",
				"adresseUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.adresse)}",
				"codePostalUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.codePostal)}",
				"telephoneUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.telephone)}",
				"emailUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.email)}",
				"metierUtilisateur":"${fn:escapeXml(Utilisateur.metier.nom)}",
				"loginUtilisateur":"${fn:escapeXml(Utilisateur.login)}",
				"passwordUtilisateur":"${fn:escapeXml(Utilisateur.password)}"},</c:forEach>]'>

		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  
  	<div class="row justify-content-center">
		<a href="/Modularis/Annuaire/AjoutUtilisateur" class="btn btn-outline-success">Ajouter un utilisateur</a>
	</div>

</div>	
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>