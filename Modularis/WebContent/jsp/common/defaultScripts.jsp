  <script type='text/javascript' src='<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js'></script>
  <script type='text/javascript' src='<%=request.getContextPath()%>/resources/js/bootstrap.min.js'></script>
  <script type='text/javascript' src='<%=request.getContextPath()%>/resources/dwr/engine.js'></script>
  <script type='text/javascript' src='<%=request.getContextPath()%>/resources/dwr/utils.js'></script>
  <script type="text/javascript">
  
  	/*Init du menu*/
  	$(document).ready ( function () {
  	    $("#open-btn").click(e => {$("#mySidenav").css('width','250px')});
  	  	$("#close-btn").click(e =>{$("#mySidenav").css('width','0px')});
  	});

  </script>