<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

	<div class="wrapper">
		<div class="formLogin">
			<h1>Accedi</h1>

			<form action="login" method="post">

				<input type="text" id="email" placeholder="Email" name="email"
					required> 
					<br>
					<input type="password" id="password"
					placeholder="Password" name="password" required>
					<br>
				<button type="submit" name="azioneLogin" value="accedi"
					id="login-button">Login</button>
			</form>

		</div>
	</div>
	<!-- effettare i controlli anche nella jsp, vedere come (jQuery? o boh)? -->
		

</body>
</html>