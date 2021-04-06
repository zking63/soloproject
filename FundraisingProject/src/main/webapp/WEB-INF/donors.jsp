<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>   
<head>
	<meta charset="ISO-8859-1">
	<title>Donors</title>
</head>
<body>
	<button><a href="/newdonor">Upload a new donor</a></button>
	<h1>Donors</h1>
	<table>
	    <thead>
	        <tr>
	            <th>First Name</th>
	            <th>Last Name</th>
	            <th>Email address</th>
	            <th>Donation</th>
	        </tr>
	    </thead>
		<tbody>
			<c:forEach items="${ donor }" var="d">
				<tr>
					<td>${ d.donorFirstName }</td>
					<td>${d.donorLastName}</td>
					<td>${d.donorEmail}</td>
					<td><c:forEach items="${ d.contributions }" var="e">
					<p>${e.getAmount()}</p>
					</c:forEach></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>