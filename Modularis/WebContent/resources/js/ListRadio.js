$(document).ready(function () {
	
	//Pour le bouton supprimer
	$("#btnSupprimer").click(function(){
		//Verification
		
		//Demande si ok
		
		
		//Requete ajax
		$.ajax({ //fonction permettant de faire de l'ajax
			type: "POST",
			async:false,
			data: "id="+idCheckedRadio, // données à transmettre
		});
	});
	
	
	//Pour le bouton editer
	$("#btnEditer").click(function(){
		//Verification qu'il y a bien un radio de cocher
		
		//Recuperation des variable utile
		var idCheckedRadio ;
		var actionUrl;
		//Requete ajax vers l'attribut action du button ( comme sa réutillisable sur tous les truc du même genre )
		$.ajax({ //fonction permettant de faire de l'ajax
			type: "GET",
			url: actionUrl,
			async:false,
			data: "id="+idCheckedRadio, // données à transmettre
		});
		
		
	});
	
});