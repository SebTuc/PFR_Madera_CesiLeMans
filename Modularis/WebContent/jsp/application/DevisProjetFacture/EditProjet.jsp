<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>List plan</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<a href="/Modularis/DevisFacture/ListProjet" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container-fluid">
		<c:choose>
			<c:when test="${Erreur != null }">
				<div class="row justify-content-center">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</div>
			</c:when>
		</c:choose>
<div class="row justify-content-center">
	<h3>Liste plan du projet ${fn:escapeXml(Projet.nom)}</h3>
</div>
<br>
<form id="List" method="post">
	<input type="text" name="idProjet" value="${fn:escapeXml(Projet.projetId) }"style="display:none;">
	<div class="row justify-content-center">
		<ul class="list-group list-radio">
			<c:choose>
				<c:when test="${isEmptyList == true }">
				<li class="list-group-item">
					Aucun plan trouver !
				</li>
				</c:when>
				<c:otherwise>
					<c:forEach var="Plan" items="${Projet.plans}" >
						<li class="list-group-item">
							<div class="form-check form-check-inline not-inline">
								<input class="form-check-input" type="radio" name="radio" id="${fn:escapeXml(Plan.planId)}" value="${fn:escapeXml(Plan.planId)}">
								<label class="form-check-label label-lg" for="${fn:escapeXml(Plan.planId)}">${fn:escapeXml(Plan.nom)}</label>
							</div>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
		<div class="form-row justify-content-center">
			<div class="form-group col-md-4 col-8">
		 		<input name="planNom" id="planNom" class="form-control" placeholder="Entrer le nom du plan" type="text" style="margin-top: 15px">
		 	</div>
		 	<div class="form-group col-md-2 col-4">
		 		<button name="btnAjouter" id="btnAjouter" class="btn material-icons material-icons-btn material-icons-btn-add-lg ml-2">add_circle</button>
		 	</div>
		</div>	
		<br> <br>
	
	<div class="row justify-content-center">
		<div class="col-6">
			<div class="row justify-content-center">
				<div class="col-md-6 col-sm-12">
					<button class="btn btn-warning btn-block btn-lg" name="btnEditer" id="btnEditer">Editer</button>
				</div>
				<div class="col-md-6 col-sm-12">
					<button class="btn btn-danger btn-block btn-lg" name="btnSupprimer" id="btnSupprimer">Supprimer</button>
				</div>
			</div>
		</div>
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
</body>

</html>