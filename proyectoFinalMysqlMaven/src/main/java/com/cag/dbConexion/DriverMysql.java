package com.cag.dbConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public class DriverMysql {
	private String nombreBd="catalogoautos";
	private String usuario="root";
	private String password="";
	private String url="jdbc:mysql://localhost:3306/"+nombreBd+"?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
			+ "serverTimezone=UTC";

	
//	private String url="jdbc:mysql://root:mxspf2m7TaQmMR6gaOcA@containers-us-west-98.railway.app:7128/"+nombreBd+"?useUnicode=true&use"
//			+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
//			+ "serverTimezone=UTC";

	
	Connection conn=null;

	public String conectar() {
		String respuesta="";
		try {
			//obtener el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//obtener la conexion
			conn=DriverManager.getConnection(url,usuario,password);
			if (conn!=null) {
				respuesta="CONECTADO";
			}else{
				respuesta="NO SE PUDO CONECTAR "+nombreBd;
			}
		}
		catch (ClassNotFoundException e) {
			respuesta="ClassNotFoundException : "+e.getMessage();
		}
		catch (SQLSyntaxErrorException e) {
			respuesta="ocurre una SQLSyntaxErrorException: "+e.getMessage()+"\n";
			respuesta+="Verifique que la base de datos, tablas ó sintaxis SQL sean correctas...";
		}
		catch (CommunicationsException e) {
			respuesta="ocurre una CommunicationsException: "+e.getMessage()+"\n";
			respuesta+="Verifique que el servidor de la base de datos este en linea...";
		}
		catch (SQLException e) {
			respuesta="ocurre una SQLException: "+e.getMessage()+"\n";
			respuesta+="Problema general de SQL...";
		}

		return respuesta;
	}

	public Connection getConnection(){
		return conn;
	}
	public void desconectar(){
		conn=null;
	}
}
