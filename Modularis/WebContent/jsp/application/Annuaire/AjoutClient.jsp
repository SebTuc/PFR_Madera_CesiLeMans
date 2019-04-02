<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Ajout client</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />

	<a href="/Modularis/Annuaire/ListClient"
		class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>

	<div role="main" class="container">

		<br>
		<form method="post">
			<c:choose>
				<c:when test="${Erreur != null }">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</c:when>
			</c:choose>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="nom">Nom</label> <input type="text"
						class="form-control" name="nom" id="nom"
						placeholder="Nom Utilisateur" autofocus required>
				</div>
				<div class="form-group col-md-6">
					<label for="prenom">Prenom</label> <input type="text"
						class="form-control" name="prenom" id="prenom"
						placeholder="Prenom Utilisateur" required>
				</div>
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">Adresse</label> <input
					type="text" class="form-control" name="adresse" id="adresse"
					placeholder="Adresse">
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="formGroupExampleInput2">Ville</label> <input
						type="text" class="form-control" name="ville" id="ville"
						placeholder="Ville">
				</div>
				<div class="form-group col-md-6">
					<label for="formGroupExampleInput2">Code Postal</label> <input
						type="text" class="form-control" name="codePostal" id="codePostal"
						placeholder="Code postal">
				</div>
			</div>

			<div class="form-group">
				<label for="formGroupExampleInput2">Telephone</label> <input
					type="text" class="form-control" name="telephone" id="telephone"
					placeholder="Telephone">
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">Email</label> <input type="text"
					class="form-control" name="email" id="email" placeholder="Email">
			</div>
			
			<button class="btn btn-primary btn-lg">Ajouter client</button>
		</form>
		<br>

	</div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>