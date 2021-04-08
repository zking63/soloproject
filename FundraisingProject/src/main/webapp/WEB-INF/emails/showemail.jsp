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
    <title>Email page</title>
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
			<h1><b>Email:</b> ${ emails.emailName }.</h1>
		</div>
		<div class="event-details-side">
			<h2>Totals</h2>
			<table class="table table-hover">
			    <thead>
			        <tr>
			            <th>Total number of donors</th>
			            <th>Send date</th>
			            <th>Send time</th>
			            <th>Total Revenue</th>
			            <th>Average donation</th>
			        </tr>
			    </thead>
				<tbody>
					<tr>
						<td>${emails.getEmaildonations().size()}</td>
						<td>${ emails.getEmailDateFormatted() }</td>
						<td>${ emails.getEmailTimeFormatted() }</td>
						<td>$${ emails.getEmailSum() }</td>
						<td>$${ emails.getEmailAverage() }<td>
					</tr>
				</tbody>
			</table>
			<h2>Donations</h2>
			<table class="table table-hover">
			    <thead>
			        <tr>
			            <th>Donor</th>
			            <th>Date</th>
			            <th>Time</th>
			            <th>Amount</th>
			            <th>Action</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${emails.getEmaildonations()}" var="c">
						<tr>
						<td><a href="/donors/${c.donor.id}">${ c.donor.donorFirstName } ${c.donor.donorLastName}</a></td>
						<td>${ c.getDonationDateFormatted() }</td>
						<td>${ c.getDonationTimeFormatted() }</td>
						<td>$${ c.amount }</td>
						<td>
							<p><a href="/donations/edit/${c.id}">Edit</a></p>
							<p><a href="/donations/delete/${c.id}">Delete</a></p>
						</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div> 
</body>
</html>