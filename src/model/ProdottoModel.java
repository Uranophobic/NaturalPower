package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ProdottoBean;

public interface ProdottoModel {

	public void doSave(ProdottoBean prod) throws SQLException;
	
	public void doDelete(String code) throws SQLException;
	
	public ProdottoBean doRetrieveByKey(String key) throws SQLException;

	public ArrayList<ProdottoBean> doRetrieveByCategoria(String categ) throws SQLException;
	
	public ArrayList<ProdottoBean> doRetrieveAll(String order) throws SQLException;

	public int doUpdate(ProdottoBean prod) throws SQLException;
	
}
