<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="myApp">
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script
	src="<c:url value="/resources/static/js/queryManagementApp.js" />"></script>

<style>
input[type=checkbox] {
	-webkit-appearance: checkbox;
}
</style>

</head>
<body ng-controller="namesCtrl as ctrl">

	<button ng-click="changeVisibility()">Create Questionnaire</button>


	<h1>Angular Demo Page</h1>
	<hr>

	<div id="1" ng-show="flag1">

		<h2>Questionnaire Creation Form</h2>
		<hr />

		<table>
			<tr>
				<td>Title</td>
				<td><input type="text" ng-model="questionnaireTitle" /></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><textarea ng-model="questionnaireDescription"></textarea></td>
			</tr>
			<tr>
				<td>Remarks</td>
				<td><input ng-model="questionnaireRemarks" /></td>
			</tr>

			<tr>
				<td>Due Date</td>
				<td><input type="date" ng-model="dueDate" /></td>
			</tr>

			<tr>
				<td>Target Respondents</td>
				<td>
					<div ng-repeat="target in respondents">
						<label for="chkCustomer_{{target.targetID}}"> <input
							id="chkCustomer_{{target.targetID}}" type="checkbox"
							ng-model="target.Selected" ng-click="getValue()" />
							{{target.Name}}
						</label>
					</div>
				</td>
			</tr>


			<tr>
				<td>Sender Name</td>
				<td><input ng-model="senderName" /></td>
			</tr>
			<tr>
				<td>Sender Job Title</td>
				<td><input ng-model="senderJobTitle" /></td>
			</tr>
			<tr>
				<td><button ng-click="insertQ()">Submit</button></td>
			</tr>
		</table>
		</form>
		<hr>
	</div>

	<div ng-show="flag2">
		<h3>Questionnaire saved view (View for adding Question)</h3>

		<table>
			<tr>
				<td><label>Questionnaire ID</label></td>
				<td>{{questionnaireID}}</td>
			</tr>
			<tr>
				<td><label>Questionnaire Title</label></td>
				<td>{{questionnaireTitle}}</td>
			</tr>
			<tr>
				<td><label>Questionnaire Description</label></td>
				<td>{{questionnaireDescription}}</td>
			</tr>
			<tr>
				<td><label>Questionnaire Remarks</label></td>
				<td>{{questionnaireRemarks}}</td>
			</tr>

			<tr>
				<td><label>Due date</label></td>
				<td>{{dueDate|date : "dd/MM/yyyy"}}</td>
			</tr>
			<tr>
				<td><label>Respondents</label></td>
				<td>{{selectedRespondents}}</td>
			</tr>


		</table>
		<hr>
	</div>

	<div id="3" ng-show="flag3">
		DIV-3
		<h3>Inserted Question view</h3>

		<table>
			<thead>
				<tr>
					<th>ID.</th>
					<th>Question</th>
					<th>DataType</th>
					<th width="20%"></th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="u in questionArray">
					<td><label>{{u.questionID}}</label></td>
					<td><label>{{u.questionDescription}}</label></td>
					<td><label>{{u.responseDataType}}</label></td>
					<td><input type="checkbox" ng-model="u.Remove" /></td>
				</tr>
			</tbody>
		</table>
		<div>
			<button ng-click="publish()">Publish Questionnaire</button>
			<button ng-click="removeRow()">Remove Question</button>
		</div>














		</table>
		<hr>




	</div>

	<div id="4" ng-show="flag4">
		Question adding DIV
		<hr>
		<table>
			<tr>
				<td>Question</td>
				<td><textarea ng-model="questionDescription" rows="2" cols="50"> </textarea></td>
			</tr>
			<tr>
				<td>Response data type</td>
				<td><select ng-model="selectedResponseDataType"
					ng-options="responseDataType.name for responseDataType in responseDataTypes"></select>
				</td>
			</tr>
			<tr>
			<tr>
				<td><button type="submit" ng-click="insertQuest()">Add
						Question</button></td>
			</tr>

		</table>

		<hr>




	</div>


</body>
</html>