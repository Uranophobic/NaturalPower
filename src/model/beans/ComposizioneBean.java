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
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
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
		return "COMPOSIZIONE [IDOrdine=" + idOrdine + ", IDProdotto=" + idProdotto + ", Quantità=" + quantità
				+ ", PrezzoUnitario=" + prezzoUnitario + ", ScontoAttuale=" + scontoAttuale + ", IVAAttuale=" + ivaAttuale + "]";
	}

	private int idOrdine;
	private String idProdotto;
	private int quantità;
	private double prezzoUnitario;
	private int scontoAttuale;
	private double ivaAttuale;
}
