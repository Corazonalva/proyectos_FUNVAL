package miniProyecto1;

public class Empleado {
	private int id;
	private String nombre;
	private byte edad;
	private Sueldo sueldo;
	private boolean activo;
	
	public String getNombre() {
		return nombre;
	}
	public byte getEdad() {
		return edad;
	}

	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
		this.sueldo.aumentarPorcentaje(-100);
	}
		
	public Sueldo getSueldo() {
		return sueldo;
	}
	
	public Empleado(int id, String nombre, byte edad,float sueldoBase) { 
		this.id = id; // falta agregar ceros a la izquierda
		this.nombre = nombre;
		this.edad = edad;
		this.activo=true;
		
		sueldo=new Sueldo(id, sueldoBase);
	}
	
	
	@Override
	public String toString() {
		return "ID= " + id + "\nNOMBRE= " + nombre + "\nEDAD= " + edad + sueldo.toString();
	}
	
}
