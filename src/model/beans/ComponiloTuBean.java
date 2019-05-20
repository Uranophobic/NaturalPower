package model.beans;

public class ComponiloTuBean {
	
	public String getIdComposto() {
		return idComposto;
	}
	
	public void setIdComposto(String idComposto) {
		this.idComposto = idComposto;
	}
	
	public String getIdComponente() {
		return idComponente;
	}
	
	public void setIdComponente(String idComponente) {
		this.idComponente = idComponente;
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
	
	public String toString() {
		return "COMPONILOTU [IDComposto=" + idComposto + ", IDComponente=" + idComponente + ", Quantità=" + quantità
				+ ", PrezzoUnitario=" + prezzoUnitario + "]";
	}

	private String idComposto, idComponente;
	private int quantità;
	private double prezzoUnitario;
}
