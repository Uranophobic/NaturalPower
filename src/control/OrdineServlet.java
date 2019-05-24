package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.UtenteBean;
import model.beans.ProdottoBean;
import model.beans.OrdineBean;
import model.beans.ComposizioneBean; 

import model.ProdottoModel;
import model.OrdineModel;
import model.ComposizioneModel;

import model.modelDS.ProdottoModelDS;
import model.modelDS.OrdineModelDS;
import model.modelDS.ComposizioneModelDS; 

public class OrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//istanza statica di tutto 
	static ProdottoModel prodottoModel = new ProdottoModelDS();
	static OrdineModel ordineModel = new OrdineModelDS();
	static ComposizioneModel composizioneModel = new ComposizioneModelDS();


	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost (request, response); 
	}

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String azione = request.getParameter("azioneOrdine");
		System.out.println(azione);

		/* Visualizza prodotti che sono presenti nel carrello dell'utente 
		 * Creo un array che rappresenta il carrello e un altro array che rappresenta i singoli prodotti nel carrello 
		 * Settando le rispettive sessioni con i nomi di: carrelloSessione e prodottiCarrello */

		if (azione.equals("procediOrdine")) {

			ArrayList<ComposizioneBean> carrello = (ArrayList<ComposizioneBean>) request.getSession().getAttribute("sessioneCarrello");
			ArrayList<ProdottoBean> prodottiNelCarrello = (ArrayList<ProdottoBean>) request.getSession().getAttribute("prodottiCarrello");

			if(!carrello.isEmpty()) {
				request.getSession().setAttribute("sessioneCarrello", carrello);
				request.getSession().setAttribute("prodottiCarello", prodottiNelCarrello);

				RequestDispatcher view = request.getRequestDispatcher("RiepilogoProdotti.jsp");
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("CarrelloVuoto.jsp");
				view.forward(request, response);
			}
		}


		/*Visualizza tutte le informazioni inerenti all'acquisto di prodotti del cliente 
		 * + le informazioni inirenti al cliente 
		 * + il totale */

		if(azione.equals("procediAcquisto")) {
			//mi prendo la sessione dell'utente
			UtenteBean utente = (UtenteBean) request.getSession().getAttribute("sessioneUtente");
			ArrayList<ComposizioneBean> carrello = (ArrayList<ComposizioneBean>) request.getSession().getAttribute("sessioneCarrello");

			//stampa dei dati personali del cliente
			System.out.println(utente.getEmail());
			System.out.println(utente.getNome());
			System.out.println(utente.getCognome());
			System.out.println(utente.getDataNascita());


			try {
				/*creo un oggetto di tipo ordine bean perchè dato che ora siamo nella parte dove l'utente ha 'confermato' i prodotti 
				che intende acquistare, a questo punto è giusto iniziare a parlare di ordine, ed ogni ordine ha un id, quindi lo generiamo
				e lo settiamo, insieme al codice utente che corrisponde all'email. */

				OrdineBean ordine = new OrdineBean();
				int id = ordineModel.generaCodice();
				ordine.setIdOrdine(id);
				ordine.setIdUtente(utente.getEmail()); 

				double prezzoTot =0;

				//per calcolare il totale bisogna tener conto anche dello sconto 
				for(int i=0; i<carrello.size(); i++) {
					if(carrello.get(i).getScontoAttuale()!=0) {
						double sconto, prezzoScontato;
						sconto = (carrello.get(i).getPrezzoUnitario() * carrello.get(i).getScontoAttuale()) / 100;
						prezzoScontato = carrello.get(i).getPrezzoUnitario() - sconto;

						prezzoTot = prezzoTot + (prezzoScontato*carrello.get(i).getQuantità());
						System.out.println(prezzoTot);	
					} else {
						prezzoTot = prezzoTot + carrello.get(i).getQuantità() * carrello.get(i).getPrezzoUnitario();
						System.out.println(prezzoTot);
					}
				}

				//il prezzo totale dell'ordine visualizzato al cliente viene settato 
				ordine.setPrezzoOrdineTotale(prezzoTot);

				//settiamo la data dell'ordine
				GregorianCalendar data = new GregorianCalendar();
				int anno = data.get(Calendar.YEAR);
				int mese = data.get(Calendar.MONTH) + 1;
				int giorno = data.get(Calendar.DAY_OF_MONTH);
				String a = String.valueOf(anno);
				String m = String.valueOf(mese);
				String g = String.valueOf(giorno);
				String dataOrdine = a+"-"+m+"-"+g;
				ordine.setDataOrdine(dataOrdine);

				//viene salvato l'ordine e settata la sessione
				ordineModel.doSave(ordine);
				request.getSession().setAttribute("sessioneOrdine", ordine);

				//per ogni oggetto di tipo composizione bean che si trova nel carrello, gli setta lo stesso id dell'ordine e lo salva nel DB
				for(ComposizioneBean c: carrello) {
					c.setIdOrdine(id);
					composizioneModel.doSave(c);
					aggiornaQuantita(c);
				}

				RequestDispatcher view = request.getRequestDispatcher("RiepilogoDati.jsp");
				view.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		/* Il cliente deve confermare che  i dati di spedizione e numero di carta siano giusti, se sono mancanti allora una 
		 * una jps gli indicherà che deve inserirli*/

		if(azione.equals("confermaDati")) {
			UtenteBean utente = (UtenteBean) request.getSession().getAttribute("sessioneUtente");

			//prendiamo i dati del cliente
			String indirizzo = utente.getIndirizzo();
			String citta = utente.getCittà();
			String cap = utente.getCAP();
			String cf = utente.getCF();
			String numCarta = utente.getNumeroCarta();
			String scadCarta = utente.getDataScadenzaCarta();

			//verifichiamo che non siano nulli 
			if ((indirizzo.equals(null) || indirizzo.isEmpty()) && 
					(citta.equals(null) || citta.isEmpty()) &&
					(cap.equals(null) || cap.isEmpty()) &&
					(cf.equals(null) || cf.isEmpty()) &&
					(numCarta.equals(null) || numCarta.isEmpty()) && 
					(scadCarta.equals(null) || scadCarta.isEmpty())) {

				//jsp che gli dice: 'aò inseriscili sti dati!'
				RequestDispatcher view = request.getRequestDispatcher("DatiMancanti.jsp");
				view.forward(request, response);
			} else {

				//jsp gli dice "si nu brav waglion, hai acquistato bene" 
				RequestDispatcher view = request.getRequestDispatcher("OrdineConfermato.jsp");
				
				//svuotamento del carrello, dato che il cliente ha acquistato il carrello si svuota (non sono sicura vada qui) 
				request.getSession().removeAttribute("sessioneCarrello");
				request.getSession().removeAttribute("prodottiCarrello");
			}			
		}

		/* Dopo che il cliente ha finalmente acquistato, può andare a vedere la fattura completa dell'ordine 
		 * cercandolo nei suoi ordini totali */
		if(azione.equals("visualizzaOrdine")) {
			UtenteBean utente = (UtenteBean) request.getSession().getAttribute("sessioneUtente");

			String m = utente.getEmail();
			int cod = Integer.parseInt(request.getParameter("idOrdine"));

			try {
				OrdineBean cercaOrd = ordineModel.doRetrieveByKey(cod);	
				if(cercaOrd.getIdUtente().equals(m)) {
					RequestDispatcher view = request.getRequestDispatcher("visualizzOrdine.jsp");
					view.forward(request, response);
				}
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		}
	}


	private void aggiornaQuantita(ComposizioneBean c) {
		ProdottoBean prod = new ProdottoBean();
		String codice = prod.getIdProdotto();
		try {

			prod = prodottoModel.doRetrieveByKey(codice);
			int quant = c.getQuantità();

			prod.setDisponibilità(prod.getDisponibilità()- quant);
			prodottoModel.doUpdate(prod);

		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}