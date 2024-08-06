package com.example.demo;

public class prodotti {
	int id;
	String nome;
	String descrizione;
	double prezzo;
	String img;
	int qnt;
	
	
	
	//GENERAZIONE METODI GET E SET
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getQnt() {
		return qnt;
	}
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}
	
	
	
	//GENERAZIONE TO STRING
	@Override
	public String toString() {
		return "prodotti [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", prezzo=" + prezzo
				+ ", img=" + img + ", qnt=" + qnt + "]";
	}
	
	
	

}
