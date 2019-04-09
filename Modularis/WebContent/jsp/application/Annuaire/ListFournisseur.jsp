<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>list Fournisseur</title>
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
		<table id="Fournisseur" class="table table-edition table-striped table-bordered"cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important;text-align: center; "
				column-defs='[{"data": "id","title": "","type": "hidden","visible": false},{"data":"nomFournisseur","title": "Nom"},{"data":"adresseFournisseur","title": "Adresse"},{"data":"codePostalFournisseur","title": "Code postal"},{"data":"telephoneFournisseur","title": "Telephone"},{"data":"emailFournisseur","title": "Email"}]' 
				data-set='[<c:forEach var="Fournisseur" items="${ListFournisseur}">{"id":"${fn:escapeXml(Fournisseur.fournisseurId)}","nomFournisseur":"${fn:escapeXml(Fournisseur.nom)}","adresseFournisseur":"${fn:escapeXml(Fournisseur.adresse)}","codePostalFournisseur":"${fn:escapeXml(Fournisseur.codePostal)}","telephoneFournisseur":"${fn:escapeXml(Fournisseur.telephone)}","emailFournisseur":"${fn:escapeXml(Fournisseur.email)}"},</c:forEach>]'>

		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  
  	<div class="row justify-content-center">
		<a href="/Modularis/Annuaire/AjoutFournisseur" class="btn btn-outline-success">Ajouter un fournisseur</a>
	</div>

</div>	
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
    	<script type="text/javascript">
		loadAltDataTable("Fournisseur",true,false);
	</script>
</body>

</html>