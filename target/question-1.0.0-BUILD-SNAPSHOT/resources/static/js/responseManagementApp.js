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

					$scope.questionnaireList = [];
					$scope.userID = "";

					// INSERT QUESTIONNAIRE
					$scope.viewQuestionnaire = function() {
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
												$window.alert("Success"
														+ $scope.questionList);
											}
										},
										function(result) {
											$window
													.alert("Server response-FAILURE! Please try again later");
										});

					};

				});