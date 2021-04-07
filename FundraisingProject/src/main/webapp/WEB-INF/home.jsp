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
<body>
     <div class="navbar">
        <ul class="navbarmenu">
            <li><a href="/home">Home</a></li>
            <li><a href="/donors">Donors</a></li>
            <li><a href="/emails">Emails</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>
    <button><a href="/newdonor">Upload a new donor</a></button>
	<button><a href="/newdonation">Upload a new donation</a></button>
	<button><a href="/newemail">Upload a new email</a></button>
	<h1>Donors</h1>
	<table class="table table-hover">
	    <thead>
	        <tr>
	            <th>Donor</th>
	            <th>Amount <a href="/sortup">^</a></th>
	            <th>Email given to</th>
	            <th>Date/time</th>
	        </tr>
	    </thead>
		<tbody>
			<c:forEach items="${ donations }" var="d">
				<tr>
					<td><a href="/donors/${d.donor.id}">${ d.donor.donorFirstName } ${d.donor.donorLastName}</a></td>
					<td>$${d.amount}</td>
					<td><a href="/emails/${d.emailDonation.id}">${d.emailDonation.emailName}</a></td>
					<td>${d.getDonationDateFormatted()} | ${d.getDonationTimeFormatted()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>