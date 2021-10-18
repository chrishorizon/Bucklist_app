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
	<title>Edit Page</title>
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
			<h1><c:out value="${bucket.name }"/></h1>
			<a href="/home">Dashboard</a>
		</div>
		<div>
			<form:form action="/bucketlist/${bucket.id}/edit" method="post" modelAttribute="bucket">
				<input type="hidden" name="_method" value="put">
				<p>
					<form:label path="name">Title:</form:label>
					<form:errors path="name" class="text-danger" />
					<form:input path="name" class="form-control" />
				</p>
				<p>
					<form:label path="description">Description:</form:label>
					<form:errors path="description" class="text-danger" />
					<form:textarea path="description" class="form-control" />
				</p>
				<div class="d-flex">
					<a href="/home" class="me-3 btn btn-primary">Cancel</a>
					<input type="submit" value="Update" class="btn btn-primary" />
				</div>
			</form:form>
			<form action="/bucketlist/${bucket.id}/delete" method="post" class="d-flex justify-content-end">
				<input type="hidden" name="_method" value="delete" />
				<input type="submit" value="Delete" class="btn btn-danger" />
			</form>
		</div>

	</div>
</body>
</html>