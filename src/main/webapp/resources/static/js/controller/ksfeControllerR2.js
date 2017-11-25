var app = angular.module('myApp');

app.controller('LoginCtrl', logC);
app.controller('ResponseCtrl', resC);
/*app.controller('ResponseCtrlP2', resP2C);
app.controller('AnswerCtrl', ansC);*/
app.controller('QuestionnaireCtrl', QC);
//app.controller('QuestionCtrl',quesC);

//INJECT DEPENDENCIES
logC.$inject = ['$scope', '$rootScope', 'LogService'];
/*resC.$inject = ['$scope', '$rootScope', '$http', 'ResQService', 'ResQuestService', 'ResAnswerService'];
resP2C.$inject = ['$scope', '$rootScope', '$http', 'ResAnswerService', 'ResponseService'];
ansC.$inject = ['$scope', '$window', '$http', '$rootScope'];
QC.$inject = ['$scope'];
quesC.$inject=['$scope', '$window', '$http','$rootScope'];*/

//--------------------------------------------------------------------------------------------------------------------------------
function logC($scope, $rootScope, LogService) {
	
	//Flags
	$rootScope.fLoginButton=true;
    $rootScope.fLogWindow=false;
    
    //Variables
    $rootScope.unit={};
    $rootScope.unit.unitID='';
    $rootScope.unit.unitName="";
    $rootScope.unit.unitTypeID='';
    $rootScope.unit.regionID='';
    $rootScope.unit.unitCode='';    
    $rootScope.unit.unitDistrict='';
    $rootScope.unit.unitManager="";
    $rootScope.unit.unitEmail='';
    $rootScope.unit.unitMobile='';
    $rootScope.unit.unitTelephone='';
        
    $scope.createQuery=function(){
    	$rootScope.$emit("createQuery", {});
    }
    
    $scope.viewLiveQuery=function(){
    	$rootScope.$emit("viewLiveQuery", {});
    	}
    
    $scope.viewEntireQuery=function(){
    	$rootScope.$emit("viewEntireQuery", {});
    }
      
    /*SHOW LOGIN WINDOW*/
    $scope.loginWindow=function(){
    	$rootScope.fLogWindow=true;}

    /*LOGIN*/
    $scope.verifyUnit = function() {
            $rootScope.unitID = $scope.unitID;
            $rootScope.password = $scope.password;
            if ($rootScope.unitID !== undefined && $rootScope.password !== undefined) {
                LogService.verify($rootScope.unitID, $rootScope.password);
                $rootScope.fLogWindow=false;
                $rootScope.fLoginButton=false;
            } else {
                alert("Login data not captured; Check LoginCtrl");
            }
        }
        /*REGISTER*/
    $scope.register = function() {
        alert("WILL BE ACTIVE SHORTLY");
    }

    /*LOGOUT*/
    $scope.logout = function() {
        $rootScope.unitID = '';
        $rootScope.password = "";
        $rootScope.unit = {};
        
        //Flags
        $rootScope.fLoginButton=true;
        $rootScope.fQQWindow=false;
		$rootScope.fQShowQ=false;
		$rootScope.fQAddQuest=false;
		$rootScope.fQQuestPublished = false;
		$rootScope.fResShowQ=false;
		$rootScope.fResShowQuest=false;
    }
};
//-------------------------------------------------------------------------------------------------------------------------------
/*RESPONSE CONTROLLER*/
function resC($scope, $rootScope, $http) {
    $scope.answerList = [];
    $scope.questionnaireID = '';
    $scope.responseRemarks = "";
    $scope.fileDescription = "";
    $scope.file = null;
    $scope.respondentName = "";
    $scope.respondentJobTitle = "";
    $scope.answerIDList = [];
    $rootScope.questionList = [];
    $rootScope.answerList = [];
    $scope.questionnaireList=[];
    var urlString="";
    
    //Flags
    $rootScope.fResQuestionWindow=false;
    $rootScope.$on("viewLiveQuery", function() {$scope.viewActiveQ();});
    $rootScope.$on("viewEntireQuery", function() {$scope.viewEntireQ();});


    /*GET ACTIVE QUESTIONNAIRE*/
    $scope.viewActiveQ = function() {
    	//Flags
    	$rootScope.fQQWindow=false;
		$rootScope.fQShowQ=false;
		$rootScope.fQAddQuest=false;
		$rootScope.fQQuestPublished = false;
		$rootScope.fResShowQ=false;
		$rootScope.fResShowQuest=false;
		$rootScope.fResAnswerPublish=false;
		$rootScope.fResConfirmation=false;
        $scope.questionnaireList = [];
//----------SERVICE-START------------------//
        if ($rootScope.unit.unitID !== undefined) {
        	urlString='/query/viewQ';
            $http({method : 'POST',
            	    url : urlString,
            		params : {"unitID" : $rootScope.unit.unitID}}).then(
            						function(result) {
            							response = angular.fromJson(result.data);
            							if (response.status === "SUCCESS") {
            								data = angular.fromJson(response.data);
            								$scope.questionnaireList = angular.fromJson(response.data);
            								//Flag
                                            $rootScope.fResShowQ=true;
            								}},
            						function(result) {
            							alert("QUERY FAILED");
            							$scope.questionnaireList=[];});
            				}}
//----------SERVICE-END------------------//
    /*GET ENTIRE QUESTIONNAIRE*/
    $scope.viewEntireQ = function() {
    	//Flags
    	$rootScope.fQQWindow=false;
		$rootScope.fQShowQ=false;
		$rootScope.fQAddQuest=false;
		$rootScope.fQQuestPublished = false;
		$rootScope.fResShowQ=false;	
		$rootScope.fResShowQuest=false;
		$rootScope.fResConfirmation=false;
        $scope.questionnaireList = [];
        //----------SERVICE-START------------------//
                if ($rootScope.unit.unitID !== undefined) {
                	urlString='/query/viewCompleteQ';
                    $http({method : 'POST',
                    	    url : urlString,
                    		params : {"unitID" : $rootScope.unit.unitID}}).then(
                    						function(result) {
                    							response = angular.fromJson(result.data);
                    							if (response.status === "SUCCESS") {
                    								data = angular.fromJson(response.data);
                    								$scope.questionnaireList = angular.fromJson(response.data);
                    								//Flag
                                                    $rootScope.fResShowQ=true;
                    								}},
                    						function(result) {
                    							alert("QUERY FAILED");
                    							$scope.questionnaireList=[];});
                    							}}
        //----------SERVICE-END------------------//

    /*GET QUESTIONS*/
    $scope.viewQuest = function(index) {
    	//Flags
    	$rootScope.fQQWindow=false;
		$rootScope.fQShowQ=false;
		$rootScope.fQAddQuest=false;
		$rootScope.fQQuestPublished = false;
		$rootScope.fResShowQ=false;
		$rootScope.fResShowQuest=false;
		//Variables
        $scope.questionList = [];
        $rootScope.selectedQuestionIDList = [];
        var questID = '';
        var len = $scope.questionnaireList[index].questionIDList.length;
        $rootScope.questionnaireID = $scope.questionnaireList[index].questionnaireID;
        for (var i = 0; i < len; i++) {
            questID = parseInt($scope.questionnaireList[index].questionIDList[i]);
            $rootScope.selectedQuestionIDList.push(questID);
        }
        //----------SERVICE-START------------------//
                           	urlString='/query/viewQuest';
                            $http({method : 'POST',
                            	    url : urlString,
                            		params : {"questionIDList" : $rootScope.selectedQuestionIDList}}).then(
                            						function(result) {
                            							response = angular.fromJson(result.data);
                            							if (response.status === "SUCCESS") {
                            								data = angular.fromJson(response.data);
                            								$scope.questionList = angular.fromJson(response.data);
                            								$rootScope.questionList = $scope.questionList;
                            								//Flag
                                                            $rootScope.fResShowQuest=true;
                            								}},
                            						function(result) {
                            							alert("QUERY FAILED");
                            							$scope.questionList=[];});
                            							}
        //----------SERVICE-END------------------//

    // VIEW/MODIFY RESPONSE
    $scope.modifyResponse = function(index) {
        $rootScope.responseID = $scope.questionnaireList[index].responseID;
        $scope.viewQuest(index);
        // PULL UP ANSWERS
            //Flags
          	  $scope.answerList = [];
              $scope.answerIDList = [];
        //----------SERVICE-START------------------//
              $http({
                  method: 'POST',
                  url: '/query/getAnswerList',
                  params: {"responseID": $rootScope.responseID}}).then(
                  function(result) {
                      $scope.response = angular.fromJson(result.data);
                      if ($scope.response.status === "SUCCESS") {
                          $scope.message = $scope.response.message;
                          $scope.answerArray = angular.fromJson($scope.response.data);
                          // extract answers from JSON
                          var len = $scope.answerArray.length;
                          for (var i = 0; i < len; i++) {
                              $scope.answerList[i] = $scope.answerArray[i].answerDescription;
                              $scope.answerIDList[i] = $scope.answerArray[i].answerID;}
                          $rootScope.fResShowQuest=true;
                          // PULL UP RESPONSE
                          $scope.getResponse();}},
                  function(result) {
                      alert("Server response-FAILURE! Please try again later");
                  });
                   //----------SERVICE-END------------------//
          };

// Pull UP RESPONSE
    $scope.getResponse = function() {
        // Flags
        // Variables
        $scope.responseArray = [];
        //----------SERVICE-START------------------//
        $http({
            method: 'POST',
            url: '/query/getResponse',
            params: {"responseID": $rootScope.responseID}}).then(function(result) {
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
                    $rootScope.fResAnswerPublish=true;
                }},
            function(result) {alert("Server response-FAILURE! Please try again later");});
            //----------SERVICE-END------------------//
    };

/*SAVE ANSWERS*/
    $scope.saveAnswer = function(responseID) {
		//Variables
    	$scope.answerIDList = [];
        if ($rootScope.selectedQuestionIDList !== undefined) {
    //----------SERVICE-START------------------//
            urlString='/query/saveAnswerR2';
            $http({method : 'POST',
			url : urlString,
			params : {"questionIDList" : $rootScope.selectedQuestionIDList,
				"answerDescriptionList" : $scope.answerList,
				"responseID" : responseID}}).then(
						function(result) {
							response = angular.fromJson(result.data);
							if (response.status === "SUCCESS") {
								data = angular.fromJson(response.data);
								var answerObjectList = angular.fromJson(response.data);
								// extract answerIDs from JSON
								var aID='';
								var len = answerObjectList.length;
								for (var i = 0; i < len; i++) {
									aID = parseInt(answerObjectList[i].answerID);
									$scope.answerIDList.push(aID);}
									//Flags
                                    $rootScope.fQQWindow=false;
                                    $rootScope.fQShowQ=false;
                                    $rootScope.fQAddQuest=false;
                                    $rootScope.fQQuestPublished = false;
                                    $rootScope.fResShowQ=false;
                                    $rootScope.fResShowQuest=false;
                                    $rootScope.fResConfirmation=false;
                                    $rootScope.fResConfirmation=true;}},
						function(result) {
							alert("QUERY FAILED");
							$scope.answerIDList=[];});
    //----------SERVICE-END------------------//
    }};

    /*SAVE RESPONSE*/
    $scope.saveResponse = function() {
    	//----------SERVICE-START------------------//
        urlString='/query/saveResponseR2';
        $http({method : 'POST',
        			url : urlString,
        			params : {
        				"unitID" : $rootScope.unit.unitID,
        				"questionnaireID" : $rootScope.questionnaireID,
        				"responseRemarks" : $scope.responseRemarks,
        				"attachmentDescription" : $scope.fileDescription,
        				"attachmentFile" : $scope.file,
        				"respondentName" : $scope.respondentName,
        				"respondentJobTitle" : $scope.respondentJobTitle
        				}}).then(function(result) {
        				response = angular.fromJson(result.data);
        				if (response.status === "SUCCESS") {
        					data = angular.fromJson(response.data);
        					$scope.responseID = angular.fromJson(response.data).responseID;
        					$scope.responseStatus=angular.fromJson(response.data).responseStatus;
        					$scope.saveAnswer($scope.responseID);}},
        					function(result) {
        					alert("QUERY FAILED");
        					$scope.responseID='';});
        //----------SERVICE-END------------------//
        };

    // PUBLISH ANSWER
    $scope.publishAnswer = function() {
        // Flags
        var len = "";
        var aID = "";
        // Service
        $http({
            method: 'POST',
            url: '/query/updateAnswer',
            params: {
                "answerIDList": $scope.answerIDList,
                "answerDescriptionList": $scope.answerList
            }}).then(function(result) {
                $scope.response = angular.fromJson(result.data);
                if ($scope.response.status === "SUCCESS") {
                    $scope.message = $scope.response.message;
                    /*$scope.answerList = angular.fromJson($scope.response.data);
                    // extract answerIDs from JSON
                    len = $scope.answerList.length;
                    for (var i = 0; i < len; i++) {
                        aID = parseInt($scope.answerList[i].answerID);
                        $scope.answerIDList.push(aID);}*/
                    // Call save-response
                    $scope.publishResponse();}},
            function(result) {alert("Server response-FAILURE! Please try again later");});
    };

    // PUBLISH RESPONSE
    $scope.publishResponse = function() {
        // Flags
        $scope.flagQuestionnaireView = false;
        // Variables
        $scope.message = "";
        $scope.response = "";
        // Service
        $http({
            method: 'POST',
            url: '/query/updateResponse',
            params: {
                "responseID": $rootScope.responseID,
                "responseRemarks": $scope.responseRemarks,
                "attachmentDescription": $scope.fileDescription,
                "attachmentFile": $scope.file,
                "respondentName": $scope.respondentName,
                "respondentJobTitle": $scope.respondentJobTitle,
            }
        }).then(function(result) {
                $scope.response = angular.fromJson(result.data);
                if ($scope.response.status === "SUCCESS") {
                    $scope.message = $scope.response.message;
                    // Flags
                    $rootScope.fQQWindow=false;
                    $rootScope.fQShowQ=false;
                    $rootScope.fQAddQuest=false;
                    $rootScope.fQQuestPublished = false;
                    $rootScope.fResShowQ=false;
                    $rootScope.fResShowQuest=false;
                    $rootScope.fResConfirmation = true;
                    $rootScope.fResAnswerPublish=false;
                    $scope.responseStatus="PUBLISHED";}},
            function(result) {alert("Server response-FAILURE! Please try again later");});
    };





    };

//-------------------------------------------------------------------------------------------------------------------------------	

//--------------------------------------------------------------------------------------------------------------------------------
/*ANSWER*/
function ansC($scope, $rootScope) {

    /*RESPONSE*/

};
//--------------------------------------------------------------------------------------------------------------------------------
/*QUESTIONNAIRE CONTROLLER*/
function QC($scope,$http,$rootScope) {
	// Questionnaire form DIV
	$scope.flag1 = false;
	$rootScope.fQQWindow=false;
	$rootScope.fQQuestWindow=false;
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
	
	$rootScope.$on("createQuery", function() {
        $scope.createQuery();
    });

	// TARGET RESPONDENTS
	$scope.respondents = [ {
		targetID : 0,
		Name : 'Entire organisation',
		Selected : false
	},{
		targetID : 1,
		Name : 'Department-Accounts',
		Selected : false
	}, {
		targetID : 2,
		Name : 'Department-Legal',
		Selected : false
	},{
		targetID : 11,
		Name : 'Region office-1',
		Selected : false
	}, {
		targetID : 12,
		Name : 'Region office-2',
		Selected : false
	},{
		targetID : 111,
		Name : 'Region-1 branches',
		Selected : false
	}, {
		targetID : 112,
		Name : 'Region-2 branches',
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
		value : 'text'
	}, {
		name : 'Number',
		value : 'number'
	}, {
		name : 'Date',
		value : 'date'
	} ];
	$scope.responseDataType = $scope.responseDataTypes[0]; // String

    //Create questionnaire
	$scope.createQuery = function() {
		$rootScope.fQQWindow=true;
		$rootScope.fQShowQ=false;
		$rootScope.fQAddQuest=false;
		$rootScope.fQQuestPublished = false;
		$rootScope.fResShowQ=false;
		$rootScope.fResShowQuest=false;
		//$rootScope.unit.unitTypeID=-1;
		// Create Questionnaire button
		$scope.flag1Button1 = false;
		
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
		//FLAGS
		$rootScope.fQQWindow=false;
		$rootScope.fQQuestPublished = false;
				
		var questionnaireFormData = {
			"questionnaireTitle" : $scope.questionnaireTitle,
			"questionnaireDescription" : $scope.questionnaireDescription,
			"questionnaireRemarks" : $scope.questionnaireRemarks,
			"dueDate" : $scope.dueDate,
			"targetRespondentIDList" : $scope.selectedRespondents,
			"senderName" : $scope.senderName,
			"senderJobTitle" : $scope.senderJobTitle
		};
			
		
		$http.post('/query/insertQ', questionnaireFormData).then(function (result) {
			
			$scope.flag1Button1 = false;
			//$scope.flag2 = true;
			$rootScope.fQShowQ=true;
			$rootScope.fQAddQuest=true;
			$rootScope.fQQuestPublished = false;

			$scope.response = angular.fromJson(result.data);
			if ($scope.response.status === "SUCCESS") {
				$scope.message = $scope.response.message;
				$scope.questionnaireID = angular.fromJson($scope.response.data).questionnaireID;}	
			},function(result){alert("Server response-FAILURE! Please try again later");
			});
		};
		
//ADD QUESTION
		$scope.insertQuest = function() {
		    var questionFormData = {
		        "questionDescription": $scope.questionDescription,
		        "responseDataType": $scope.selectedResponseDataType.value,
		        "questionnaireID": $scope.questionnaireID
		    };
		    $http.post('insertQuest', questionFormData).then(function(result) {
		            // FLAGS
		            $rootScope.fQShowQ = true;
		            $rootScope.fQAddQuest = true;
		            $rootScope.fQSavedQuest = true;
		            $rootScope.fQQuestPublished = false;

		            $scope.response = angular.fromJson(result.data);
		            if ($scope.response.status === "SUCCESS") {
		                $scope.message = $scope.response.message;
		                $scope.questionID = angular.fromJson($scope.response.data).questionID;
		                $scope.questionDescription = angular.fromJson($scope.response.data).questionDescription;
		                $scope.responseDataType = angular.fromJson($scope.response.data).responseDataType;
		                $scope.questionArray.push({
		                        'questionID': $scope.questionID,
		                        'questionDescription': $scope.questionDescription,
		                        'responseDataType': $scope.responseDataType});
		                // CLEAR TEXTBOX.
		                $scope.questionID = "";
		                $scope.questionDescription = "";
		                $scope.responseDataType = "";
		            }},
		        function(result) {alert("Server response-FAILURE! Please try again later");
		        });
		};	

	// PUBLISH QUESTIONNAIRE
	$scope.publish = function() {
		$rootScope.fQQuestPublished = false;
		$scope.questionIDList = [];
		for (var i = 0; i < $scope.questionArray.length; i++) {
			var questionID = $scope.questionArray[i].questionID;
			$scope.questionIDList.push(questionID);}
		$http({
			method : "post",
			url : "/query/updateQ",
			params : {
				"questionIDList" : $scope.questionIDList,
				"questionnaireID" : $scope.questionnaireID
			}}).then(function(result) {
							$rootScope.fQShowQ = false;
				            $rootScope.fQAddQuest = false;
				            $rootScope.fQSavedQuest = false;
							$rootScope.fQQuestPublished = true;},
						function(result) {alert("Server response-FAILURE! Please try again later");
						});
	};

	// REMOVE SELECTED ROW(s) FROM TABLE.
	$scope.removeRow = function() {
		var questionList = [];
		angular.forEach($scope.questionArray, function(value) {
			if (value.Remove) {
				$http({
					method : "post",
					url : "/query/deleteQuest",
					params : {
						"questionID" : value.questionID}});
			} else {questionList.push(value);}
		});
		$scope.questionArray = questionList;};
}