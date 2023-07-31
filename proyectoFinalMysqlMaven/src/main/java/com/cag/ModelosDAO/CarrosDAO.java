package com.cag.ModelosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cag.dbConexion.DriverMysql;
import com.cag.modelos.Carros; 

public class CarrosDAO {
	 DriverMysql driver = null;
	 Connection con= null;
	 PreparedStatement pstmt=null;
	 
	 public boolean conectar() {
		 driver= new DriverMysql();
		 if(driver.conectar().equals("CONECTADO")) {
			 con=driver.getConnection();
			 return true;
		 }
		 return false;
	 }
	 	 

	 public boolean agregarActualizarCarro(Carros carro,boolean modoEdicion)  {
		 boolean bandera=true;
		 if(!conectar()){return false;}  // error de conexión ";

		 try {
			 if(modoEdicion) {
				 String query = "UPDATE carros SET modelo=?, marca=?, tipo=?, precio=?, color=?, foto=?, nPuertas=? WHERE id=?";
				 pstmt = con.prepareStatement(query);
				 pstmt.setString(1, carro.getModelo());
				 pstmt.setInt(2, carro.getMarca());
				 pstmt.setString(3, carro.getTipo());
				 pstmt.setDouble(4, carro.getPrecio());
				 pstmt.setString(5, carro.getColor());
				 pstmt.setBytes (6, carro.getFoto());
				 pstmt.setInt(7, carro.getNPuertas());
				 pstmt.setInt(8, carro.getId());
				 pstmt.executeUpdate();
			 }else {
				 String query = "INSERT INTO carros (modelo, marca, tipo, precio, color, foto, nPuertas) VALUES (?, ?, ?, ?, ?, ?, ?)";
				 pstmt = con.prepareStatement(query);
				 pstmt.setString(1, carro.getModelo());
				 pstmt.setInt(2, carro.getMarca());
				 pstmt.setString(3, carro.getTipo());
				 pstmt.setDouble(4, carro.getPrecio());
				 pstmt.setString(5, carro.getColor());
				 pstmt.setBytes(6, carro.getFoto());
				 pstmt.setInt(7, carro.getNPuertas());
				 pstmt.execute();	
			 }			

			 bandera=true;

		 }catch (SQLException e) {
			 System.out.println("error SqlException");
			 //e.printStackTrace();
			 bandera=false;
		 }
		 catch (Exception e) {
			 System.out.println("error Exception");
			 //e.printStackTrace();
			 bandera=false;
		 }
		 finally {
			 try {
				 pstmt.close();
			 } catch (SQLException e) { 
				 //e.printStackTrace();
			 }
			 try {
				 con.close();
			 } catch (SQLException e) { 
				 //e.printStackTrace();
			 }
			 driver.desconectar();
		 } 
		 return bandera;
	 }
	 
	 public boolean eliminarCarro(int id) { 
		 boolean bandera=true;
		 if(!conectar()){return false;}  // error de conexión ";

		 try {			 
			 String query = "DELETE FROM carros WHERE id=?";

			 pstmt = con.prepareStatement(query);
			 pstmt.setInt(1, id); 
			 pstmt.executeUpdate();
			 bandera=true;

		 }catch (SQLException e) {
			 System.out.println("error SqlException");
			 //e.printStackTrace();
			 bandera=false;
		 }
		 catch (Exception e) {
			 System.out.println("error Exception");
			 //e.printStackTrace();
			 bandera=false;
		 }
		 finally {
			 try {
				 pstmt.close();
			 } catch (SQLException e) { 
				 //e.printStackTrace();
			 }
			 try {
				 con.close();
			 } catch (SQLException e) { 
				 //e.printStackTrace();
			 }
			 driver.desconectar();
		 } 
		 return bandera;
	 }
	 
	 public List<Carros> cargarCatalogoCarros(){
		 List<Carros> listaCarros;
		 listaCarros=null;
		 if(!conectar()){return listaCarros;}  // error de conexión ";
		    
			try {
				listaCarros= new ArrayList<Carros>();
				ResultSet resultado=null;	
				//String query = "SELECT * FROM carros";
				String query = "SELECT a.*,b.nombre FROM carros a INNER JOIN marcas b ON a.marca=b.id ORDER BY b.nombre ASC,a.modelo ASC";

				pstmt = con.prepareStatement(query); 
				resultado=pstmt.executeQuery(); 

				while (resultado.next()) { 
					int id = resultado.getInt("id");
					String modelo = resultado.getString("modelo");
					int marca = resultado.getInt("marca");
					String tipo = resultado.getString("nombre");
					double precio = resultado.getDouble("precio");
					String color = resultado.getString("color");
					byte[] foto = resultado.getBytes("foto");
					int nPuertas = resultado.getInt("nPuertas");

					Carros carro = new Carros(id, modelo, marca, tipo, precio, color, foto, nPuertas);
					listaCarros.add(carro);
				}
				

			}catch (SQLException e) {
				System.out.println("error SqlException");
				//e.printStackTrace(); 
			}
			catch (Exception e) {
				System.out.println("error Exception");
				//e.printStackTrace(); 
			}
			finally {
				try {
					pstmt.close();
				} catch (SQLException e) { 
					//e.printStackTrace();
				}
				try {
					con.close();
				} catch (SQLException e) { 
					//e.printStackTrace();
				}
				driver.desconectar();
			} 
		 
		 
		 return listaCarros;
	 }
	 
	 public List<Carros> filtrarCarros(String filtro){
		 List<Carros> listaCarros;
		 listaCarros=null;
		 if(!conectar()){return listaCarros;}  // error de conexión ";
		    
			try {
				listaCarros= new ArrayList<Carros>();
				ResultSet resultado=null;	 
				//String query = "SELECT * FROM carros WHERE modelo LIKE ? OR marca LIKE ?";
				String query = "SELECT a.*,b.nombre FROM carros a INNER JOIN marcas b ON a.marca=b.id WHERE a.modelo LIKE ? OR b.nombre LIKE ? ORDER BY b.nombre ASC,a.modelo ASC";

				pstmt = con.prepareStatement(query); 
				pstmt.setString(1, "%"+filtro+"%");
				pstmt.setString(2, "%"+filtro+"%"); 
				resultado=pstmt.executeQuery();  

				while (resultado.next()) { 
					int id = resultado.getInt("id");
					String modelo = resultado.getString("modelo");
					int marca = resultado.getInt("marca");
					String tipo = resultado.getString("nombre");
					double precio = resultado.getDouble("precio");
					String color = resultado.getString("color");
					byte[] foto = resultado.getBytes("foto");
					int nPuertas = resultado.getInt("nPuertas");

					Carros carro = new Carros(id, modelo, marca, tipo, precio, color, foto, nPuertas);
					listaCarros.add(carro);
				}
				

			}catch (SQLException e) {
				System.out.println("error SqlException");
				//e.printStackTrace(); 
			}
			catch (Exception e) {
				System.out.println("error Exception");
				//e.printStackTrace(); 
			}
			finally {
				try {
					pstmt.close();
				} catch (SQLException e) { 
					//e.printStackTrace();
				}
				try {
					con.close();
				} catch (SQLException e) { 
					//e.printStackTrace();
				}
				driver.desconectar();
			} 
		 
		 
		 return listaCarros;
	 }
	 
	 
	 public Carros buscarCarro(int id) { 
		 Carros carro=null;
		 if(!conectar()){return carro;}  // error de conexión ";
		    
			try {
				ResultSet resultado=null;	
				//String query = "SELECT * FROM carros WHERE id=?";
				String query = "SELECT a.*,b.nombre FROM carros a INNER JOIN marcas b ON a.marca=b.id WHERE a.id=?";

			    pstmt = con.prepareStatement(query);
				pstmt.setInt(1, id); 
				resultado=pstmt.executeQuery(); 
				
				 if (resultado.next()) {  
	                    int carroId = resultado.getInt("id");
	                    String modelo = resultado.getString("modelo");
	                    int marca = resultado.getInt("marca");
	                    String tipo = resultado.getString("nombre");
	                    double precio = resultado.getDouble("precio");
	                    String color = resultado.getString("color");
	                    byte[] foto = resultado.getBytes("foto");
	                    int nPuertas = resultado.getInt("nPuertas");

	                    carro = new Carros(carroId, modelo, marca, tipo, precio, color, foto, nPuertas);
	                }
				

			}catch (SQLException e) {
				System.out.println("error SqlException");
				//e.printStackTrace(); 
			}
			catch (Exception e) {
				System.out.println("error Exception");
				//e.printStackTrace(); 
			}
			finally {
				try {
					pstmt.close();
				} catch (SQLException e) { 
					//e.printStackTrace();
				}
				try {
					con.close();
				} catch (SQLException e) { 
					//e.printStackTrace();
				}
				driver.desconectar();
			} 
		 return carro;
	 }
	 
	 

}
