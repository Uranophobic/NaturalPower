package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdottoModel;
import model.beans.ProdottoBean;
import model.modelDS.ProdottoModelDS;

public class ProdottoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	static ProdottoModel prodottoModel = new ProdottoModelDS();

	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost (request, response); 
	}

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String azione = request.getParameter("azioneProdotto");

		if(azione.equals("visualizzaCatalogo")) {
			/*si prende tutti i prodotti dal db e li mette in un array prodottiNelCatalogo 
			 * se magicamente questo catalogo fosse vuoto, c'è qualcosa che non va 
			 * se ovviamente non è vuoto allora visualizza la jsp di catalogo e setta la sessione prodottiCatalogo
			 */
			try {
				ArrayList<ProdottoBean> prodottiNelCatalogo = prodottoModel.doRetrieveAll("idProdotto");
				if(!prodottiNelCatalogo.isEmpty()) {
					request.getSession().setAttribute("prodottiCatalogo", prodottiNelCatalogo);
					RequestDispatcher view = request.getRequestDispatcher("visualizzaCatalogo.jsp"); 
					view.forward(request, response);
				} else {
					RequestDispatcher view = request.getRequestDispatcher("404NotFound.jsp");
					view.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		if(azione.equals("visualizzaProdotto")) {
			/*per fare distinzione la variabile articolo fa riferimento SOLO al prodotto da cercare/visualizzare singolarmente, 
			 * tutto il resto si chiama prodotto. 
			 */

			//diciamo che si prende l'id dell'articolo che è stato selezionato per essere visualizzato (credo)
			String idArticolo = request.getParameter("idProdotto");

			try {
				//lo cerca in mezzo agl'altri articoli 
				ProdottoBean prodotto = prodottoModel.doRetrieveByKey(idArticolo); 
				request.getSession().setAttribute("vediProdotto", prodotto); //lo setta nella sessione 
				RequestDispatcher view = request.getRequestDispatcher("visualizzaProdotto.jsp"); //lo visualizza insieme a tutte le sue caratteristiche 
				view.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if(azione.equals("nuoviArrivi")) {
			/*ci vuole la data di caricamento del prodotto in modo da fare un confronto e quindi determinare
			 * quali sono gli articoli aggiunti di recente
			 */

			ArrayList<ProdottoBean> nuoviArrivi = new ArrayList<ProdottoBean>();

			try {
				ArrayList<ProdottoBean> tuttiProdotti = prodottoModel.doRetrieveAll("IDProdotto");

				for(ProdottoBean p: tuttiProdotti) {
					int mese = p.getDataCaricamento().MONTH;

					if(mese == 0) {         //gennaio
						nuoviArrivi.add(p);

					} else if (mese == 1) { //febbraio
						nuoviArrivi.add(p);	

					} else if (mese == 2) { //marzo
						nuoviArrivi.add(p);	

					} else if (mese == 3) { //aprile
						nuoviArrivi.add(p);	

					}else if (mese == 4) { //maggio
						nuoviArrivi.add(p);	

					}else if (mese == 5) { //giugno
						nuoviArrivi.add(p);	

					}else if (mese == 6) { //luglio 
						nuoviArrivi.add(p);	

					}else if (mese == 7) { //agosto
						nuoviArrivi.add(p);	

					}else if (mese == 8) { //settembre
						nuoviArrivi.add(p);	

					}else if (mese == 9) { //ottobre
						nuoviArrivi.add(p);	

					}else if (mese == 10) { //novembre
						nuoviArrivi.add(p);	

					}else if (mese == 11) { //dicembre
						nuoviArrivi.add(p);	
					}
				}

				if(!nuoviArrivi.isEmpty()) {
					request.getSession().setAttribute("nuoviArrivi", nuoviArrivi);
					RequestDispatcher view = request.getRequestDispatcher("nuoviArrivi.jsp");
					view.forward(request, response);
				} else {
					RequestDispatcher view = request.getRequestDispatcher("404NotFound.jsp");
					view.forward(request, response);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if(azione.equals("modaDelMomento")) {
			//forse
		}

		if(azione.equals("ricercaProdotto")) {
			ArrayList<ProdottoBean> risultatiRic = new ArrayList<ProdottoBean>();
			String parola = request.getParameter("cerca");  //boh
			//ordina tutti i prodotti per nome (alfabetico)
			try {
				
				ArrayList<ProdottoBean> tuttiProdotti = prodottoModel.doRetrieveAll("Nome");
				String parola2 = parola.toLowerCase();
				
				for(ProdottoBean p : tuttiProdotti) {
					if(p.getNome().contains(parola2)) {
						risultatiRic.add(p);
					}
				}
				
				if(!risultatiRic.isEmpty()) {
					request.getSession().setAttribute("ricercaProd", risultatiRic);
					RequestDispatcher view = request.getRequestDispatcher("risultatoRicerca.jsp");
					view.forward(request, response);
				} else {
					RequestDispatcher view = request.getRequestDispatcher("nessunRisultato.jsp");
					view.forward(request, response);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}


}
