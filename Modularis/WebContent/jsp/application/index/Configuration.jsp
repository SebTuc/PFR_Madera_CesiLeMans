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
		<p class="lead">Configuration</p>
    <a href="/Modularis" class="btn btn-outline-dark"><span aria-hidden="true">&larr;</span> Retour</a>
	    <div class="d-flex align-items-center justify-content-around flex-wrap">
			
				<a href="Configuration/AjoutGamme" class="p-2 col-md-4 menu-link">
					Gamme
				</a>
			
				<a href="Configuration/AjoutFamilleComposant" class="p-2 col-md-4 menu-link">
					Famille Composant
				</a>
			
				<a href="Configuration/AjoutMetier" class="p-2 col-md-4 menu-link">
					Metier
				</a>
				<a href="Configuration/AjoutUniteMesure" class="p-2 col-md-4 menu-link">
					Unite Mesure
				</a>
    	</div>
     </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>