<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Ajout Composant</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />
	<a href="/Modularis/Configuration/ListModule"
		class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>
	<div role="main" class="container">

		<br> <br>
		<form method="post">
			<div class="form-group justify-content-center">
				<label for="nom">Nom Module</label> <input type="text"
					class="form-control" name="nom" id="nom"
					placeholder="Nom du composant" required>
			</div>
			<ul class="list-group list-group-flush" id="ListComposant">
				<li class="list-group-item">
					<button type="button" id="addComposant" data-toggle="modal"
						data-target="#ModalAddComposant"
						class="btn material-icons material-icons-btn material-icons-btn-add-lg ml-2">add_circle</button>
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
					<div class="form-check form-check-inline">
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
			<div class="row justify-content-center">
				<button class="btn btn-primary btn-lg ">Ajouter Module</button>
			</div>
		</form>
	</div>


	<div class="modal static fade" id="ModalAddComposant" tabindex="-1"
		role="dialog" aria-labelledby="ModalAddComposantTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="ModalAddComposantTitle">Ajouter un
						composant</h5>
				</div>
				<div class="modal-body">
					<label for="Composant">Composant</label> 
<!-- 					<select id="Composant" name="Composant" class="selectpicker" data-live-search="true" required> -->
						<select id="Composant" name="Composant" class="custom-select" required>
							<option value=""></option>
						<c:forEach var="Composant" items="${ListComposant}">
							<option value="${fn:escapeXml(Composant.composantId) }">${fn:escapeXml(Composant.nom) } | ${fn:escapeXml(Composant.materiaux.nom) } | ${fn:escapeXml(Composant.familleComposant.nom) }</option>
						</c:forEach>
					</select> <label for="nombreComposant">Nombre Composant</label> <input
						type="number" class="form-control" name="nombreComposant" min="1"
						id="nombreComposant">
				</div>
				<div class="modal-footer">
					<button type="button" id="btnQuitter" class="btn btn-primary"
						data-dismiss="modal">Quitter</button>
					<button type="button" id="btnAjouter" class="btn btn-danger">Ajouter</button>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="/jsp/common/defaultScripts.jsp" />
	<script type='text/javascript'
		src='<%=request.getContextPath()%>/resources/js/ajoutComposantInModule.js'></script>
	<script type='text/javascript'
		src='<%=request.getContextPath()%>/resources/js/bootstrap-select.min.js'></script>
		
</body>

</html>