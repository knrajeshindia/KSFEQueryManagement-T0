<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html ng-app="myApp">

<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script src="<c:url value="/resources/static/js/loginApp.js" />"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
      
    
	<title>Home</title>
</head>
<body >

<h1>KSFE Home page</h1>

<hr>
<div class="container" ng-controller="loginController">

  <div class="row">
    
   <div class="col-4"> </div>
   <div class="col-4">
    
        <h3 class="form-signin-heading">Please sign in</h3>
        <label for="unitID" class="sr-only">UNIT ID</label>
        <input type="number" ng-model="unitID" class="form-control" placeholder="Unit ID" autofocus>
        <label for="password" class="sr-only">Password</label>
        <input type="password" ng-model="password" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" ng-click="verifyUnit()">Sign in</button><br>
     
      <div class="form-group ">
		<button type="button" class="btn btn-primary btn-lg btn-block login-button">Register</button>
		</div>
      
    </div>

    </div>  

    </div> <!-- /container -->
</body>
</html>