<%@ page contentType="text/html;charset=UTF-8;" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div class="container">
	<c:url var="subscribe" value="/newsletter/create"/>
	<form action="${subscribe}" method="post">
		<h2>Newsletter Subscription</h2>

		<label for="to">Your Email:</label>
		<input class="form-control" name="email" id="to" type="email" placeholder="Enter your email" required/><br>
		<input class="d-none" value="true" name="enable" />
		<button class="btn btn-primary">Subscribe</button>
	</form>
	<br>
	<div class="alert alert-info">${newsletterMessage}</div>
</div>
</body>
</html>
