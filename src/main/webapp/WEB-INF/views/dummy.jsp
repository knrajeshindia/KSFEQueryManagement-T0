<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html ng-app="myApp">

<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script src="<c:url value="/resources/static/js/loginControllerDummy.js" />"></script>
<script src="<c:url value="/resources/static/js/randomService.js" />"></script>

</head>
<body ng-controller="loginC" >
<p>Random Number : {{randomNumber}} </p>
<hr>
<button ng-click="generateRandom()">Generate random1</button>
</body>
</html>