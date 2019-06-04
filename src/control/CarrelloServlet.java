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

import model.beans.ComposizioneBean;
import model.beans.ProdottoBean;
import model.beans.UtenteBean;
import model.ComposizioneModel;
import model.ProdottoModel;
import model.modelDS.ComposizioneModelDS;
import model.modelDS.ProdottoModelDS;

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ProdottoModel prodottoModel = new ProdottoModelDS();
	static ComposizioneModel composizioneModel = new ComposizioneModelDS();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String azioneCarrello = request.getParameter("azioneCarrello");

		System.out.println(azioneCarrello);

		if(azioneCarrello.equals("visualizzaProdotto")){
			try {
				ArrayList<ProdottoBean> prodottiNelCatalogo = prodottoModel.doRetrieveAll("idProdotto");
				String idProd = request.getParameter("idProdotto");

				for( int i=0; i<prodottiNelCatalogo.size(); i++) {
					if(prodottiNelCatalogo.get(i).getIdProdotto().equalsIgnoreCase(idProd)) {
						request.getServletContext().setAttribute("prodottoVisualizzato", idProd);
						RequestDispatcher view = getServletContext().getRequestDispatcher("/VisualizzaProdotto.jsp");
						view.forward(request, response);
					}
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}



		if(azioneCarrello.equals("aggiungiAlCarrello")){

			UtenteBean userSessione =(UtenteBean) request.getSession().getAttribute("utenteSessione");

			if(userSessione==null){

				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("notLogged");

				//response.sendRedirect("Catalogo.jsp");
			}

			else{

				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("logged");

				String idProd = request.getParameter("idProdotto");

				try {
					ProdottoBean p = prodottoModel.doRetrieveByKey(idProd);

					ArrayList<ComposizioneBean> carrello = (ArrayList<ComposizioneBean>) request.getSession().getAttribute("carrelloSessione");
					int quantitaCarrello =(int) (request.getSession().getAttribute("quantitaCarrello"));  
					if(carrello.size()==0){
						ComposizioneBean combo = new ComposizioneBean();
						combo.setIdProdotto(p.getIdProdotto());
						combo.setPrezzoUnitario(p.getPrezzo());
						combo.setScontoAttuale(p.getSconto());
						combo.setQuantità(1);
						carrello.add(combo);
						quantitaCarrello ++;

					}
					//il carrello non è vuoto
					else{
						int i;

						for(i = 0; i<carrello.size(); i++){
							//incrementa quantità di prodotto già presente
							if((carrello.get(i).getIdProdotto())==idProd){
								carrello.get(i).setQuantità((carrello.get(i).getQuantità()+1));
								quantitaCarrello ++;
								//System.out.println("Sono nel FOR, prodotto incrementato");
								break;
							}

							//aggiungi un nuovo prodotto al carrello
							//quindi una nuova Composizione, setta tutti gli attributi
							if(i==carrello.size()){
								ComposizioneBean combo = new ComposizioneBean();
								combo.setIdProdotto(p.getIdProdotto());
								combo.setPrezzoUnitario(p.getPrezzo());
								combo.setScontoAttuale(p.getSconto());
								combo.setQuantità(1);
								carrello.add(combo);
								quantitaCarrello ++;
								System.out.println("Nuovo prodotto");
							}




						} 

						request.getSession().removeAttribute("carrelloSessione");
						request.getSession().setAttribute("carrelloSessione", carrello);
						request.getSession().removeAttribute("quantitaCarrello");
						request.getSession().setAttribute("quantitaCarrello", quantitaCarrello);


						//p = prodottoModel.doRetrieveByKey(idProd);
						//request.getSession().setAttribute("prodottoSingolo", p);
						//RequestDispatcher view = request.getRequestDispatcher("Catalogo.jsp");
						//view.forward(request, response);
					}catch (SQLException e) {

						e.printStackTrace();
					}
				}

			}

			if(azioneCarrello.equals("rimuoviDalCarrello")){
				String idProd = request.getParameter("idProdotto");
				ArrayList<ComposizioneBean> carrello = (ArrayList<ComposizioneBean>) request.getSession().getAttribute("carrelloSessione");
				System.out.println("Carrello1 "+carrello);
				int quantitaCarrello =(int) (request.getSession().getAttribute("quantitaCarrello"));
				for(ComposizioneBean c: carrello){
					if(c.getIdProdotto()==idProd){
						int sottr = c.getQuantità();
						quantitaCarrello -= sottr;
						carrello.remove(c);
						break;
					}
				}
				System.out.println("Carrello 2 "+carrello);
				request.getSession().removeAttribute("carrelloSessione");
				request.getSession().setAttribute("carrelloSessione", carrello);
				request.getSession().removeAttribute("quantitaCarrello");
				request.getSession().setAttribute("quantitaCarrello", quantitaCarrello);

				if(carrello.size()!=0){
					RequestDispatcher view = request.getRequestDispatcher("VisualizzaCarrello3.jsp");
					view.forward(request, response);
				}
				else{
					RequestDispatcher view = request.getRequestDispatcher("Catalogo.jsp");
					view.forward(request, response);
				}
			}

			if(azioneCarrello.equals("aggiungiQuantita")){
				try {
					String idProd = request.getParameter("idProd");
					ProdottoBean p = prodottoModel.doRetrieveByKey(idProd);

					int quantita = Integer.parseInt(request.getParameter("quantità"));
					ArrayList<ComposizioneBean> carrello = (ArrayList<ComposizioneBean>) request.getSession().getAttribute("carrelloSessione");
					//System.out.println("Carrello "+carrello);
					int quantitaCarrello =(int) (request.getSession().getAttribute("quantitaCarrello"));
					if(carrello.size()==0){
						ComposizioneBean combo = new ComposizioneBean();
						combo.setIdProdotto(p.getIdProdotto());
						combo.setPrezzoUnitario(p.getPrezzo());
						combo.setScontoAttuale(p.getSconto());
						combo.setQuantità(quantita);

						carrello.add(combo);
						quantitaCarrello += quantita;
						//non fa dispatcher
					}
					else{
						int i;

						for(i = 0; i<carrello.size(); i++){
							//incrementa quantità di prodotto già presente
							if((carrello.get(i).getIdProdotto())==idProd){
								if(carrello.get(i).getQuantità() >  quantita) {
									quantitaCarrello -= (carrello.get(i).getQuantità() - quantita);
								}
								if(carrello.get(i).getQuantità() < quantita){
									quantitaCarrello += quantita -  carrello.get(i).getQuantità();
								}

								carrello.get(i).setQuantità(quantita);

								break;
							}
						}

						if(i==carrello.size()){
							ComposizioneBean combo = new ComposizioneBean();
							combo.setIdProdotto(p.getIdProdotto());
							combo.setPrezzoUnitario(p.getPrezzo());
							combo.setScontoAttuale(p.getSconto());
							combo.setQuantità(quantita);
							carrello.add(combo);
							quantitaCarrello += quantita;
							System.out.println("Nuovo prodotto");
						}
					}

					request.getSession().removeAttribute("carrelloSessione");
					request.getSession().setAttribute("carrelloSessione", carrello);
					request.getSession().removeAttribute("quantitaCarrello");
					request.getSession().setAttribute("quantitaCarrello", quantitaCarrello);
					System.out.println(quantita + "questa è la quantità di chi ti è biecchio");
					RequestDispatcher view = request.getRequestDispatcher("Catalogo.jsp");
					view.forward(request, response);

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			if(azioneCarrello.equals("visualizzaCarrello")){
				UtenteBean userSessione =(UtenteBean) request.getSession().getAttribute("utenteSessione");

				ArrayList<ComposizioneBean> carrello = (ArrayList<ComposizioneBean>) request.getSession().getAttribute("carrelloSessione");

				ArrayList<ProdottoBean> prodottiNelCarrello = new ArrayList<ProdottoBean>();

				ProdottoBean prodotto = new ProdottoBean();

				if((userSessione==null)||((userSessione!=null)&&(carrello.size()==0))){
					response.sendRedirect("CarrelloVuoto.jsp");
				}
				else{

					try {
						for(ComposizioneBean c: carrello){

							prodotto = prodottoModel.doRetrieveByKey(c.getIdProdotto());

							prodottiNelCarrello.add(prodotto);


						}


						request.getSession().removeAttribute("carrelloSessione");
						request.getSession().setAttribute("carrelloSessione", carrello);
						request.getSession().removeAttribute("prodottiCarrello");
						request.getSession().setAttribute("prodottiCarrello", prodottiNelCarrello);

						RequestDispatcher view = request.getRequestDispatcher("VisualizzaCarrello3.jsp");
						view.forward(request, response);
					}catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}
		}

	}
