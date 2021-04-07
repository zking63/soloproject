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
	<title>Update donation</title>
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
	<button><a href="/newemail">Upload a new email</a></button>
	<h1>Edit donation</h1>
	<div class="user-form">
	    <form:form method="POST" action="/donations/edit/${donation.id}" modelAttribute="donation">
	    	<form:hidden value="${ user.id }" path="donation_uploader"/>
	    	<p>
	            <form:label path="amount">Amount:</form:label>
	            <form:errors path="amount"></form:errors>
	            <form:input type="long" path="amount"/>
	        </p>
	        <p>
	            <form:label path="Dondate">Date:</form:label>
	            <form:errors path="Dondate"></form:errors>
	            <form:input type="Dondate" value="${dateFormat}" path="Dondate"/>
	        </p>
	        <p>
		        <label for="donor">Assign a donor:</label>
				<select id="donor" name="donor">
				  	<c:forEach items="${ donor }" var="p">
			        	<option value="${ p.id }">${ p.donorEmail }</option>
			        </c:forEach>
				</select>
	        </p>
	        <p>
		        <label for="emailDonation">Assign an email:</label>
				<select id="emailDonation" name="emailDonation">
				  	<c:forEach items="${ email }" var="e">
			        	<option value="${ e.id }">${ e.emailRefcode }</option>
			        </c:forEach>
				</select>
	        </p>
	        <input type="submit" value="Submit"/>
	    </form:form>
	</div> 
</body>
</html>