$(document).ready(function () {
	

	var nbComposant = 0;
	
	
	$("#btnAjouter").click(function(){
		
		//Initialize var
		var nomComposant = $("option:selected","#Composant")[0].text;
		var idComposant = $("#Composant").val();
		var quantite = $("#nombreComposant").val();

		
		if( quantite != null && quantite != "" && quantite > 0){
			if(idComposant != null && nomComposant != null && nomComposant != ""){
				if(!ComposantExistInModule(idComposant)){

					$("#ListComposant").append('<li class="list-group-item list-composant">'+
							'<div class="row">'+
								'<div class="col-8">'+
									'<input class="form-control" idComposant="'+idComposant+'" value="'+nomComposant+'" style="text-align:center" disabled>'+
								'</div>'+
								'<div class="col-3">'+
									'<input class="form-control" quantiteComposant="'+quantite+'" value="'+quantite+'" style="text-align:center" disabled>'+
								'</div>'+
								'<div class="col-1">'+
									'<input class="form-check-input" type="radio" name="selectItems" style="margin-top: 13px;margin-left: 0px;">'+
								'</div>'+
							'</div>'+
							'</li>');
					
					
						
					$('#ModalAddComposant').modal('hide');
						
				}else{
					alert("Vous avez deja ajoute se composant !")
				}
			}else{
				alert("Ajouter un composant.");
			}
			
			
		}else{
			
			alert("La quantite doit etre superieur a 0 !");
		}
		
		
		//On verifie qu'on recupe des info correct et que se composant n'as pas déjà était ajouter
		
		
			
	});
	
	
	$("#supprLastComposant").click(function(){
		
		var data =$("input[name='selectItems']:checked")[0].parentNode.parentNode.parentNode;
		//Voir pour selectionner via un chiffre genre le 2eme a la place de numItems
		if (data != null && data != undefined){
			data.remove();
		}
	});
	
	$("#submitModule").click(function(){
		
		//Initialize var
		var nomModule = $("#nom").val() ;
		var gamme = $("#gamme").val();
		var checkedAngle;
		
		if(($("#Angle").is(":checked"))){
			checkedAngle = true;
		}else{
			checkedAngle = false;
		}
		
		var typeAngle = $("#typeAngle").val();
		var uniteMesure = $("#uniteMesure").val();
		var ListComposant = [];
		var ListQuantite = [];
		
		$(".list-composant").each(function(){
			//sa serais mieux une map
			var data = this;
			var idComposant = data.lastElementChild.childNodes[0].childNodes[0].attributes[1].value;
			var quantiteComposant = data.lastElementChild.childNodes[1].childNodes[0].attributes[1].value;
			
			ListComposant.push(idComposant);
			ListQuantite.push(quantiteComposant)
			
		});
		
		//verifier que tout est bon 
		var flag = false;
		if(nomModule != null && nomModule != ""){
			if(gamme != null && gamme != ""){
				if(uniteMesure != null && uniteMesure != ""){
					if(checkedAngle){
						if(typeAngle != null && typeAngle != ""){
							if(ListComposant.length != 0 && ListQuantite.length != 0){
								flag = true;
							}
							
						}
					}else{
						if(ListComposant.length != 0 && ListQuantite.length != 0){
							typeAngle = "";
							flag = true;
						}
					}
				}
			}
		}
		
		if(flag == true){
			var returnHtml = '';
			//request Ajax (d'abord ajout du module ensuite les liaison moduleXcomposant )
			if (checkedAngle){
				$.ajax({
					  method: "POST",
					  data: {ListComposant:ListComposant, nomModule:nomModule, ListQuantite:ListQuantite , gamme:gamme , typeAngle:typeAngle , uniteMesure:uniteMesure},
					  dataType:'json',
					  async: false,
					  success: function(data){
						  returnHtml = data;
				      } 
				});
			}else{
				
				$.ajax({
					  method: "POST",
					  data: {ListComposant:ListComposant, nomModule:nomModule, ListQuantite:ListQuantite , gamme:gamme , uniteMesure:uniteMesure},
					  dataType:'json',
					  async: false,
					  success: function(data){
						  returnHtml = data;
				      }
				});
				
			}
			//on ajoute la value au form hidden
			$("#sendSubmit").val(returnHtml.retour);
			
			//On submit
			$("#formSubmit").submit();
		}else{
			
			alert("Verifier les saisies !")
			
		}
		
	});
	
	function ComposantExistInModule(id){
		var flag = false;
		$(".list-composant").each(function(){
			
			var data = this;
			var idComposant = data.lastElementChild.childNodes[0].childNodes[0].attributes[1].value;
			if(idComposant == id){
				flag = true;
			}
			
		});
		
		return flag;
		
	}
	
	
	
});