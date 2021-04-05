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
	            <form:label path="amount">Amount</form:label>
	            <form:errors path="amount"></form:errors>
	            <form:input type="amount" path="amount"/>
	        </p>
	        <p>
	            <form:label path="date">Amount</form:label>
	            <form:errors path="date"></form:errors>
	            <form:input type="date" value="${dateFormat}" path="date"/>
	        </p>
	        <p>
		        <label for="donor">Assign a donor:</label>
				<select id="donor" name="donor">
				  	<c:forEach items="${ donor }" var="p">
			        	<option value="${ p.id }">${ p.donorEmail }</option>
			        </c:forEach>
				</select>
	        <input type="submit" value="Submit"/>
	        </p>
	    </form:form>
	</div> 
</body>
</html>