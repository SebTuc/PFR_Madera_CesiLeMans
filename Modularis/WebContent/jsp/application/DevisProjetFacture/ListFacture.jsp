<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>List facture</title>
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
	<h3>Liste des factures en attente</h3>
</div>
<form id="List" method="post">
	<div class="row justify-content-center">
		<ul class="list-group list-radio" >
			<c:choose>
				<c:when test="${isEmptyList == true }">
				<li class="list-group-item">
					Aucune facture trouv&eacute; !
				</li>
				</c:when>
				<c:otherwise>
					<c:forEach var="Facture" items="${ListFacture}" >
						<li class="list-group-item">
							<div class="form-check form-check-inline not-inline">
								<div class="media">
  									<div class="media-body">
										<input class="form-check-input" type="radio" name="radio" id="${fn:escapeXml(Facture.factureId)}" value="${fn:escapeXml(Facture.factureId)}">
										<label class="form-check-label label-lg" for="${fn:escapeXml(Facture.factureId)}">${fn:escapeXml(Facture.devis.projet.nom)} </label>
									</div>
									<c:choose>
										<c:when test="${Facture.devis.projet.image.imageId != null}">
										<img src="/Modularis/Photo?id=${Facture.devis.projet.image.imageId }" class="ml-3 rounded-circle" alt="miniature ${fn:escapeXml(Facture.devis.projet.nom)}" style="width: 75px;height:75px" />
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
					<button class="btn btn-danger btn-block btn-block" name="btnSupprimer" id="btnSupprimer">Passer en etat pay&eacute;</button>
				</div>
				<br>
			</div>
		
</form>
</div>
<div class="modal static fade" id="ModalConfirmationSuppression" tabindex="-1" role="dialog" aria-labelledby="ModalConfirmationSuppressionTitle" aria-hidden="true">
	  	<div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="ModalConfirmationSuppressionTitle">Suppression</h5>
		      </div>
		      <div class="modal-body">
		        Etez-vous sur de vouloir continuer ?
		      </div>
			      <div class="modal-footer">
			        <button type="button" id="btnModalSupprNon" class="btn btn-primary" data-dismiss="modal">Non</button>
			    	<button type="button" id="btnModalSupprOui" class="btn btn-danger">Oui</button>
		    	</div>
		   	</div>
		</div>
	</div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
  <script type='text/javascript'
		src='<%=request.getContextPath()%>/resources/js/generateDevis.js'></script>
</body>

</html>