<%--
  Created by IntelliJ IDEA.
  User: zimlewis
  Date: 11/30/24
  Time: 1:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form class="mt-3" id="newsletter-form" method="post">
	<!-- Email -->
	<div class="mb-3">
		<label for="email" class="form-label">Email</label>
		<input type="email" class="form-control" id="email" name="email" placeholder="Enter Email" required>
	</div>

	<!-- Enable -->
	<div class="mb-3">
		<label for="enable" class="form-label">Enable</label>
		<select class="form-select" id="enable" name="enable" required>
			<option value="true">Enabled</option>
			<option value="false">Disabled</option>
		</select>
	</div>

	<!-- Submit Button -->
	<button formaction="${mainURL}newsletter/create" class="btn btn-info" type="submit">Create</button>
	<button formaction="${mainURL}newsletter/update" class="btn btn-success" type="submit">Update</button>
	<button formaction="${mainURL}newsletter/delete" class="btn btn-danger" type="submit">Delete</button>
	<button type="reset" class="btn btn-secondary">Reset</button>
</form>
<h2 class="mb-3">Email & Enable Table</h2>
<table class="table table-striped table-bordered">
	<thead class="table-dark">
	<tr>
		<th>Email</th>
		<th>Enabled</th>
		<th>Action</th>
	</tr>
	</thead>
	<tbody>
	<!-- Example Row -->

	<c:forEach var="newsletterObject" items="${newsletterList}">
		<tr>
			<td>${newsletterObject.email}</td>

			<td>
				<c:choose>
					<c:when test="${newsletterObject.enable}">
						<span class="badge bg-success">Yes</span>
					</c:when>
					<c:otherwise>
						<span class="badge bg-danger">No</span>
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<button onclick="fillToNewsletterForm(
                    {
						email: '${newsletterObject.email}',
						enable: '${newsletterObject.enable}',
					}
                )" class="btn btn-primary btn-sm">Edit</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<script>
	const fillToNewsletterForm = (data) => {
        const emailField = document.querySelector('#newsletter-form #email');
        const enableField = document.querySelector('#newsletter-form #enable');

    	emailField.value = data.email;
        enableField.value = data.enable;
    }
</script>