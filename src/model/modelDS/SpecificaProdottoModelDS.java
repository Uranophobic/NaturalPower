package model.modelDS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.SpecificaProdottoModel;
import model.beans.SpecificaProdottoBean;

public class SpecificaProdottoModelDS implements SpecificaProdottoModel {

	@Override
	public synchronized void doSave(SpecificaProdottoBean prod) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void doDelete(String code) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized SpecificaProdottoBean doRetrieveByKey(String key) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized ArrayList<SpecificaProdottoBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized int doUpdate(SpecificaProdottoBean prod) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
