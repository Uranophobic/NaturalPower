package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ComposizioneBean;



public interface ComponiloTuModel {
	
	public void doSave(ComposizioneBean composition) throws SQLException;
	
	public ArrayList<ComposizioneBean> doRetrieveAll(String order) throws SQLException;

}
