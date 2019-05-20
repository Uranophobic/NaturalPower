package model.beans;

public class OrdineBean {

	public OrdineBean() {}
	
	public int getIdOrdine() {
		return idOrdine;
	}
	
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	
	public String getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	
	public double getPrezzoOrdineTotale() {
		return prezzoOrdineTotale;
	}
	
	public void setPrezzoOrdineTotale(double prezzoOrdineTotale) {
		this.prezzoOrdineTotale = prezzoOrdineTotale;
	}
	
	public String getDataOrdine() {
		return dataOrdine;
	}
	
	public void setDataOrdine(String dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	
	public String toString() {
		return "ORDINE [IDOrdine=" + idOrdine + ", IDUtente=" + idUtente + ", PrezzoOrdineTotale="
				+ prezzoOrdineTotale + ", DataOrdine=" + dataOrdine + "]";
	}

	private int idOrdine;
	private String idUtente;
	private double prezzoOrdineTotale;
	private String dataOrdine;
}
