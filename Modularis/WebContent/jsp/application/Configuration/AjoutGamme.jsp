<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Edition Gamme</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<div role="main" class="container">
  
<br>

<a href="/Modularis/Configuration" class="btn btn-outline-dark"><span aria-hidden="true">&larr;</span> Retour</a>
<br><br>
<div class="card card-edition">

	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="Edition" class="table table-edition table-striped table-bordered" cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important; text-align: center;" 
			column-defs='[{"data": "id","title": "","type": "hidden","visible": false},{"data":"valeur","title": "Gamme"}]' 
			data-set='[<c:forEach var="Gamme" items="${ListGamme}">{"id":"${fn:escapeXml(Gamme.gammeId)}","valeur":"${fn:escapeXml(Gamme.nom)}"},</c:forEach>]'>
		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  
  	<div class="row">
		<div class="col-xs-6 col-sm-4">
		    <form method="post" class="form-inline">
		      <div class="form-group">
		        <input id="gammeNom" class="form-control" name="gammeNom" placeholder="Nouvel Gamme" required />
		        <button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
		      </div>
		    </form>
		</div>
	</div>
    
</div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>