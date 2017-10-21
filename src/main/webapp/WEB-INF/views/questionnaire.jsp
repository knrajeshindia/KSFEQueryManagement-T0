<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html ng-app="queryManagementApp">
<head>


<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
0
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script>
var app = angular.module('queryManagementApp',[]);
app.controller('questionnaireController', function($scope) {

scope.insertQuestionnaire=function($scope, $window, $http) {
//DIV-1
$scope.flag1 = false;
//DIV-2
$scope.flag2 = false;
//DIV-3
$scope.flag3 = false;
//DIV-4
$scope.flag4 = false;

//Initialise variables to null
$scope.questionnaireTitle="";
$scope.questionnaireDescription="";
$scope.questionnaireRemarks="";
$scope.dueDate="";
$scope.targetRespondentIDList="";
$scope.senderName="";
$scope.senderJobTitle="";

$http({method: "get",
       url: "test",}).then
       (function (result) {
                        $window.alert("Server response-SUCCESS!");
                        $scope.flag1 = true;
                        },
       function (result) {
                        $window.alert("Server response-FAILURE! Please try again later");
       });



var questionnaireFormData={
"questionnaireTitle":$scope.questionnaireTitle,
"questionnaireDescription":$scope.questionnaireDescription,
"questionnaireRemarks":$scope.questionnaireRemarks,
"dueDate":$scope.dueDate,
"targetRespondentIDList":$scope.targetRespondentIDList,
"senderName":$scope.senderName,
"senderJobTitle":$scope.senderJobTitle};
$http.post('insertQ',questionnaireFormData).then(function (result){
$scope.response = angular.fromJson(result.data);
                        if ($scope.response.status === "SUCCESS") {
                            $scope.message = $scope.response.message;
                            $scope.data = angular.fromJson($scope.response.data).accountStatus;
                            $scope.status = $scope.response.message;
                            $scope.questionnaireID = angular.fromJson($scope.response.data).questionnaireID;
                            $scope.displayMessage=$scope.status + " | " + $scope.message + " | " + $scope.data;
                            $window.alert($scope.displayMessage);
                            }


},function (result){

}
 $window.alert("Server response-FAILURE! Please try again later");




)


}







});



</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questionnaire</title>

<style type="text/css">
.error {
	color: red;
}
</style>

</head>
<body ng-controller="questionnaireController">

	<div id="1" ng-show="flag1" >

		<h2>Questionnaire Creation Form</h2>
		<hr />
		<form:form action="insertQuestionnaire" method="post" modelAttribute="questionnaire">
			<table>
				<tr>
					<th>Title</th>
					<td><form:input type="text" path="questionnaireTitle" ng-model="questionnaireTitle"
							id="questionnaireTitle" /> <form:errors
							path="questionnaireTitle" cssClass="error" /></td>
				</tr>
				<tr>
					<th>Description</th>
					<td><form:textarea path="questionnaireDescription" ng-model="questionnaireDescription"
							id="questionnaireDescription" /> <form:errors
							path="questionnaireDescription" cssClass="error" /></td>
				</tr>
				<tr>
					<th>Remarks</th>
					<td><form:input path="questionnaireRemarks" ng-model="questionnaireRemarks"
							id="questionnaireRemarks" /> <form:errors
							path="questionnaireRemarks" cssClass="error" /></td>
				</tr>

				<tr>
					<th>Due Date</th>
					<td><form:input type="date" path="dueDate" ng-model="dueDate" id="dueDate" /> <form:errors
							path="dueDate" cssClass="error" /></td>
				</tr>

				<tr>
					<th>Target Respondents</th>
					<td><form:checkboxes items="${respondentList}"
							path="targetRespondentIDList" ng-model="targetRespondentIDList" id="targetRespondentIDList" /> <form:errors
							path="targetRespondentIDList" cssClass="error" /></td>
				</tr>
				<tr>
					<th>Sender Name</th>
					<td><form:input path="senderName" ng-model="senderName" id="senderName" /> <form:errors
							path="senderName" cssClass="error" /></td>
				</tr>
				<tr>
					<th>Sender Job Title</th>
					<td><form:input path="senderJobTitle" ng-model="senderJobTitle" id="senderJobTitle" />
						<form:errors path="senderJobTitle" cssClass="error" /></td>
				</tr>
				<tr>
					<td><button type="submit" ng-click="insertQuestionnaire()">Create Questionnaire</button></td>
				</tr>
			</table>
		</form:form>

	</div>
<!-- ---------------------------------------------------------------------------------------------------------------------------------- -->
	<div id="2" ng-show="flag2">

		<h2>Question Creation Form</h2>
		<hr />
		<form:form action="insertQuestion" method="post"
			modelAttribute="question">
			<table>
				<tr>
					<th>Question</th>
					<td><form:textarea type="text" path="questionDescription"
							id="questionDescription" rows="2" cols="50" /> <form:errors
							path="questionDescription" cssClass="error" /></td>
				</tr>
				<tr>
					<th>Response data type</th>
					<td><form:select path="responseDataType"
							items="${dataTypeList}" /> <form:errors
							path="questionDescription" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:hidden path="questionnaireID" id="questionnaireID"
							value="1" /> <form:errors path="questionnaireID"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td><button type="submit">Add Question</button></td>
				</tr>

			</table>
		</form:form>

	</div>
<!-- ---------------------------------------------------------------------------------------------------------------------------------- -->
	<div id="3" ng-show="flag3">

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

	</div>
	<!-- ---------------------------------------------------------------------------------------------------------------------------------- -->

	<div id="4" ng-show="flag4">

	</div>
	<!-- ---------------------------------------------------------------------------------------------------------------------------------- -->
DEMO PAGE
	<div></div>
<!-- ---------------------------------------------------------------------------------------------------------------------------------- -->

</body>
</html>
