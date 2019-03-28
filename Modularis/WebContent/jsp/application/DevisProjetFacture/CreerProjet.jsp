<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Creer un projet</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />

	<a href="/Modularis/DevisFacture"
		class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>

	<div role="main" class="container">

		<br>
		<form method="post" id="formPost" encType="multipart/form-data">
			<c:choose>
				<c:when test="${Erreur != null }">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</c:when>
			</c:choose>
			<div class="form-row justify-content-center">
				<div class="form-group col-md-12 col-xl-6">
					<label >Nom projet</label>
					  <input type="text" class="form-control" id="nomProjet" name="nomProjet" placeholder="nom du projet..." required>

				</div>
				<div class="form-group col-md-12 col-xl-6">
					<label >Image du projet (optionnel)</label>
					<div class="custom-file">
					  <input type="file" class="custom-file-input" accept="image/*" id="customFile" name="customFile">
					  <label class="custom-file-label" for="customFile">Selectionner un fichier</label>
					</div>
				</div>
			</div>
			
			<br>
			
			<div id="previsualisation"style="display:none;">
				<div class="row justify-content-center">
					<h3>Previsualisation :</h3>
				</div>
				<div class="row justify-content-center">
					<img src="" class="img-fluid" id="imgPrevi" alt="Previsualisation" style="max-height:400px;">
				</div>
			</div>
			<br>
			
			<div class="row justify-content-center">
				<button class="btn btn-primary btn-lg" id="submitProjet">Creer projet vierge</button>
			</div>
		</form>
		<br>

	</div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
	<script type='text/javascript' src='<%=request.getContextPath()%>/resources/js/CreationProjet.js'></script>
</body>

</html>