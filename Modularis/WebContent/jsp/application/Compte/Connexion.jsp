<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />

	<div class="container-fluid h-100">
		<div class="row justify-content-center align-items-center">
			<form method="post">
				<div class="row justify-content-center">
					<h3>Connexion</h3>
				</div>
				<c:choose>
					<c:when test="${Erreur != null }">
						<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
					</c:when>
				</c:choose>
				<div class="form-group">
					<input id="login" class="form-control" name="login"
						placeholder="Entrer login" required />
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="Entrer mot de passe" required />
				</div>

				<button class="btn btn-primary btn-block">Connexion</button>
			</form>
		</div>
	</div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>