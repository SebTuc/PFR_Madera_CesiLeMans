<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Accueil</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>

	<!-- INDEX -->
	
	
	<jsp:include page="/jsp/common/navbar.jsp" />
	

    <div role="main" class="container">
		<p class="lead">Bienvenue sur l'application Modularis</p>
    
	    <div class="d-flex align-items-center justify-content-around flex-wrap">
			
				<a href="Catalogue" class="p-2 col-md-4 menu-link">
					Catalogue (Visionner / Editer)
				</a>
			
				<a href="Configuration" class="p-2 col-md-4 menu-link">
					Configuration
				</a>
			
				<a href="Devis" class="p-2 col-md-4 menu-link">
					Devis / Facture
				</a>
				
				<a href="GestionUtilisateur" class="p-2 col-md-4 menu-link">
					Gestion Utilisateur / Client / Fournisseur
				</a>
				<a href="Gestion Stock" class="p-2 col-md-4 menu-link">
					Gestion Stock
				</a>
    	</div>
     </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>