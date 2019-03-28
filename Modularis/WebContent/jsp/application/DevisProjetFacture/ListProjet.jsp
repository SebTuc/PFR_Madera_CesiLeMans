<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>List projet</title>
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
	<h3>Liste des Projet</h3>
</div>
<br>
<form id="List" method="post">
	<div class="row justify-content-center">
		<ul class="list-group list-radio" >
			<c:choose>
				<c:when test="${isEmptyList == true }">
				<li class="list-group-item">
					Aucun projet trouver !
				</li>
				</c:when>
				<c:otherwise>
					<c:forEach var="Projet" items="${ListProjet}" >
						<li class="list-group-item">
							<div class="form-check form-check-inline not-inline">
								<div class="media">
  									<div class="media-body">
										<input class="form-check-input" type="radio" name="radio" id="${fn:escapeXml(Projet.projetId)}" value="${fn:escapeXml(Projet.projetId)}">
										<label class="form-check-label label-lg" for="${fn:escapeXml(Projet.projetId)}">${fn:escapeXml(Projet.nom)} </label>
									</div>
									<c:choose>
										<c:when test="${Projet.image.imageId != null}">
										<img src="/Modularis/Photo?id=${Projet.image.imageId }" class="ml-3 rounded-circle" alt="miniature ${fn:escapeXml(Projet.nom)}" style="width: 75px;height:75px" />
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
		<div class="row justify-content-center">
		 	<a href="/Modularis/DevisFacture/CreerProjet" class="btn material-icons material-icons-btn material-icons-btn-add-lg ml-2">add_circle</a>
		</div>	
		<br> <br>

		<input type="text" value="" name="clientId" id="clientId" style="display:none;" >	

			<div class="row justify-content-center">
				<div class="col-md-3 col-sm-6">
					<button class="btn btn-warning btn-block btn-lg" name="btnEditer" id="btnEditer">Editer plan projet selectionn&eacute;</button>
				</div>
				<br>
				<div class="col-md-3 col-sm-6">
					<button class="btn btn-danger btn-block btn-lg" name="btnSupprimer" id="btnSupprimer">Supprimer</button>
				</div>
				<br>
				<div class="col-md-3 col-sm-6">
					<button class="btn btn-success btn-block btn-lg" type="button" name="btnGenerateDevis" data-toggle="modal"
						data-target="#ModalCreationDevis" id="btnGenerateDevis">Generer un devis</button>
				</div>
				<div class="col-md-3 col-sm-6">
					<button class="btn btn-warning btn-block btn-lg" name="btnEditerProjet" id="btnEditerProjet">Editer projet</button>
				</div>
			</div>
		
</form>
</div>
<div class="modal static fade" id="ModalCreationDevis" tabindex="-1"
		role="dialog" aria-labelledby="ModalCreationDevisTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="ModalCreationDevisTitle">Selectionner client</h5>
				</div>
				<div class="modal-body">
					<label for="Composant">Client</label>
					<!-- 					<select id="Composant" name="Composant" class="selectpicker" data-live-search="true" required> -->
					<select id="Client" name="Client" class="custom-select"
						required>
						<option value=""></option>
						<c:forEach var="Client" items="${ListClient}">
							<option value="${fn:escapeXml(Client.clientId) }">${fn:escapeXml(Client.donneesPersonelle.nom) }  ${fn:escapeXml(Client.donneesPersonelle.prenom) }</option>
						</c:forEach>
					</select>
				</div>
				<div class="modal-footer">
					<button type="button" id="btnQuitter" class="btn btn-primary"
						data-dismiss="modal">Quitter</button>
					<button type="button" id="btnCreer" class="btn btn-danger">Creer devis</button>
					<a id="btnAjouter" href="/Modularis/Annuaire/AjoutClient" class="btn btn-success" >Creer un client</a>
				</div>
			</div>
		</div>
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