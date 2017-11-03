var app = angular.module("myApp", [])
.controller("loginC",function ($scope, random) {
	$scope.generateRandom = function() {
		alert("function");
		$scope.randomNumber = random.id;
	}
});

// ---------------------------------------------------------------------------------------------------------------------------------


