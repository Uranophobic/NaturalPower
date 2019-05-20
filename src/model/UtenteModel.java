package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.UtenteBean;

public interface UtenteModel {
	
	public void doSave(UtenteBean user) throws SQLException;
	
	public UtenteBean doRetrieveByKey (String email) throws SQLException;
	
	public ArrayList<UtenteBean> doRetrieveAll(String order) throws SQLException;
	
	public int doUpdate(UtenteBean user) throws SQLException;
}
