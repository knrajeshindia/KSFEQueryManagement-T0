angular
		.module('myApp', [])
		.controller(
				'responseController',
				function($scope, $window, $http) {

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
					
					
					// ----------------------------------------------------------------------------------------------------
					// QUESTIONNAIRE
					// Variables
					$scope.message = "";
					$scope.response = "";
					$scope.data = "";
					$scope.questionnaireList = [];
					// Functions
					// PULL UP VALID QUESTIONNAIRE
					$scope.viewQuestionnaire = function() {
						// Flag
						$scope.flagQuestionnaireView = true;
						$scope.flagQuestionView = false;
						// Variables
						$scope.unitID = 1;
						$scope.message = "";
						$scope.response = "";
						$scope.data = "";
						// Service
						$http({method : "post",
							url : "/query/viewQ",
							params : {"unitID" : $scope.unitID
							}}).then(function(result) {
											$scope.response = angular.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.data = angular.fromJson($scope.response.data);
												$scope.questionnaireList = angular.fromJson($scope.response.data);
												// $scope.unitID = "";
												}},
										function(result) {$window.alert("Server response-FAILURE! Please try again later");
										});};
					
										
										// PULL UP COMPLETE QUESTIONNAIRE
										$scope.viewCompleteQuestionnaire = function() {
											flagReset();
											variableReset();
											// Flag
											$scope.flagQuestionnaireView = true;
											// Variables
											$scope.unitID = 1;
											
											// Service
											$http({method : "post",
												url : "/query/viewCompleteQ",
												params : {"unitID" : $scope.unitID
												}}).then(function(result) {
																$scope.response = angular.fromJson(result.data);
																if ($scope.response.status === "SUCCESS") {
																	$scope.message = $scope.response.message;
																	$scope.data = angular.fromJson($scope.response.data);
																	$scope.questionnaireList = angular.fromJson($scope.response.data);
																	// $scope.unitID = "";
																	}},
															function(result) {$window.alert("Server response-FAILURE! Please try again later");
															});};					

					// -------------------------------------------------------------------------------------
					// QUESTION

					// Variables
					$scope.selectedQuestionIDList = [];
					$scope.questionList = [];
					$scope.responseDataType = "";
					// Functions
					// Pull UP QUESTIONS
					$scope.viewQuest = function(index) {
						// Flags
						$scope.flagQuestionnaireView = false;
						$scope.flagQuestionView = true;
						$scope.answerProcess = true;
						// Variables
						$scope.selectedQuestionIDList = [];
						$scope.questionList = [];
						$scope.questionnaireID = "";
						$scope.responseDataType = "";
						$scope.responseRemarks = "";
						$scope.fileDescription = "";
						$scope.file = "";
						$scope.respondentName = "";
						$scope.respondentJobTitle = "";

						$scope.answerIDList = [];
						$scope.responseStatus = "";
						$scope.message = "";
						$scope.response = "";
						var len = "";
						var qID = "";
						$scope.questionnaireID = $scope.questionnaireList[index].questionnaireID;
						len = $scope.questionnaireList[index].questionIDList.length;
						for (var i = 0; i < len; i++) {
							qID = parseInt($scope.questionnaireList[index].questionIDList[i]);
							$scope.selectedQuestionIDList.push(qID);
						}
						// Service
						$http({method : "post",
								url : "/query/viewQuest",
								params : {"questionIDList" : $scope.selectedQuestionIDList}}).then(
										function(result) {$scope.response = angular.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.questionList = angular.fromJson($scope.response.data);}},
										function(result) {$window.alert("Server response-FAILURE! Please try again later");
										});};

					// -------------------------------------------------------------------------------------

					// ANSWER

					// #Variables#
					$scope.answerList = [];
					$scope.answerIDList = [];
					// #Functions#

					// SAVE ANSWER
					$scope.saveAnswer = function() {
						alert("saveAnswer Called, flagPublishResponse :");
						// Flags
						$scope.flagQuestionnaireView = false;
						$scope.flagAnswerProcess = false;
						// Variables
						$scope.message = "";
						$scope.response = "";
						var len = "";
						var aID = "";
						
						// Service
						$http({	method : "post",
								url : "/query/saveAnswer",
								params : {	"questionIDList" : $scope.selectedQuestionIDList,
											"answerDescriptionList" : $scope.answerList}}).then(
										function(result) {
											$scope.response = angular.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.answerList = [];
												$scope.answerList = angular.fromJson($scope.response.data);
												// extract answerIDs from JSON
												len = $scope.answerList.length;
												for (var i = 0; i < len; i++) {
													aID = parseInt($scope.answerList[i].answerID);
													$scope.answerIDList.push(aID);}
												
												// Call save-response
												$scope.saveResponse();}},
										function(result) {$window.alert("Server response-FAILURE! Please try again later");});};

					// VIEW/MODIFY RESPONSE
					$scope.modifyResponse = function(index) {
						$scope.responseID = $scope.questionnaireList[index].responseID;
						$scope.viewQuest(index);
						// Flags
						$scope.flagAnswerProcess = false;
						$scope.flagQuestionnaireView = false;
						$scope.flagQuestionView = false;
						// PULL UP ANSWERS
						$scope.getAnswerList();};

					// PULL UP ANSWERS
					$scope.getAnswerList = function() {
						// Flags
						// Variables
						$scope.answerList=[];
						$scope.answerIDList=[];
						// Service

						$http({	method : "post",
								url : "/query/getAnswerList",
								params : {"responseID" : $scope.responseID}}).then(
										function(result) {$scope.response = angular.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.answerArray = angular.fromJson($scope.response.data);
												// extract answers from JSON
												var len = $scope.answerArray.length;
												for (var i = 0; i < len; i++) {
													$scope.answerList[i] = $scope.answerArray[i].answerDescription;
													$scope.answerIDList[i] = $scope.answerArray[i].answerID;}
												// PULL UP RESPONSE
												$scope.getResponse();}},
										function(result) {$window.alert("Server response-FAILURE! Please try again later");});};
										
										

					// PUBLISH ANSWER
					$scope.publishAnswer = function() {
						// Flags
						$scope.flagQuestionnaireView = false;
						$scope.flagQuestionView = false;
						$scope.flagAnswerProcess = false;
						$scope.flagResponsePublish = true;
						
						// Functions
						
						// Flags
						
						// Variables
						$scope.message = "";
						$scope.response = "";
						var len = "";
						var aID = "";
						
						// Service
						$http({	method : "post",
								url : "/query/updateAnswer",
								params : {	"answerIDList" : $scope.answerIDList,
											"answerDescriptionList" : $scope.answerList}}).then(
										function(result) {
											$scope.response = angular.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.answerList = [];
												$scope.answerList = angular.fromJson($scope.response.data);
												// extract answerIDs from JSON
												len = $scope.answerList.length;
												for (var i = 0; i < len; i++) {
													aID = parseInt($scope.answerList[i].answerID);
													$scope.answerIDList.push(aID);}
												
												// Call save-response
												$scope.publishResponse();}},
										function(result) {$window.alert("Server response-FAILURE! Please try again later");});};
				

					// ---------------------------------------------------------------------------------

					// RESPONSE
					// #Flags#

					// #Variables#
					// #Functions#

					// SAVE RESPONSE
					$scope.saveResponse = function() {
						alert("saveResponse invoked, flagResponsePublish:"	+ $scope.flagResponsePublish);
						// Flags
						$scope.flagQuestionnaireView = false;
						// Variables
						$scope.message = "";
						$scope.response = "";

						// Service
						$http({	method : "post",
								url : "/query/saveResponse",
								params : {
										"unitID" : $scope.unitID,
										"questionnaireID" : $scope.questionnaireID,
										"responseRemarks" : $scope.responseRemarks,
										"attachmentDescription" : $scope.fileDescription,
										"attachmentFile" : $scope.file,
										"respondentName" : $scope.respondentName,
										"respondentJobTitle" : $scope.respondentJobTitle,
										"answerIDList" : $scope.answerIDList,
										"responseStatus" : $scope.responseStatus}}).then(function(result) {
											$scope.response = angular.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.responseID = angular.fromJson($scope.response.data).responseID;
												// Flags
												$scope.flagQuestionView = false;
												$scope.flagResponseConfirmation = true;
												$scope.flagResponsePublishConfirmation=false;
												$scope.flagResponsePublish = false;}},
										function(result) {$window.alert("Server response-FAILURE! Please try again later");});};
										
										
										
										// PUBLISH RESPONSE
										$scope.publishResponse = function() {
											alert("publishResponse invoked");
											// Flags
											$scope.flagQuestionnaireView = false;
											// Variables
											$scope.message = "";
											$scope.response = "";
											// Service
											$http({	method : "post",
													url : "/query/updateResponse",
													params : {"responseID"	:$scope.responseID,														
															"responseRemarks" : $scope.responseRemarks,
															"attachmentDescription" : $scope.fileDescription,
															"attachmentFile" : $scope.file,
															"respondentName" : $scope.respondentName,
															"respondentJobTitle" : $scope.respondentJobTitle,
															}}).then(function(result) {
																$scope.response = angular.fromJson(result.data);
																if ($scope.response.status === "SUCCESS") {
																	$scope.message = $scope.response.message;
																	alert($scope.message);
																	//$scope.responseID = angular.fromJson($scope.response.data).responseID;
																	// Flags
																	reset();
																	$scope.flagResponsePublishConfirmation=true;}},
															function(result) {$window.alert("Server response-FAILURE! Please try again later");});};

										
										

					// Pull UP RESPONSE
					$scope.getResponse = function() {

						// Flags
						// Variables
						$scope.responseArray = [];
						// Service
						$http({method : "post",
							url : "/query/getResponse",
							params : {"responseID" : $scope.responseID}}).then(function(result) {
											$scope.response = angular.fromJson(result.data);
											if ($scope.response.status === "SUCCESS") {
												$scope.message = $scope.response.message;
												$scope.responseArray = angular.fromJson($scope.response.data);
												$scope.responseRemarks = $scope.responseArray.responseRemarks;
												$scope.fileDescription = $scope.responseArray.attachmentDescription;
												// $scope.file,
												$scope.respondentName = $scope.responseArray.respondentName;
												$scope.respondentJobTitle = $scope.responseArray.respondentJobTitle;
												// Flags
												$scope.flagAnswerProcess = true;
												$scope.flagQuestionnaireView = false;
												$scope.flagQuestionView = true;}},
										function(result) {$window.alert("Server response-FAILURE! Please try again later");});};

					// -----------------------------------------------------------------------------------------------------------------------------

				});