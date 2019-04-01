<!-- MODAL SUPPRESSION-->
<div class="modal fade seminor-login-modal" data-backdrop="static" id="confirm-modal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Corps Modal -->
      <div class="modal-body seminor-login-modal-body">
        <h5 class="modal-title text-center" id="confirm-modal-title"></h5>
        <button type="button" class="close" data-dismiss="modal">
          <span><i class="material-icons">close</i></span>
        </button>

        <form class="seminor-login-form" id="confirm-modal-form">
          <div id="confirm-modal-inputs">
          </div>
          <div class="btn-check-log">  
            <button type="submit" class="btn-check-login" id="confirm-modal-button"></button>
          </div>
        </form>

      </div>
    </div>
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

<script type="text/javascript">
$(document).ready ( function () {
    initConfimModal();
});
</script>