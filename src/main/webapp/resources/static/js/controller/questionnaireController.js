'use strict';
var app = angular.module('queryManagementApp',[]);
app.controller('questionnaireController', function($scope) {
    $scope.names = [
        {name:'Jani',country:'Norway'},
        {name:'Carl',country:'Sweden'},
        {name:'Margareth',country:'England'},
        {name:'Hege',country:'Norway'},
        {name:'Joe',country:'Denmark'},
        {name:'Gustav',country:'Sweden'},
        {name:'Birgit',country:'Denmark'},
        {name:'Mary',country:'England'},
        {name:'Kai',country:'Norway'}
        ];

scope.insertQuestionnaire=function($scope, $window, $http) {
//DIV-1
$scope.flag1 = false;
//DIV-2
$scope.flag2 = false;
//DIV-3
$scope.flag3 = false;
//DIV-4
$scope.flag4 = false;

//Initialise variables to null
$scope.questionnaireTitle="";
$scope.questionnaireDescription="";
$scope.questionnaireRemarks="";
$scope.dueDate="";
$scope.targetRespondentIDList="";
$scope.senderName="";
$scope.senderJobTitle="";

$http({method: "get",
       url: "test",}).then
       (function (result) {
                        $window.alert("Server response-SUCCESS!");
                        $scope.flag1 = true;
                        },
       function (result) {
                        $window.alert("Server response-FAILURE! Please try again later");
       });



var questionnaireFormData={
"questionnaireTitle":$scope.questionnaireTitle,
"questionnaireDescription":$scope.questionnaireDescription,
"questionnaireRemarks":$scope.questionnaireRemarks,
"dueDate":$scope.dueDate,
"targetRespondentIDList":$scope.targetRespondentIDList,
"senderName":$scope.senderName,
"senderJobTitle":$scope.senderJobTitle};
$http.post('insertQ',questionnaireFormData).then(function (result){
$scope.response = angular.fromJson(result.data);
                        if ($scope.response.status === "SUCCESS") {
                            $scope.message = $scope.response.message;
                            $scope.data = angular.fromJson($scope.response.data).accountStatus;
                            $scope.status = $scope.response.message;
                            $scope.questionnaireID = angular.fromJson($scope.response.data).questionnaireID;
                            $scope.displayMessage=$scope.status + " | " + $scope.message + " | " + $scope.data;
                            $window.alert($scope.displayMessage);
                            }


},function (result){

 $window.alert("Server response-FAILURE! Please try again later");

}



)


}







});



