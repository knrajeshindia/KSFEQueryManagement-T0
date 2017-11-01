<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html ng-app="myApp">

<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script src="<c:url value="/resources/static/js/responseManagementApp.js" />"></script>


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
	<button ng-click="viewQuestionnaire()">View Valid Questionnaire</button>
	<button ng-click="#">View Complete Questionnaire</button>
	<br>

	<div ng-show="flagQuestionnaireView">
		<table width="90%">
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Description</th>
				<th>Remarks</th>
				<th>Posted</th>
				<th>Due</th>
				<th>QuestionID</th>
				<th>Sender</th>
				<th>JobTitle</th>
				<th>ResponseID</th>
				<th>Status</th>
			</tr>

			<tr ng-repeat="questionnaire in questionnaireList">

				<td><label>{{questionnaire.questionnaireID}}</label></td>
				<td><label>{{questionnaire.questionnaireTitle}}</label></td>
				<td><label>{{questionnaire.questionnaireDescription}}</label></td>
				<td><label>{{questionnaire.questionnaireRemarks}}</label></td>
				<td><label>{{questionnaire.postedDate|date:
						'dd-MM-yyyy'}}</label></td>
				<td><label>{{questionnaire.dueDate|date: 'dd-MM-yyyy'}}</label></td>
				<td><label>{{questionnaire.questionIDList}}</label></td>
				<td><label>{{questionnaire.senderName}}</label></td>
				<td><label>{{questionnaire.senderJobTitle}}</label></td>
				<td><label>{{questionnaire.responseID}}</label></td>
				<td><label>{{questionnaire.responseStatus}}</label></td>
				<td>
				<button ng-click="viewQuest($index)" ng-hide={{questionnaire.responseFlag}} >OPEN</button> 
				<button ng-click="modifyResponse($index)" ng-show={{questionnaire.responseFlag}} >MODIFY</button><br></td>
			</tr>
		</table>
	</div>

	<br>

	<div ng-show="flagQuestionView" class="table-responsive">
		<p>
			<strong>QUESTIONNAIRE - ID :{{questionnaireID}}</strong>
		</p>
		<table class="table table-bordered" width="50%">
<thead>
				<tr>
					<th>QuestionID</th>
					<th>Description</th>
					<th>Data Type</th>
					<th>Answer</th>
				</tr>
			</thead>

			<tr ng-repeat="q in questionList">

				<td><label>{{q.questionID}}</label></td>
				<td><label>{{q.questionDescription}}</label></td>
				<td><label>{{q.responseDataType}}</label></td>
				<td><input type={{q.responseDataType}} ng-model="answerList[$index]" placeholder={{answerList[$index]}} /></td>
			</tr>

			<tr>
				<td>Attach File</td>
				<td><input type="file" ng-model="file" enctype="multipart/form-data" /></td>
			</tr>
			<tr>
				<td>File Description</td>
				<td><input type="text" ng-model="fileDescription" /></td>
			</tr>
			<tr>
				<td>Remarks</td>
				<td><input type="text" ng-model="responseRemarks" /></td>
			</tr>
			<tr>
				<td>Respondent Name</td>
				<td><input type="text" ng-model="respondentName" /></td>
			</tr>
			<tr>
				<td>Respondent Job Title</td>
				<td><input type="text" ng-model="respondentJobTitle" /></td>
			</tr>
			<tr ng-show="flagQuestionView">

				<td>
					<button type="button" ng-click="saveAnswer()" ng-hide="flagAnswerProcess">SAVE</button>
					<button type="button" ng-click="modifyAnswer()" ng-show="flagAnswerProcess">PUBLISH</button>
				</td>
			</tr>


		</table>


	</div>
	
	<div ng-show="flagResponseConfirmation" ><br>	
	<strong>Response Saved: ID - {{responseID}}</strong>	
	</div>





</body>
</html>