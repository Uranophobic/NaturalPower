<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
</head>
<body>

<link rel="stylesheet" href="https://necolas.github.io/normalize.css/3.0.2/normalize.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,300,700&subset=latin,latin-ext">
<link rel="stylesheet" href="css/headerstyle.css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Slab:400,600,700" rel="stylesheet">

<div class="navbar">
<ul>
  <nobr>
  <li><a href="#home">Home</a></li>
  <li><a href="#news">Catalogo</a></li>
  <li><a href="#contact">Componilo Tu!</a></li>
  <li><a href="#about">About Us</a></li>
   <li>   <div id="barCerca">
            <form action="ProdottoServlet" method = "POST" id="cercaForm" >
                <input name ="cliccaEcerca" required type="text" placeholder=" Digita qui.." id="InputCerca" >
                <button id="lenteIcona" name="azioneProdotto" value="ricerca" type="submit"> 
                    <i  class="fa fa-search" ></i>
                </button>
            </form>
            </div></li>
  
 <div id="navright">

  <li><a href="#profilo">Profilo</a></li> <!-- Ricordati che al posto di "profilo" c'è "Hi nome utente! -->
  <li><a href="#carrello">Carrello</a></li>
  </div>
  </nobr>
</ul>

  

</div>


</body>
</html>