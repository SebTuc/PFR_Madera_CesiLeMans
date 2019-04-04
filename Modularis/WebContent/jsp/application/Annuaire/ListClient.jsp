<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Liste des clients</title>
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
		<table id="Client" class="table table-edition table-striped table-bordered"cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important;text-align: center; "
				column-defs='[{"data": "id","title": "","type": "hidden","visible": false},
				{"data":"nomClient","title": "Nom"},
				{"data":"prenomClient","title": "Prenom"},
				{"data":"adresseClient","title": "Adresse"},
				{"data":"ville","title": "Ville"},
				{"data":"codePostalClient","title": "Code postal"},
				{"data":"telephoneClient","title": "Telephone"},
				{"data":"emailClient","title": "Email"}]' 
				
				data-set='[<c:forEach var="Client" items="${ListClient}">{"id":"${fn:escapeXml(Client.clientId)}",
				"nomClient":"${fn:escapeXml(Client.donneesPersonelle.nom)}",
				"prenomClient":"${fn:escapeXml(Client.donneesPersonelle.prenom)}",
				"adresseClient":"${fn:escapeXml(Client.donneesPersonelle.adresse)}",
				"ville":"${fn:escapeXml(Client.donneesPersonelle.ville)}",
				"codePostalClient":"${fn:escapeXml(Client.donneesPersonelle.codePostal)}",
				"telephoneClient":"${fn:escapeXml(Client.donneesPersonelle.telephone)}",
				"emailClient":"${fn:escapeXml(Client.donneesPersonelle.email)}"},</c:forEach>]'>

		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  
  	<div class="row justify-content-center">
		<a href="/Modularis/Annuaire/AjoutClient" class="btn btn-outline-success">Ajouter un client</a>
	</div>

</div>	
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
  	<script type="text/javascript">
		loadAltDataTable("Client",true,false);
	</script>
</body>

</html>