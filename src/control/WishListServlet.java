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

import model.beans.UtenteBean;
import model.ComposizioneModel;
import model.ProdottoModel;
import model.beans.ComposizioneBean;
import model.beans.ProdottoBean;
import model.modelDS.ComposizioneModelDS;
import model.modelDS.ProdottoModelDS;

@WebServlet("/WishListServlet")
public class WishListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ProdottoModel prodottoModel = new ProdottoModelDS();
	static ComposizioneModel composizioneModel = new ComposizioneModelDS();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String azione = request.getParameter("azioneWishlist");

		// Aggiungi un prodotto alla wishlist
		if (azione.equals("aggiungiAWishlist")) {

			String idProd = request.getParameter("idProd");

			try {

				ProdottoBean prodotto = prodottoModel.doRetrieveByKey(idProd);

				// Verifica della wishlist della sessione utente al momento dell'invocazione del metodo
				ArrayList<ComposizioneBean> wishlist = (ArrayList<ComposizioneBean>) request.getSession().getAttribute("wishlistSessione");
				int quantit‡Wishlist = (int) (request.getSession().getAttribute("quantitaWishlist"));

				// Se la wishlist Ë vuota aggiungi il prodotto alla wishlist e incrementa il numero di prodotti nella wishlist
				if (wishlist.size() == 0) {

					ComposizioneBean prodComposizione = new ComposizioneBean();

					prodComposizione.setIdProdotto(prodotto.getIdProdotto());
					prodComposizione.setPrezzoUnitario(prodotto.getPrezzo());
					prodComposizione.setScontoAttuale(prodotto.getSconto());
					prodComposizione.setIva(prodotto.getIva());
					prodComposizione.setQuantit‡(1);
					wishlist.add(prodComposizione);
					quantit‡Wishlist++;

				}

				// Se il carrello non Ë vuoto si verificano due casi
				else {

					//Primo caso: si aggiunge un prodotto gi‡ presente nella wishlist
					int i;
					for(i = 0; i < wishlist.size(); i++){

						// Si incrementa la quantit‡ del prodotto gi‡ presente
						if((wishlist.get(i).getIdProdotto()) == idProd){

							wishlist.get(i).setQuantit‡((wishlist.get(i).getQuantit‡()+1));
							quantit‡Wishlist++;
							break;

						}

					}

					// Secondo caso: si aggiunge un prodotto nuovo nella wishlist
					if(!wishlist.isEmpty()) {

						// Si inserisce un nuovo prodotto (Composizione), settando tutti gli attributi
						ComposizioneBean prodComposizione = new ComposizioneBean();

						prodComposizione.setIdProdotto(prodotto.getIdProdotto());
						prodComposizione.setPrezzoUnitario(prodotto.getPrezzo());
						prodComposizione.setScontoAttuale(prodotto.getSconto());
						prodComposizione.setIva(prodotto.getIva());
						prodComposizione.setQuantit‡(1);
						wishlist.add(prodComposizione);
						quantit‡Wishlist++;

					}

				} 

				request.getSession().removeAttribute("wishlistSessione");
				request.getSession().setAttribute("wishlistSessione", wishlist);
				request.getSession().removeAttribute("quantit‡Wishlist");
				request.getSession().setAttribute("quantit‡Wishlist", quantit‡Wishlist);

				//prodotto = prodottoModel.doRetrieveByKey(idProd);
				//request.getSession().setAttribute("prodottoSingolo", prodotto);
				//RequestDispatcher view = request.getRequestDispatcher("Catalogo.jsp");
				//view.forward(request, response);

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		// Rimuove un prodotto interamente (cioË non diminuisce la quantit‡) dalla wishlist
		if(azione.equals("rimuoviDaWishlist")){

			String idProd = request.getParameter("idProdotto");
			ArrayList<ComposizioneBean> wishlist = (ArrayList<ComposizioneBean>) request.getSession().getAttribute("carrelloSessione");
			int quantit‡Wishlist =(int) (request.getSession().getAttribute("quantit‡Wishlist"));

			// Cerca il prodotto da eliminare nella wishlist dato l'id
			for(ComposizioneBean prod: wishlist) {

				if (prod.getIdProdotto() == idProd) {

					// Salva la quantit‡ del prodotto e la rimuove dalla quantit‡ della wishlist
					int sottr = prod.getQuantit‡();
					quantit‡Wishlist -= sottr;
					wishlist.remove(prod);
					break;

				}

			}

			request.getSession().removeAttribute("wishlistSessione");
			request.getSession().setAttribute("wishlistSessione", wishlist);
			request.getSession().removeAttribute("quantit‡Wishlist");
			request.getSession().setAttribute("quantit‡Wishlist", quantit‡Wishlist);

			if(wishlist.size() != 0) {

				RequestDispatcher view = request.getRequestDispatcher("VisualizzaWishlist.jsp");
				view.forward(request, response);

			} else {

				RequestDispatcher view = request.getRequestDispatcher("Catalogo.jsp");
				view.forward(request, response);

			}

		}

		// Aggiorna la quantit‡ del prodotto nella schermata della wishlist
		if (azione.equals("aggiungiQuantit‡")) {

			try {

				String idProd = request.getParameter("idProdotto");
				ProdottoBean prodotto = prodottoModel.doRetrieveByKey(idProd);
				int quantit‡ = Integer.parseInt(request.getParameter("quantit‡"));

				ArrayList<ComposizioneBean> wishlist = (ArrayList<ComposizioneBean>) request.getSession().getAttribute("wishlistSessione");
				int quantit‡Wishlist = (int) (request.getSession().getAttribute("quantit‡Wishlist"));

				int i;
				for (i = 0; i < wishlist.size(); i++) {

					if ((wishlist.get(i).getIdProdotto()) == idProd) {

						// per sapere come funziona questa serie di if controllare file math.txt
						// ca a final o teng sul ij
						// quindi dovreste chiedermelo
						if(wishlist.get(i).getQuantit‡() >  quantit‡) {
							quantit‡Wishlist -= (wishlist.get(i).getQuantit‡() - quantit‡);
						}

						if(wishlist.get(i).getQuantit‡() < quantit‡){
							quantit‡Wishlist += (quantit‡ -  wishlist.get(i).getQuantit‡());
						}

						wishlist.get(i).setQuantit‡(quantit‡);
						break;

					}

				}

				request.getSession().removeAttribute("wishlistSessione");
				request.getSession().setAttribute("wishlistSessione", wishlist);
				request.getSession().removeAttribute("quantit‡Wishlist");
				request.getSession().setAttribute("quantit‡Wishlist", quantit‡Wishlist);

				if (wishlist.size() != 0) {

					RequestDispatcher view = request.getRequestDispatcher("VisualizzaWishlist.jsp");
					view.forward(request, response);

				} else {

					RequestDispatcher view = request.getRequestDispatcher("Catalogo.jsp");
					view.forward(request, response);

				}

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}


		// Visualizza wishlist
		if(azione.equals("visualizzaWishlist")){

			ArrayList<ComposizioneBean> wishlist = (ArrayList<ComposizioneBean>) request.getSession().getAttribute("wishlistSessione");
			ArrayList<ProdottoBean> prodottiWishlist = new ArrayList<ProdottoBean>();
			ProdottoBean prodotto = new ProdottoBean();

			// Se la wishlist Ë vuota reindirizza alla jsp WishlistVuota.jsp
			if(wishlist.size() == 0) {

				response.sendRedirect("WishlistVuota.jsp");

				// Altrimenti inserisce tutti i prodotti nell'arraylist prodottiWishlist
			} else {

				try {

					for(ComposizioneBean prod: wishlist) {

						prodotto = prodottoModel.doRetrieveByKey(prod.getIdProdotto());
						prodottiWishlist.add(prodotto);

					}

					request.getSession().removeAttribute("wishlistSessione");
					request.getSession().setAttribute("wishlistSessione", wishlist);
					request.getSession().removeAttribute("prodottiWishlist");
					request.getSession().setAttribute("prodottiWishlist", prodottiWishlist);

					RequestDispatcher view = request.getRequestDispatcher("VisualizzaWishlist.jsp");
					view.forward(request, response);

				} catch (SQLException e) {

					e.printStackTrace();

				}

			}

		}

	}

}
