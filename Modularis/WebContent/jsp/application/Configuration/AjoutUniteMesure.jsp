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

<div role="main" class="container">
  
<br>

<a href="/Modularis/Configuration" class="btn btn-outline-dark"><span aria-hidden="true">&larr;</span> Retour</a>

<br>
<div class="card card-edition">
	<div class="card-header">
		<input id="search_table" type="text" class="form-control card-search" placeholder="Rechercher..." />
	</div>
	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="Edition" class="table table-edition table-striped table-bordered"cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important; ">
			<thead>
				<tr style="text-align: center">
			  		<th>Unite de Mesure</th>
			  	</tr>
			</thead>
			<tbody>
				<c:forEach var="UniteMesure" items="${ListUniteMesure}">
					<tr style="text-align: center">
				    	<td id="${fn:escapeXml(UniteMesure.uniteId) }"> ${fn:escapeXml(UniteMesure.nomUnite) } </td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  
  	<div class="row justify-content-center">
		<div class="col-4">
		    <form method="post" class="form-inline">
		      <div class="form-group">
		        <input id="uniteMesureNom" class="form-control" name="uniteMesureNom" placeholder="Nouvel Unite de mesure" required />
		        <button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
		      </div>
		    </form>
		</div>
		<div class="col-4">
			<div id="modification" class="row">
				<button id="buttonEditRow" class="btn btn-warning btn-block">Edit selected row</button>
				<div id="update_bouton" class="col-6" style="display: none;"><button id="button_update" class="btn btn-warning btn-block" data-toggle="modal" data-target="#ModalConfirmation" >Update</button></div>
				<div id="return_button" class="col-6" style="display: none;"><button id="button_retour" class="btn btn-default btn-block" >Return</button></div>
			</div>
			
		</div>
		<div class="col-4">
			<button id="buttonDeleteRow" class="btn btn-danger btn-block" data-toggle="modal" data-target="#ModalConfirmationSuppression">Delete row</button>
		</div>
	</div>
</div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
  <jsp:include page="/jsp/common/confirmModal.jsp" />
</body>

</html>