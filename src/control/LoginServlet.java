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
				//vengono presi i riferimenti alla mail e la password insieriti dall'utente.
				String email=req.getParameter("email");
				System.out.println(email);
				String password=req.getParameter("password");
				System.out.println(password);
				// viene effuttuata la ricerca dell'utente in base alla mail inserita
				utente=model.doRetrieveByKey(email);

				//effettuo controlli
				if(!utente.getEmail().equals(null)) {
					System.out.println("email corretta");
					//controllo password
					if(utente.getPassword().equals(password)) {
						System.out.println(utente.getPassword());

						resp.setContentType("text/html;charset=utf-8");
						resp.getWriter().write("successo");

						//in questo caso vengono settate le sessioni

						//sessione dell'utente loggato
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
					}else {
						//password inserita errate
						resp.setContentType("text/html;charset=utf-8");
						resp.getWriter().write("passwordErrata");

						System.out.println("Password Errata");
					}

				}else {
					//email e password errate
					resp.setContentType("text/html;charset=utf-8");
					resp.getWriter().write("no");

					System.out.println("Email NO");
					//response.sendRedirect("index.jsp");

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
			//resp.sendRedirect(jsp)

		}


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}


}