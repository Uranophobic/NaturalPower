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

import model.OrdineModel;
import model.UtenteModel;
import model.beans.UtenteBean;
import model.modelDS.OrdineModelDS;
import model.modelDS.UtenteModelDS;
import model.beans.*;
/*
 * Questa è la servlet dedicata all'utente. In tale servlet vengono effettuati i controlli sulle azioni che
 * l'utente può compiere quali: registrarsi, la formazione dell'account e la parte relativa agli ordini.
 */
@WebServlet("/utenteservlet")
public class UtenteServlet extends HttpServlet
{	
	private static final long serialVersionUID = 1L;

	//dichiarazione statica dei model di ordine e utente.
	static UtenteModel model=(UtenteModel) new UtenteModelDS();
	static OrdineModel oModel=new OrdineModelDS();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String azione=req.getParameter("azioneUtente");

		/*
		 * effettuo controlli: primo controllo relativo alla registrazione, secondo controllo relativo alla formazione dell'account, terzo controllo relativo agli ordini
		 */
		//primo controllo
		if(azione.equals("registrazione"))
		{
			try {
				String nome=req.getParameter("nome");
				String cognome=req.getParameter("cognome");
				String dataNascita=req.getParameter("dataNascita");
				String citta=req.getParameter("citta");
				String sesso=req.getParameter("sesso");
				String email=req.getParameter("email");
				String password=req.getParameter("password");
				String confermaPassword=req.getParameter("confermaPassword");

				/*
				 * Viene effettuato il controllo per verificare che la password inserita all'intenro del campo password, corrisponda alla 
				 * password inserita nel campo recupera password, in quel caso vengono settati ad utente tutti le info inserite nel form di r
				 * registrazione.
				 */
				if(password.equals(confermaPassword)){

					UtenteBean utente=new UtenteBean();
					utente.setNome(nome);
					utente.setCognome(cognome);
					utente.setDataNascita(dataNascita);
					utente.setCittà(citta);
					utente.setSesso(sesso);
					utente.setEmail(email);
					utente.setPassword(password);

					//l'utente viene "salvato"
					model.doSave(utente);
					//sessioni
					//sessione utente registrato
					req.getSession().setAttribute("sessioneUtente",utente);
					//sessione composizione
					ArrayList<ComposizioneBean>carrello=new ArrayList<ComposizioneBean>();
					req.getSession().setAttribute("carrelloUtente", carrello);
					//sessione prodotti
					ArrayList<ProdottoBean>prodotti=new ArrayList<ProdottoBean>();
					req.getSession().setAttribute("prodottiUtente", prodotti);
					//sessione degli ordini
					ArrayList<OrdineBean>ordini=new ArrayList<OrdineBean>();
					req.getSession().setAttribute("ordineUtente", ordini);

					RequestDispatcher view = req.getRequestDispatcher("jsp");//index.jsp
					view.forward(req, resp);
				}else {
					resp.sendRedirect("login.jsp");
				}



			}catch(SQLException e)
			{
				e.printStackTrace();
			}

		}
		//secondo controllo
		if(azione.equals("accountUtente"))
		{
			try {

				UtenteBean utente=(UtenteBean) req.getSession().getAttribute("sessioneUtente");

				String nome=req.getParameter("nome"); 
				String cognome=req.getParameter("cognome");
				String dataNascita=req.getParameter("dataNascita");
				String sesso=req.getParameter("sesso");
				String email=req.getParameter("email");
				String citta=req.getParameter("citta");
				String indirizzo=req.getParameter("indirizzo");
				String tipoCarta=req.getParameter("tipoCarta");
				String numeroCarta=req.getParameter("numeroCarta");
				String dataScadenzaCarta=req.getParameter("dataScadenzaCarta");
				String codiceCVV=req.getParameter("codiceCVV");
				String password1=req.getParameter("password1");
				String password2=req.getParameter("password2");
				String datiDiSpedizione=utente.getIndirizzo();
				utente.setDatiDiSpedizione(datiDiSpedizione);

				String datiPagamento=utente.getTipoCarta()+utente.getNumeroCarta()+utente.getDataScadenzaCarta()+utente.getNumeroCarta();
				utente.setDatiDiPagamento(datiPagamento);

				//controllo password, per aggiornamento o modifica della password
				if(password1.equals(password2))
				{

					utente.setPassword(password2);
					model.doUpdate(utente);
					req.getSession().setAttribute("sessioneUtente", utente);
					RequestDispatcher view = req.getRequestDispatcher("jsp");
					view.forward(req,resp);
				}else{
					resp.sendRedirect("Account.jsp");
				}

			}catch(SQLException e) {
				e.printStackTrace();

			}

		}
		//terzo controllo
		//Non sono sicura di questo qui dentro.
		if(azione.equals("ordiniUtente"))
		{

			try {
				UtenteBean utente=(UtenteBean) req.getSession().getAttribute("sessioneUtente");
				ArrayList<OrdineBean> ordiniUtente=new ArrayList<OrdineBean>();
				ArrayList<OrdineBean> ordiniRestituiti=oModel.doRetrieveAll("idOrdine");


				for(OrdineBean ord: ordiniRestituiti) {
					if(ord.getIdUtente().equals(utente.getEmail())) {
						ordiniUtente.add(ord);
					}

				}
				if(ordiniUtente.size()!=0) {
					req.getSession().setAttribute("sessioneUtente", utente);
					req.getSession().setAttribute("ordiniUtente", ordiniUtente);
					RequestDispatcher view = req.getRequestDispatcher("jsp");
					view.forward(req, resp);
				}else{
					resp.sendRedirect("NessunOrdine.jsp");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}