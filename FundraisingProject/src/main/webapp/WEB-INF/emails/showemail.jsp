<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
   	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
		rel="stylesheet" 
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
		crossorigin="anonymous">
	<link rel="stylesheet" href="css/main.css" />
    <title>Email page</title>
</head>
<body>
	<div class="container2">
		<div class="titles">
			<button><a href="/newdonor">Upload a new donor</a></button>
			<button><a href="/newdonation">Upload a new donation</a></button>
			<button><a href="/newemail">Upload a new email</a></button>
			<button><a href="/donors">Donors</a></button>
			<button><a href="/emails">Emails</a></button>
			<button><a href="/logout">Logout</a></button>
			<h1>Hello, ${ user.firstName }. Welcome to ${ emails.emailName }.</h1>
		</div>
		<div class="event-details-side">
			<h2>Donations</h2>
			<table class="table table-hover">
			    <thead>
			        <tr>
			            <th>Donor</th>
			            <th>Date</th>
			            <th>Time</th>
			            <th>Amount</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${emails.getEmaildonations()}" var="c">
						<tr>
						<td><a href="/donors/${c.id}">${ c.donor.donorFirstName } ${c.donor.donorLastName}</a></td>
						<td>${ c.getDonationDateFormatted() }</td>
						<td>${ c.getDonationTimeFormatted() }</td>
						<td>${ c.amount }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div> 
</body>
</html>