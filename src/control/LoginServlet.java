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

import model.UtenteModel;
import model.beans.ComposizioneBean;
import model.beans.OrdineBean;
import model.beans.ProdottoBean;
import model.beans.UtenteBean;
import model.modelDS.UtenteModelDS;
/*
 * Questa è la servlet del login, che effettua i controlli di un utente registrato che vuole effettuare l'accesso al sito. 
 * I controlli che vengono effettuati sono: accesso al sito che prevede l'inserimento corretto di email e password e quindi 
 * l'effettiva corrispondenza di quest'ultime all'interno del db. Nel caso di accesso effettuato vengono instaziate le sessioni, 
 * per tutte le "attività" che l'utente loggato può svolgere all'interno del sito e della sua area utente. Inoltre vengono effettuati
 * i controlli che negano l'accesso all'utente quindi nel caso di inserimento errata di mail e/o password. Infine viene effettuato il controllo
 * per eseguire il "logout".
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID=11L;

	static UtenteModel model=(UtenteModel) new UtenteModelDS();
	UtenteBean utente =new UtenteBean();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//questa stringa azione login, serve per effettuare i controlli sulle varie azioni dell'utente
		String azioneLogin=req.getParameter("azioneLogin");
		System.out.println(azioneLogin);

		//controllo prodotti nel carrello, inizialmente vuoto e si setta la sessione inziale.
		int quantitaCarrello=0;
		req.getSession().setAttribute("quantitaCarrello", quantitaCarrello);

		/*
		 * L'utente dopo aver inserito email e password preme su accedi, da qui partono i vari controlli sopra descritti.
		 */
		if(azioneLogin.equals("accedi")) {
			try {
				//vengono presi i riferimenti alla mail e la password inseriti dall'utente.
				String email=req.getParameter("email");
				System.out.println(email);
				String password=req.getParameter("password");
				System.out.println(password);
				// viene effuttuata la ricerca dell'utente in base alla mail inserita
				//	utente=model.doRetrieveByKey(email);

				ArrayList<UtenteBean> utentiAll=model.doRetrieveAll(email);
				for(int i=0; i<utentiAll.size();i++) {
					if(utentiAll.get(i).getEmail().equals(email) && utentiAll.get(i).getPassword().equals(password)) {

						resp.setContentType("text/html;charset=utf-8");
						resp.getWriter().write("le credenziali inserite risultano corrette, accesso effettuato");

						System.out.println("Login effettuato correttamente");
						/*
						 * email e password inserite correttamente, l'utente viene  loggato pertanto vengono istanziate tutte le sessioni
						 */
						req.getSession().setAttribute("UtenteLoggato", utente);

						//sessione di tutte le attività che può svolgere
						// sessione composizione bean che si riferisce al carello
						ArrayList<ComposizioneBean> carrello=new ArrayList<ComposizioneBean>();
						req.getSession().setAttribute("carrello", carrello);

						//sessione dei prodotti acquistabili
						ArrayList<ProdottoBean> prodotti=new ArrayList<ProdottoBean>();
						req.getSession().setAttribute("prodotti", prodotti);

						//sessione degli ordini effettuati, e sono visualizzati all'interno del menu dentro la voce "i miei ordini"
						ArrayList<OrdineBean> ordini=new ArrayList<OrdineBean>();
						req.getSession().setAttribute("i miei ordini", ordini);
						RequestDispatcher view = req.getRequestDispatcher("Loginjsp.jsp");
						view.forward(req, resp);

					}else if(utentiAll.get(i).getEmail().equals(email)&&(!utentiAll.get(i).getPassword().equals(password)||(utentiAll.get(i).getPassword().equals(null)))) {

						//email corretta, password errata o nulla
						resp.setContentType("text/html;charset=utf-8");
						resp.getWriter().write("passwordErrata");

						System.out.println("Password Errata, accesso negato");

					}else if((!utentiAll.get(i).getEmail().equals(email)||(utentiAll.get(i).getEmail().equals(null))&&(utentiAll.get(i).getPassword().equals(password)))) {

						//email errata o nulla, password corretta= accesso negato

						resp.setContentType("text/html;charset=utf-8");
						resp.getWriter().write("emailErrata");

						System.out.println("Email  Errata, accesso negato");
					}else if(!(utentiAll.get(i).getEmail().equals(email)&&(utentiAll.get(i).getPassword().equals(password)))) {

						//email e password errate, accesso negato.
						resp.setContentType("text/html;charset=utf-8");
						resp.getWriter().write("emailErrata e password errata");

						System.out.println(" Email Errata e Password Errata, accesso negato");
					}else if(utentiAll.get(i).getEmail().equals(null)&&(utentiAll.get(i).getPassword().equals(null))) {
						//email e password non inserite

						resp.setContentType("text/html;charset=utf-8");
						resp.getWriter().write("le credenziali di accesso non sono state inseriti");

						System.out.println("Le credenziali di accesso non sono state inserite, accesso negato");
					}
				}




			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		//logout
		if(azioneLogin.equals("esci"))
		{
			//si "chiude" la sessione e l'utene viene reindirizzato alla pagina iniziale
			req.getSession().invalidate();
			resp.sendRedirect("index.jsp");
		}


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}


}