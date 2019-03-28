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

<a href="/Modularis/Configuration" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container-fluid">
<!-- Faire une collapse et un bouton pour l'ouvrir avec critere de recherche ou un truc comme sa , ensuite faire dans le get un trie sur la list selon les parametre recu en get -->
<div class="row justify-content-center">
	<h3>Liste des composants</h3>
</div>
<div class="row justify-content-center">
	<button class="btn btn-info btn-sm" type="button" data-toggle="collapse" data-target="#collapsedCritere" aria-expanded="false" aria-controls="collapseExample" >Ajouter critere de recherce</button>
</div>
<br>
<div class="collapse" id="collapsedCritere">
	<form method="get">
		<div class="row justify-content-center">
			<div class="jumbotron" style="padding: 2rem 2rem">
				<h5>selectionner les critere de recherche et valider :</h5>
				<br>
				<label for="familleComposant">Famille Composant</label>
				<select id="familleComposant" class="custom-select" name="familleComposant" required>
					<option value="-1" selected></option>
			   		<c:forEach var="FamilleComposant" items="${ListFamilleComposant }">
			   			<c:choose>
						    <c:when test="${FamilleComposant.familleComposantId == familleComposantId }">
						       <option selected value="${fn:escapeXml(FamilleComposant.familleComposantId) }">${fn:escapeXml(FamilleComposant.nom) } </option>
						    </c:when>    
						    <c:otherwise>
								<option value="${fn:escapeXml(FamilleComposant.familleComposantId) }">${fn:escapeXml(FamilleComposant.nom) } </option>
						    </c:otherwise>
						</c:choose>
			   		</c:forEach>
				</select>
				<label for="materiaux">Materiaux</label>
				<select id="materiaux" class="custom-select" name="materiaux" required>
					<option value="-1" selected></option>
			   		<c:forEach var="Materiaux" items="${ListMateriaux }">
			   			<c:choose>
							    <c:when test="${Materiaux.materiauxId == materiauxId}">
							       <option selected value="${fn:escapeXml(Materiaux.materiauxId) }">${fn:escapeXml(Materiaux.nom) } </option>
							    </c:when>    
							    <c:otherwise>
									<option value="${fn:escapeXml(Materiaux.materiauxId) }">${fn:escapeXml(Materiaux.nom) } </option>
							    </c:otherwise>
							</c:choose>
			   		</c:forEach>
				</select>
				<br><br>
				<div class="row justify-content-center">
					<div class="col-6 justify-content-center">
						<div class="row justify-content-center">
							<button class="btn btn-primary" name="btnCritere" id="btnCritere">Appliquer</button>
						</div>
					</div>
					<div class="col-6 justify-content-center">
						<div class="row justify-content-center">
							<a href="/Modularis/Configuration/ListComposant" class="btn btn-primary">Refresh</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
</div>
<br>
<form id="List" method="post">
	<div class="row justify-content-center">
		<ul class="list-group list-radio">
			<c:choose>
				<c:when test="${isEmptyList == true }">
				<li class="list-group-item">
					Aucune valeur trouver !
				</li>
				</c:when>
				<c:otherwise>
					<c:forEach var="Composant" items="${ListComposant}" >
						<li class="list-group-item">
							<div class="form-check form-check-inline not-inline">
								<input class="form-check-input" type="radio" name="radio" id="${fn:escapeXml(Composant.composantId)}" value="${fn:escapeXml(Composant.composantId)}">
								<label class="form-check-label label-lg" for="${fn:escapeXml(Composant.composantId)}">
								${fn:escapeXml(Composant.nom)} <span class="badge badge-primary" style="font-size: 60%">${fn:escapeXml(Composant.prixUnitaire)} &euro; /u</span> | Materiaux : ${fn:escapeXml(Composant.materiaux.nom)} | Famille Compsoant : ${fn:escapeXml(Composant.familleComposant.nom)} 
								</label>
							</div>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
		<div class="row justify-content-center">
		 	<a href="/Modularis/Configuration/AjoutComposant" class="btn material-icons material-icons-btn material-icons-btn-add-lg ml-2">add_circle</a>
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