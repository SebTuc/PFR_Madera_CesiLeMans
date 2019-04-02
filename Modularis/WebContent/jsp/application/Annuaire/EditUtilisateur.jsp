<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Edition Utilisateur</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />

	<a href="/Modularis/Annuaire/ListUtilisateur"
		class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>

	<div role="main" class="container">

		<br>
		<form method="post">
			<input type="hidden" class="form-control" name="id" value="${UtilisateurSelected.utilisateurId}" />
			<c:choose>
				<c:when test="${Erreur != null}">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</c:when>
			</c:choose>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="nom">Nom</label> <input type="text"
						class="form-control" name="nom" id="nom"
						placeholder="Nom Utilisateur"
						value="${UtilisateurSelected.donneesPersonelle.nom}" autofocus required>
				</div>
				<div class="form-group col-md-6">
					<label for="prenom">Prenom</label> <input type="text"
						class="form-control" name="prenom" id="prenom"
						placeholder="Prenom Utilisateur"
						value="${UtilisateurSelected.donneesPersonelle.prenom}" required>
				</div>
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">Adresse</label> <input
					type="text" value="${UtilisateurSelected.donneesPersonelle.adresse}" class="form-control" name="adresse" id="adresse"
					placeholder="Adresse">
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="formGroupExampleInput2">Ville</label> <input
						type="text" value="${UtilisateurSelected.donneesPersonelle.ville}" class="form-control" name="ville" id="ville"
						placeholder="Ville">
				</div>
				<div class="form-group col-md-6">
					<label for="formGroupExampleInput2">Code Postal</label> <input
						type="text" value="${UtilisateurSelected.donneesPersonelle.codePostal}" class="form-control" name="codePostal" id="codePostal"
						placeholder="Code postal">
				</div>
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">Telephone</label> <input
					type="text" class="form-control"
					value="${UtilisateurSelected.donneesPersonelle.telephone}" name="telephone"
					id="telephone" placeholder="Telephone">
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">Email</label> <input type="text"
					class="form-control" value="${UtilisateurSelected.donneesPersonelle.email}"
					name="email" id="email" placeholder="Email">
			</div>
			<div class="form-group ">
				<label for="metier">Metier</label> <select id="metier" name="metier"
					class="custom-select" required>
					<c:forEach var="Metier" items="${ListMetier}">
						<c:choose>
							<c:when test="${Metier.metierId == UtilisateurSelected.metier.metierId}">
								<option selected value="${fn:escapeXml(Metier.metierId) }">${fn:escapeXml(Metier.nom) }
								</option>
							</c:when>
							<c:when test="${Metier.metierId != UtilisateurSelected.metier.metierId}">
								<option value="${fn:escapeXml(Metier.metierId) }">${fn:escapeXml(Metier.nom) }
								</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="form-group ">
				<label for="metier">Entrepot</label> <select id="entrepot"
					name="entrepot" class="custom-select" required>
					<c:forEach var="Entrepot" items="${ListEntrepot}">
						<c:choose>
							<c:when
								test="${Entrepot.entrepotId == UtilisateurSelected.entrepot.entrepotId}">
								<option selected value="${fn:escapeXml(Entrepot.entrepotId) }">${fn:escapeXml(Entrepot.lieux) }
								</option>
							</c:when>
							<c:when
								test="${Entrepot.entrepotId != UtilisateurSelected.entrepot.entrepotId}">
								<option value="${fn:escapeXml(Entrepot.entrepotId) }">${fn:escapeXml(Entrepot.lieux) }
								</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">Login</label> <input type="text"
					class="form-control" value="${UtilisateurSelected.login}" name="login"
					id="login" placeholder="Login">
			</div>

			<div class="form-group">
				<div class="form-check form-check-inline">
					<input class="form-check-input" data-toggle="collapse"
						data-target="#collapseMdp" type="checkbox" id="mdp"
						name="updatePassword" value="true"> <label
						class="form-check-label" for="inlineCheckbox1">Modifier le
						mot de passe</label>
				</div>
			</div>

			<div class="form-group">
				<div class="row collapse" id="collapseMdp">

					<div class="form-group col-md-6">
						<label for="password">Password</label> <input class="form-control" type="password"
							name="password" id="password" placeholder="Password">
					</div>
					<div class="form-group col-md-6">
						<label for="confirmPassword">Confirm password</label> <input
							type="password" name="confirmPassword" class="form-control" id="confirmPassword"
							placeholder="Confirm Password">
					</div>

				</div>

			</div>
			<button class="btn btn-primary btn-lg">Modifier utilisateur</button>
		</form>
		<br>

	</div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>