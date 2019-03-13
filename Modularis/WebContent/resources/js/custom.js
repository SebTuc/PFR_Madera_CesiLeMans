/*Fichier contenant toutes les interactions dynamiques du site ou futur code liaison avec le back en JEE (Eventuellement de l'AJAX pour la recherche,...)*/
 
/*Init du menu*/
$(document).ready ( function () {
    $("#open-btn").click(e => {$("#mySidenav").css('width','250px')});
    $("#close-btn").click(e =>{$("#mySidenav").css('width','0px')});
    
    
    /*
     * Initialisation de la dataTable
     */
    var table = $('#Edition').DataTable({
        
    	"paging":   true,
        "ordering": true,
		"sDom": '<"searchBox">rtip',
        "info":     false,
		"autoWidth": false
		
    });
 
    // sert a placer la pagination et la serch table où l'on veut
    var table_paginate = $('#Edition_paginate');
	$('#new_table_paginate').html(table_paginate);
    
    $('#search_table').on( 'keyup', function () {
        table.search( this.value ).draw();
    } );
    
    //Selection item
    $('#Edition tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
    
    //-------------------   Delete select item  -----------------------
    $('#buttonDeleteRow').click( function () {
    	 var nbSelection = $('.selected').length;
         if(nbSelection != 0){
        	//On demande si ok 
        	 $('#ModalConfirmation').modal({backdrop: 'static', keyboard: false, show: true});
         	
         	$('#btnModalNon').click(function(){
         		
         		$('#ModalConfirmation').modal('hide');
         		
         	});
         	$('#btnModalOui').click(function(){
     	    	//Ici executer le code pour supprimer
     	    	var idValue= "";
     	    	$('.selected').each(function(index){
     	        	
     	        	var data = this;
     	        	for(var test in data.cells){
     	        		var tdValue = data.cells[test].innerText
     	        		if (tdValue != undefined){
     	        			idValue = data.cells[test].attributes[0].value
     	        		}
     	        	}
     	        	
     	    	});
     	    	 $.ajax({ // fonction permettant de faire de l'ajax
     				   type: "POST",
     				   data: "id="+idValue+"&action=Delete", // données à transmettre
     				   success: function(msg){ // si l'appel a bien fonctionné
     						if(msg==1) // si a fonctionnée
     						{
     	
     						}
     						else // si n'a pas fonctionnée
     						{
     							
     						}
     				   }
     			 });
     	    	 //On supprime pour le visuel (cette methode ne supprime pas en base
     	        table.row('.selected').remove().draw( false );
     	        $('#ModalConfirmation').modal('hide');
         	});
         }
    	
    } );
    
    
    
    //------------------  Edition select item  ----------------------------
    $('#buttonEditRow').click(function edit() {
    	//Voir a la limite pour enlever l'ajout classique
        var nbSelection = $('.selected').length;
        if(nbSelection != 0){
	        $('.selected').each(function(index){
	        	
	        	var data = this;
	        	$(this).removeClass('selected');
	        	var newTr = '<tr class="Update" >';
	        	for(var test in data.cells){
	        		var tdValue = data.cells[test].innerText
	        		if (tdValue != undefined){
	        			var idValue = data.cells[test].attributes[0].value
	        			newTr += '<td><input id="'+idValue+'" type="text" style="text-align: center" class="form-control" value="'+tdValue+'"></td>';
	        		}
	        	}
	        	newTr += '</tr>'
	        	$(this).replaceWith(newTr);
	        });
	        $('#buttonEditRow').remove();
	        $('.modification').append('<div class="col-6 update_bouton"><button id="button_update" class="btn btn-warning btn-block">Update</button></div>')
	        $('.modification').append('<div class="col-6 return_button"><button id="button_retour" class="btn btn-default btn-block">Return</button></div>')
	        
	        $('#button_retour').on( "click", function() {
			
				$('.Update').each(function(index){
					
					var data = $(this).find('input');
					
					$(this).removeClass('Update');
					
					if (($(this).rowIndex) % 2 != 1){
						
						var newTr = '<tr style="text-align: center" role ="row" class="odd">';
						
					}else{
						
						var newTr = '<tr style="text-align: center" role ="row" class="even">';
						
					}
					for(var test in data){
						var tdValue = data[test].defaultValue;
						if (tdValue != undefined){
							var idValue = data[test].attributes[0].value					
							newTr += '<td id="'+idValue+'">'+tdValue+'</td>';
						}
					}

					newTr += '</tr>'
					
					$(this).replaceWith(newTr);
				});
				$('.return_button').remove();
				$('.update_bouton').remove();
				$('.modification').append('<button id="buttonEditRow" class="btn btn-warning btn-block">Edit selected row</button>')
				$('#buttonEditRow').on( "click", function (){
					//on reinstance
					edit()
				});	
	        });
	        $('#button_update').on("click",function() {
				 
				 //On demande confirmation
	        	$('#ModalConfirmation').modal({backdrop: 'static', keyboard: false, show: true});
	        	
	        	$('#btnModalNon').click(function(){
	        		
	        		$('#ModalConfirmation').modal('hide');
	        		
	        	});
	        	$('#btnModalOui').click(function(){
	        		//On mes a jour ou non selon confirmation ou non
	        		var idValue= "";
					 var valueSend= "";
					 $('.Update').each(function(index){
						 var data = $(this).find('input');
						 for(var test in data){
								var tdValue = data[test].value;
								if (tdValue != undefined){
									if(tdValue == ""){
										continue;
									}
									idValue = data[test].attributes[0].value;					
									valueSend = tdValue;
								}
						}
					 });
					 
					 $.ajax({ // fonction permettant de faire de l'ajax
						   type: "POST",
						   data: "id="+idValue+"&valeur="+valueSend+"&action=Edition", // données à transmettre
					 });
					 $('#ModalConfirmation').modal('hide');
					 //faire de l'asynchrone pur pour pas avoir besoin de refresh la page
					 $('.Update').each(function(index){
							
							var data = $(this).find('input');
							
							$(this).removeClass('Update');
							
							if (($(this).rowIndex) % 2 != 1){
								
								var newTr = '<tr style="text-align: center" role ="row" class="odd">';
								
							}else{
								
								var newTr = '<tr style="text-align: center" role ="row" class="even">';
								
							}
							for(var test in data){
								var tdValue = data[test].value;
								if (tdValue != undefined){
									var idValue = data[test].attributes[0].value					
									newTr += '<td id="'+idValue+'">'+tdValue+'</td>';
								}
							}

							newTr += '</tr>'
							
							$(this).replaceWith(newTr);
						});
					 	
						$('.return_button').remove();
						$('.update_bouton').remove();
						$('.modification').append('<button id="buttonEditRow" class="btn btn-warning btn-block">Edit selected row</button>')
						$('#buttonEditRow').on( "click", function (){
							//on reinstance le bouton
							edit()
						});	
						
	        	});
				 
			 });
        }
    } );
    
    
    
});



/**
 * Initialise le lancement des modal de confirmation
 */
function initConfimModal () {
  let domElements;
  domElements = $('[confirm-modal]');
  if(domElements.length > 0){
    $(domElements).each( (id,element) => {
      $(element).on("click", launchConfimModal);
    });
  }

}

/**
 * Modifie la modal de confirmation avant sont lancement
 * @param {HTMLElement} element Element du DOM ayant appelé la methode
 */
function launchConfimModal(element){
  let title,buttonName,methodForm,actionForm,paramString,params;
  title = $(element.target).attr("confirm-modal-title");
  buttonName = $(element.target).attr("confirm-modal-button");
  actionForm = $(element.target).attr("confirm-modal-action");
  methodForm = $(element.target).attr("confirm-modal-method");
  paramString = $(element.target).attr("confirm-modal-param");

  title.length > 0 ? $("#confirm-modal-title").html(title):$("#confirm-modal-title").html("Confirmation");
  buttonName.length > 0 ? $("#confirm-modal-button").html(buttonName):$("#confirm-modal-button").html("Confirmation");
  actionForm.length > 0 ? $("#confirm-modal-form").attr("action",actionForm) :$("#confirm-modal-form").attr("action",'');
  
  if (methodForm.length > 0 && (methodForm.toUpperCase() === "POST" || methodForm.toUpperCase() === "GET")) {
    $("#confirm-modal-form").attr("method",methodForm.toUpperCase())
  } else {
    $("#confirm-modal-form").attr("method",'get');
  }

  if (paramString.length > 0) {
    try {

      // Parsing d'un objet JSON simple type {"name": "name", "value": "value"}
      params = JSON.parse(paramString);      

      let confirmModalInputs = $("#confirm-modal-inputs");
      
      $.each(params, function(i, param) {
        confirmModalInputs.append('<input type="hidden" name="'+param.name+'" value="'+param.value+'">');
      });
      
    } catch (error) {
      console.log(error);
    }
  }

}