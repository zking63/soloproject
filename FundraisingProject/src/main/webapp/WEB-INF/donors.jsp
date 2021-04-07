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
	<title>Donors</title>
</head>
<body style="padding:15px">
	<button><a href="/newdonor">Upload a new donor</a></button>
	<button><a href="/newdonation">Upload a new donation</a></button>
	<button><a href="/logout">Logout</a></button>
	<h1>Donors</h1>
	<table class="table table-hover">
	    <thead>
	        <tr>
	            <th>Name</th>
	            <th>Email address</th>
	            <th>Most recent donation amount</th>
	            <th>Most recent donation date</th>
	            <th>Action</th>
	        </tr>
	    </thead>
		<tbody>
			<c:forEach items="${ donor }" var="d">
				<tr>
					<td><a href="/donors/${d.id}">${ d.donorFirstName } ${d.donorLastName}</a></td>
					<td>${d.donorEmail}</td>
					<td>$${d.getMostRecentdonation()}</td>
					<td>${d.getMostRecentdonationDate()}</td>
					<td>
					<p><a href="/donors/edit/${d.id}">Edit</a></p>
					<p><a href="/donors/delete/${d.id}">Delete</a></p>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>