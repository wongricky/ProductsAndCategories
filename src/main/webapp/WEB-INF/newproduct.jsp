<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>New Product</title>
</head>
<body>
	<div id = "wrapper">
		<h1>New Product</h1>
		<br>
		<form:form action = "/products/new/process" method = "post" modelAttribute = "product">
			<p><form:errors path = "name"/></p>
			<form:label path = "name">Name: </form:label>
			<form:input path = "name"/><br>
			<p><form:errors path = "description"/></p>
			<form:label path = "description">Description: </form:label><br>
			<form:textarea cols = "50" rows = "4" path = "description"/><br><br>
			<form:label path = "price">Price: </form:label>
			<form:input path = "price"/><br><br>
			<input type = "submit" value = "Create">
		</form:form>
	</div>
</body>
</html>