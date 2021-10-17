<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Create Bucketlist Page</title>
	<!-- for CSS styling-->
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="js/app.js"></script>
	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container mt-3">
		<div class="d-flex align-items-center justify-content-between">
			<h1>Create Bucket List Item</h1>
			<a href="/home">Dashboard</a>
		</div>
		<div>
			<form:form action="/bucketlist/new" method="post" modelAttribute="bucket" class="mt-3">
				<p>
					<form:label path="name">Title:</form:label>
					<form:errors path="name" class="text-danger"/>
					<form:input path="name" class="form-control"/>
				</p>
				<p>
					<form:label path="description">Description:</form:label>
					<form:errors path="description" class="text-danger"/>
					<form:textarea path="description" class="form-control"/>
				</p>
				<div class="d-flex justify-content-end">
					<a href="/home" class="btn btn-primary">Cancel</a>
					<input type="submit" value="Submit" class="ms-5 btn btn-primary"/>
				</div>
			</form:form>
		</div>
	
	</div>
</body>
</html>