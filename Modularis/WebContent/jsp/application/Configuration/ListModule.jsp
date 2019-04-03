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
		<c:choose>
			<c:when test="${Erreur != null }">
				<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
			</c:when>
		</c:choose>
		<div class="row justify-content-center">
	<h3>Liste des Modules</h3>
</div>
<div class="row justify-content-center">
	<button class="btn btn-info btn-sm" type="button" data-toggle="collapse" data-target="#collapsedCritere" aria-expanded="false" aria-controls="collapseExample" >Ajouter critere de recherce</button>
</div>
<br>
<div class="collapse" id="collapsedCritere">
	<form method="get">
		<div class="row justify-content-center">
			<div class="jumbotron" style="padding: 2rem 2rem">
				<h5>selectionner les criteres de recherche et valider :</h5>
				<br>
				<label for="gamme">Gamme</label>
				<select id="gamme" class="custom-select" name="gamme" required>
					<option value="-1" selected></option>
			   		<c:forEach var="Gamme" items="${ListGamme }">
			   			<c:choose>
							    <c:when test="${Gamme.gammeId == gammeId}">
							       <option selected value="${fn:escapeXml(Gamme.gammeId) }">${fn:escapeXml(Gamme.nom) } </option>
							    </c:when>    
							    <c:otherwise>
									<option value="${fn:escapeXml(Gamme.gammeId) }">${fn:escapeXml(Gamme.nom) } </option>
							    </c:otherwise>
							</c:choose>
			   		</c:forEach>
				</select>
				<label for="materiaux">Nom Module :</label>
				<input class="form-control" type="text" id="nomModule" name="nomModule" placeholder="Module contenant le mot..." value="${fn:escapeXml(nomModule) }"/>
				<br><br>
				<div class="row justify-content-center">
					<div class="col-6 justify-content-center">
						<div class="row justify-content-center">
							<button class="btn btn-primary" name="btnCritere" id="btnCritere">Appliquer</button>
						</div>
					</div>
					<div class="col-6 justify-content-center">
						<div class="row justify-content-center">
							<a href="/Modularis/Configuration/ListModule" class="btn btn-primary">Refresh</a>
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
					<c:forEach var="Module" items="${ListModule}" >
						<li class="list-group-item">
							<div class="form-check form-check-inline not-inline">
								<input class="form-check-input" type="radio" name="radio" id="${fn:escapeXml(Module.moduleId)}" value="${fn:escapeXml(Module.moduleId)}">
								<label class="form-check-label label-lg" for="${fn:escapeXml(Module.moduleId)}">
								${fn:escapeXml(Module.nom)} | Gamme : ${fn:escapeXml(Module.gamme.nom)} | Unite mesure : ${fn:escapeXml(Module.uniteMesure.nomUnite)} 
								</label>
							</div>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
		<div class="row justify-content-center">
		 	<a href="/Modularis/Configuration/AjoutModule" class="btn material-icons material-icons-btn material-icons-btn-add-lg ml-2">add_circle</a>
		</div>	
		<br> <br>
	
	<div class="row justify-content-center">
		<div class="col-6">
			<div class="row justify-content-center">
				<div class="col-md-6 col-sm-12">
					<button class="btn btn-warning btn-block btn-block" name="btnEditer" id="btnEditer">Editer</button>
				</div>
				<div class="col-md-6 col-sm-12">
					<button class="btn btn-danger btn-block btn-block" name="btnSupprimer" id="btnSupprimer" type="button" data-toggle="modal" data-target="#ModalConfirmationSuppression" id="btnSupprimer">Supprimer</button>
				</div>
			</div>
		</div>
	</div>
</form>

</div>
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
   <jsp:include page="/jsp/common/confirmModal.jsp" />
   <script type='text/javascript'
		src='<%=request.getContextPath()%>/resources/js/generateDevis.js'></script>
</body>

</html>