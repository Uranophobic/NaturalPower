package model.modelDS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.ComposizioneModel;
import model.beans.ComposizioneBean;

public class ComposizioneModelDS implements ComposizioneModel {

	private static DataSource ds;
	private static final String TABLE_NAME = "composizione";

	@Override
	public void doSave(ComposizioneBean composition) throws SQLException {

		Connection connection=null;
		PreparedStatement preparedStatement=null;

		String insertSQL="INSERT INTO " + ComposizioneModelDS.TABLE_NAME
				+" (idOrdine, idProdotto, quantità, prezzoUnitario, scontoAttuale, ivaAttuale) "
				+"VALUES(?, ?, ?, ?, ?, ?)";

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, composition.getIdOrdine());
			preparedStatement.setString(2, composition.getIdProdotto());
			preparedStatement.setInt(3, composition.getQuantità());
			preparedStatement.setDouble(4, composition.getPrezzoUnitario());
			preparedStatement.setInt(5, composition.getScontoAttuale());
			preparedStatement.executeUpdate();


		} finally {

			try {

				if(preparedStatement!=null)
					preparedStatement.close();

			} finally {

				if(connection!=null)
					connection.close();

			}

		}

	}

	@Override
	public ArrayList<ComposizioneBean> doRetriveAll(String order) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ComposizioneBean> components = new ArrayList<ComposizioneBean>();
		String selectSQL = "SELECT * FROM " + ComposizioneModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {

			connection=ds.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				ComposizioneBean bean = new ComposizioneBean();

				bean.setIdOrdine(rs.getInt("idOrdine"));
				bean.setIdProdotto(rs.getString("idProdotto"));
				bean.setPrezzoUnitario(rs.getDouble("prezzoUnitario"));
				bean.setScontoAttuale(rs.getInt("scontoAttuale"));
				bean.setIva(rs.getDouble("ivaAttuale"));
				components.add(bean);
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

		return components;

	}

}


