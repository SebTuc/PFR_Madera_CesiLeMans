<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>List Composant</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />

	<a href="/Modularis/Catalogue/ListCatalogue" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>
	<div role="main" class="container-fluid">
<c:choose>
				<c:when test="${Erreur != null }">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</c:when>
			</c:choose>

		<section class="row-section">
			<div class="container">
				<div class="row">
				<h3>Liste des projet du catalogue : <b>${Catalogue.catalogueNom}</b></h3>
				</div>				
				<div class="col-md-10 offset-md-1 row-block">
					<ul>
						<c:forEach items="${ListProjet}" var="Projet">
						<li>
							<div class="media">
								<div class="media-left align-self-center">
									<img style="height: 75px" class="rounded-circle" src="/Modularis/Photo?id=${fn:escapeXml(Projet.image.imageId)}">
								</div>
								<div class="media-body">
									<h4>${fn:escapeXml(Projet.nom)}</h4>
								</div>
								<div class="media-right align-self-end">
									<a href="DetailCatalogueProjet?idProjet=${Projet.projetId}" class="btn btn-default">En savoir plus</a>
								</div>
							</div>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</section>
	</div>

	<br> <br>

	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>