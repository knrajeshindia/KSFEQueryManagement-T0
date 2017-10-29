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
					$scope.flagAnswer=false;
					$scope.flagQuestionnaireView=false;
					$scope.flagQuestionView=false;
					$scope.answerSave=false;
					$scope.answerPublish=false;

					$scope.questionnaireList = [];
					$scope.questionList = [];
					$scope.answerList=[];
					$scope.answerObjectList=[];
					$scope.userID = "";
					$scope.dataType = "text";
					$scope.response=[{
							questionID:0,
							answerDescription:"NULL"
						}];

					// INSERT QUESTIONNAIRE
					$scope.viewQuestionnaire = function() {
						$scope.flag1 = true;
						$scope.flagQuestionnaireView=true;
						$scope.flagQuestionView=false;
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
						$scope.questionnaireID=$scope.questionnaireList[index].questionnaireID;
						$scope.flagQuestionnaireView=false;
						$scope.flagQuestionView=true;
						$scope.answerProcess=true;

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
												$scope.answerSave=true;
											}
										},
										function(result) {
											$window
													.alert("Server response-FAILURE! Please try again later");
										});

					};



					// Save answer
					$scope.saveAnswer=function(){
                    $scope.answerProcess=false;
                    $scope.response=[];
//                    alert($scope.selectedQuestionIDList[0]);
//                    alert($scope.answerList[0]);
                    
                    $http(
							{
								method : "post",
								url : "/query/saveResponse",
								params : {
									"questionIDList" : $scope.selectedQuestionIDList,
									"answerDescriptionList" : $scope.answerList
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
				
                    
                    
//                    
// for(var i=0;i<$scope.answerList.length;i++){
// alert($scope.selectedQuestionIDList[i]);
// $scope.questionID=$scope.selectedQuestionIDList[i].questionID;
// alert($scope.answerList[i]);
// $scope.answerDescription=$scope.answerList[i];
// $scope.answerObjectList
// .push({
// 'questionID' : $scope.questionID,
// 'answerDescription' : $scope.questionDescription});
// alert($scope.questionID+" | "+$scope.answerDescription);}
// }



				});