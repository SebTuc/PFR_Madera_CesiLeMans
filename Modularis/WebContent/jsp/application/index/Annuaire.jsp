<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Configuration</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>
	<jsp:include page="/jsp/common/navbar.jsp" />

	<a href="/Modularis" class="btn btn-outline-dark return-btn"><span
		aria-hidden="true">&larr;</span> Retour</a>
	<div role="main" class="container">
		<div class="col-lg-12 text-center">
			<h1>Annuaire</h1>
			<br />
		</div>
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<button onclick="window.location.href = 'Annuaire/ListFournisseur';"
					class="menu-link">
					<i class="material-icons" style="float: left">flight_land</i>Liste
					des fournisseurs
				</button>
			</div>
			<div class="col-sm-6 col-md-4">
				<button onclick="window.location.href = 'Annuaire/ListClient';"
					class="menu-link">
					<i class="material-icons" style="float: left">supervisor_account</i>Liste
					des clients
				</button>
			</div>
			<div class="col-sm-6 col-md-4">
				<c:choose>
					<c:when test='${Utilisateur.metier.nom == "Moderateur"}'>
						<button
							onclick="window.location.href = 'Annuaire/ListUtilisateur';"
							class="menu-link">
							<i class="material-icons" style="float: left">face</i>Liste des
							utilisateurs
						</button>
					</c:when>
					<c:when test='${Utilisateur.metier.nom != "Moderateur"}'>
						<button
							onclick="window.location.href = 'Annuaire/EditUtilisateur';"
							class="menu-link">
							<i class="material-icons" style="float: left">info</i>Modifier vos informations
						</button>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>