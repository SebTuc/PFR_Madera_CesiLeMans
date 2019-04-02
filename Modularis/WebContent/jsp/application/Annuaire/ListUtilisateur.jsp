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
<c:choose>
	<c:when test="${Erreur != null}">
		<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
	</c:when>
</c:choose>
<div class="card card-edition">

	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="EditionUtilisateur" class="table table-edition table-striped table-bordered" cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important;text-align: center; "
				column-defs='[{"data": "id","title": "","type": "hidden","visible": false},
				{"data":"nomUtilisateur","title": "Nom"},
				{"data":"prenomUtilisateur","title": "Prenom"},
				{"data":"adresseUtilisateur","title": "Adresse"},
				{"data":"villeUtilisateur","title": "Ville"},
				{"data":"codePostalUtilisateur","title": "Code postal"},
				{"data":"telephoneUtilisateur","title": "Telephone"},
				{"data":"emailUtilisateur","title": "Email"},
				{"data":"metierUtilisateur","type": "disabled","title": "Metier"},
				{"data":"entrepotUtilisateur","type": "disabled","title": "Entrepot"}]' 
				
				data-set='[<c:forEach var="Utilisateur" items="${ListUtilisateur}">{"id":"${fn:escapeXml(Utilisateur.utilisateurId)}",
				"nomUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.nom)}",
				"prenomUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.prenom)}",
				"adresseUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.adresse)}",
				"villeUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.ville)}",
				"codePostalUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.codePostal)}",
				"telephoneUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.telephone)}",
				"emailUtilisateur":"${fn:escapeXml(Utilisateur.donneesPersonelle.email)}",
				"metierUtilisateur":"${fn:escapeXml(Utilisateur.metier.nom)}",
				"entrepotUtilisateur":"${fn:escapeXml(Utilisateur.entrepot.lieux)}"},</c:forEach>]'>

		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  
  	<div class="row justify-content-center">
		<a href="/Modularis/Annuaire/AjoutUtilisateur" class="btn btn-outline-success">Ajouter un utilisateur</a>
		<a href="/Modularis/Annuaire/EditUtilisateur" id="editUser" class="btn btn-outline-success">Editer un utilisateur</a>
	</div>



</div>	
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
  <script type="text/javascript">
	$("#editUser").hide();
	$(document).ready(function () {
		loadAltDataTable("EditionUtilisateur",false);
		$('#EditionUtilisateur tbody').on('click', 'tr', function () {
			$("#editUser").show();
			var oTable = $('#EditionUtilisateur').DataTable();
			var pos = oTable.row(this).index();
			var row = oTable.row(pos).data()
			$("#editUser").attr("href", "/Modularis/Annuaire/EditUtilisateur?id="+row.id);
		});
	});

	</script>
</body>

</html>