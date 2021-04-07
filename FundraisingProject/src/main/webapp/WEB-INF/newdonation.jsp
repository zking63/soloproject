<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>   
<head>
	<meta charset="ISO-8859-1">
	<title>New donation</title>
</head>
<body>
	<h1>New donation</h1>
	<div class="user-form">
	    <form:form method="POST" action="/newdonation" modelAttribute="donation">
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
		        <label for="email">Assign an email:</label>
				<select id="email" name="email">
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