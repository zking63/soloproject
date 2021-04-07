<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>   
<head>
	<meta charset="ISO-8859-1">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" 
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
		crossorigin="anonymous">
	<link rel="stylesheet" href="/css/main.css"/>
	<title>Emails</title>
</head>
<body style="padding:15px">
	<button><a href="/newdonor">Upload a new donor</a></button>
	<button><a href="/newdonation">Upload a new donation</a></button>
	<button><a href="/logout">Logout</a></button>
	<h1>Emails</h1>
	<table class="table table-hover">
	    <thead>
	        <tr>
	            <th>Name</th>
	            <th>Send time</th>
	            <th>Send date</th>
	            <th>Refcode</th>
	            <th>Total revenue</th>
	            <th>Average donation</th>
	        </tr>
	    </thead>
		<tbody>
			<c:forEach items="${ email }" var="e">
				<tr>
					<td><a href="/emails/${e.id}">${ e.emailName }</a></td>
					<td>${e.getEmailTimeFormatted()}</td>
					<td>${e.getEmailDateFormatted()}</td>
					<td>${e.emailRefcode}</td>
					<td>${e.getEmailSum()}</td>
					<td>${e.getEmailAverage()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>