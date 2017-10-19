<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<hr />
	<form:form action="insertQuestion" method="post"
		modelAttribute="question">
		<table>



			<tr>
				<th>Question</th>
				<td><form:textarea type="text" path="questionDescription"
						id="questionDescription" rows = "2" cols = "50" /> <form:errors
						path="questionDescription" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Response data type</th>
				<td><form:select path="responseDataType"
						items="${dataTypeList}" /> <form:errors
						path="questionDescription" cssClass="error" /></td>
			</tr>



			<tr>

				<td><form:hidden path="questionnaireID"	id="questionnaireID" value="1" /> <form:errors
						path="questionnaireID" cssClass="error" /></td>
			</tr>
			<tr>
				<td><button type="submit">Add Question</button></td>
			</tr>

		</table>
	</form:form>
</body>
</html>
