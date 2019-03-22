<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Ajout Fournisseur</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<a href="/Modularis/Annuaire/ListFournisseur" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>

<div role="main" class="container">
 
<br><br>
<form method="post">
  <div class="form-group">
    <label for="formGroupExampleInput">Nom</label>
    <input type="text" class="form-control" name="nom" id="nom" placeholder="Nom Fournisseur" required>
  </div>
  <div class="form-row">
	  <div class="form-group col-md-6">
	    <label for="formGroupExampleInput2">Adresse</label>
	    <input type="text" class="form-control" name="adresse" id="adresse" placeholder="Adresse" required>
	  </div>
	  <div class="form-group col-md-6">
	    <label for="formGroupExampleInput2">Code Postal</label>
	    <input type="text" class="form-control" name="codePostal" id="codePostal" placeholder="Code postal" required>
	  </div>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">Telephone</label>
    <input type="text" class="form-control" name="telephone" id="telephone" placeholder="Telephone">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">Email</label>
    <input type="text" class="form-control" name="email" id="email" placeholder="Email">
  </div>
   <button class="btn btn-primary btn-lg ">Ajouter fournisseur</button>
</form>
<br>

</div>	
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>