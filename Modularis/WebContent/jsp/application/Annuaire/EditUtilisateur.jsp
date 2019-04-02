<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Ajout Utilisateur</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />

	<a href="/Modularis/Annuaire/ListFournisseur"
		class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>

	<div role="main" class="container">

		<br>
		<form method="post">
			<c:choose>
				<c:when test="${Erreur != null}">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</c:when>
			</c:choose>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="nom">Nom</label> <input type="text"
						class="form-control" name="nom" id="nom"
						placeholder="Nom Utilisateur" value="${Utilisateur.donneesPersonelle.nom}" autofocus required>
				</div>
				<div class="form-group col-md-6">
					<label for="prenom">Prenom</label> <input type="text"
						class="form-control" name="prenom" id="prenom"
						placeholder="Prenom Utilisateur" required>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="formGroupExampleInput2">Adresse</label> <input
						type="text" class="form-control" name="adresse" id="adresse"
						placeholder="Adresse">
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
			<div class="form-group ">
				<label for="metier">Metier</label> <select id="metier" name="metier"
					class="custom-select" required>
					<c:forEach var="Metier" items="${ListMetier }">
						<option value="${fn:escapeXml(Metier.metierId) }">${fn:escapeXml(Metier.nom) }
						</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group ">
				<label for="metier">Entrepot</label> <select id="entrepot" name="entrepot"
					class="custom-select" required>
					<c:forEach var="Entrepot" items="${ListEntrepot}">
						<option value="${fn:escapeXml(Entrepot.entrepotId) }">${fn:escapeXml(Entrepot.lieux) }
						</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">Login</label> <input type="text"
					class="form-control" name="login" id="login" placeholder="Login">
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="password">Password</label> <input type="password"
						class="form-control" name="password" id="password"
						placeholder="Password" required>
				</div>
				<div class="form-group col-md-6">
					<label for="confirmPassword">Confirm password</label> <input
						type="password" class="form-control" name="confirmPassword"
						id="confirmPassword" placeholder="Confirm Password" required>
				</div>
			</div>
			<button class="btn btn-primary btn-lg ">Ajouter utilisateur</button>
		</form>
		<br>

	</div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>