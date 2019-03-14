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

<div class="card card-edition">
	<div class="card-header">
		<input id="search_table" type="text" class="form-control card-search" placeholder="Rechercher..." />
	</div>
	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="Edition" class="table table-edition table-striped table-bordered"cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important; ">
			<thead>
				<tr style="text-align: center">
			  		<th>Gamme</th>
			  	</tr>
			</thead>
			<tbody>
				<c:forEach var="Gamme" items="${ListGamme}">
					<tr style="text-align: center">
				    	<td id="${fn:escapeXml(Gamme.gammeId) }"> ${fn:escapeXml(Gamme.nom) } </td>
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
		        <input id="gammeNom" class="form-control" name="gammeNom" placeholder="Nouvel Gamme" required />
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
    
    <!-- Modal Edition -->
	<div class="modal static fade" id="ModalConfirmation" tabindex="-1" role="dialog" aria-labelledby="ModalConfirmationTitle" aria-hidden="true">
	  	<div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="ModalConfirmationTitle">Edition</h5>
		      </div>
		      <div class="modal-body">
		        Etez-vous sur de vouloir continuer ?
		      </div>
			      <div class="modal-footer">
			        <button type="button" id="btnModalNon" class="btn btn-primary" data-dismiss="modal">Non</button>
			    	<button type="button" id="btnModalOui" class="btn btn-danger">Oui</button>
		    	</div>
		   	</div>
		</div>
	</div>
	<!-- Modal Suppresion -->
	<div class="modal static fade" id="ModalConfirmationSuppression" tabindex="-1" role="dialog" aria-labelledby="ModalConfirmationSuppressionTitle" aria-hidden="true">
	  	<div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="ModalConfirmationSuppressionTitle">Suppression</h5>
		      </div>
		      <div class="modal-body">
		        Etez-vous sur de vouloir continuer ?
		      </div>
			      <div class="modal-footer">
			        <button type="button" id="btnModalSupprNon" class="btn btn-primary" data-dismiss="modal">Non</button>
			    	<button type="button" id="btnModalSupprOui" class="btn btn-danger">Oui</button>
		    	</div>
		   	</div>
		</div>
	</div>
    
</div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
  <jsp:include page="/jsp/common/confirmModal.jsp" />
</body>

</html>