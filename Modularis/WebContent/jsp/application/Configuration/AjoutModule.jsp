<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Ajout Module</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />
	<a href="/Modularis/Configuration/ListModule"
		class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>
	<div role="main" class="container">

		<br>
		<c:choose>
			<c:when test="${Erreur != null }">
				<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
			</c:when>
		</c:choose>
		<form method="post" id="formSubmit">

			<input type="hidden" class="form-control" name="sendSubmit"
				id="sendSubmit" value="">

		</form>
		<div class="form-row justify-content-center">
			<div class="form-group col-6">
				<label for="nom">Nom Module</label> <input type="text"
					class="form-control" name="nom" id="nom"
					placeholder="Nom du module" maxlength="50" required>
			</div>
			<div class="form-group col-6">
				<label for="uniteMesure">Unite mesure</label> <select
					id="uniteMesure" name="uniteMesure" class="custom-select" required>
					<option value=""></option>
					<c:forEach var="UniteMesure" items="${ListUniteMesure }">
						<option value="${fn:escapeXml(UniteMesure.uniteId) }">${fn:escapeXml(UniteMesure.nomUnite) }
						</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<ul class="list-group" id="ListComposant"
			style="text-align: center; max-height: 400px; overflow: auto; margin-bottom: 20px;">
			<li class="list-group-item">
				<div class="form-check-inline">Ajout Composant</div>
				<button type="button" id="addComposant" data-toggle="modal"
					data-target="#ModalAddComposant"
					class="btn material-icons material-icons-btn material-icons-btn-add-lg ml-2">add_circle</button>
			</li>
			<li class="list-group-item">
				<div class="row">
					<div class="col-8">
						<h3 style="text-align: center;">Nom Composant</h3>
					</div>
					<div class="col-3">
						<h3 style="text-align: center;">Quantite</h3>
					</div>
				</div>
			</li>
		</ul>
		<div class="form-row">
			<div class="form-group col-md-4">
				<label for="familleComposant">Gamme</label> <select id="gamme"
					name="gamme" class="custom-select" required>
					<option value=""></option>
					<c:forEach var="Gamme" items="${ListGamme }">
						<option value="${fn:escapeXml(Gamme.gammeId) }">${fn:escapeXml(Gamme.nom) }
						</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group col-md-4">
				<div class="form-check form-check-inline alignCheckAngle">
					<input class="form-check-input" data-toggle="collapse"
						data-target="#collapseAngle" type="checkbox" id="Angle"
						value="Angle"> <label class="form-check-label"
						for="inlineCheckbox1">Contient un angle</label>
				</div>
			</div>

			<div class="form-group col-md-4">
				<div class="collapse" id="collapseAngle">
					<label for="materiaux">Type d'angle</label> <select id="typeAngle"
						name="typeAngle" class="custom-select">
						<option value=""></option>
						<c:forEach var="Angle" items="${ListAngle}">
							<option value="${fn:escapeXml(Angle.angleId) }">${fn:escapeXml(Angle.typeAngle) }
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<br> <br>
		<div class="row justify-content-center">
			<div class="col-4">
				<button class="btn btn-primary btn-block" type="button"
					id="submitModule">Ajouter Module</button>
			</div>
			<div class="col-4">
				<button class="btn btn-danger btn-block" type="button"
					id="supprComposant">Supprimer composant selectionn&eacute;</button>
			</div>
		</div>
	</div>


	<div class="modal static fade" id="ModalAddComposant" tabindex="-1"
		role="dialog" aria-labelledby="ModalAddComposantTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content p-2">
				<div class="modal-header">
					<h5 class="modal-title" id="ModalAddComposantTitle">Ajouter un
						composant</h5>
				</div>
				<div class="modal-body">
					<div class="row">
						<label for="Composant">Composant</label>
					</div>
					
					<div class="row">
						<select id="Composant" name="Composant"
							class="selectpicker w-100" data-live-search="true"
							required>
							<option value=""></option>
							<c:forEach var="Composant" items="${ListComposant}">
								<option value="${fn:escapeXml(Composant.composantId) }">${fn:escapeXml(Composant.nom) }
									| ${fn:escapeXml(Composant.materiaux.nom) } |
									${fn:escapeXml(Composant.familleComposant.nom) }</option>
							</c:forEach>
						</select> 
					</div>
					<div class="row">
						<label for="nombreComposant">Nombre Composant</label> <input
							type="number" class="form-control" name="nombreComposant" min="1"
							id="nombreComposant">
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="btnQuitter" class="btn btn-danger"
						data-dismiss="modal">Quitter</button>
					<button type="button" id="btnAjouter" class="btn btn-primary">Ajouter</button>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="/jsp/common/defaultScripts.jsp" />
	<script type='text/javascript'
		src='<%=request.getContextPath()%>/resources/js/ajoutComposantInModule.js'></script>

</body>

</html>