<%--
  Created by IntelliJ IDEA.
  User: zimlewis
  Date: 11/30/24
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form class="mt-3" id="category-form" method="post">
	<!-- ID -->
	<div class="mb-3">
		<label for="id" class="form-label">ID</label>
		<input type="text" class="form-control" id="id" name="id" placeholder="Enter ID" required>
	</div>

	<!-- Name -->
	<div class="mb-3">
		<label for="name" class="form-label">Name</label>
		<input type="text" class="form-control" id="name" name="name" placeholder="Enter Name" required>
	</div>

	<!-- Submit Button -->
	<button formaction="${mainURL}category/create" class="btn btn-info" type="submit">Create</button>
	<button formaction="${mainURL}category/update" class="btn btn-success" type="submit">Update</button>
	<button formaction="${mainURL}category/delete" class="btn btn-danger" type="submit">Delete</button>
	<button type="reset" class="btn btn-secondary">Reset</button>
</form>

<h2 class="mb-3">Category</h2>
<table class="table table-striped table-bordered">
	<thead class="table-dark">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Action</th>
	</tr>
	</thead>
	<tbody>
	<!-- Example Row -->

	<c:forEach var="categoryObject" items="${categoryList}">
		<tr>
			<td>${categoryObject.id}</td>
			<td>${categoryObject.name}</td>
			<td>
				<button onclick="fillToCategoryTable(
                    {
                    	id: '${categoryObject.id}',
						name: '${categoryObject.name}'
                    }
                    )" class="btn btn-primary btn-sm">Action</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<script>
	const fillToCategoryTable = (data) => {
        const idField = document.querySelector('#category #id');
        const nameField = document.querySelector('#category #name');

        idField.value = data.id;
        nameField.value = data.name;
	}
</script>