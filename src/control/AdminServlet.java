package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.OrdineBean;
import model.beans.ProdottoBean;
import model.beans.SpecificaProdottoBean;
import model.modelDS.ComposizioneModelDS;
import model.modelDS.OrdineModelDS;
import model.modelDS.ProdottoModelDS;
import model.modelDS.SpecificaProdottoModelDS;
import model.modelDS.UtenteModelDS;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static ProdottoModelDS prodottoModel = new ProdottoModelDS();
	static SpecificaProdottoModelDS specificaProdottoModel = new SpecificaProdottoModelDS();
	static ComposizioneModelDS composizioneModel = new ComposizioneModelDS();
	static OrdineModelDS ordineModel = new OrdineModelDS();
	static UtenteModelDS utenteModel = new UtenteModelDS();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String azioneAdmin = request.getParameter("azioneAdmin");

		// Visualizzare gli ordini totali effettuati dai clienti
		if(azioneAdmin.equals("visualizzaOrdini")){
			
			ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();
			
			try {

				ordini = ordineModel.doRetrieveAll("idOrdine");
				request.getSession().setAttribute("ordini", ordini);
				RequestDispatcher view = request.getRequestDispatcher("AdminVisualizzaOrdini.jsp");
				view.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		// Inserimento nuovo prodotto
		if(azioneAdmin.equals("inserisciProdotto")) {

			try {

				ProdottoBean prodotto = new ProdottoBean();
				SpecificaProdottoBean specificheProdotto = new SpecificaProdottoBean();

				String nome = request.getParameter("nome");
				String descrizione = request.getParameter("descrizione");
				String categoria = request.getParameter("categoria");
				double prezzo = Double.parseDouble(request.getParameter("prezzo"));
				double iva = Double.parseDouble(request.getParameter("iva"));
				int sconto = Integer.parseInt(request.getParameter("sconto"));
				String immaginePath = request.getParameter("immaginePath");

				GregorianCalendar dataOdierna = new GregorianCalendar();
				int anno = dataOdierna.get(Calendar.YEAR);
				int mese = dataOdierna.get(Calendar.MONTH);
				int giorno = dataOdierna.get(Calendar.DAY_OF_MONTH);

				String caricamento = anno + "-" + mese + "-" + giorno;

				prodotto.setNome(nome);
				prodotto.setDescrizione(descrizione);
				prodotto.setCategoria(categoria);
				prodotto.setPrezzo(prezzo);
				prodotto.setSconto(sconto);
				prodotto.setCaricamento(caricamento);
				prodotto.setImmaginePath(immaginePath);				
				
				// Inserimento specifiche prodotto
				switch(categoria) {

				case "fiore" : 
					String tipoFiore = request.getParameter("tipo");
					String coloreFiore = request.getParameter("coloreFiore");
					String tipoStelo = request.getParameter("tipoStelo");

					specificheProdotto.setTipo(tipoFiore);
					specificheProdotto.setColoreFiore(coloreFiore);
					specificheProdotto.setTipoStelo(tipoStelo);

				case "pianta" :
					String tipoPianta = request.getParameter("tipo");
					String grandezzaPianta = request.getParameter("grandezzaPianta");

					specificheProdotto.setTipo(tipoPianta);
					specificheProdotto.setGrandezzaPianta(grandezzaPianta);

				case "accessorio" :
					String tipoAccessorio = request.getParameter("tipo");

					specificheProdotto.setTipo(tipoAccessorio);

				}

				prodottoModel.doSave(prodotto);
				specificaProdottoModel.doSave(specificheProdotto);

				response.sendRedirect("AdminIntro.jsp");

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		// Modifica singolo prodotto
		if(azioneAdmin.equals("modificaProdotto")) {

			try {
				// DA MODIFICARE QUANDO FARO' LA JSP ADMINUPDATEPRODOTTO
				ProdottoBean prodotto = new ProdottoBean();

				String idProdotto = request.getParameter("idProdotto");
				prodotto = prodottoModel.doRetrieveByKey(idProdotto);
				request.getSession().setAttribute("updateProdottoOggetto", prodotto);

				RequestDispatcher view = request.getRequestDispatcher("AdminUpdateProdotto.jsp");
				view.forward(request, response);

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		// Aggiorna il prodotto dopo aver effettuato le modifiche
		if(azioneAdmin.equals("updateProdotto")){

			try {

				String idProdotto = request.getParameter("idProdotto");

				ProdottoBean prodotto = prodottoModel.doRetrieveByKey(idProdotto);
				SpecificaProdottoBean specificheProdotto = specificaProdottoModel.doRetrieveByKey(idProdotto);

				String nome = request.getParameter("nome");
				String descrizione = request.getParameter("descrizione");
				String categoria = request.getParameter("categoria");
				double prezzo = Double.parseDouble(request.getParameter("prezzo"));
				int sconto = Integer.parseInt(request.getParameter("sconto"));
				double iva = Double.parseDouble(request.getParameter("iva"));
				String immaginePath = request.getParameter("immaginePath");

				prodotto.setNome(nome);
				prodotto.setDescrizione(descrizione);
				prodotto.setCategoria(categoria);
				prodotto.setPrezzo(prezzo);
				prodotto.setSconto(sconto);
				prodotto.setIva(iva);
				prodotto.setImmaginePath(immaginePath);

				switch(categoria) {

				case "fiore" : 
					String tipoFiore = request.getParameter("tipo");
					String coloreFiore = request.getParameter("coloreFiore");
					String tipoStelo = request.getParameter("tipoStelo");

					specificheProdotto.setTipo(tipoFiore);
					specificheProdotto.setColoreFiore(coloreFiore);
					specificheProdotto.setTipoStelo(tipoStelo);

				case "pianta" :
					String tipoPianta = request.getParameter("tipo");
					String grandezzaPianta = request.getParameter("grandezzaPianta");

					specificheProdotto.setTipo(tipoPianta);
					specificheProdotto.setGrandezzaPianta(grandezzaPianta);

				case "accessorio" :
					String tipoAccessorio = request.getParameter("tipo");

					specificheProdotto.setTipo(tipoAccessorio);

				}

				prodottoModel.doUpdate(prodotto);
				specificaProdottoModel.doUpdate(specificheProdotto);

				response.sendRedirect("AdminIntro.jsp");


			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		// Rimozione prodotto
		if(azioneAdmin.equals("rimuoviProdotto")) {

			try {

				String idProdotto = request.getParameter("idProdotto");

				prodottoModel.doDelete(idProdotto);
				specificaProdottoModel.doDelete(idProdotto);

				response.sendRedirect("AdminIntro.jsp");

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}


	}

}
