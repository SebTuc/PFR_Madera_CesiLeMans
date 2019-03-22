<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Inscription</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<div role="main" class="container">
	<form method="post">
		<div class="form-group">
	    	<label for="formGroupExampleInput">Login</label>
	    	<input type="text" class="form-control" name="login" id="login" placeholder="login" required>
	  	</div>
	  	<div class="form-row">
		  	<div class="form-group col-md-6">
		    	<label for="formGroupExampleInput2">Mot de passe</label>
		    	<input type="password" class="form-control" name="password" id="password" placeholder="Mot de passe" required>
			</div>
	 	</div>
	   <button class="btn btn-primary btn-lg ">Créer le compte</button>
	</form>
</div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>