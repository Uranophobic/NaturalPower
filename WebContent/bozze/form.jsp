<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login form</title>
<!-- collegamento css -->
<!-- ajax -->


</head>
<body>


	<h1>Login</h1>

	<form action="LoginServlet" method="post">
	
		<div> 
		
			<input type="text" id="email" placeholder="Email" name="email" required>
		</div>
		<div>
			<input type="password" id="password" placeholder="Password"
				name="password" required>
		</div>

		<div>
			<button type="submit" name="azione" value="accedi" id="login-button">Accedi</button>
		</div>

		
	</form>



</body>

</html>