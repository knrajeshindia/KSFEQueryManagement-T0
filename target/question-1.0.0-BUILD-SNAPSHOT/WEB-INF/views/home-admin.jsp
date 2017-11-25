<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <html ng-app="myApp">

    <head>

        <style>
            input[type=checkbox] {
                -webkit-appearance: checkbox;
            }
        </style>

    </head>

    <body ng-controller="QuestionnaireController as ctrl">

        <button ng-click="changeVisibility()" ng-show="flag1Button1">Create Questionnaire</button>

        <h1>KSFE</h1>
        <hr>

        

            
            </form>
            <hr>
        </div>

        
            </table>
            <hr>
        </div>

        <div id="4" ng-show="flag4">
            <h3>Add Question</h3>
            <hr>
            <table>
                <tr>
                    <td>Question</td>
                    <td>
                        <textarea ng-model="questionDescription" rows="2" cols="50"> </textarea>
                    </td>
                </tr>
                <tr>
                    <td>Response data type</td>
                    <td>
                        <select ng-model="selectedResponseDataType" ng-options="responseDataType.name for responseDataType in responseDataTypes"></select>
                    </td>
                </tr>
                <tr>
                    <tr>
                        <td>
                            <button type="submit" ng-click="insertQuest()">Add Question
                            </button>
                        </td>
                    </tr>

            </table>

            <hr>

        
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
        <script src="<c:url value="/resources/static/js/ksfeApp.js" />"></script>
            
            <script src="<c:url value="/resources/static/js/service/loginService.js" />"></script>
            <script src="<c:url value="/resources/static/js/controller/questionnaireController.js" />"></script>

    </body>

    </html>