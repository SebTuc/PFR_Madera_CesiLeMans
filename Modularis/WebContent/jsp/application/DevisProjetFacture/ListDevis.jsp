<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>List Devis</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<a href="/Modularis/DevisFacture" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container-fluid">

		<c:choose>
			<c:when test="${Erreur != null }">
				<div class="row justify-content-center">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</div>
			</c:when>
		</c:choose>

<div class="row justify-content-center">
	<h3>Liste des Devis en attente</h3>
</div>
<form id="List" method="post">
	<div class="row justify-content-center">
		<ul class="list-group list-radio" >
			<c:choose>
				<c:when test="${isEmptyList == true }">
				<li class="list-group-item">
					Aucun devis trouver !
				</li>
				</c:when>
				<c:otherwise>
					<c:forEach var="Devis" items="${ListDevis}" >
						<li class="list-group-item">
							<div class="form-check form-check-inline not-inline">
								<div class="media">
  									<div class="media-body">
										<input class="form-check-input" type="radio" name="radio" id="${fn:escapeXml(Devis.devisId)}" value="${fn:escapeXml(Devis.devisId)}">
										<label class="form-check-label label-lg" for="${fn:escapeXml(Devis.devisId)}">${fn:escapeXml(Devis.projet.nom)} |  ${fn:escapeXml(Devis.client.donneesPersonelle.prenom)}  ${fn:escapeXml(Devis.client.donneesPersonelle.nom)}</label>
									</div>
									<c:choose>
										<c:when test="${Devis.projet.image.imageId != null}">
										<img src="/Modularis/Photo?id=${Devis.projet.image.imageId }" class="ml-3 rounded-circle" alt="miniature ${fn:escapeXml(Devis.projet.nom)}" style="width: 75px;height:75px" />
										</c:when>
									</c:choose>
								</div>
							</div>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
		<br>
			<div class="row justify-content-center">
				<div class="col-md-3 col-sm-6">
					<button class="btn btn-success btn-block btn-block" name="btnVisualiser" id="btnVisualiser">Visionner</button>
				</div>
				<br>
				<div class="col-md-3 col-sm-6">
					<button type="button" class="btn btn-danger btn-block btn-block" name="btnSupprimer"  data-toggle="modal" data-target="#ModalConfirmationSuppression" id="btnSupprimer">Supprimer</button>
				</div>
				<br>
			</div>
		
</form>
</div>
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
	<jsp:include page="/jsp/common/confirmModal.jsp"/>
</body>

</html>