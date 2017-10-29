<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<html ng-app="myApp">

<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script
	src="<c:url value="/resources/static/js/responseManagementApp.js" />"></script>


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
	<button ng-click="viewQuestionnaire()">View Pending
		Questionnaire</button>
	<br>
	<br>

	<table class="table table-bordered" width="90%"
		ng-show="flagQuestionnaireView">
		<tr style="text-align: center">
			<th>ID</th>
			<th>Title</th>
			<th>Description</th>
			<th>Remarks</th>
			<th>Posted</th>
			<th>Due</th>
			<th>QuestionID</th>
			<th>Sender</th>
			<th>JobTitle</th>

		</tr>
		<tr ng-repeat="u in questionnaireList">

			<td><label>{{u.questionnaireID}}</label></td>
			<td><label>{{u.questionnaireTitle}}</label></td>
			<td><label>{{u.questionnaireDescription}}</label></td>

			<td><label>{{u.questionnaireRemarks}}</label></td>
			<td><label>{{u.postedDate|date: 'dd-MM-yyyy'}}</label></td>
			<td><label>{{u.dueDate|date: 'dd-MM-yyyy'}}</label></td>
			<td><label>{{u.questionIDList}}</label></td>
			<td><label>{{u.senderName}}</label></td>
			<td><label>{{u.senderJobTitle}}</label></td>
			<td>
				<button ng-click="viewQuest($index)">OPEN</button> <br> <br>
			</td>
		</tr>
	</table>

	<br>
	<br>

<div class="table-responsive">
	<table class="table table-bordered" width="50%"
		ng-show="flagQuestionView">

		<tr"text-align:center">
			<th>QuestionID</th>
			<th>Description</th>
			<th>Data Type</th>
			<th>Answer</th>
		</tr>

		<tr ng-repeat="q in questionList">

			<td><label>{{q.questionID}}</label></td>
			<td><label>{{q.questionDescription}}</label></td>
			<td><label>{{q.responseDataType}}</label></td>
			<td><input type={{dataType}} ng-model="answerList[$index]" /></td>						
		</tr>
		
		<tr ng-show="flagQuestionView">
			
			<td class="btn-group btn-group-xs">
				<button type="button" class="btn btn-primary"
					ng-click="saveAnswer()" ng-show="answerProcess">SAVE</button>&nbsp
				<button type="button" class="btn btn-primary"
					ng-click="modifyAnswer()" ng-hide="answerProcess">PUBLISH</button>
			</td>
		</tr>


	</table>


	</div>





</body>
</html>
