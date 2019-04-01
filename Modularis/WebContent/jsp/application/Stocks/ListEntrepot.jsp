<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des stocks</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />
</head>
<body>
	<!-- LISTE DES ENTREPOTS -->
	<jsp:include page="/jsp/common/navbar.jsp" />
	<a href="/Modularis" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
	 <br />
	<div class="text-center">
        	<h1>Gestion des stocks</h1>
        	<h4>Liste des entrepôts</h4>
      	</div>
    <div role="main" class="container">
   		 <br />
   		 <!--<div class="alert alert-danger" role="alert" style="text-align:center">Attention, le stock de certains composants est bientôt vide !</div>-->
	     <div class="row">
		     <c:forEach var="Entrepot" items="${ListEntrepot}">
				<div class="col-sm-6 col-md-4">
						<button onclick="window.location.href = 'AjoutStock?id=${fn:escapeXml(Entrepot.entrepotId)}';" data-badge="!" class="btn-modularis btn-modularis-hover badgeentrepot"><i class="material-icons md-48">home</i> <br/>${fn:escapeXml(Entrepot.lieux)}</button>
				</div>
			</c:forEach>
    	</div>
     </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>