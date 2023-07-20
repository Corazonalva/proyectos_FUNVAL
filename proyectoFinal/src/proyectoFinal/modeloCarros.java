package proyectoFinal;

import java.util.ArrayList;
import java.util.List;

public class modeloCarros {
	private List<Carros> listaCarros;
	
	public List<Carros> cargarCatalogoCarros()
	{
		//System.out.println("LOAD DATA...");
		int id=1;
		listaCarros= new ArrayList<Carros>();
		listaCarros.add(new Carros(id,"CITY","HONDA","SEDAN",350000,"ROJO-NEGRO-BLANCO-GRIS-AZUL-PLATA","/resources/city.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id,"CIVIC","HONDA","SEDAN",400000,"AZUL-ROJO","/resources/civic.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id,"CRV","HONDA","SEDAN",380000,"PLATA","/resources/crv.png",4));		
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id,"JETTA SPORTLINE","VW","SEDAN",530000,"ROJO-NEGRO-BLANCO-GRIS","/resources/jetta-Sportline.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id,"JETTA TRENDLINE","VW","SEDAN",330000,"NEGRO-BLANCO-GRIS","/resources/jetta-Trendline.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id,"VIRTUS TRENDLINE","VW","SEDAN",310000,"NEGRO-GRIS","/resources/virtus-Trendline.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id,"VIRTUS HIGHLINE","VW","SEDAN",410000,"NEGRO-GRIS-AZUL","/resources/virtus-Highline.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id,"CRV-HIBRIDO","HONDA","SEDAN",520000,"NEGRO","/resources/crv-hibrido.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id, "HRV","HONDA","SUV",420000,"BLANCO","/resources/hrv.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id, "INTEGRA","HONDA","SUV",380000,"GRIS","/resources/integra.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id, "MDX TYPE S","HONDA","SUV",360000,"ROJO","/resources/mdx-type-s.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id, "ODYSSEY","HONDA","SUV",390000,"AZUL","/resources/odyssey.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id, "PILOT 2023","HONDA","HATCHBACK",480000,"BLANCO","/resources/pilot-2023.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id, "RDX","HONDA","HATCHBACK",300000,"NEGRO","/resources/rdx.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id, "   TLX","HONDA","HATCHBACK",250000,"ROJO","/resources/tlx.png",4));
		id=listaCarros.size()+1;
		listaCarros.add(new Carros(id, "TLX TYPE S","HONDA","HATCHBACK",290000,"GRIS","/resources/tlx-type-s.png",4));
		
		return listaCarros;
	}
	
	
	public List<Carros> buscarCarros(String filtro)
	{		
		List<Carros> carrosFiltrados= new ArrayList<>();
		filtro.trim();
		if(!filtro.isBlank() && !filtro.isEmpty()) {
			for (Carros carro : listaCarros) { 
				if (carro.getModelo().toUpperCase().contains(filtro.toUpperCase())) {
					carrosFiltrados.add(carro);
				}
			} 
			
			if(carrosFiltrados.size()==0)
			{
			 	for (Carros carro : listaCarros) { 
					if (carro.getMarca().toUpperCase().contains(filtro.toUpperCase())) {
						carrosFiltrados.add(carro);
					}
				}	 
			}
			
			if(carrosFiltrados.size()==0)
			{
				for (Carros carro : listaCarros) { 
					double precio; 
					try {
						precio = Double.parseDouble(filtro);
						if (carro.getPrecio()<=precio) {
							carrosFiltrados.add(carro);
						}
					} catch (NumberFormatException e) {
						precio=0;
					}					
				}	 
			}
			
			// falta por agregar tercer fitro modelo+marca+precio
		}
		else
		{ 
			return listaCarros;
		}
		// System.out.println("filtros: "+carrosFiltrados);
		return carrosFiltrados;
	}
	
	public void guardarAgregarCarro(String modelo,String marca,String tipo, 
		      double precio,String color,String foto,int nPuertas,boolean modoEdicion,int idGlobal)
	{
		if(modoEdicion) {
			Carros carro= new Carros(idGlobal, modelo, marca, tipo, precio, color, foto, nPuertas);
			for(int c=0;c<listaCarros.size();c++) {
				if(listaCarros.get(c).getId()==idGlobal) {
					listaCarros.set(c, carro);					
				}
			}
		}
		else {
			Carros carro= new Carros(listaCarros.size()+1, modelo, marca, tipo, precio, color, foto, nPuertas);
			listaCarros.add(carro);
		} 
	}
	
	

	@Override
	public String toString() {
		return listaCarros+"";
	}
	
	

}
