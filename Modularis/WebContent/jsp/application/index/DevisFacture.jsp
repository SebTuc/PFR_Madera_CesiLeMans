<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Catalogues</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>
	<jsp:include page="/jsp/common/navbar.jsp" />
	
<a href="/Modularis" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container">
		<div class="col-lg-12 text-center">
	        <h1>Gestion des devis/projet/facture</h1>
	        <br />
	    </div>
		<div class="row">
			<div class="col-sm-12 col-md-4">
				<button onclick="window.location.href = 'DevisFacture/ListProjet';" class="menu-link"><i class="material-icons" style="float:left">edit</i>Liste des projets</button>
			</div>
			<div class="col-sm-12 col-md-4">
				<button onclick="window.location.href = 'DevisFacture/ListDevis';" class="menu-link"><i class="material-icons" style="float:left">edit</i>Liste des devis</button>
			</div>
			<div class="col-sm-12 col-md-4">
				<button onclick="window.location.href = 'DevisFacture/ListFacture';" class="menu-link"><i class="material-icons" style="float:left">edit</i>Liste des facture</button>
			</div>
			
	     </div>
     </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>