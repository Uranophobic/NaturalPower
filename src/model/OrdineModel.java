package model;

import java.sql.SQLException;

import model.beans.OrdineBean;
import java.util.*;

public interface OrdineModel
{
	
	public void doSave(OrdineBean order) throws SQLException;
	
	public OrdineBean doRetriveByKey(int code)throws SQLException;
	
	public ArrayList<OrdineBean> doRetriveAll(String order)throws SQLException;
	
	public int generaCodice()throws SQLException;
	
	
}