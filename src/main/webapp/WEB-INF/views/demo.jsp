<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<title>Angular demo</title>
</head>
<body ng-app="queryManagementApp">
<script src="<c:url value="/resources/static/js/app.js" />"></script>
<!-- <img src="ksfe-logo.jpg" alt="KSFE" style="width:100px;height:80px;">	 -->
<h1>Angular Demo Page</h1>
<hr>

<div ng-controller="questionnaireController">
DIV-1
<script>
function main(){
console.log("main function called");
}
</script>
</div>

<div>
DIV-2
</div>

<div>
DIV-3
</div>

<div>
DIV-4
</div>


</body>
</html>
