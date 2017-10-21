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

</head>
<body ng-controller="namesCtrl as ctrl">

	<button ng-click="changeVisibility()">Create Questionnaire</button>
	<br>
	<br>
	<button ng-click="test()">Test Server</button>

	<h1>Angular Demo Page</h1>
	<hr>

	<div id="1" ng-show="flag1">

		<h2>Questionnaire Creation Form</h2>
		<hr />

		<table>
			<tr>
				<td>Title</td>
				<td><input type="text" data-ng-model="questionnaireTitle" /></td>
			</tr>




			<tr>
				<td>Description</td>
				<td><textarea data-ng-model="questionnaireDescription"></textarea>
				</td>
			</tr>
			<tr>
				<td>Remarks</td>
				<td><input data-ng-model="questionnaireRemarks" /></td>
			</tr>

			<tr>
				<td>Due Date</td>
				<td><input type="date" data-ng-model="dueDate" /></td>
			</tr>

			<!-- 				<tr> -->
			<!-- 					<td>Target Respondents</td> -->
			<%-- 					<td><input type="checkbox" items="${respondentList}" --%>
			<!-- 							data-ng-model="targetRespondentIDList" data-ng-model="targetRespondentIDList" id="targetRespondentIDList" /> </td> -->
			<!-- 				</tr> -->
			<tr>
				<td>Sender Name</td>
				<td><input data-ng-model="senderName" /></td>
			</tr>
			<tr>
				<td>Sender Job Title</td>
				<td><input data-ng-model="senderJobTitle" /></td>
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
				<td><label>Questionnaire Remarks</label></td>
				<td>{{targetRespondentIDList}}</td>
			</tr>
			<tr>
				<td><label>Due date</label></td>
				<td>{{dueDate}}</td>
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
				<tr ng-repeat="u in questions">
					<td><span ng-bind="u.questionID"></span></td>
					<td><span ng-bind="u.questionDescription"></span></td>
					<td><span ng-bind="u.responseDataType"></span></td>

					<td>
						<button type="button" ng-click="ctrl.edit(u.id)"
							class="btn btn-success custom-width">Edit</button>
						<button type="button" ng-click="ctrl.remove(u.id)"
							class="btn btn-danger custom-width">Remove</button>
					</td>
				</tr>
			</tbody>
		</table>














		</table>
		<hr>




	</div>

	<div id="4" ng-show="flag4">
		Question adding DIV
		<hr>
		<table>
			<tr>
				<td>Question</td>
				<td><textarea data-ng-model="questionDescription" rows="2"
						cols="50"> </textarea></td>
			</tr>
			<tr>
				<td>Response data type</td>
				<td><select data-ng-model="selectedResponseDataType"
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
