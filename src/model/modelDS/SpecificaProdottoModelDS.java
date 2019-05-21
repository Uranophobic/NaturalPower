package model.modelDS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.SpecificaProdottoModel;
import model.beans.ProdottoBean;
import model.beans.SpecificaProdottoBean;

public class SpecificaProdottoModelDS implements SpecificaProdottoModel {
	
	private static DataSource ds;
	private static final String TABLE_NAME = "specificaprodotto";
	
	static {
		try {

			// ci vanno cose

		} catch (Exception e){
			//ci vanno altre cose
		}
	}

	
	@Override
	public synchronized void doSave(SpecificaProdottoBean prod) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + SpecificaProdottoModelDS.TABLE_NAME
				+ " (IDProdotto, Tipo, ColoreFiore, TipoStelo, GrandezzaPianta) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, prod.getIdProdotto());
			preparedStatement.setString(2, prod.getTipo());
			preparedStatement.setString(3, prod.getColoreFiore());
			preparedStatement.setString(4, prod.getTipoStelo());
			preparedStatement.setString(8, prod.getGrandezzaPianta());
			
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
		
		String deleteSQL = "DELETE FROM " + SpecificaProdottoModelDS.TABLE_NAME + " WHERE IDProdotto = ?";
		
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
	public synchronized SpecificaProdottoBean doRetrieveByKey(String key) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		SpecificaProdottoBean bean = new SpecificaProdottoBean();

		String selectSQL = "SELECT * FROM " + SpecificaProdottoModelDS.TABLE_NAME + " WHERE IDProdotto = ?";

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, key);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				bean.setIdProdotto(rs.getString("IDProdotto"));
				bean.setTipo(rs.getString("Tipo"));
				bean.setColoreFiore(rs.getString("ColoreFiore"));
				bean.setTipoStelo(rs.getString("TipoStelo"));
				bean.setGrandezzaPianta(rs.getString("GrandezzaPianta"));
				
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
	public synchronized ArrayList<SpecificaProdottoBean> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<SpecificaProdottoBean> products = new ArrayList<SpecificaProdottoBean>();
		
		String selectSQL = "SELECT * FROM " + SpecificaProdottoModelDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				SpecificaProdottoBean bean = new SpecificaProdottoBean();
				
				bean.setIdProdotto(rs.getString("IDProdotto"));
				bean.setTipo(rs.getString("Tipo"));
				bean.setColoreFiore(rs.getString("ColoreFiore"));
				bean.setTipoStelo(rs.getString("TipoStelo"));
				bean.setGrandezzaPianta(rs.getString("GrandezzaPianta"));
				
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
	public synchronized int doUpdate(SpecificaProdottoBean prod) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String selectSQL = "UPDATE " + SpecificaProdottoModelDS.TABLE_NAME + " SET Tipo = ? ," +
				"ColoreFiore = ? , TipoStelo = ? , GrandezzaPianta = ? ," +
				"WHERE IDProdotto = ?";
		
		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, prod.getTipo());
			preparedStatement.setString(2, prod.getColoreFiore());
			preparedStatement.setString(3, prod.getGrandezzaPianta());
			
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
