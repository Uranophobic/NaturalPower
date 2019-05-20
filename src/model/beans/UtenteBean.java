package model.beans;

public class UtenteBean {

	public UtenteBean() {}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getSesso() {
		return sesso;
	}
	
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	public String getCF() {
		return CF;
	}
	
	public void setCF(String CF) {
		this.CF = CF;
	}
	
	public String getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getCitt�() {
		return citt�;
	}
	
	public void setCitt�(String citt�) {
		this.citt� = citt�;
	}	

	public String getCAP() {
		return CAP;
	}

	public void setCAP(String cAP) {
		CAP = cAP;
	}
	
	public String getNumeroCarta() {
		return numeroCarta;
	}
	
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	
	public String getDataScadenzaCarta() {
		return dataScadenzaCarta;
	}
	
	public void setDataScadenzaCarta(String dataScadenzaCarta) {
		this.dataScadenzaCarta = dataScadenzaCarta;
	}

	public String toString() {
		return "UTENTE [E-mail=" + email + ", Password=" + password + ", Nome=" + nome + ", Cognome=" + cognome
				+ ", Sesso=" + sesso + ", CodiceFiscale=" + CF + ", DataDiNascita=" + dataNascita + ", Indirizzo=" + indirizzo
				+ ", Citt�=" + citt� + ", NumeroDellaCarta=" + numeroCarta + ", DataDiScadenzaDellaCarta=" + dataScadenzaCarta
				+ "]";
	}

	private String email, password;
	private String nome, cognome, sesso, CF, dataNascita;
	private String indirizzo, citt�, CAP;
	private String numeroCarta, dataScadenzaCarta;
}
