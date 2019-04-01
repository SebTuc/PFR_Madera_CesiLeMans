<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Editer un projet</title>
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
				<div class="col-12">
					<input type="text" name=id
						value="${fn:escapeXml(Projet.projetId) }" style="display: none;">
					<c:choose>
						<c:when test="${Erreur != null }">
							<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
						</c:when>
					</c:choose>
					<div class="form-row justify-content-center">
						<div class="form-group col-md-12 col-xl-6">
							<label>Nom projet</label> <input type="text" class="form-control"
								id="nomProjet" value="${fn:escapeXml(Projet.nom) }"
								name="nomProjet" placeholder="nom du projet..." autofocus required>

						</div>

						<div class="form-group col-md-12 col-xl-6">
							<label>Image du projet (optionnel)</label>
							<div class="custom-file">
								<input type="file" class="custom-file-input" accept="image/*"
									id="customFile" name="customFile"> <label
									class="custom-file-label" for="customFile">Selectionner
									un fichier</label>
							</div>
						</div>
					</div>

					<br>
					<c:choose>
						<c:when test="${TakeImage == true }">
							<div id="previsualisation">
								<div class="row justify-content-center">
									<h3>Previsualisation :</h3>
								</div>
								<div class="row justify-content-center">

									<img
										src="/Modularis/Photo?id=${fn:escapeXml(Projet.image.imageId) }"
										class="img-fluid" id="imgPrevi" alt="Previsualisation"
										style="max-height: 400px;">
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div id="previsualisation" style="display: none;">
								<div class="row justify-content-center">
									<h3>Previsualisation :</h3>
								</div>
								<div class="row justify-content-center">

									<img src="" class="img-fluid" id="imgPrevi"
										alt="Previsualisation" style="max-height: 400px;">
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					<br>
				</div>
				<div class="row justify-content-center">
				<div class="col-5">

					<button class="btn btn-primary btn-block" name="submitProjet" id="submitProjet">Editer
						projet</button>
				</div>
				<div class="col-5">
					<button class="btn btn-primary btn-block" name="supprImage"
						id="supprImage" type="button" data-toggle="modal" data-target="#ModalConfirmationSuppression">Supprimer image du projet</button>
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
			        <button type="button" id="btnModalSupprImageNon" class="btn btn-primary" data-dismiss="modal">Non</button>
			    	<button type="button" id="btnModalSupprImageOui" class="btn btn-danger">Oui</button>
		    	</div>
		   	</div>
		</div>
	</div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
	<script type='text/javascript'
		src='<%=request.getContextPath()%>/resources/js/CreationProjet.js'></script>
		  <script type='text/javascript'
		src='<%=request.getContextPath()%>/resources/js/generateDevis.js'></script>
</body>

</html>