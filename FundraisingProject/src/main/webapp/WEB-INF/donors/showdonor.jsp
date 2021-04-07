<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" 
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
		crossorigin="anonymous">
	<link rel="stylesheet" href="/css/main.css"/>
    <title>Donor page</title>
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
	<div class="container2">
		<div class="titles">
			<h1><b>Donor history:</b> ${ donor.donorFirstName } ${ donor.donorLastName }</h1>
		</div>
		<h2>Donations</h2>
			<table class="table table-hover">
			    <thead>
			        <tr>
			            <th>Date</th>
			            <th>Time</th>
			            <th>Amount</th>
			            <th>Email</th>
			            <th>Action</th>
			        </tr>
			    </thead>
				<tbody>
				<c:forEach items="${ donor.contributions }" var="d">
					<tr>
						<td>${ d.getDonationDateFormatted() }</td>
						<td>${ d.getDonationTimeFormatted() }</td>
						<td>${ d.amount }</td>
						<td><a href="/emails/${ d.emailDonation.id }">${ d.emailDonation.emailName }</a></td>
						<td>
							<p><a href="/donations/edit/${d.id}">Edit</a></p>
							<p><a href="/donations/delete/${d.id}">Delete</a></p>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
</body>
</html>