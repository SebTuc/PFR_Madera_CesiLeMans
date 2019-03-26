<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Edition Angle</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<a href="/Modularis/Configuration" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container">

<br>
		<c:choose>
			<c:when test="${Erreur != null }">
				<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
			</c:when>
		</c:choose>
<div class="card card-edition">

	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="Edition" class="table table-edition table-striped table-bordered"cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important;text-align: center; "
				column-defs='[{"data": "id","title": "","type": "hidden","visible": false},{"data":"typeAngle","title": "Type angle"},{"data":"prixUnitaire","title": "Prix angle"}]' 
				data-set='[<c:forEach var="Angle" items="${ListAngle}">{"id":"${fn:escapeXml(Angle.angleId)}","typeAngle":"${fn:escapeXml(Angle.typeAngle)}","prixUnitaire":"${fn:escapeXml(Angle.prixUnitaire)}"},</c:forEach>]'>
			
		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  
  	<div class="row">
		<div class="col-xs-6 col-sm-4">
		    <form method="post" class="form-inline">
		      <div class="form-group">
		        <input id="angleNom" class="form-control" maxlength="25" name="angleNom" placeholder="Type d'angle" required />
		         <input id="anglePrix" class="form-control" name="anglePrix" placeholder="Prix angle" required />
		        <button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
		      </div>
		    </form>
		</div>
		
	</div>
</div>
	
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>