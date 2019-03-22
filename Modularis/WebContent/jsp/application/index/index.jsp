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
   		 <br />
		 <div class="col-lg-12 text-center">
        	<h1>Bienvenue sur l'application Modularis !</h1>
        	<br />
      	</div>
	     <div class="row">
			<div class="col-sm-6 col-md-4">
				<button onclick="window.location.href = 'Catalogue';" class="btn-modularis btn-modularis-hover"><i class="material-icons md-48">shopping_cart</i> <br/>Catalogue (Visionner / Editer)</button>
			</div>
			<div class="col-sm-6 col-md-4">
				<button onclick="window.location.href = 'Devis';" class="btn-modularis btn-modularis-hover"><i class="material-icons md-48">library_books</i> <br/>Devis / Facture</button>
			</div>
			<div class="col-sm-6 col-md-4">
				<button onclick="window.location.href = 'Annuaire';" class="btn-modularis btn-modularis-hover"><i class="material-icons md-48">people</i> <br/>Annuaire</button>
			</div>
			<div class="col-sm-6 col-md-4">
				<button onclick="window.location.href = 'Gestion Stock';" class="btn-modularis btn-modularis-hover"><i class="material-icons md-48">search</i> <br/>Gestion Stock</button>
			</div>
			<div class="col-sm-6 col-md-4">
				<button onclick="window.location.href = 'Configuration';" class="btn-modularis btn-modularis-hover"><i class="material-icons md-48">settings</i> <br/> Configuration</button>
			</div>
    	</div>
     </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>