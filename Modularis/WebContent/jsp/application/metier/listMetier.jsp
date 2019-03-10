<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Liste des Metiers</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

  <div role="main" class="container">
    <ul class="list-group mt-4">
      <c:forEach var="Metier" items="${metiers}">
        <li class="list-group-item list-group-item-light d-flex justify-content-between align-items-center">
          <span>${fn:escapeXml(Metier.nom)}</span>
          <div class="d-flex">
            <form method="get" action="MetierEdit">
              <input type="hidden" name="buttonEdit" value="${Metier.metierId}">
              <button type="submit" class="material-icons-btn material-icons material-icons-airy">edit</button>
            </form>
            <button data-toggle="modal" data-target="#modal-confirm"
              class="material-icons-btn material-icons material-icons-airy" 
              confirm-modal 
              confirm-modal-title="&Egrave;tes vous sur de vouloir supprimer <i>${fn:escapeXml(Metier.nom)}</i> ?"
              confirm-modal-button="Supprimer"
              confirm-modal-action=""
              confirm-modal-method="POST"
              confirm-modal-param='[{"name":"metierId","value":"${Metier.metierId}"}]'>delete</button>
          </div>
        </li>
      </c:forEach>
    </ul>
    <form method="post" class="form-inline mt-3">
      <div class="form-group">
        <input id="metierNom" class="form-control" name="metierNom" placeholder="Nouveau metier" required />
        <button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
      </div>
    </form>
  </div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
  <jsp:include page="/jsp/common/confirmModal.jsp" />
</body>

</html>