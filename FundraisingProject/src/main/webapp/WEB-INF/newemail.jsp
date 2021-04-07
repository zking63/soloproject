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
    <title>New email page</title>
</head>
<body>
	<div class="titles">
		<h1>Hello, ${ user.firstName }</h1>
		<button><a href="/logout">Logout</a></button>
	</div>
	<div class="user-form">
		<h1>Upload a new email</h1>
	    <form:form method="POST" action="/newemail" modelAttribute="email">
	    	<form:hidden value="${ user.id }" path="email_uploader"/>
	    	<p>
	            <form:label path="emailName">Name:</form:label>
	            <form:errors path="emailName"></form:errors>
	            <form:input type="emailName" path="emailName"/>
	        </p>
	        <p>
	            <form:label path="emailRefcode">Refcode:</form:label>
	            <form:errors path="emailRefcode"></form:errors>
	            <form:input type="emailRefcode" path="emailRefcode"/>
	        </p>
	        <p>
	            <form:label path="Emaildate">Date and time:</form:label>
	            <form:errors path="Emaildate"></form:errors>
	            <form:input type="Emaildate" value="${dateFormat}" path="Emaildate"/>
	        </p>
	        <input type="submit" value="Upload!"/>
	    </form:form>
	</div>  
</body>
</html>