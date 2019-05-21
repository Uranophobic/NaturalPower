package model.beans;

public class SpecificaProdottoBean {

	public SpecificaProdottoBean() {}
	
	public String getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		return "SPECIFICAPRODOTTO [IDProdotto=" + idProdotto + ", Tipo=" + tipo + ", ColoreDelFiore=" + coloreFiore + ", TipoDelloStelo="
				+ tipoStelo + ", GrandezzaDellaPianta=" + grandezzaPianta + "]";
	}

	private String idProdotto;
	private String tipo;
	private String coloreFiore, tipoStelo, grandezzaPianta;
}
