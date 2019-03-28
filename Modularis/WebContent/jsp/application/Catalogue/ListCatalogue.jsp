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

	<a href="/Modularis/Catalogue" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>
	<div role="main" class="container-fluid">


		<section class="row-section">
			<div class="container">
				<div class="row">
				<h3>Liste des catalogues</h3>
				</div>				
				<div class="col-md-10 offset-md-1 row-block">
					<ul>
						<c:choose>
							<c:when test="${isEmptyList == true }">
								<li>
									<div class="media">
										<div class="media-body">
											<h4>Aucun catalogue</h4>
											<p>Aucun catalogue n'existe ou ne contient de projet.</p>
										</div>
										
									</div>
								</li>
							</c:when>
							<c:otherwise>
							<c:forEach items="${ListCatalogue}" var="Catalogue">
							<li>
								<div class="media">
									<div class="media-body">
										<h4>${fn:escapeXml(Catalogue.catalogueNom)}</h4>
										<p>Ann&eacute;e ${fn:escapeXml(Catalogue.annee)}</p>
									</div>
									<div class="media-right align-self-end">
										<a href="ListCatalogueProjet?idCatalogue=${fn:escapeXml(Catalogue.catalogueId)}" class="btn btn-default">En savoir plus</a>
									</div>
								</div>
							</li>
							</c:forEach>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</section>
	</div>

	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>