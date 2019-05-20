package model.beans;

public class ComposizioneBean {

	public ComposizioneBean() {}
	
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public String getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}
	public int getQuantit�() {
		return quantit�;
	}
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}
	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public void setPrezzoUnitario(double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	public int getScontoAttuale() {
		return scontoAttuale;
	}
	public void setScontoAttuale(int scontoAttuale) {
		this.scontoAttuale = scontoAttuale;
	}

	public double getIva() {
		return ivaAttuale;
	}

	public void setIva(double ivaAttuale) {
		this.ivaAttuale = ivaAttuale;
	}

	public String toString() {
		return "COMPOSIZIONE [IDOrdine=" + idOrdine + ", IDProdotto=" + idProdotto + ", Quantit�=" + quantit�
				+ ", PrezzoUnitario=" + prezzoUnitario + ", ScontoAttuale=" + scontoAttuale + ", IVAAttuale=" + ivaAttuale + "]";
	}

	private int idOrdine;
	private String idProdotto;
	private int quantit�;
	private double prezzoUnitario;
	private int scontoAttuale;
	private double ivaAttuale;
}
