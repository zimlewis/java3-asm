<%@ page contentType="text/html;charset=UTF-8;" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="login" value="/login" />

<div class="mx-auto card p-4 shadow-lg" style="max-width: 400px; width: 100%;">
	<h3 class="text-center mb-4">Login</h3>
	<form action="login" method="POST">
		<!-- Email Field -->
		<div class="mb-3">
			<label for="email" class="form-label">Email</label>
			<input
					type="email"
					class="form-control"
					id="email"
					name="email"
					placeholder="Enter your email"
					required>
		</div>
		<!-- Password Field -->
		<div class="mb-3">
			<label for="password" class="form-label">Password</label>
			<input
					type="password"
					class="form-control"
					id="password"
					name="password"
					placeholder="Enter your password"
					required>
		</div>
		<!-- Login Button -->
		<div class="d-grid">
			<button type="submit" class="btn btn-primary">Login</button>
		</div>

		<div class="text-center mt-3">
			<a href="${login}" class="text-decoration-none">Register</a>
		</div>
	</form>
</div>