package model.modelDS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.ComponiloTuModel;
import model.beans.ComposizioneBean;

public class ComponiloTuModelDS implements ComponiloTuModel {

	@Override
	public synchronized void doSave(ComposizioneBean composition) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized ArrayList<ComposizioneBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
