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
		<p class="lead">Gestion des catalogues</p>
    <br>
	    <div class="d-flex align-items-center justify-content-around flex-wrap">
			
				<a href="Catalogue/AjoutCatalogue" class="p-2 col-md-4 menu-link">
					Ajout Catalogue
				</a>
			
				<a href="Catalogue/EditCatalogue" class="p-2 col-md-4 menu-link">
					Edition Catalogue
				</a>

			</div>
     </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>