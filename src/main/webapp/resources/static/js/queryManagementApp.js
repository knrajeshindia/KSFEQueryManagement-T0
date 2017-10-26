angular
		.module('myApp', [])
		.controller(
				'namesCtrl',
				function($scope, $window, $http) {
					// Questionnaire form DIV
					$scope.flag1 = false;
					// Create Questionnaire button
					$scope.flag1Button1 = true;
					// Questionnaire response DIV
					$scope.flag2 = false;
					// Question display DIV
					$scope.flag3 = false;
					// Question create DIV
					$scope.flag4 = false;
					// Questionnaire publish message DIV
					$scope.flag5 = false;

					//Define min due date
					$scope.today=new Date();

					// POPULATE QUESTION ARRAY
					$scope.questionArray = [];

					// TARGET RESPONDENTS
					$scope.respondents = [ {
						targetID : 1,
						Name : 'All Departments',
						Selected : false
					}, {
						targetID : 2,
						Name : 'All Regions',
						Selected : false
					}, {
						targetID : 3,
						Name : 'All Branches',
						Selected : false
					} ];

					$scope.getValue = function() {
						$scope.selectedRespondents = [];
						$scope.message = "";
						for (var i = 0; i < $scope.respondents.length; i++) {
							if ($scope.respondents[i].Selected) {
								var selectedTargetID = $scope.respondents[i].targetID;
								var selectedTargetName = $scope.respondents[i].Name;
								$scope.selectedRespondents
										.push(selectedTargetID);
								$scope.message += "ID: " + selectedTargetID
										+ " Name: " + selectedTargetName + "\n";

							}
						}

						// $window.alert($scope.message);
					};

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

                    //Create questionnaire
					$scope.changeVisibility = function() {
						// Questionnaire form DIV
						$scope.flag1 = true;
						// Create Questionnaire button
						$scope.flag1Button1 = false;
						// Questionnaire response DIV
						$scope.flag2 = false;
						// Question display DIV
						$scope.flag3 = false;
						// Question create DIV
						$scope.flag4 = false;
						// Questionnaire publish message DIV
						$scope.flag5 = false;

						$scope.questionnaireTitle="";
						$scope.questionnaireDescription="";
						$scope.questionnaireRemarks="";
						$scope.dueDate="";
						$scope.senderName="";
						$scope.senderJobTitle="";
						$scope.questionArray=[];
						for (var i = 0; i < $scope.respondents.length; i++) {
                        	$scope.respondents[i].Selected=false;
                        	 }
					};

					// INSERT QUESTIONNAIRE
					$scope.insertQ = function() {
						var questionnaireFormData = {
							"questionnaireTitle" : $scope.questionnaireTitle,
							"questionnaireDescription" : $scope.questionnaireDescription,
							"questionnaireRemarks" : $scope.questionnaireRemarks,
							"dueDate" : $scope.dueDate,
							"targetRespondentIDList" : $scope.selectedRespondents,
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
											// Create Questionnaire button
											$scope.flag1Button1 = false;
											$scope.flag2 = true;
											// Question create DIV
											$scope.flag4 = true;
											$scope.flag5 = false;

											// $window.alert("Questionnaire
											// created");
											$scope.response = angular
													.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.questionnaireID = angular
														.fromJson($scope.response.data).questionnaireID;

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
											// Create Questionnaire button
											$scope.flag1Button1 = false;
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

												$scope.questionArray
														.push({
															'questionID' : $scope.questionID,
															'questionDescription' : $scope.questionDescription,
															'responseDataType' : $scope.responseDataType
														});

												// CLEAR TEXTBOX.
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

					// PUBLISH QUESTIONNAIRE
					$scope.publish = function() {
						// $window.alert("Publish invoked");

						$scope.questionIDList = [];
						for (var i = 0; i < $scope.questionArray.length; i++) {
							var questionID = $scope.questionArray[i].questionID;
							$scope.questionIDList.push(questionID);
						}

						$http({
							method : "post",
							url : "/query/updateQ",
							params : {
								"questionIDList" : $scope.questionIDList,
								"questionnaireID" : $scope.questionnaireID
							}
						})
								.then(
										function(result) {
											// Questionnaire form DIV
											$scope.flag1 = false;
											// Create Questionnaire button
											$scope.flag1Button1 = true;
											// Questionnaire response DIV
											$scope.flag2 = false;
											// Question display DIV
											$scope.flag3 = false;
											// Question Adding DIV
											$scope.flag4 = false;
											// Questionnaire publish message DIV
											$scope.flag5 = true;

										},
										function(result) {
											$window
													.alert("Server response-FAILURE! Please try again later");
										});
					};

					// REMOVE SELECTED ROW(s) FROM TABLE.
					$scope.removeRow = function() {
						var arrMovie = [];
						angular.forEach($scope.questionArray, function(value) {
							if (value.Remove) {
								$http({
									method : "post",
									url : "/query/deleteQuest",
									params : {
										"questionID" : value.questionID
									}
								});
							} else {
								arrMovie.push(value);
							}
						});
						$scope.questionArray = arrMovie;
					};

				});