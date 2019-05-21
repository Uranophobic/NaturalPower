package model;

import java.sql.SQLException;

import model.beans.OrdineBean;
import java.util.*;

public interface OrdineModel
{
	
	public void doSave(OrdineBean order) throws SQLException;
	
	public OrdineBean doRetrieveByKey(int code)throws SQLException;
	
	public ArrayList<OrdineBean> doRetrieveAll(String order)throws SQLException;
	
	public int generaCodice()throws SQLException;
	
	
}