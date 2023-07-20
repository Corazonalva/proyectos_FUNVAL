package proyectoFinal;

public abstract class Automovil {
	private int id;
	private String modelo;
	private String marca;
	private String tipo;
	private Double precio;
	private String color;
	private String foto;
	private int nPuertas;
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
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
	public String getFoto() {
		return foto;
	}
	public void setFotos(String foto) {
		this.foto = foto;
	}
	
	public int getId() {
		return id;
	}
	
	public int getNPuertas() {
		return nPuertas;
	}
	
	
	public Automovil(int id,String modelo, String marca, String tipo, Double precio, String color, String foto,int nPuertas) {
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
	
//	@Override
//	public String toString() {
//		return "id="+id+" modelo=" + modelo + ", marca=" + marca + ", tipo=" + tipo + ", precio=" + precio + ", color="
//				+ color + ", foto=" + foto;
//	}
	

}
