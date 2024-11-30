<%--
  Created by IntelliJ IDEA.
  User: zimlewis
  Date: 11/30/24
  Time: 1:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="mt-3" id="user-form" method="post" enctype="multipart/form-data">
	<!-- ID -->
	<div class="mb-3">
		<label for="id" class="form-label">ID</label>
		<input type="text" class="form-control" id="id" name="id" placeholder="Enter ID" required>
	</div>

	<!-- Title -->
	<div class="mb-3">
		<label for="title" class="form-label">Title</label>
		<input type="text" class="form-control" id="title" name="title" placeholder="Enter Title" required>
	</div>

	<!-- Content -->
	<div class="mb-3">
		<label for="content" class="form-label">Content</label>
		<textarea class="form-control" id="content" name="content" rows="5" placeholder="Enter Content" required></textarea>
	</div>

	<!-- Image -->
	<div class="mb-3">
		<label for="image" class="form-label">Upload Image</label>
		<input
				type="file"
				class="form-control"
				id="image"
				name="image"
				accept="image/*"
				onchange="previewImageNewsAdmin(event)" required>
		<div class="mt-3 d-flex align-items-center justify-content-center" style="min-height: 200px;">
			<img id="imagePreview" class="object-fit-contain border rounded w-100 h-100 d-none" alt="Image Preview">
		</div>

	</div>

	<!-- Posted Date -->
	<div class="mb-3">
		<label for="postedDate" class="form-label">Posted Date</label>
		<input type="date" class="form-control" id="postedDate" name="postedDate" required>
	</div>

	<!-- Author -->
	<div class="mb-3">
		<label for="author" class="form-label">Author</label>
		<select class="form-select" id="author" name="author" required>
			<c:forEach items="${userList}" var="userObject">
				<option value="${userObject.id}">${userObject.fullname}</option>
			</c:forEach>
		</select>
	</div>

	<!-- View Count -->
	<div class="mb-3">
		<label for="viewCount" class="form-label">View Count</label>
		<input type="number" class="form-control" id="viewCount" name="viewCount" placeholder="Enter View Count" required>
	</div>

	<!-- Category ID -->
	<div class="mb-3">
		<label for="categoryId" class="form-label">Category</label>
		<select class="form-select" id="categoryId" name="categoryId" required>
			<c:forEach items="${categoryList}" var="categoryObject">
				<option value="${categoryObject.id}">${categoryObject.name}</option>
			</c:forEach>
		</select>
	</div>

	<!-- Home -->
	<div class="mb-3">
		<label for="home" class="form-label">Display on Home</label>
		<select class="form-select" id="home" name="home" required>
			<option value="true">Yes</option>
			<option value="false">No</option>
		</select>
	</div>

	<!-- Submit Button -->
	<button formaction="${mainURL}news/create" class="btn btn-info" type="submit">Create</button>
	<button formaction="${mainURL}news/update" class="btn btn-success" type="submit">Update</button>
	<button formaction="${mainURL}news/delete" class="btn btn-danger" type="submit">Delete</button>
	<button type="reset" class="btn btn-secondary">Reset</button>
</form>

<h2 class="mb-3">Entity Table</h2>
<table class="table table-striped table-bordered table-responsive">
	<thead class="table-dark">
	<tr>
		<th>ID</th>
		<th>Title</th>
		<th>Content</th>
		<th>Image</th>
		<th>Posted Date</th>
		<th>Author</th>
		<th>View Count</th>
		<th>Category ID</th>
		<th>Home</th>
		<th>Action</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="newsObject" items="${newsList}">
		<tr>
			<td>${newsObject.id}</td>
			<td>${newsObject.title}</td>
			<td>
				<div class="d-inline-block text-truncate" style="max-width: 200px">
						${newsObject.content}
				</div>

			</td>
			<td>
					${newsObject.image}
			</td>
			<td>${newsObject.postedDate}</td>
			<td>
				<c:forEach items="${userList}" var="userObject">
					<c:if test="${userObject.id == newsObject.author}">
						${userObject.fullname}
					</c:if>
				</c:forEach>
			</td>
			<td>${newsObject.viewCount}</td>
			<td>
				<c:forEach items="${categoryList}" var="categoryObject">
					<c:if test="${newsObject.categoryId == categoryObject.id}">
						${categoryObject.name}
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:choose>
					<c:when test="${newsObject.home}">
						Yes
					</c:when>
					<c:otherwise>
						No
					</c:otherwise>
				</c:choose>

			</td>
			<td>
				<button onclick="fillToNewsForm(
                    {
                    	id: '${newsObject.id}',
						title: '${newsObject.title}',
						content: '${newsObject.content}',
						image: '${newsObject.image}',
						postedDate: '${newsObject.postedDate}',
						author: '${newsObject.author}',
						viewCount: '${newsObject.viewCount}',
						categoryId: '${newsObject.categoryId}',
						home: '${newsObject.home}'

                    }
				)" class="btn btn-primary btn-sm">Action</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<script>
    function previewImageNewsAdmin(event) {
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

    const fillToNewsForm = (data) => {
        const idField = document.querySelector('#user-form #id');
        const titleField = document.querySelector('#user-form #title');
        const contentField = document.querySelector('#user-form #content');
        const imageField = document.querySelector('#user-form #image');
        const postedDateField = document.querySelector('#user-form #postedDate');
        const categoryField = document.querySelector('#user-form #categoryId');
        const homeField = document.querySelector('#user-form #home');
        const viewCount = document.querySelector('#user-form #viewCount')
        const imagePreview = document.querySelector('#user-form #imagePreview');

        idField.value = data.id;
        titleField.value = data.title;
        contentField.value = data.content;
        imagePreview.src = '${mainURL}get-image/' + data.image;
        imagePreview.classList.remove('d-none');
        fetch('${mainURL}get-image/' + data.image)
            .then(res => res.blob()) // Gets the response and returns it as a blob
            .then(blob => {
                const file = new File([blob], 'image', {type:blob.type})

                const dataTransfer = new DataTransfer();
                dataTransfer.items.add(file);

                imageField.files = dataTransfer.files;

                console.log(imageField.files)
            });
        viewCount.value = data.viewCount;
        postedDateField.value = data.postedDate;
        categoryField.value = data.categoryId;
        homeField.value = data.home;


	}
</script>