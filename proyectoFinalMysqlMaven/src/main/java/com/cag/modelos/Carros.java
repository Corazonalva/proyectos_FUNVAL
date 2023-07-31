package com.cag.modelos;

public class Carros extends Automovil{
	public Carros(int id,String modelo,int marca,String tipo, 
			double precio,String color,byte[] foto,int nPuertas) {
		super(id,modelo, marca, tipo, precio, color, foto, nPuertas);
	}

	public String[] getColores()
	{
		return super.getColor().split("-");
	}

	@Override
	public String getdatos() {		
		return super.getId()+" "+super.getModelo()+" "+super.getMarca();//+" "+super.getFoto();//+" "+super.getTipo();
	}

	@Override
	public String toString() {
		return getdatos();
	}
}
