package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ComponiloTuBean;
import model.beans.ComposizioneBean;
import model.beans.ProdottoBean;



public interface ComponiloTuModel {
	
	
	
	public ArrayList<ComponiloTuBean> doRetrieveAll(String order) throws SQLException;
	
	public void doDelete(String code) throws SQLException;
	
	public ComponiloTuBean doRetrieveByKey(String key) throws SQLException;

	void doSave(ComponiloTuBean composition) throws SQLException;

}
