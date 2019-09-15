  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>${product.name}</title>
</head>
<body>
	<div id = "wrapper">
		<h1>${product.name}</h1>
		<div id = "top">
			<h5>Price: $${product.price}</h5>
			<h5>Description: ${product.description}</h5>
		</div>
		<div id = "main">
			<div id = "categories">
				<h3>Categories:</h3>
				<ul>
					<c:forEach items = "${added}" var = "cat">
				        <li>${cat.name}</li>
		        </c:forEach>
				</ul>
			</div>
			<div id = "categoryadd">
				<h6>Add a category:</h6>
				<form action = "/products/${product.id}/add" method = "post" >
					<select name = "name">
						<c:forEach items = "${menu}" var = "menu">
							<option value = "${menu.name}">${menu.name}</option>
						</c:forEach>
					</select>
					<br><br>
					<input type = "submit" value = "Add">
				</form>
			</div>
		</div>
	</div>
</body>
</html>