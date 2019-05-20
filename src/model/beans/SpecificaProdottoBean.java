package model.beans;

public class SpecificaProdottoBean {

	public SpecificaProdottoBean() {}
	
	public String getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getTipoFiore() {
		return tipoFiore;
	}
	
	public void setTipoFiore(String tipoFiore) {
		this.tipoFiore = tipoFiore;
	}
	
	public String getTipoPianta() {
		return tipoPianta;
	}
	
	public void setTipoPianta(String tipoPianta) {
		this.tipoPianta = tipoPianta;
	}
	
	public String getTipoAccessorio() {
		return tipoAccessorio;
	}
	
	public void setTipoAccessorio(String tipoAccessorio) {
		this.tipoAccessorio = tipoAccessorio;
	}
	
	public String getColoreFiore() {
		return coloreFiore;
	}
	
	public void setColoreFiore(String coloreFiore) {
		this.coloreFiore = coloreFiore;
	}
	
	public String getTipoStelo() {
		return tipoStelo;
	}
	
	public void setTipoStelo(String tipoStelo) {
		this.tipoStelo = tipoStelo;
	}
	
	public String getGrandezzaPianta() {
		return grandezzaPianta;
	}
	
	public void setGrandezzaPianta(String grandezzaPianta) {
		this.grandezzaPianta = grandezzaPianta;
	}

	public String toString() {
		return "SPECIFICAPRODOTTO [IDProdotto=" + idProdotto + ", TipoDiFiore=" + tipoFiore + ", TipoDellaPianta="
				+ tipoPianta + ", TipoDiAccessorio=" + tipoAccessorio + ", ColoreDelFiore=" + coloreFiore + ", TipoDelloStelo="
				+ tipoStelo + ", GrandezzaDellaPianta=" + grandezzaPianta + "]";
	}

	private String idProdotto;
	private String tipoFiore, tipoPianta, tipoAccessorio;
	private String coloreFiore, tipoStelo, grandezzaPianta;
}
