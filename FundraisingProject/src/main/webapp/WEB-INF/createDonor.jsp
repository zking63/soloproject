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
    <title>Event page</title>
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
	<button><a href="/newdonation">Upload a new donation</a></button>
	<button><a href="/newemail">Upload a new email</a></button>
	<div class="titles">
	</div>
	<div class="user-form">
		<h1>Upload a donor</h1>
	    <form:form method="POST" action="/newdonor" modelAttribute="donor">
	    	<form:hidden value="${ user.id }" path="uploader"/>
	    	<p>
	            <form:label path="donorFirstName">First Name:</form:label>
	            <form:errors path="donorFirstName"></form:errors>
	            <form:input type="donorFirstName" path="donorFirstName"/>
	        </p>
	        <p>
	            <form:label path="donorLastName">Last Name:</form:label>
	            <form:errors path="donorLastName"></form:errors>
	            <form:input type="donorLastName" path="donorLastName"/>
	        </p>
	        <p>
	            <form:label path="donorEmail">Email:</form:label>
	            <form:errors path="donorEmail"></form:errors>
	            <form:input type="donorEmail" path="donorEmail"/>
	        </p>
	        <input type="submit" value="Upload!"/>
	    </form:form>
	</div>  
</body>
</html>