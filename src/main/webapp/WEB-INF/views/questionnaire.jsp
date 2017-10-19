<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questionnaire</title>

<style type="text/css">
.error {
	color: red;
}
</style>

</head>
<body>
	<h2>Questionnaire Creation Form</h2>
	<hr/>
	<form:form action="insertQuestionnaire" method="post" modelAttribute="questionnaire">
		<table>
			<tr>
				<th>Title</th>
				<td><form:input type="text" path="questionnaireTitle" id="questionnaireTitle" />
				<form:errors path="questionnaireTitle" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Description</th>
				<td><form:textarea path="questionnaireDescription" id="questionnaireDescription" />
				 <form:errors path="questionnaireDescription" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Remarks</th>
				<td><form:input path="questionnaireRemarks" id="questionnaireRemarks" />
				 <form:errors path="questionnaireRemarks" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Posted Date</th>
				<td><form:input type="date" path="postedDate" id="postedDate" />
				<form:errors path="postedDate" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Due Date</th>
				<td><form:input type="date" path="dueDate" id="dueDate" />
				 <form:errors path="dueDate" cssClass="error" /></td>
			</tr>

			<tr>
				<th>Target Respondents</th>
				<td>
				<form:checkboxes items="${respondentList}" path="targetRespondentList" id="targetRespondentList" />
 				<form:errors path="targetRespondentList" cssClass="error" />
 				</td> 
			</tr>
			<tr>
				<th>Sender Name</th>
				<td><form:input path="senderName" id="senderName" />
				 <form:errors path="senderName" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Sender Job Title</th>
				<td><form:input path="senderJobTitle" id="senderJobTitle" />
				 <form:errors path="senderJobTitle" cssClass="error" /></td>
			</tr>
			<tr>
				<td><button type="submit">Create Questionnaire</button></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
