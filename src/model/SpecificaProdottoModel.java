package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.SpecificaProdottoBean;

public interface SpecificaProdottoModel {
	
	public void doSave(SpecificaProdottoBean prod) throws SQLException;
	
	public void doDelete(String code) throws SQLException;
	
	public SpecificaProdottoBean doRetrieveByKey(String key) throws SQLException;
	
	public ArrayList<SpecificaProdottoBean> doRetrieveAll(String order) throws SQLException;

	public int doUpdate(SpecificaProdottoBean prod) throws SQLException;

}
