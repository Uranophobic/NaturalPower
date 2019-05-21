package model.modelDS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.GregorianCalendar;
import model.ProdottoModel;
import model.beans.ProdottoBean;

public class ProdottoModelDS implements ProdottoModel {

	private static DataSource ds;
	private static final String TABLE_NAME = "prodotto";
	
	static {
		
		try {

			// ci vanno cose

		} catch (Exception e){
			
			//ci vanno altre cose
			
		}
	}

	@Override
	public synchronized void doSave(ProdottoBean prod) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + ProdottoModelDS.TABLE_NAME
				+ " (IDProdotto, Nome, Descrizione, Categoria, Prezzo, Sconto, Iva, ImmaginePath) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, prod.getIdProdotto());
			preparedStatement.setString(2, prod.getNome());
			preparedStatement.setString(3, prod.getDescrizione());
			preparedStatement.setString(4, prod.getCategoria());
			preparedStatement.setDouble(5, prod.getPrezzo());
			preparedStatement.setInt(6, prod.getSconto());
			preparedStatement.setDouble(7, prod.getIva());
			preparedStatement.setString(8, prod.getImmaginePath());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
			
		} finally {
			
			try {
				
				if(preparedStatement != null)
					preparedStatement.close();
				
			} finally {
				
				if (connection != null)
					connection.close();
				
			}
			
		}
		
	}

	@Override
	public synchronized void doDelete(String code) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM " + ProdottoModelDS.TABLE_NAME + " WHERE IDProdotto = ?";
		
		try {
			
			try {

				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);

				preparedStatement.setString(1, code);

				preparedStatement.executeUpdate();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}

		} finally {
			
			try {
				
				if(preparedStatement != null)
					preparedStatement.close();
				
			} finally {
				
				if (connection != null)
					connection.close();
				
			}
		}
		
	}

	@Override
	public synchronized ProdottoBean doRetrieveByKey(String key) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProdottoBean bean = new ProdottoBean();

		String selectSQL = "SELECT * FROM " + ProdottoModelDS.TABLE_NAME + " WHERE IDProdotto = ?";

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, key);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				bean.setIdProdotto(rs.getString("IDProdotto"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setCategoria(rs.getString("Categoria"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setSconto(rs.getInt("Sconto"));
				bean.setIva(rs.getDouble("Iva"));
				bean.setImmaginePath(rs.getString("ImmaginePath"));
				
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

	@Override
	public synchronized ArrayList<ProdottoBean> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProdottoBean> products = new ArrayList<ProdottoBean>();
		
		String selectSQL = "SELECT * FROM " + ProdottoModelDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				ProdottoBean bean = new ProdottoBean();
				
				bean.setIdProdotto(rs.getString("IDProdotto"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setCategoria(rs.getString("Categoria"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setSconto(rs.getInt("Sconto"));
				bean.setIva(rs.getDouble("Iva"));
				bean.setImmaginePath(rs.getString("ImmaginePath"));
				
				products.add(bean);
				
			}
			
		} finally {
			
			try {
				
				if (preparedStatement != null)
					preparedStatement.close();
				
			} finally {
				
				if(connection != null)
					connection.close();
				
			}
			
		}
		
		return products;
		
	}

	@Override
	public synchronized int doUpdate(ProdottoBean prod) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String selectSQL = "UPDATE " + ProdottoModelDS.TABLE_NAME + " SET Nome = ? , Descrizione = ? ," +
				"Categoria = ? , Prezzo = ? , Sconto = ? ," +
				"Iva = ? , ImmaginePath = ? "+ 
				"WHERE IDProdotto = ?";
		
		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, prod.getNome());
			preparedStatement.setString(2, prod.getDescrizione());
			preparedStatement.setString(3, prod.getCategoria());
			preparedStatement.setDouble(4, prod.getPrezzo());
			preparedStatement.setInt(5, prod.getSconto());
			preparedStatement.setDouble(6, prod.getIva());
			preparedStatement.setString(7, prod.getImmaginePath());
			
			preparedStatement.setString(8, prod.getIdProdotto());

			result = preparedStatement.executeUpdate();

			//connection.commit();

		} finally {
			
			try {
				
				if (preparedStatement != null)
					preparedStatement.close();
				
			} finally {
				
				if (connection != null)
					connection.close();
			}
			
		}
		
		return result; // ritorna uno se è andato tutto ok altrimenti 0 
		
	}

}
