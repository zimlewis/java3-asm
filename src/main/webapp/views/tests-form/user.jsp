<%--
  Created by IntelliJ IDEA.
  User: zimlewis
  Date: 11/29/24
  Time: 1:02 PM
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
<h2>User Form</h2>
<form method="POST">
	<!-- ID -->
	<label for="id">ID</label>
	<input type="text" id="id" name="id" placeholder="Enter your ID" required>

	<!-- Full Name -->
	<label for="fullname">Full Name</label>
	<input type="text" id="fullname" name="fullname" placeholder="Enter your full name" required>

	<!-- Password -->
	<label for="password">Password</label>
	<input type="password" id="password" name="password" placeholder="Enter your password" required>

	<!-- Email -->
	<label for="email">Email</label>
	<input type="email" id="email" name="email" placeholder="Enter your email" required>

	<!-- Phone -->
	<label for="phone">Phone</label>
	<input type="tel" id="phone" name="phone" placeholder="Enter your phone number" required>

	<!-- Role -->
	<label for="role">Role</label>
	<select id="role" name="role" required>
		<option value="true">Admin</option>
		<option value="false">User</option>
	</select>

	<!-- Gender -->
	<label for="gender">Gender</label>
	<select id="gender" name="gender" required>
		<option value="true">Male</option>
		<option value="false">Female</option>
	</select>

	<!-- Birthday -->
	<label for="birthday">Birthday</label>
	<input type="date" id="birthday" name="birthday" required>

	<!-- Submit Button -->
	<button formaction="/Assignment/user/create" type="submit">Create</button>
	<button formaction="/Assignment/user/update" type="submit">Update</button>
	<button formaction="/Assignment/user/delete" type="submit">Delete</button>
	<button type="reset">Reset</button>
</form>
</body>
</html>
