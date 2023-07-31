package com.cag.modelos;

public abstract class Automovil {
	private int id;
	private String modelo;
	private int marca;
	private String tipo;
	private Double precio;
	private String color;
	private byte[] foto;
	private int nPuertas; 

	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}	 
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}	 
	public int getId() {
		return id;
	}
	public int getNPuertas() {
		return nPuertas;
	}


	public int getMarca() {
		return marca;
	}
	public void setMarca(int marca) {
		this.marca = marca;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	
	public Automovil(int id,String modelo, int marca, String tipo, Double precio, String color, byte[] foto,int nPuertas) {
		this.modelo = modelo;
		this.marca = marca;
		this.tipo = tipo;
		this.precio = precio;
		this.color = color;
		this.foto = foto;
		this.id=id;
		this.nPuertas=nPuertas;
	}


	public abstract String getdatos();
}
