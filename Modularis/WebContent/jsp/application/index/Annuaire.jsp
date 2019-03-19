<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Configuration</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>
	<jsp:include page="/jsp/common/navbar.jsp" />
	

    <div role="main" class="container">
		<p class="lead">Annuaire</p>
		<br>
    <a href="/Modularis" class="btn btn-outline-dark"><span aria-hidden="true">&larr;</span> Retour</a>
    <br>
    <br>
	    <div class="d-flex align-items-center justify-content-around flex-wrap">
			
				<a href="Annuaire/ListFournisseur" class="p-2 col-md-4 menu-link">
					Fournisseur
				</a>
			
				<a href="Annuaire/ListClient" class="p-2 col-md-4 menu-link">
					Client
				</a>
			
				<a href="Annuaire/ListUtilisateur" class="p-2 col-md-4 menu-link">
					Utilisateur
				</a>
			</div>
     </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>