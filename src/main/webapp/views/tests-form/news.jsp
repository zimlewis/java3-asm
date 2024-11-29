<%--
  Created by IntelliJ IDEA.
  User: zimlewis
  Date: 11/29/24
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>User Form</title>
	<style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        form {
            max-width: 400px;
            margin: auto;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input, textarea, select, button {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
	</style>
</head>
<body>
<h2>Create a Post</h2>
<form method="POST" enctype="multipart/form-data">
	<!-- ID -->
	<label for="id">ID</label>
	<input type="text" id="id" name="id" placeholder="Enter the ID" required>

	<!-- Title -->
	<label for="title">Title</label>
	<input type="text" id="title" name="title" placeholder="Enter the title" required>

	<!-- Content -->
	<label for="content">Content</label>
	<textarea id="content" name="content" rows="5" placeholder="Enter the content" required></textarea>

	<!-- Image -->
	<label for="image-src">Image</label>
	<input type="text" id="image" name="image" style="display: none">
	<input onchange="handleFileSelect(event)" type="file" id="image-src" name="image-src" accept="image/*">

	<!-- Posted Date -->
	<label for="postedDate">Posted Date</label>
	<input type="date" id="postedDate" name="postedDate" required>

	<!-- Author -->
	<label for="author">Author</label>
	<input type="text" id="author" name="author" placeholder="Enter the author name" required>

	<!-- View Count -->
	<label for="viewCount">View Count</label>
	<input type="number" id="viewCount" name="viewCount" placeholder="Enter the view count" min="0" required>

	<!-- Category ID -->
	<label for="categoryId">Category</label>
	<input type="text" id="categoryId" name="categoryId" placeholder="Enter the category ID" required>

	<!-- Home -->
	<label for="home">Display on Home Page</label>
	<select id="home" name="home" required>
		<option value="true">Yes</option>
		<option value="false">No</option>
	</select>

	<!-- Submit Button -->
	<button formaction="/Assignment/news/create" type="submit">Create</button>
	<button formaction="/Assignment/news/update" type="submit">Update</button>
	<button formaction="/Assignment/news/delete" type="submit">Delete</button>
	<button type="reset">Reset</button>
</form>
</body>
</html>

<script>
	const imageField = document.querySelector("#image");

    // Function to display the file name after selection
    function handleFileSelect(event) {
        // Get the selected file
        const file = event.target.files[0];

        imageField.value = file.name;
    }
</script>
