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
           
            $scope.answerIDList = [];
            $scope.responseStatus = "";
            
            
            //FLAGS
            $scope.flagQuestionnaireView = false;
            $scope.flagQuestionView = false;
            $scope.answerProcess = false;
            
            //---------------------------------
            
            $scope.flagAnswer = false;
            $scope.answerSave = false;
            $scope.answerPublish = false;

            $scope.questionnaireList = [];
            $scope.questionList = [];
            $scope.answerList = [];
            $scope.answerObjectList = [];
            $scope.unitID = 1;
            $scope.dataType = "text";
            $scope.response = [{
                questionId: '',
                answerDescription: "",
                file: '',
                fileDescription: "",
                sender: "",
                jobTitle: ""

            }];
            $scope.file = "";
            $scope.fileDescription = "";
            $scope.responseRemarks = "";
            $scope.sender = "";
            $scope.jobTitle = "";

//----------------------------------------------------------------------------------------------------
         // QUESTIONNAIRE
			// Variables
			$scope.message="";
			$scope.response="";
			$scope.data="";
			$scope.questionnaireList = [];
			// Functions
			// INSERT QUESTIONNAIRE
			$scope.viewQuestionnaire = function() {
				//Flag
				$scope.flagQuestionnaireView = true;
				$scope.flagQuestionView = false;
				//Variables
				$scope.unitID = 1;
				$scope.message="";
				$scope.response="";
				$scope.data="";
				//Service
				$http({
					method : "post",
					url : "/query/viewQ",
					params : {
						"unitID" : $scope.unitID
					}}).then(function(result) {
									$scope.response = angular.fromJson(result.data);
									if ($scope.response.status === "SUCCESS") {
										$scope.message = $scope.response.message;
										$scope.data = angular.fromJson($scope.response.data);
										$scope.questionnaireList = angular.fromJson($scope.response.data);
										//$scope.unitID = "";
										}
								},
								function(result) {
									$window.alert("Server response-FAILURE! Please try again later");
								});
			};

			// -------------------------------------------------------------------------------------
			// QUESTION
			
			// Variables
			$scope.selectedQuestionIDList = [];
			$scope.questionList = [];
			$scope.responseDataType = "";
			// Functions
			// Pull UP QUESTIONS
			$scope.viewQuest = function(index) {
				//Flags
				$scope.flagQuestionnaireView = false;
				$scope.flagQuestionView = true;
				$scope.answerProcess = true;
				//Variables
				$scope.selectedQuestionIDList = [];
				$scope.questionList = [];
				$scope.questionnaireID ="";
				$scope.responseDataType = "";
				$scope.responseRemarks="";
				$scope.fileDescription="";
				$scope.file="";
				$scope.respondentName="";
				$scope.respondentJobTitle="";
				
				$scope.answerIDList=[];
				$scope.responseStatus="";						
				$scope.message="";
				$scope.response="";
				var len="";
				var qID="";
				$scope.questionnaireID = $scope.questionnaireList[index].questionnaireID;
				len = $scope.questionnaireList[index].questionIDList.length;
				for (var i = 0; i < len; i++) {
					qID = parseInt($scope.questionnaireList[index].questionIDList[i]);
					$scope.selectedQuestionIDList.push(qID);
				}
				//Service
				$http({		method : "post",
							url : "/query/viewQuest",
							params : {
								"questionIDList" : $scope.selectedQuestionIDList
							}}).then(function(result) {
									$scope.response = angular
											.fromJson(result.data);
									if ($scope.response.status === "SUCCESS") {
										$scope.message = $scope.response.message;
										$scope.questionList = angular.fromJson($scope.response.data);
									}},
								function(result) {
									$window.alert("Server response-FAILURE! Please try again later");
								});};

			// -------------------------------------------------------------------------------------

								// ANSWER
								
								// #Variables#
								$scope.answerList = [];					
								$scope.answerIDList = [];
								// #Functions#
								
								// SAVE ANSWER
								$scope.saveAnswer = function() {
									//Flags
									$scope.flagQuestionnaireView = false;
									$scope.flagAnswerProcess = false;
									//Variables
									$scope.message="";
									$scope.response="";
									var len="";
									var aID="";
									
									
									//Service
									$http({		method : "post",
												url : "/query/saveAnswer",
												params : {	"questionIDList" : $scope.selectedQuestionIDList,
															"answerDescriptionList" : $scope.answerList
												}}).then(function(result) {
														$scope.response = angular
																.fromJson(result.data);
														if ($scope.response.status === "SUCCESS") {
															$scope.message = $scope.response.message;
															$scope.answerList=[];
															$scope.answerList = angular.fromJson($scope.response.data);
															//extract answerIDs from JSON
															len=$scope.answerList.length;
															for (var i = 0; i < len; i++) {
																aID = parseInt($scope.answerList[i].answerID);
																$scope.answerIDList.push(aID);
															}
															alert($scope.message);
															//Call save-response
															$scope.saveResponse();
															}},
													function(result) {
														$window.alert("Server response-FAILURE! Please try again later");
													});};
									
								// -------------------------------------------------------------------------------------
								
													// RESPONSE
													// #Flags#
													
													// #Variables#
													// #Functions#
													
													// SAVE RESPONSE
													$scope.saveResponse = function() {
														
														//Flags
														$scope.flagQuestionnaireView = false;														
														//Variables
														$scope.message="";
														$scope.response="";
														
														$scope.responseStatus="DRAFT";
//														alert($scope.responseStatus+"|"+$scope.unitID+"|"+$scope.questionnaireID+"|"+$scope.responseRemarks+"|"+
//																$scope.fileDescription+"|"+$scope.respondentName+"|"+$scope.respondentJobTitle+"|"+$scope.answerIDList);
														//Service
														$http({		method : "post",
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
																		"responseStatus" : $scope.responseStatus
																	}}).then(function(result) {
																			$scope.response = angular
																					.fromJson(result.data);
																			if ($scope.response.status === "SUCCESS") {
																				$scope.message = $scope.response.message;
																				$scope.responseID = angular.fromJson($scope.response.data).responseID;
																				alert($scope.message);
																				alert($scope.responseID);}},
																		function(result) {
																			$window.alert("Server response-FAILURE! Please try again later");
																		});};

										//-----------------------------------------------------------------------------------------------------------------------------			
													
													
												});