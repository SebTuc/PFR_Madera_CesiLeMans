<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Edition piece</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<a href="/Modularis/DevisFacture/EditPlan?id=${fn:escapeXml(Piece.plan.planId)}" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container-fluid">
		<c:choose>
			<c:when test="${Erreur != null }">
				<div class="row justify-content-center">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</div>
			</c:when>
		</c:choose>
<div class="row justify-content-center">
	<h3>Liste piece du projet ${fn:escapeXml(Piece.plan.projet.nom)} - ${fn:escapeXml(Piece.plan.nom)} - ${fn:escapeXml(Piece.nom)} </h3>
</div>
<br>
<form id="List" method="post">
	<input type="text" name="idPiece" value="${fn:escapeXml(Piece.pieceId) }"style="display:none;">
	<div class="row justify-content-center">
		<ul class="list-group list-radio">
			<c:choose>
				<c:when test="${isEmptyList == true }">
				<li class="list-group-item">
					Aucun module trouver !
				</li>
				</c:when>
				<c:otherwise>
					<c:forEach var="Module" items="${Piece.modules}" >
						<li class="list-group-item">
							<div class="form-check form-check-inline not-inline">
								<input class="form-check-input" type="radio" name="radio" id="${fn:escapeXml(Module.moduleId)}" value="${fn:escapeXml(Module.moduleId)}">
								<label class="form-check-label label-lg" for="${fn:escapeXml(Module.moduleId)}">${fn:escapeXml(Module.nom)}  |  Gamme  :  ${fn:escapeXml(Module.gamme.nom)}</label>
							</div>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
		<div class="form-row justify-content-center" style="margin-top: 20px;">
			<div class="form-group col-md-6 col-8">
				<label for="module">Selectionner le module a ajouter</label> 
				<select id="module" name="module" class="custom-select">
						<option value=""></option>
					<c:forEach var="Module" items="${ListModule }">
						<option value="${fn:escapeXml(Module.moduleId) }">${fn:escapeXml(Module.nom) }  |  Gamme : ${fn:escapeXml(Module.gamme.nom)}</option>
					</c:forEach>
				</select>
			</div>
		 	<div class="form-group col-md-4 col-2">
		 		<button name="btnAjouter" id="btnAjouter" class="btn material-icons material-icons-btn material-icons-btn-add-lg ml-2" style="margin-top: 10px;">add_circle</button>
		 	</div>
		</div>	
		<br> <br>
	
	<div class="row justify-content-center">
		<div class="col-6">
			<div class="row justify-content-center">
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