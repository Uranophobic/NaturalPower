package model.modelDS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.beans.UtenteBean;
import model.UtenteModel;

public class UtenteModelDS {

	private static DataSource ds;

	static {
		try {

			// ci vanno cose
		} catch (Exception e) {

			//ci vanno altre cose
		}
	}

	private static final String TABLE_NAME = "utente";

	public synchronized void doSave(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO" + UtenteModelDS.TABLE_NAME 
				+ "( Email, Nome, Cognome, Password, DataNascita, Sesso, Cap, CF, Indirizzo, Città, DataScadenzaCarta, NumeroCarta)" 
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			connection = ds.getConnection();
			preparedStatement.setString(1, utente.getEmail());
			preparedStatement.setString(2, utente.getNome());
			preparedStatement.setString(3, utente.getCognome());
			preparedStatement.setString(4, utente.getPassword());
			preparedStatement.setString(5, utente.getDataNascita());
			preparedStatement.setString(6, utente.getSesso());
			preparedStatement.setString(7, utente.getCAP());
			preparedStatement.setString(8, utente.getCF());
			preparedStatement.setString(9, utente.getIndirizzo());
			preparedStatement.setString(10, utente.getCittà());
			preparedStatement.setString(11, utente.getDataScadenzaCarta());
			preparedStatement.setString(12, utente.getNumeroCarta());

			preparedStatement.executeUpdate();

		} finally { 

			try {
				if(preparedStatement !=null) {
					preparedStatement.close();
				}
			} finally {
				if(connection != null) {
					connection.close();
				}
			}
		}
	}

	public synchronized UtenteBean doRetrieveByKey (String email) throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteBean bean = new UtenteBean();

		String selectSQL= "SELECT * FORM" + UtenteModelDS.TABLE_NAME + "WHERE Email = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {

				bean.setEmail(rs.getString("Email"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setPassword(rs.getString("Password"));
				bean.setDataNascita(rs.getString("DataNascita"));
				bean.setSesso(rs.getString("Sesso"));
				bean.setCAP(rs.getString("CAP"));
				bean.setCF(rs.getString("CF"));
				bean.setIndirizzo(rs.getString("Indirizzo"));
				bean.setCittà(rs.getString("Città"));
				bean.setDataScadenzaCarta(rs.getString("DataScadenzaCarta"));
				bean.setNumeroCarta(rs.getString("NumeroCarta"));
			}	

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}

		return bean;
	}

	public synchronized ArrayList<UtenteBean> doRetrieveAll(String ordine) throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<UtenteBean> utenti = new ArrayList<UtenteBean>();

		String selectSQL = "SELCT * FORM" + UtenteModelDS.TABLE_NAME;

		if(ordine != null &&  !ordine.equals("")) {
			selectSQL += " ORDER BY " + ordine;
		}

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {

				UtenteBean bean = new UtenteBean();

				bean.setEmail(rs.getString("Email"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setPassword(rs.getString("Password"));
				bean.setDataNascita(rs.getString("DataNascita"));
				bean.setSesso(rs.getString("Sesso"));
				bean.setCAP(rs.getString("CAP"));
				bean.setCF(rs.getString("CF"));
				bean.setIndirizzo(rs.getString("Indirizzo"));
				bean.setCittà(rs.getString("Città"));
				bean.setDataScadenzaCarta(rs.getString("DataScadenzaCarta"));
				bean.setNumeroCarta(rs.getString("NumeroCarta"));

				utenti.add(bean);
			}

		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}

		}

		return utenti;

	}

	public synchronized int doUpdate(UtenteBean utente) throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		String selectSQL = "UPDATE" + UtenteModelDS.TABLE_NAME + "SET Nome = ?, Cognome = ?," 
				+ "Password = ?, DataNascita= ?, Sesso = ?, CAP = ?, CF = ?," 
				+ "Indirizzo = ?, Città = ?, DataScadenzaCarta = ?, NumeroCarta = ?" 
				+ "WHERE Email = ? ";

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, utente.getNome());
			preparedStatement.setString(2, utente.getCognome());
			preparedStatement.setString(3, utente.getPassword());
			preparedStatement.setString(4, utente.getDataNascita());
			preparedStatement.setString(5, utente.getSesso());
			preparedStatement.setString(6, utente.getCAP());
			preparedStatement.setString(7, utente.getCF());
			preparedStatement.setString(8, utente.getIndirizzo());
			preparedStatement.setString(9, utente.getCittà());
			preparedStatement.setString(10, utente.getDataScadenzaCarta());
			preparedStatement.setString(11, utente.getNumeroCarta());
			preparedStatement.setString(12, utente.getEmail());

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}

		}

		return result; 
	}
}
