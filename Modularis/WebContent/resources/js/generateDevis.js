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
	
	$("#btnModalSupprOui").click(function(e){
		var input = $("<input>")
        .attr("type", "hidden")
        .attr("name", "btnSupprimer").val("true");
		//On ajout si correct le client selectionner
        $('#List').append(input);
		$("#List").submit();
		
	});
	
	$("#btnModalSupprImageOui").click(function(e){
		var input = $("<input>")
        .attr("type", "hidden")
        .attr("name", "supprImage");
		//On ajout si correct le client selectionner
        $('#formPost').append(input);
		$("#formPost").submit();
		
	});
		
});