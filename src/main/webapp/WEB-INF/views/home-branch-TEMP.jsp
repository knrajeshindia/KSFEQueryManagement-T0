<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="myApp">

<head>
<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script	src="<c:url value="/resources/static/js/responseManagementApp.js" />"></script>

<title>Response</title>

<style type="text/css">
.error {
	color: red;
}
input[type=checkbox] {
	-webkit-appearance: checkbox;
}

</style>

</head>
<body ng-controller="responseController">
	<h2>Response Form</h2>
	<hr>
		<button ng-click="viewQuestionnaire()" ng-show="flag1Button1">View Pending Questionnaire</button>
		<br><br>

		<table class="table table-sm table-inverse" >
		<thead>
		<tr>
		<th>INDEX</th><th>ID</th><th>Title</th><th>Description</th><th>Remarks</th><th>Posted</th><th>Due</th><th>QuestionID</th><th>Sender</th><th>JobTitle</th>

		</tr>
</thead>
		<tr ng-repeat="u in questionnaireList">
		                    <td><label>{{$index}}</label></td>
        					<td><label>{{u.questionnaireID}}</label></td>
        					<td><label>{{u.questionnaireTitle}}</label></td>
        					<td><label>{{u.questionnaireDescription}}</label></td>

        					<td><label>{{u.questionnaireRemarks}}</label></td>
                            <td><label>{{u.postedDate|date: 'dd-MM-yyyy'}}</label></td>
                            <td><label>{{u.dueDate|date: 'dd-MM-yyyy'}}</label></td>
                            <td><label>{{u.questionIDList}}</label></td>
                            <td><label>{{u.senderName}}</label></td>
                            <td><label>{{u.senderJobTitle}}</label></td>
                            <td> <button ng-click="open($index)">OPEN</button><br><br></td>
                           </tr>

			<tr>
				<th>Remarks</th>
				<td><textarea path="responseRemarks" id="responseRemarks" />
					<errors path="responseRemarks" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Attachment File Description</th>
				<td><input type="text" path="attachmentDescription"
						id="attachmentDescription" /> <errors
						path="attachmentDescription" cssClass="error" /></td>
			</tr>
			<tr>
				<th>Attach File</th>
				<td><input type="file" path="attachmentFile"
						id="attachmentFile" /> <errors path="attachmentFile"
						cssClass="error" /></td>
			</tr>
			<tr>
				<th>Respondent Name</th>
				<td><input type="text" path="respondentName"
						id="respondentName" /> <errors
						path="respondentName" cssClass="error" /></td>
			</tr>
<tr><th>Respondent Job Title</th>
				<td><input type="text" path="respondentJobTitle"
						id="respondentJobTitle" /> <errors path="respondentJobTitle"
						cssClass="error" /></td>
			</tr>

			<tr>
				<td><button type="submit">Create Response</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
