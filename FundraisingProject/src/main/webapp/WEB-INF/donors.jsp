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
	        </tr>
	    </thead>
		<tbody>
			<c:forEach items="${ donor }" var="q">
				<tr>
					<td>${ q.donorFirstName }</td>
					<td>${q.donorLastName}></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>