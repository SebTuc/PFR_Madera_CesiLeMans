<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>

	<head>		
		<meta charset="ISO-8859-1">
		<title>Blank Page</title>
		<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">	
	</head>
	
	<body>
	
		<div class="container-fluid">
	
			<jsp:include page="/jsp/common/menuNavbar.jsp" />
			
			<br><br><br>
			
			<div class="row">
				<div class="col">
					<h1 class="display-4">Blank</h1>
				</div>
			</div>
			
			<!-- Implement Code Here ;) -->						
			
		</div>
	
		<script src="resources/js/bootstrap.min.js"></script>
	
	</body>
	
</html>