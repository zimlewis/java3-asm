<%--
  Created by IntelliJ IDEA.
  User: zimlewis
  Date: 11/29/24
  Time: 1:03 PM
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
<h2>Newsletter</h2>
<form method="POST">
	<!-- Email -->
	<label for="email">Email</label>
	<input type="email" id="email" name="email" placeholder="Enter your email" required>

	<!-- Enable -->
	<label for="enable">Enable</label>
	<select id="enable" name="enable" required>
		<option value="true">Enabled</option>
		<option value="false">Disabled</option>
	</select>

	<!-- Submit Button -->
	<button formaction="/Assignment/newsletter/create" type="submit">Create</button>
	<button formaction="/Assignment/newsletter/update" type="submit">Update</button>
	<button formaction="/Assignment/newsletter/delete" type="submit">Delete</button>
	<button type="reset">Reset</button>
</form>
</body>
</html>
