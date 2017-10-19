<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Response</title>

<style type="text/css">
.error {
	color: red;
}
</style>

</head>
<body>
	<h2>Response Creation Form</h2>
	<hr />
	<form:form action="insertResponse" method="post"
		modelAttribute="response">
		<form:hidden path="unitID"	id="unitID" value="1" />
		<form:hidden path="questionnaireID"	id="questionnaireID" value="1" />
		<form:hidden path="responseDate"	id="responseDate" value="2017-10-19" />
		<form:hidden path="responseStatus"	id="complete" value="1" />
		<table>

			<tr>
				<th>Remarks</th>
				<td><form:textarea path="responseRemarks" id="responseRemarks" />
					<form:errors path="responseRemarks" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Attachment File Description</th>
				<td><form:input type="text" path="attachmentDescription"
						id="attachmentDescription" /> <form:errors
						path="attachmentDescription" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Attach File</th>
				<td><form:input type="file" path="attachmentFile"
						id="attachmentFile" /> <form:errors path="attachmentFile"
						cssClass="error" /></td>
			</tr>
			<tr>
				<th>Respondent Name</th>
				<td><form:input type="text" path="respondentName"
						id="respondentName" /> <form:errors
						path="respondentName" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Respondent Job Title</th>
				<td><form:input type="text" path="respondentJobTitle"
						id="respondentJobTitle" /> <form:errors path="respondentJobTitle"
						cssClass="error" /></td>
			</tr>

			<tr>
				<td><button type="submit">Create Response</button></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
