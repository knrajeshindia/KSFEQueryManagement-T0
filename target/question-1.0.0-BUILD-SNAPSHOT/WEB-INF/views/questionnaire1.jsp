<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>

<title>Questionnaire - page</title>
<script type="text/javascript">
	var app = angular.module("QuestionnaireManagement", []);

	//Controller Part
	app
			.controller(
					"QuestionnaireController",
					function($scope, $http) {

						$scope.countries = [];
						$scope.countryForm = {
							id : -1,
							countryName : "",
							population : ""
						};

						//Now load the data from server
						_refreshCountryData();

						//HTTP POST/PUT methods for add/edit country 
						// with the help of id, we are going to find out whether it is put or post operation

						$scope.submitCountry = function() {

							var method = "";
							var url = "";
							if ($scope.countryForm.id == -1) {
								//Id is absent in form data, it is create new country operation
								method = "POST";
								url = '/AngularjsSpringRestExample/countries';
							} else {
								//Id is present in form data, it is edit country operation
								method = "PUT";
								url = '/AngularjsSpringRestExample/countries';
							}

							$http({
								method : method,
								url : url,
								data : angular.toJson($scope.countryForm),
								headers : {
									'Content-Type' : 'application/json'
								}
							}).then(_success, _error);
						};

						//HTTP DELETE- delete country by Id
						$scope.deleteCountry = function(country) {
							$http(
									{
										method : 'DELETE',
										url : '/AngularjsSpringRestExample/country/'
												+ country.id
									}).then(_success, _error);
						};

						// In case of edit, populate form fields and assign form.id with country id
						$scope.editCountry = function(country) {

							$scope.countryForm.countryName = country.countryName;
							$scope.countryForm.population = country.population;
							$scope.countryForm.id = country.id;
						};

						/* Private Methods */
						//HTTP GET- get all countries collection
						function _refreshCountryData() {
							$http(
									{
										method : 'GET',
										url : 'http://localhost:8080/AngularjsSpringRestExample/countries'
									}).then(function successCallback(response) {
								$scope.countries = response.data;
							}, function errorCallback(response) {
								console.log(response.statusText);
							});
						}

						function _success(response) {
							_refreshCountryData();
							_clearFormData()
						}

						function _error(response) {
							console.log(response.statusText);
						}

						//Clear the form
						function _clearFormData() {
							$scope.countryForm.id = -1;
							$scope.countryForm.countryName = "";
							$scope.countryForm.population = "";

						}
						;
					});
</script>

<link href="<c:url value="/resources/css/style0.css" />" rel="stylesheet">
<head>
<body ng-app="QuestionnaireManagement"
	ng-controller="QuestionnaireController">
	<h1>AngularJS Restful web services example using $http</h1>
	<form ng-submit="submitCountry()">
		<table>

			<tr>
				<th colspan="2">Add/Edit country</th>
			</tr>
			<tr>
				<td>Country</td>
				<td><input type="text" ng-model="countryForm.countryName" /></td>
			</tr>
			<tr>
				<td>Population</td>
				<td><input type="text" ng-model="countryForm.population" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"
					class="blue-button" /></td>
			</tr>
		</table>
	</form>
	<table>
		<tr>

			<th>CountryName</th>
			<th>Population</th>
			<th>Operations</th>

		</tr>

		<tr ng-repeat="country in countries">

			<td>{{ country.countryName }}</td>
			<td>{{ country.population }}</td>

			<td><a ng-click="editCountry(country)" class="blue-button">Edit</a>
				| <a ng-click="deleteCountry(country)" class="red-button">Delete</a></td>
		</tr>

	</table>
</body>
</html>