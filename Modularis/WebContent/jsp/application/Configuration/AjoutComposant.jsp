<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Ajout Composant</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />
	<a href="/Modularis/Configuration/ListComposant"
		class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>
	<div role="main" class="container">

		<br>
		<c:choose>
			<c:when test="${Erreur != null }">
				<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
			</c:when>
		</c:choose>
		<br>
		<form method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="nom">Nom Composant</label> <input type="text"
						class="form-control" name="nom" id="nom"
						placeholder="Nom du composant" value="${fn:escapeXml(NomComposant) }" required>
				</div>
				<div class="form-group col-md-6">
					<label for="prixUnitaire">Prix unitaire</label> 
					<div class="input-group-append">
					<input type="text"
						class="form-control" name="prixUnitaire" id="adresse"
						placeholder="Prix Unitaire" aria-describedby="addonEuro" required>
						<span class="input-group-text" value="${fn:escapeXml(PrixUnitaire) }" id="addonEuro">&euro;</span>
					</div>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="familleComposant">Famille Composant</label> 
					<select
						id="familleComposant" name="familleComposant"
						class="custom-select" required>
						<option value=""></option>
						<c:forEach var="FamilleComposant" items="${ListFamilleComposant }">
							<c:choose>
						    <c:when test="${FamilleComposant.familleComposantId == FamilleComposantId }">
						       <option selected value="${fn:escapeXml(FamilleComposant.familleComposantId) }">${fn:escapeXml(FamilleComposant.nom) } </option>
						    </c:when>    
						    <c:otherwise>
								<option value="${fn:escapeXml(FamilleComposant.familleComposantId) }">${fn:escapeXml(FamilleComposant.nom) } </option>
						    </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="form-group col-md-4">
					<label for="fournisseur">Fournisseur</label> <select
						id="fournisseur" class="custom-select" name="fournisseur" required>
						<option value=""></option>
						<c:forEach var="Fournisseur" items="${ListFournisseur }">
							<c:choose>
							    <c:when test="${Fournisseur.fournisseurId == FournisseurId}">
							       <option selected value="${fn:escapeXml(Fournisseur.fournisseurId) }">${fn:escapeXml(Fournisseur.nom) } </option>
							    </c:when>    
							    <c:otherwise>
									<option value="${fn:escapeXml(Fournisseur.fournisseurId) }">${fn:escapeXml(Fournisseur.nom) } </option>
							    </c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="form-group col-md-4">
					<label for="materiaux">Materiaux</label> <select id="materiaux"
						name="materiaux" class="custom-select" required>
						<option value=""></option>
						<c:forEach var="Materiaux" items="${ListMateriaux }">
							<c:choose>
							    <c:when test="${Materiaux.materiauxId == MateriauxId}">
							       <option selected value="${fn:escapeXml(Materiaux.materiauxId) }">${fn:escapeXml(Materiaux.nom) } </option>
							    </c:when>    
							    <c:otherwise>
									<option value="${fn:escapeXml(Materiaux.materiauxId) }">${fn:escapeXml(Materiaux.nom) } </option>
							    </c:otherwise>
							</c:choose>
		   			
						</c:forEach>
					</select>
				</div>
			</div>
			<button class="btn btn-primary btn-lg ">Ajouter Composant</button>
		</form>
	</div>

	<jsp:include page="/jsp/common/defaultScripts.jsp" />
	<jsp:include page="/jsp/common/confirmModal.jsp" />
</body>

</html>