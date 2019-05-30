package model.beans;

import java.util.GregorianCalendar;

public class ProdottoBean {

	public ProdottoBean() {}

	public String getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getDisponibilità() {
		return disponibilità;
	}

	public void setDisponibilità(int disponibilità) {    //quantità totale di quel prodotto
		this.disponibilità = disponibilità;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

	public GregorianCalendar getDataCaricamento() {
		return dataCaricamento;
	}

	public void setDataCaricamento(GregorianCalendar dataCaricamento) {
		this.dataCaricamento = dataCaricamento;
	}

	public String getImmaginePath(){
		return immaginePath;
	}

	public void setImmaginePath(String immaginePath){
		immaginePath = immaginePath;
	}

	public String toString() {
		return "PRODOTTO [IDProdotto=" + idProdotto + ", Nome=" + nome + ", Descrizione=" + descrizione
				+ ", Categoria=" + categoria + ", Prezzo=" + prezzo + ", Disponibilità="+ disponibilità
				+ ", IVA=" + iva + ", Sconto=" + sconto+ ", DataCaricamento =" + dataCaricamento
				+ ", PathImmagine=" + immaginePath + "]";
	}

	private String idProdotto, nome, descrizione, categoria, immaginePath;
	private double prezzo, iva;
	private int sconto, disponibilità;
	private GregorianCalendar dataCaricamento;


}
