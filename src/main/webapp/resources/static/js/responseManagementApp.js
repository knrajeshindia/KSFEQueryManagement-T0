angular
		.module('myApp', [])
		.controller(
				'responseController',
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
					$scope.flagAnswer = false;
					$scope.flagQuestionnaireView = false;
					$scope.flagQuestionView = false;
					$scope.answerSave = false;
					$scope.answerPublish = false;

					$scope.questionnaireList = [];
					$scope.questionList = [];
					$scope.answerList = [];
					$scope.answerObjectList = [];
					$scope.userID = "";
					$scope.dataType = "text";
					$scope.response = [ {
						questionId : '',
						answerDescription : "",
						file : '',
						fileDescription : "",
						sender : "",
						jobTitle : ""

					} ];

					// INSERT QUESTIONNAIRE
					$scope.viewQuestionnaire = function() {
						$scope.flag1 = true;
						$scope.flagQuestionnaireView = true;
						$scope.flagQuestionView = false;
						$scope.userID = 1;
						$http({
							method : "post",
							url : "/query/viewQ",
							params : {
								"userID" : $scope.userID
							}
						})
								.then(
										function(result) {
											$scope.response = angular
													.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.data = angular
														.fromJson($scope.response.data);
												$scope.questionnaireList = angular
														.fromJson($scope.response.data);
												$scope.userID = "";
												// $window.alert("Success"+$scope.questionnaireList);
											}
										},
										function(result) {
											$window
													.alert("Server response-FAILURE! Please try again later");
										});
					};
					// Pull UP QUESTIONS

					$scope.viewQuest = function(index) {
						$scope.selectedQuestionIDList = [];
						$scope.questionList = [];
						$scope.userID = 1;
						$scope.dataType = "";
						$scope.questionnaireID = $scope.questionnaireList[index].questionnaireID;
						$scope.flagQuestionnaireView = false;
						$scope.flagQuestionView = true;
						$scope.answerProcess = true;

						var len = $scope.questionnaireList[index].questionIDList.length;
						for (var i = 0; i < len; i++) {
							var qID = parseInt($scope.questionnaireList[index].questionIDList[i]);
							$scope.selectedQuestionIDList.push(qID);
						}

						$http(
								{
									method : "post",
									url : "/query/viewQuest",
									params : {
										"questionIDList" : $scope.selectedQuestionIDList
									}
								})
								.then(
										function(result) {
											$scope.response = angular
													.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.data = angular
														.fromJson($scope.response.data);
												$scope.questionList = angular
														.fromJson($scope.response.data);
												$scope.answerSave = true;
											}
										},
										function(result) {
											$window
													.alert("Server response-FAILURE! Please try again later");
										});

					};

					// Save answer
					$scope.saveAnswer = function() {
						$scope.answerProcess = false;
						$scope.response = [];
						$scope.answerObjectList = [];
						
						//BIND ANSWER OBJECT
						for (var i = 0; i < $scope.answerList.length; i++) {
							$scope.answerObjectList.push({
								questionID : $scope.selectedQuestionIDList[i],
								answerDescription : $scope.answerList[i]
							});
						}

						//BIND RESPONSE OBJECT
						$scope.response.push({
							file : $scope.file,
							fileDescription : $scope.fileDescription,
							sender : $scope.sender,
							jobTitle : $scope.jobTitle
						});

						//alert($scope.response[0].fileDescription);
						

						$http(
								{
									method : "post",
									url : "/query/saveResponse",
									params : {
										"questionIDList" : $scope.selectedQuestionIDList,
										"answerDescriptionList" : $scope.answerList,
										"answerList": $scope.answerObjectList,
										"response":$scope.response
									}
								})
								.then(
										function(result) {
											$scope.response = angular
													.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												alert($scope.message);
											}
										},
										function(result) {
											$window
													.alert("Server response-FAILURE! Please try again later");
										});

					}

				});