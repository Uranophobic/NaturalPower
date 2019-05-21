package model.modelDS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.ComponiloTuModel;
import model.beans.ComponiloTuBean;
import model.beans.ComposizioneBean;
import model.beans.ProdottoBean;

public class ComponiloTuModelDS implements ComponiloTuModel {

	private static DataSource ds;
	private static final String TABLE_NAME="componilotu";

	static {
		
		try {
			
			//cose
			
		} catch (Exception e) {
			
			//altre cose
			
		}
	}
	
	@Override 
	public synchronized void doSave(ComponiloTuBean composition) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="INSERT INTO " + ComponiloTuModelDS.TABLE_NAME +
				" (IDComposto, IDComponente, Quantità, PrezzoUnitario) "
				+ "VALUES(?, ?, ?, ?)";

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, composition.getIdComposto());
			preparedStatement.setString(2, composition.getIdComponente());
			preparedStatement.setInt(3, composition.getQuantità());
			preparedStatement.setDouble(4,composition.getPrezzoUnitario());

			preparedStatement.executeUpdate();
			
			connection.commit();

		} finally {
			
			try {
				
				if(preparedStatement!=null)
					preparedStatement.close();
				
			}  finally {
				
				if(connection!=null)
					connection.close();

			}
			
		}
		
	}

	@Override
	public void doDelete(String code) throws SQLException
	{
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String deleteSQL="DELETE FROM"+ComponiloTuModelDS.TABLE_NAME+"WHERE IDComposto=?";

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
				
				if(preparedStatement!=null)
					preparedStatement.close();
			
			} finally {
				
				if (connection != null)
					connection.close();
				
			}
		}

	}

	@Override
	public ComponiloTuBean doRetrieveByKey(String key) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ComponiloTuBean componiloTu = new ComponiloTuBean();

		String selectSQL="SELECT * FROM " + ComponiloTuModelDS.TABLE_NAME + "WHERE IDComposto = ?";

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, key);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {

				componiloTu.setIdComposto(rs.getString("IDComposto"));
				componiloTu.setIdComponente(rs.getString("IDComponente"));
				componiloTu.setQuantità(rs.getInt("Quantità"));
				componiloTu.setPrezzoUnitario(rs.getDouble("PrezzoUnitario"));

			}

		} finally {

			try {

				if(preparedStatement!=null)
					preparedStatement.close();

			} finally {

				if(connection!=null)
					connection.close();

			}
			
		}
		
		return componiloTu;

	}

	@Override
	public ArrayList<ComponiloTuBean> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ComponiloTuBean> componiloTu = new ArrayList<ComponiloTuBean>();

		String selectSQL = "SELECT * FROM " + ComponiloTuModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				ComponiloTuBean componilo = new ComponiloTuBean();

				componilo.setIdComposto(rs.getString("IDComposto"));
				componilo.setIdComponente(rs.getString("IDComponente"));
				componilo.setQuantità(rs.getInt("Quantità"));
				componilo.setPrezzoUnitario(rs.getDouble("PrezzoUnitario"));

				componiloTu.add(componilo);

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

		return componiloTu;

	}
}

