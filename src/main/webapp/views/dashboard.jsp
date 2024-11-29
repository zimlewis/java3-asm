<%@ page import="com.assignment.dao.NewsDAO" %><%--
  Created by IntelliJ IDEA.
  User: zimlewis
  Date: 11/29/24
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="news-list.jsp" var="page" />
<div class="row">

	<h1>Welcome, ${user.fullname}</h1>
	<c:if test="${!user.role}">
		<h3 class="text-center my-4">Create News</h3>
		<form action="" method="POST" enctype="multipart/form-data">
			<!-- Title Field -->
			<div class="mb-3">
				<label for="title" class="form-label">Title</label>
				<input
						type="text"
						class="form-control"
						id="title"
						name="title"
						placeholder="Enter title"
						required>
			</div>
			<!-- Content Field -->
			<div class="mb-3">
				<label for="content" class="form-label">Content</label>
				<textarea
						class="form-control"
						id="content"
						name="content"
						rows="5"
						placeholder="Write your content here..."
						required>

				</textarea>
			</div>
			<!-- Image Upload -->
			<div class="mb-3">
				<label for="image" class="form-label">Upload Image</label>
				<input
						type="file"
						class="form-control"
						id="image"
						name="image"
						accept="image/*"
						onchange="previewImage(event)" required>
				<div class="mt-3 d-flex align-items-center justify-content-center" style="min-height: 200px;">
					<img id="imagePreview" class="object-fit-contain border rounded w-100 h-100 d-none" alt="Image Preview">
				</div>

			</div>
			<!-- Select Field -->
			<div class="mb-3">
				<label for="category" class="form-label">Category</label>
				<select class="form-select" id="category" name="category" required>
					<c:forEach items="${categories}" var="categoryObject">
						<option value="${categoryObject.id}">${categoryObject.name}</option>
					</c:forEach>
				</select>
			</div>
			<!-- Submit Button -->
			<div class="d-grid">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
		<jsp:include page="${page}" />
	</c:if>
<%--	<h2>${user.role}</h2>--%>
</div>

<script>
    function previewImage(event) {
        const imageInput = event.target;
        const imagePreview = document.getElementById('imagePreview');

        if (imageInput.files && imageInput.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                imagePreview.src = e.target.result;
                imagePreview.classList.remove('d-none');
            };
            reader.readAsDataURL(imageInput.files[0]);
        }
    }
</script>