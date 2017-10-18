<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question</title>

<style type="text/css">
.error {
	color: red;
}
</style>

</head>
<body>
	<h2>Question Creation Form</h2>
	<hr/>
	<form:form action="insertQuestion" method="post" modelAttribute="question">
		<table>
			
			<tr>
				<th>Description</th>
				<td><form:input path="questionDescription" /> <form:errors
						path="questionDescription" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Remarks</th>
				<td><form:input path="questionRemarks" /> <form:errors
						path="questionRemarks" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><button type="submit">Create Question</button></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
