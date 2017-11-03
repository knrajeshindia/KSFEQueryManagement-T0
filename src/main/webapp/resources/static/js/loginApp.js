angular
		.module('myApp', [])
		.controller(
				'loginController',
				function($scope, $window, $http, $location) {

					// Variables
					$scope.message = "";
					$scope.response = "";
					$scope.data = "";
					$scope.questionnaireList = [];
					$scope.responseDataType = "";
					$scope.answerList = [];
					$scope.answerIDList = [];
					$scope.selectedQuestionIDList = [];
					$scope.questionList = [];
					$scope.questionnaireID = "";
					$scope.responseDataType = "";
					$scope.responseRemarks = "";
					$scope.fileDescription = "";
					$scope.file = "";
					$scope.respondentName = "";
					$scope.respondentJobTitle = "";
					$scope.saveAnswerURL = "";
					$scope.saveResponseURL = "";
					$scope.unitID = "";
					$scope.password="";
					$scope.unitDetails=[];
					

					$scope.answerIDList = [];
					$scope.responseStatus = "";
					$scope.responseArray = [];
					$scope.answerArray = [];

					// FLAGS
					$scope.flagQuestionnaireView = false;
					$scope.flagQuestionView = false;
					$scope.answerProcess = false;
					$scope.flagResponseConfirmation = false;
					$scope.flagResponsePublishConfirmation=false;
					$scope.flagResponsePublish = false;

					// ---------------------------------
					//RESET
					var flagReset=function(){
						// FLAGS
						$scope.flagQuestionnaireView = false;
						$scope.flagQuestionView = false;
						$scope.answerProcess = false;
						$scope.flagResponseConfirmation = false;
						$scope.flagResponsePublishConfirmation=false;
						$scope.flagResponsePublish = false;
					}

					var variableReset=function(){
					$scope.message = "";
					$scope.response = "";
					$scope.data = "";
					$scope.questionnaireList = [];
					$scope.responseDataType = "";
					$scope.answerList = [];
					$scope.answerIDList = [];
					$scope.selectedQuestionIDList = [];
					$scope.questionList = [];
					$scope.questionnaireID = "";
					$scope.responseDataType = "";
					$scope.responseRemarks = "";
					$scope.fileDescription = "";
					$scope.file = "";
					$scope.respondentName = "";
					$scope.respondentJobTitle = "";
					$scope.saveAnswerURL = "";
					$scope.saveResponseURL = "";

					$scope.answerIDList = [];
					$scope.responseStatus = "";
					$scope.responseArray = [];
					$scope.answerArray = [];
					}
					
					//LOGIN
					$scope.verifyUnit=function(){
					
					variableReset();
					$http({method : "post",
						url : "/query/verifyUnit",
						params : {	"unitID" : $scope.unitID,
									"password" : $scope.password,
						}}).then(function(result) {
										$scope.response = angular.fromJson(result.data);
										if ($scope.response.status === "SUCCESS") {
											$scope.message = $scope.response.message;
											$scope.data = angular.fromJson($scope.response.data);
											$scope.unitDetails = angular.fromJson($scope.response.data);
											alert($scope.unitDetails.unitID+"|"+$scope.unitDetails.unitName+"|"+$scope.unitDetails.unitAddress+"|"+$scope.unitDetails.unitEmail);
											$scope.changeViewWindow();}},
									function(result) {$window.alert("Server response-FAILURE! Please try again later");
									});};		
						
						
					// ----------------------------------------------------------------------------------------------------
					
																				
										$scope.changeViewWindow = function(person){
										$window.location.href = "/query/response";
										}

				});