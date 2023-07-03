package miniProyecto1;

public class Sueldo {
	private int idEmpleado;
	private float sueldoBase;
	private float aumento;
	private float sueldoActual;
 	
	public Sueldo(int idEmpleado, float sueldoBase) { 
		this.idEmpleado = idEmpleado;
		this.sueldoBase = sueldoBase;
		this.aumento = 0.00f;
		this.sueldoActual = sueldoBase;
 	}

	@Override
	public String toString() {
		return "\nSUELDO ANTERIOR=$" + sueldoBase + "\nAUMENTO= " + aumento + "%" + "\nSUELDO ACTUAL=$" + sueldoActual;
	}
	
	public void aumentarPorcentaje(float aumento)
	{
		this.aumento=aumento;
		this.sueldoBase=this.sueldoActual;
		this.sueldoActual=sueldoActual+(sueldoActual*(aumento/100.00f)); 
		if(this.aumento<0) {this.aumento=0.00f;}
	}
	
	
}
