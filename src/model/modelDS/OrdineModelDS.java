package model.modelDS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

import javax.sql.DataSource;



import model.OrdineModel;
import model.beans.OrdineBean;

public class OrdineModelDS implements OrdineModel
{
	private static DataSource ds; 
	private static final String TABLE_NAME="ordine";

	@Override
	public void doSave(OrdineBean order) throws SQLException {

		Connection connection=null; 
		PreparedStatement preparedStatement=null; 

		String insertSQL="INSERT INTO"+ OrdineModelDS.TABLE_NAME
				+ "( idOrdine, idUtente, prezzoOrdineTotale, dataOrdine )"
				+"VALUES(?,?,?,?)";
		try {

			connection =  ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, order.getIdOrdine());

			preparedStatement.setString(2, order.getIdUtente());

			preparedStatement.setDouble(3, order.getPrezzoOrdineTotale()); 

			preparedStatement.setString(4, order.getDataOrdine());


			preparedStatement.executeUpdate();

		}finally {

			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			}finally {
				if(connection!=null)
					connection.close();
			}
		}		
	}

	@Override
	public  synchronized OrdineBean doRetriveByKey(int code) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		OrdineBean bean=new OrdineBean();

		String selectSQL="SELECT * FROM "+OrdineModelDS.TABLE_NAME+" WHERE idOrdine = ? ";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {

				bean.setIdOrdine(rs.getInt("idOrdine"));
				bean.setIdUtente(rs.getString("idUtente"));
				bean.setPrezzoOrdineTotale(rs.getDouble("dataOrdine"));
			}

		}finally {

			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			}finally {
				if(connection!=null)
					connection.close();
			}
		}
		return bean;

	}

	@Override
	public  ArrayList<OrdineBean> doRetriveAll(String order) throws SQLException {

		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ArrayList<OrdineBean>orders=new ArrayList<OrdineBean>();

		String selectSQL="SELECT * FROM"+OrdineModelDS.TABLE_NAME;

		if(order!=null&&!order.equals("")) {
			selectSQL+="ORDER BY"+order;
		}

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				OrdineBean bean = new OrdineBean();

				bean.setIdOrdine(rs.getInt("idOrdine"));
				bean.setIdUtente(rs.getString("idUtente"));
				bean.setPrezzoOrdineTotale(rs.getDouble("prezzoOrdineTotale"));
				bean.setDataOrdine(rs.getString("dataOrdine"));

				orders.add(bean);
			}

		}finally {

			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			}finally {
				if(connection!=null)
					connection.close();
			}

		}
		return orders;
	}

	@Override
	public int generaCodice() throws SQLException {

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String sql="SELECT COUNT(*) AS TOTAL FROM"+OrdineModelDS.TABLE_NAME;

		int rowCount=0;
		try {
			connection=ds.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next()) {
				rowCount=rs.getInt("total");
			}	
		}finally {

			if(connection!=null)
				connection.close();

		}
		return rowCount+1;
	}


}
