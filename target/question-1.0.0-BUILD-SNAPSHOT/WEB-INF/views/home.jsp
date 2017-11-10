<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

        <html ng-app="myApp">

        <head>

            <title>Home</title>
        </head>

        <body>

            <h1>KSFE Home page</h1>

            <hr>
            <div class="container" ng-controller="LoginController">

                <div class="row">

                    <div class="col-4"></div>
                    <div class="col-4">

                        <h3 class="form-signin-heading">Please sign in</h3>
                        <label for="unitID" class="sr-only">UNIT ID</label>
                        <input type="number" ng-model="unitID" class="form-control" placeholder="Unit ID" autofocus>
                        <label for="password" class="sr-only">Password</label>
                        <input type="password" ng-model="password" class="form-control" placeholder="Password" required><br>
                        <button class="btn btn-lg btn-primary btn-block" ng-click="verifyUnit()">Sign in</button>
                        <br>

                        <div class="form-group ">
                            <button type="button" class="btn btn-primary btn-lg btn-block login-button">Register</button>
                        </div>

                    </div>

                </div>

            </div>
            <!-- /container -->

            <script src="<c:url value="/resources/static/js/angular.js" />"></script>
            <script src="<c:url value="/resources/static/js/ksfeApp.js" />"></script>           
            <script src="<c:url value="/resources/static/js/service/loginService.js" />"></script>
             <script src="<c:url value="/resources/static/js/controller/loginController.js" />"></script>
            

        </body>

        </html>