package com.cag.ModelosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cag.dbConexion.DriverMysql; 
import com.cag.modelos.Marcas;

public class MarcasDAO {
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
	 
	 public boolean agregarActualizarMARCA(Marcas marca,boolean modoEdicion)  {
		 boolean bandera=true;
		 if(!conectar()){return false;}  // error de conexión ";

		 try {
			 if(modoEdicion) {
				 String query = "UPDATE marcas SET nombre=? WHERE id=?";
				 pstmt = con.prepareStatement(query);
				 pstmt.setString(1, marca.getNombre()); 
				 pstmt.setInt(1, marca.getId());
				 pstmt.executeUpdate();
			 }else {
				 String query = "INSERT INTO marcas (nombre) VALUES (?)";

				 pstmt = con.prepareStatement(query);
				 pstmt.setString(1, marca.getNombre()); 
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
	 
	 public boolean eliminarMarca(int id) { 
		 boolean bandera=true;
		 if(!conectar()){return false;}  // error de conexión ";

		 try {			 
			 String query = "DELETE FROM marcas WHERE id=?";

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
	 
	 public List<Marcas> cargarCatalogoMarcas(){
		 List<Marcas> listaMarcas;
		 listaMarcas=null;
		 if(!conectar()){return listaMarcas;}  // error de conexión ";
		    
			try {
				listaMarcas= new ArrayList<Marcas>();
				ResultSet resultado=null;	
				String query = "SELECT * FROM marcas";

				pstmt = con.prepareStatement(query); 
				resultado=pstmt.executeQuery(); 

				while (resultado.next()) { 
					int id = resultado.getInt("id");
					String nombre = resultado.getString("nombre");

					Marcas marca = new Marcas(id, nombre);
					listaMarcas.add(marca);
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
		 return listaMarcas;
	 }
	 
}
