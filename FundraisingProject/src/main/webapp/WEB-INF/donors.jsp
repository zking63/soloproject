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
     	<h1 class="titles"><a href="/home">LoJo Fundraising</a></h1>
        <ul class="navbarmenu">
            <li class="main"><a href="/home">Home</a>
            </li>
            <li><a href="/donors">Donors</a></li>
            <li><a href="/emails">Emails</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>
    <div class="buttons1">
    <button><a href="/newdonor">Upload a new donor</a></button>
	<button><a href="/newdonation">Upload a new donation</a></button>
	<button><a href="/newemail">Upload a new email</a></button>
	</div>
	<div class="wrapper">
	<h2>Donors</h2>
	<form method="post" class="date-form" action="/donors">
		<input type="date" value="${startdateD}" name="startdateD"/>
		<input type="date" value="${enddateD}" name="enddateD"/>
		<button>Set</button>
	</form>
	<table class="table table-hover">
	    <thead>
	        <tr>
	            <th>Name</th>
	            <th>Email address</th>
	            <th>Most recent donation amount</th>
	            <th>Most recent donation date</br>
	            	<form class="pointer" method="post" action="/donors/sortdown">
						<input type="hidden" name="field" value="latestDonation">
						<input type="hidden" name="startdateE" value="${ startdateE}">
						<input type="hidden" name="enddateE" value="${ enddateE}">
						<button>^</button>
					</form>
					<form class="pointer" method="post" action="/donors/sortup">
						<input type="hidden" name="field" value="latestDonation">
						<input type="hidden" name="startdateE" value="${ startdateE}">
						<input type="hidden" name="enddateE" value="${ enddateE}">
						<button>v</button>
					</form>
	            </th>
	            <th>Times given</th>
	            <th>Total given</th>
	            <th>Average donation amount</th>
	            <th>Action</th>
	        </tr>
	    </thead>
		<tbody>
			<c:forEach items="${ donor }" var="d">
				<tr>
					<td><a href="/donors/${d.id}">${ d.donorFirstName } ${d.donorLastName}</a></td>
					<td>${d.donorEmail}</td>
					<td>$${d.getMostrecentDonationbyDonor().getAmount()}</td>
					<td>${d.getMostrecentDonationbyDonor().getDonationDateFormatted()} </br> ${d.getMostrecentDonationbyDonor().getDonationTimeFormatted()}</td>
					<td>${d.getDonordata().getDonor_contributioncount()}</td>
					<td>$${d.getDonordata().getDonorsum() }</td>
					<td>$${d.getDonordata().getDonoraverage() }</td>
					<td>
					<p><a href="/donors/edit/${d.id}">Edit</a></p>
					<p><a href="/donors/delete/${d.id}">Delete</a></p>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>