package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Realizzando una servlet apposita per il ComponiloTu dovremmo considerare le
 *  funzionalità che vengono realizzate in tutta la classe AdminServlet attraverso 
 *  l'if principale, le quali sono: inserimento prodotto composizione, modifica
 *  prodotto composizione, update prodotto composizione, elimina prodotto composizione. 
 *  (Nota: per visualizza ordini il problema non sussiste in quanto la funzione 
 *  costruita in AdminServlet mostra tutti gli ordini effettuati e sicuramente 
 *  le composizioni possono essere reperite nella lista degli ordini totali, dal 
 *  momento che vengono aggiunti attraverso i costrutti sopra citati) 
 *  Forse bisogna prendere in considerazione l'idea di svolgere la selezione dei
 *  prodotti da inserire nella composizione in una JSP.
 *  Alla fine della creazione del prodotto composizione bisogna aggiungerlo all'ordine. */

@WebServlet("/ComponiloTuServlet")
public class ComponiloTuServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
