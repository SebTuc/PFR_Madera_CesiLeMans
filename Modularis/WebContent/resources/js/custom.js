/*Fichier contenant toutes les interactions dynamiques du site ou futur code liaison avec le back en JEE (Eventuellement de l'AJAX pour la recherche,...)*/
 
/*Init du menu*/
$(document).ready ( function () {
    $("#open-btn").click(e => {$("#mySidenav").css('width','250px')});
    $("#close-btn").click(e =>{$("#mySidenav").css('width','0px')});
    
    
    /*
     * Initialisation de la dataTable
     * 
     */
    
    $('#Edition').DataTable();
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