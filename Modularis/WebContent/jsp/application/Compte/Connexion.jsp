<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />
</head>

<body class="bg">
	<!-- NAVBAR -->
	<nav class="navbar navbar-expand navbar-dark navbar-modularis">
		<a class="navbar-brand navbar-logo" style="color:#FFF">MODULARIS</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

	</nav>
	<div class="login-page">
		<div class="form">
			<h2 style="color:white">CONNEXION</h2> <br>
			<form method="post" class="login-form">
			    <c:choose>
			      <c:when test="${Erreur != null }">
			        <div class="alert-login" role="alert">${fn:escapeXml(Erreur)}</div>
			      </c:when>
			    </c:choose>
			      <div class="flex-row">
			        <input id="login" class="lf--input" name="login"
			               placeholder="Login" autofocus required />
			      </div>
			      <div class="flex-row">
			        <input type="password" class="lf--input" id="password"
			               name="password" placeholder="Mot de passe" required />
			      </div>
			         <button class="login-btn" type='submit'>SE CONNECTER</button>
			</form>
		</div>
	</div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>