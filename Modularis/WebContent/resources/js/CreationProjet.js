$(document).ready(function () {

	var currentPhotoData;
	var currentPhotoInfo;

	//Affichage de l'image a la selection et on peut rajouter les info si besoin
	$(".custom-file-input").on("change", function() {

		var fileName = $(this).val().split("\\").pop();

		$(this).siblings(".custom-file-label").addClass("select").html(fileName);

		var input = this;

		currentPhotoInfo = $(this)[0].files;

		if (input.files && input.files[0]) {

			var reader = new FileReader();

			reader.onload = function (e) {

				$("#previsualisation").show();

				$("#imgPrevi").attr('src', e.target.result);

				currentPhotoData = e.target.result;                
			}
		}

		
		
		if(fileName != null && fileName != undefined && fileName != ""){
			
			reader.readAsDataURL(input.files[0]);
			
		}else{
			
			$(this).siblings(".custom-file-label").addClass("select").html("Selectionner un fichier");
			$("#imgPrevi").attr('src', "");
			$("#previsualisation").hide();
			
		}
		
	});

});