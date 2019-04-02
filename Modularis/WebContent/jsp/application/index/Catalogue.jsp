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
	
<a href="/Modularis/Index" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container">
		<div class="col-lg-12 text-center">
	        <h1>Catalogues</h1>
	        <br />
	    </div>
		<div class="row">
			<div class="col-sm-12 col-md-4">
				<button onclick="window.location.href = 'Catalogue/AjoutCatalogue';" class="menu-link"><i class="material-icons" style="float:left">library_add</i>Ajouter un catalogue</button>
			</div>
			<div class="col-sm-12 col-md-4">
				<button onclick="window.location.href = 'Catalogue/EditCatalogue';" class="menu-link"><i class="material-icons" style="float:left">edit</i>Editer les catalogues</button>
			</div>
			<div class="col-sm-12 col-md-4">
				<button onclick="window.location.href = 'Catalogue/ListCatalogue';" class="menu-link"><i class="material-icons" style="float:left">view_list</i>Voir les catalogues</button>
			</div>
	     </div>
     </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>