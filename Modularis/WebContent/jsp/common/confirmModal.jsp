<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- MODAL SUPPRESSION-->
<div class="modal fade seminor-login-modal" data-backdrop="static" id="modal-confirm">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Corps Modal -->
      <div class="modal-body seminor-login-modal-body">
        <h5 class="modal-title text-center" id="confirm-modal-title"></h5>
        <button type="button" class="close" data-dismiss="modal">
          <span><i class="material-icons">close</i></span>
        </button>

        <!-- <form method="post" class="seminor-login-form" id="confirm-modal-form"> -->
        <form class="seminor-login-form" id="confirm-modal-form">
          <div id="confirm-modal-inputs">
            <!-- <input type="hidden" name="metierId" value="${Metier.metierId}"> -->
            <input type="hidden">
          </div>
          <div class="btn-check-log">  
            <button type="submit" class="btn-check-login" id="confirm-modal-button"></button>
          </div>
        </form>

      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
$(document).ready ( function () {
    initConfimModal();
});
</script>