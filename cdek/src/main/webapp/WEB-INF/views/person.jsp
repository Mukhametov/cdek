<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Main form</title>
</head>
<body>

	<h2>Adding person</h2>
	<form:form method="POST" action="/testwork/personAdd">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>


	<hr>


	<h2>View persons</h2>

	<form:form method="get" action="/testwork/person">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

	<c:if test="${not empty persons}">
		<table>
			<c:forEach var="person" items="${persons}">
				<tr>
					<td>${person.id}</td>
					<td>${person.name}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty persons}">
		No data
	</c:if>
	
</body>
</html>