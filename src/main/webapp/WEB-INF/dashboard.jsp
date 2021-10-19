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
	<title>Home page</title>
	<!-- for CSS styling-->
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="js/app.js"></script>
	<!-- for Bootstrap CSS 
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" /> -->
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="top-nav">
			<h3 class="username-nav"><c:out value="${loggedInUser.userName}"/>'s</h3>
			<a href="/logout">Logout</a>
		</div>
		<div>
			<h1 class="bucket-nav">Bucket List</h1>
			<a style="float:right" href="/bucketlist/new">+ Add Item</a>
			<br>
			<div>
				<table>
					<thead>
						<tr>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="c" items="${loggedInUser.buckets}">
							<tr>
								<td><input type="checkbox">
								<a href="/bucketlist/${c.id}" class="edit-btn"><c:out value="${c.name}"/></a>
								<td class="action-btn">
								<a href="/bucketlist/${c.id}/edit" class="custom-btn btn-11">Edit</a>
									<form action="/bucketlist/${c.id}/delete" method="post">
											<input type="hidden" name="_method" value="delete" />
											<input type="submit" value="Delete" class="custom-btn btn-1" />
									</form></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>
</body>
</html>