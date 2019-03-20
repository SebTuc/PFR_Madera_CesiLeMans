<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>List Piece</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<div role="main" class="container">
  
<br>
<a href="/Modularis/Devis" class="btn btn-outline-dark"><span aria-hidden="true">&larr;</span> Retour</a>
<br><br>

	<form method="post">
		<c:forEach var="Piece" items="${ListPiece}" >
			<div class="form-check form-check-inline not-inline">
				  <input class="form-check-input" type="radio" name="radio-inline" id="${fn:escapeXml(Piece.pieceId)}" value="${fn:escapeXml(Piece.pieceId)}">
				  <label class="form-check-label label-lg" for="${fn:escapeXml(Piece.pieceId)}">${fn:escapeXml(Piece.nom)}</label>
			</div>
		</c:forEach>
		<div class="form-check form-check-inline not-inline">
		 	<a href="/Modularis/Devis/AjoutPiece" class="btn material-icons material-icons-btn material-icons-btn-add-lg ml-2">add_circle</a>
		</div>	
		<div class="row align-items-end">
		    <div class="col">
		      <button class="btn btn-danger btn-block btn-lg" id="btnSupprimer">Supprimer</button>
		    </div>
		    <div class="col">
		      <button class="btn btn-warning btn-block btn-lg" id="btnEditer">Editer</button>
		    </div>
	 	</div>
	</form>
	
</div>
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>