package miniProyecto1;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal_2 { 
	private static String nombre;
	private static byte edad;
	private static float sueldoBase;
	private static float aumento;  
	private static Scanner keyboard;

	private static int consecutivoEmpleados;
	private static int consecutivoExempleados;	
	private static ArrayList<Empleado> listaEmpleados;
	private static ArrayList<Empleado> listaExempleados;
	
	public static void main(String[] args) {
		listaEmpleados= new ArrayList<Empleado>();
		listaExempleados= new ArrayList<Empleado>();
		consecutivoEmpleados=0;
		consecutivoExempleados=0;
		
		byte opcion=0;
		byte closeOpcion=6;
		keyboard= new Scanner(System.in);

		do {
			System.out.println("|-----------------------------------------|");
			System.out.println("|       Ingresa el número de opción    v2 |");
			System.out.println("|-----------------------------------------|");
			System.out.println("|1.- CONTRATAR EMPLEADO                   |");
			System.out.println("|2.- DESPEDIR EMPLEADO                    |");
			System.out.println("|3.- AUMENTAR SUELDO                      |");
			System.out.println(String.format("|4.- LISTA DE EMPLEADOS        [%d] Reg.   |",consecutivoEmpleados-consecutivoExempleados));
			System.out.println(String.format("|5.- LISTA DE EXEMPLEADOS      [%d] Reg.   |",consecutivoExempleados));
			System.out.println("|-----------------------------------------|");
			System.out.println("|6.- SALIR...                             |");
			System.out.println("|-----------------------------------------|");
			if(keyboard.hasNextByte()){opcion=keyboard.nextByte();keyboard.nextLine();}
			else {opcion=(byte) (closeOpcion+1);}

			switch (opcion) { 
			case 1:
				contratarEmpleado();
				break;

			case 2: 
				bajaEmpleado();
				break;

			case 3 : 	
				aumetarSueldo();
				break;

			case 4 : 
				imprimeEmpleados();
				break;

			case 5 :  
				imprimeExempleados();
				break;

			case 6 : 
				System.out.println("\t<<< Fin del programa >>>");	
				break;

			default:  
				imprimeMSInfo("Opción no valida"); 
				break;
			} 
		} while(opcion!=closeOpcion);
		keyboard.close();
	}



	private static void contratarEmpleado()
	{		
		byte closeOpcion=1; 
		boolean bandera;
		do {
			System.out.println("|-----------------------------------------|");
			System.out.println(String.format("| [ CONTRATAR EMPLEADO ]       [%d] Reg.   |",consecutivoEmpleados-consecutivoExempleados));
			System.out.println("|-----------------------------------------|");
			System.out.println("");

			do {
				System.out.print("Ingrese el nombre del empleado:");
				nombre=keyboard.nextLine(); 
				nombre=nombre.trim().toUpperCase();	
				if(nombre.isBlank() || nombre.isEmpty()){imprimeMSInfo("");} 
			}while(nombre.isBlank() || nombre.isEmpty());
			bandera=true;
			do {
				System.out.print("Ingrese la edad del empleado:");				
				if(keyboard.hasNextByte()){edad=keyboard.nextByte();bandera=false;}else {imprimeMSInfo("");keyboard.nextLine();}	 
			}while(bandera);
			bandera=true;
			do {
				System.out.print("Ingrese el sueldo del empleado:");				
				if(keyboard.hasNextFloat()){sueldoBase=keyboard.nextFloat();bandera=false;}else {imprimeMSInfo("");keyboard.nextLine();}				
			}while(bandera);

			keyboard.nextLine();
			closeOpcion=continuarAgregando("Desea agregar información de otro empleado"); 
			listaEmpleados.add(new Empleado(consecutivoEmpleados+1, nombre, edad, sueldoBase));
			consecutivoEmpleados=listaEmpleados.size();

		} while(closeOpcion!=0);
	}


	private static void aumetarSueldo()
	{		
		byte closeOpcion=1; 
		boolean bandera;
		do {
			System.out.println("|-----------------------------------------|");
			System.out.println("| [ AUMENTAR SUELDO ]                     |");
			System.out.println("|-----------------------------------------|");
			System.out.println("");

			do {
				System.out.print("Ingrese el nombre del empleado:");
				nombre=keyboard.nextLine(); 
				nombre=nombre.trim().toUpperCase();	
				if(nombre.isBlank() || nombre.isEmpty()){imprimeMSInfo("");} 
			}while(nombre.isBlank() || nombre.isEmpty());

			int indice=buscarEmpleado(nombre);
			if(indice>=0)
			{
				bandera=true;
				do {
					System.out.print("Ingrese el porcentaje de aumento: ");				
					if(keyboard.hasNextFloat()){aumento=keyboard.nextFloat();bandera=false;}else {imprimeMSInfo("");keyboard.nextLine();}				
				}while(bandera);

				keyboard.nextLine();
				listaEmpleados.get(indice).getSueldo().aumentarPorcentaje(aumento);
				imprimeMSInfo("Aumento aplicado correctamente");
			}
			else
			{
				imprimeMSInfo("El nombre del empleado no existe");
			}

			closeOpcion=continuarAgregando("Desea buscar otro empleado?"); 

		} while(closeOpcion!=0);
	}

	private static int buscarEmpleado(String nombre) // no valida nombre de empleados repetidos
	{		
		int indice=-1;  
		for(int c=0;c<listaEmpleados.size();c++)
		{
			if(listaEmpleados.get(c).getNombre().equals(nombre) && listaEmpleados.get(c).isActivo())
			{
				indice=c;
				break;
			}
		} 
		return indice;
	}

	private static void imprimeEmpleados()
	{
		System.out.println("|-----------------------------------------|");
		System.out.println(String.format("| [ LISTA DE EMPLEADOS ]       [%d] Reg.   |",consecutivoEmpleados-consecutivoExempleados));
		System.out.println("|-----------------------------------------|");
		System.out.println("");

		for(Empleado empleado: listaEmpleados)
		{
			if(empleado.isActivo())
			{
				System.out.println(empleado.toString());
				System.out.println("");
			}	
		}
		
		imprimeMSInfo("< >"); 		
	}

	private static void bajaEmpleado()
	{
		byte closeOpcion=1; 
		do {
			System.out.println("|-----------------------------------------|");
			System.out.println("| [ DESPEDIR EMPLEADO ]                   |");
			System.out.println("|-----------------------------------------|");
			System.out.println("");

			do {
				System.out.print("Ingrese el nombre del empleado:");
				nombre=keyboard.nextLine(); 
				nombre=nombre.trim().toUpperCase();	
				if(nombre.isBlank() || nombre.isEmpty()){imprimeMSInfo("");} 
			}while(nombre.isBlank() || nombre.isEmpty());

			int indice=buscarEmpleado(nombre);
			if(indice>=0)
			{
				listaEmpleados.get(indice).setActivo(false);

				listaExempleados.add(listaEmpleados.get(indice));
				consecutivoExempleados=listaExempleados.size();
				imprimeMSInfo("Empleado dado de baja correctamente");
			}
			else
			{
				imprimeMSInfo("El nombre del empleado no existe \n\t  ó ya fue dado de baja");
			}

			closeOpcion=continuarAgregando("Desea buscar otro empleado?"); 

		} while(closeOpcion!=0);		
	}

	private static void imprimeExempleados()
	{
		System.out.println("|-----------------------------------------|");
		System.out.println(String.format("| [ LISTA DE EXEMPLEADOS ]     [%d] Reg.   |",consecutivoExempleados));
		System.out.println("|-----------------------------------------|");
		System.out.println("");

		for(int c=0;c<listaExempleados.size();c++)
		{
			System.out.println(listaExempleados.get(c).toString());
			System.out.println("");
		}

		imprimeMSInfo("< >"); 		
	}


	// ----------   mensajes y validaciones generales ---------------------

	private static void imprimeMSInfo(String msg)	
	{
		if(msg.equalsIgnoreCase(""))
		{System.out.println("\t< Dato no valido >");}
		else
		{System.out.println(String.format("\t< %s >",msg));}		
		System.out.println("\t< Presione ENTER para continuar >");
		keyboard.nextLine();
	}

	private static byte continuarAgregando(String msg)
	{
		System.out.println("\n");

		String opcion="S";
		byte valor=0;
		do {
			System.out.println(String.format("\t< %s >",msg));
			System.out.print("\t< [S/N] >");
			opcion=keyboard.nextLine(); 
			opcion=opcion.replaceAll("\\s*", "");
			if(opcion.equalsIgnoreCase("S")) {valor=1;}else {
				if(opcion.equalsIgnoreCase("N")) {valor=0;}else {
					System.out.println("");
					imprimeMSInfo("Opción no valida");
				}		    
			}		    
		}while(!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N")); 
		return valor;
	}

	
	//-------------------------------------------------------------------


}
