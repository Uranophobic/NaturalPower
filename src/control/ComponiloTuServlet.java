package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ComposizioneModel;
import model.ProdottoModel;
import model.SpecificaProdottoModel;
import model.beans.ProdottoBean;
import model.modelDS.ComposizioneModelDS;
import model.modelDS.ProdottoModelDS;
import model.modelDS.SpecificaProdottoModelDS;

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
     
	static ProdottoModel prodottoModel = new ProdottoModelDS();
	static SpecificaProdottoModel specificaProdottoModel = new SpecificaProdottoModelDS();
	static ComposizioneModel composizioneModel = new ComposizioneModelDS();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String azione = request.getParameter("azioneCompiniloTu");
		
		if(azione.equals("componiloTu")) { //prima cosa che si pone difronte all'utente è la scelta dei fiori
			
			request.getSession().setAttribute("componiloTuSessione", azione);
			
			try {
				
				ArrayList<ProdottoBean> prodottiNelCatalogo = prodottoModel.doRetrieveByCategoria("fiori");
				String categoria = request.getParameter("categoria");
				
				if(!prodottiNelCatalogo.isEmpty()) {
					RequestDispatcher view = request.getRequestDispatcher("sceltaFiori.jsp"); //la jsp scelta fiori ha un tasto "conferma" che ti visualizza la scelta del contenitore (if dopo)
					view.forward(request, response);
				} else {
					RequestDispatcher view = request.getRequestDispatcher("404NotFound.jsp");
					view.forward(request, response);
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		} 
		
		if(azione.equals("sceltaContenitore")) {
			
			
		}
		
		
	}

}
