<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrati</title>
</head>
<body>

	<div class="wrapper">
		<div class="formRegistrazione">
			<h1>Registrati</h1>

			<form action="registrazione" method="post">

				Nome:<input type="text" id="nome" placeholder="Nome" name="nome"
					required> <br> Cognome: <input type="text"
					id="cognome" placeholder="Cognome" name="cognome" required>
				<br> Data di nascita: <input type="text" id="dataNascita"
					placeholder="Data di nascita" name="dataNascita" required>
				<br>
				<!-- questo va aggiustato, mettere radio button -->
				Sesso: <input type="radio" name="genere" value="Male" required>
				M <input type="radio" name=gender value="female" required> F
				<br> Città: <input type="text" id="citta" placeholder="Città"
					name="citta" required> <br> Indirizzo<input
					type="text" id="indirizzo" placeholder="Indirizzo" name="indirizzo"
					required> <br>
				<!-- anche qui mettere suggerimento a tendina vedere come fare -->
				Tipo Carta<input type="text" id="tipocarta" placeholder="Tipo Carta"
					name="tipoCarta" required> <br> Numero Carta <input
					type="text" id="numeroCarta" placeholder="Numero Carta"
					name="numeroCarta" required> <br> Data Scadenza Carta:
				<input type="text" id="dataScadenzaCarta"
					placeholder="Data Scadenza Carta" name="datascadenzacarta" required>
				<br> Codice CVV: <input type="text" id="codiceCVV"
					placeholder="Codice CVV" name="codiceCVV" required> <br>
				Dati di spedizione: <input type="text" id="datiSpedizione"
					placeholder="Dati di Spedizione" name="datiSpedizione" required>
				<br> Password <input type="password" id="password"
					placeholder="Password" name="password" required> <br>
				Conferma Password <input type="password" id="password"
					placeholder=" Conferma Password" name="password" required>
				<br> <br>
				<button type="submit" name="azioneLogin" value="accedi"
					id="login-button">Conferma</button>
			</form>

		</div>
	</div>
	<!-- effettare i controlli anche nella jsp, vedere come (jQuery? o boh)? -->


</body>
</html>