angular
		.module('myApp', [])
		.controller(
				'namesCtrl',
				function($scope, $window, $http) {
					// Questionnaire form DIV
					$scope.flag1 = false;
					// Questionnaire response DIV
					$scope.flag2 = false;
					// Question display DIV
					$scope.flag3 = false;
					// Question create DIV
					$scope.flag4 = false;

					$scope.questions = [ {
						questionID : 1,
						questionDescription : 'd',
						responseDataType : 'd'
					}, {
						questionID : 1,
						questionDescription : 'd',
						responseDataType : 'd'
					} ];

					$scope.targetRespondentIDLists = [ {
						name : 'All Departments',
						value : 1
					}, {
						name : 'All Regions',
						value : 2
					}, {
						name : 'All Branches',
						value : 3
					} ];

					$scope.responseDataTypes = [ {
						name : 'Text',
						value : 'String'
					}, {
						name : 'Number',
						value : 'double'
					}, {
						name : 'Date',
						value : 'Date'
					} ];
					$scope.responseDataType = $scope.responseDataTypes[0]; // String

					$scope.changeVisibility = function() {
						// Questionnaire form DIV
						$scope.flag1 = true;

					};

					$scope.test = function() {
						$http({
							method : "get",
							url : "/query/test",
						})
								.then(
										function(result) {
											// Questionnaire form DIV
											$scope.flag1 = false;
										},
										function(result) {
											$window
													.alert("Server response-FAILURE! Please try again later");
										});
					};

					$scope.insertQ = function() {
						var questionnaireFormData = {
							"questionnaireTitle" : $scope.questionnaireTitle,
							"questionnaireDescription" : $scope.questionnaireDescription,
							"questionnaireRemarks" : $scope.questionnaireRemarks,
							"dueDate" : $scope.dueDate,
							"targetRespondentIDList" : $scope.targetRespondentIDList,
							"senderName" : $scope.senderName,
							"senderJobTitle" : $scope.senderJobTitle
						};

						$http
								.post('insertQ', questionnaireFormData)
								.then(
										function(result) {
											// Questionnaire form DIV
											$scope.flag1 = false;
											// Questionnaire response DIV
											$scope.flag2 = true;
											$scope.response = angular
													.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.questionnaireID = angular
														.fromJson($scope.response.data).questionnaireID;

												// Question create DIV
												$scope.flag4 = true;

											}
										},
										function(result) {
											$window
													.alert("Server response-FAILURE! Please try again later");
										});
					};

					$scope.insertQuest = function() {
						var questionFormData = {
							"questionDescription" : $scope.questionDescription,
							"responseDataType" : $scope.selectedResponseDataType.value,
							"questionnaireID" : $scope.questionnaireID
						};
						$http
								.post('insertQuest', questionFormData)
								.then(
										function(result) {
											// Questionnaire form DIV
											$scope.flag1 = false;
											// Questionnaire response DIV
											$scope.flag2 = true;
											// Question display DIV
											$scope.flag3 = true;
											// Question create DIV
											$scope.flag4 = true;

											$scope.response = angular
													.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;

												$scope.questionID = angular
														.fromJson($scope.response.data).questionID;
												$scope.questionDescription = angular
														.fromJson($scope.response.data).questionDescription;
												$scope.responseDataType = angular
														.fromJson($scope.response.data).responseDataType;
												$scope.questions
														.push({
															'questionID' : $scope.questionID,
															'questionDescription' : $scope.questionDescription,
															'responseDataType' : $scope.responseDataType
														})
												$scope.questionID = "";
												$scope.questionDescription = "";
												$scope.responseDataType = "";

											}
										},
										function(result) {
											$window
													.alert("Server response-FAILURE! Please try again later");
										});
					};

				});
