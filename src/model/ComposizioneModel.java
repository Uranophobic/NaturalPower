package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ComposizioneBean;

public interface ComposizioneModel 
{
	public void doSave(ComposizioneBean composition)throws SQLException;
	public ArrayList<ComposizioneBean>doRetriveAll(String order)throws SQLException;
}
