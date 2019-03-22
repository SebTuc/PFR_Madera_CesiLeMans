<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Edition Unite Mesure</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<a href="/Modularis/Configuration" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>

<div role="main" class="container">
  
<br>

<br>
<div class="card card-edition">
	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="Edition" class="table table-edition table-striped table-bordered" cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important;text-align: center;" 
			column-defs='[{"data": "id","title": "","type": "hidden","visible": false},{"data":"valeur","title": "Unite de mesure"}]' 
			data-set='[<c:forEach var="UniteMesure" items="${ListUniteMesure}">{"id":"${fn:escapeXml(UniteMesure.uniteId)}","valeur":"${fn:escapeXml(UniteMesure.nomUnite)}"},</c:forEach>]'>
		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  
  	<div class="row">
		<div class="col-xs-6 col-4">
		    <form method="post" class="form-inline">
		      <div class="form-group">
		        <input id="uniteMesureNom" class="form-control" name="uniteMesureNom" placeholder="Nouvel Unite de mesure" required />
		        <button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
		      </div>
		    </form>
		</div>
	</div>
</div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>