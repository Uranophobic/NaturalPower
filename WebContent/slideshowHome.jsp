<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<head>
  <title>Slideshow Home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:400,600,700" rel="stylesheet">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/slideshowHomeStyle.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

  <div id="myCarousel" class="carousel slide">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li class="item1 active"></li>
      <li class="item2"></li>
      <li class="item3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">

        <div class="item active">
        <a href="http://www.google.it"><img src="img/piante.jpg" alt="Piante Grasse" style="width:100%;"></a>
    <!--     <div class="carousel_top">
          <h2 style="font-size:8vw">C'è aria di novità!</h2>
          <h4 style="font-size:4vw">Leggi i cinque benefici delle piante grasse!</h4>
        </div> -->
      </div>

      <div class="item">
         <a href="http://www.viciouspotato.net"><img src="img/sbrigati.jpg" alt="Saldi" style="width:100%;"></a>
    <!--    <div class="carousel-caption">
          <h4 style="font-size:6vw">Sbrigati! Questi saldi stanno per appassire!</h4>
        </div> -->
      </div>
    
      <div class="item">
        <a href="https://it.lipsum.com"><img src="img/regalo.jpg" alt="Regalo" style="width:100%;"></a>
 <!--        <div class="carousel-caption" >
          <h4 style="font-size:5vw">Vuoi proprio fare un regalo a chi-sai-tu?</h4>
          <h3 style="font-size:8vw">Vai al catalogo</h3>
        </div> -->
      </div>
  
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Indietro</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Avanti</span>
    </a>
  </div>
</div>
<script src="javascript/slideshowHome.js"></script>
</body>
</html>
