<%--
  Created by IntelliJ IDEA.
  User: zimlewis
  Date: 11/30/24
  Time: 1:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="mt-3" id="user-form" method="post">
	<!-- ID -->
	<div class="mb-3">
		<label for="id" class="form-label">ID</label>
		<input type="text" class="form-control" id="id" name="id" placeholder="Enter ID" required>
	</div>

	<!-- Fullname -->
	<div class="mb-3">
		<label for="fullname" class="form-label">Fullname</label>
		<input type="text" class="form-control" id="fullname" name="fullname" placeholder="Enter Fullname" required>
	</div>

	<!-- Password -->
	<div class="mb-3">
		<label for="password" class="form-label">Password</label>
		<input type="text" class="form-control" id="password" name="password" placeholder="Enter Password" required>
	</div>

	<!-- Email -->
	<div class="mb-3">
		<label for="email" class="form-label">Email</label>
		<input type="email" class="form-control" id="email" name="email" placeholder="Enter Email" required>
	</div>

	<!-- Phone -->
	<div class="mb-3">
		<label for="phone" class="form-label">Phone</label>
		<input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter Phone Number" required>
	</div>

	<!-- Role -->
	<div class="mb-3">
		<label for="role" class="form-label">Role</label>
		<select class="form-select" id="role" name="role" required>
			<option value="true">Admin</option>
			<option value="false">User</option>
		</select>
	</div>

	<!-- Gender -->
	<div class="mb-3">
		<label for="gender" class="form-label">Gender</label>
		<select class="form-select" id="gender" name="gender" required>
			<option value="true">Male</option>
			<option value="false">Female</option>
		</select>
	</div>

	<!-- Birthday -->
	<div class="mb-3">
		<label for="birthday" class="form-label">Birthday</label>
		<input type="date" class="form-control" id="birthday" name="birthday" required>
	</div>

	<!-- Submit Button -->
	<button formaction="${mainURL}user/create" class="btn btn-info" type="submit">Create</button>
	<button formaction="${mainURL}user/update" class="btn btn-success" type="submit">Update</button>
	<button type="reset"  class="btn btn-secondary">Reset</button>
</form>

<h2 class="mb-3">User Information Table</h2>
<table class="table table-striped table-bordered table-responsive">
	<thead class="table-dark">
	<tr>
		<th>ID</th>
		<th>Full Name</th>
		<th>Password</th>
		<th>Email</th>
		<th>Phone</th>
		<th>Role</th>
		<th>Gender</th>
		<th>Birthday</th>
		<th>Action</th>
	</tr>
	</thead>
	<tbody>
	<!-- Example Row -->
	<c:forEach items="${userList}" var="userObject">
		<tr>
			<td>${userObject.id}</td>
			<td>${userObject.fullname}</td>
			<td>${userObject.password}</td>
			<td>${userObject.email}</td>
			<td>${userObject.phone}</td>
			<c:choose>
				<c:when test="${userObject.role}">
					<td>
						<span class="badge bg-success">Admin</span>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<span class="badge bg-warning">User</span>
					</td>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${userObject.gender}">
					<td>
						<span class="badge bg-primary">male</span>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<span class="badge bg-danger">female</span>
					</td>
				</c:otherwise>
			</c:choose>
			<td>${userObject.birthday}</td>
			<td>
				<button onclick="fillToUserForm(
                    {
                    	id: '${userObject.id}',
						fullname: '${userObject.fullname}',
						password: '${userObject.password}',
						email: '${userObject.email}',
						phone: '${userObject.phone}',
						role: '${userObject.role}',
						gender: '${userObject.gender}',
						birthday: '${userObject.birthday}'
                    }
				)" class="btn btn-primary btn-sm">Edit</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<script>
	const fillToUserForm = (data) => {
		const idField = document.querySelector('#user-form #id');
        const fullnameField = document.querySelector('#user-form #fullname');
        const passwordField = document.querySelector('#user-form #password');
        const emailField = document.querySelector('#user-form #email');
        const roleField = document.querySelector('#user-form #role');
        const phoneField = document.querySelector('#user-form #phone');
        const genderField = document.querySelector('#user-form #gender');
        const birthdayField = document.querySelector('#user-form #birthday');

        idField.value = data.id;
        fullnameField.value = data.fullname;
        passwordField.value = data.password;
        emailField.value = data.email;
        phoneField.value = data.phone;
        roleField.value = data.role;
        genderField.value = data.gender;
        birthdayField.value = data.birthday;
	}
</script>