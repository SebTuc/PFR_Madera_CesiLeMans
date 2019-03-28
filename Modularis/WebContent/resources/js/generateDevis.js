$(document).ready(function () {

	$("#btnCreer").click(function(){

		var idClient;
		//On ajout si correct le client selectionner

		idClient = $("#Client").val();
		if(idClient != null && idClient != ""){
			$("#clientId").val(idClient);
			$("#ModalCreationDevis").hide();
			//on lance le submit du form
			$("#List").submit();
		}
		
	});
});