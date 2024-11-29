<%--
  Created by IntelliJ IDEA.
  User: zimlewis
  Date: 11/29/24
  Time: 1:06 PM
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
<h2>Category</h2>
<form method="POST">
	<!-- ID -->
	<label for="id">ID</label>
	<input type="text" id="id" name="id" placeholder="Enter the ID" required>

	<!-- Name -->
	<label for="name">Name</label>
	<input type="text" id="name" name="name" placeholder="Enter your name" required>

	<!-- Submit Button -->
	<button formaction="/Assignment/category/create" type="submit">Create</button>
	<button formaction="/Assignment/category/update" type="submit">Update</button>
	<button formaction="/Assignment/category/delete" type="submit">Delete</button>
	<button type="reset">Reset</button>
</form>
</body>
</html>
