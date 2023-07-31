package com.cag.proyectoFinalMysqlMaven;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException; 
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.cag.ModelosDAO.CarrosDAO;
import com.cag.ModelosDAO.MarcasDAO;
import com.cag.modelos.Carros;
import com.cag.modelos.Marcas;

import javax.swing.JComboBox; 

public class VentanaMain extends JFrame {
	private static JPanel contentPane;
	private static JPanel pnlTop; 
	private static JPanel pnlLeft;
	private static JPanel pnlAutos; 
	private static JPanel pnlEditarAgregar; 
	
	private static JPanel[] pnlCarros;
	private static JLabel[] lblImagenCarro;
	private static JLabel[] lblNomCarros;
	
  	private static List<Carros> listaMCarros; 
	private static JTextField txtBuscar; 
	private static JPanel pnlDetalles;
	private static JLabel lblIMCarro;
	private static JLabel lblNombreAuto; 
	private static JPanel pnlColores; 
	private static JLabel lblNPuertas; 
	private static JLabel lblPrecio;
	private static JLabel lblIMCarroAdd;
	private static JTextField txtNombre;
	private static JTextField txtPuertas;
	private static JTextField txtPrecio;
	private static JTextField txtImagen;
	private static JCheckBox chkRojo,chkNegro,chkBlanco,chkAzul,chkGris,chkPlata; 
	private static JComboBox cmbMarcas; 
	private static JButton btnAgregar;
	private static JButton btnEditar;
	
	private static Integer indiceGlobal,idGlobal;
	private static boolean modoEdicion;
	private static CarrosDAO carroDAO;
	private JButton btnAgregarMarca;
 
	public VentanaMain() {
	    iniciarComponentes();
	    carroDAO = new CarrosDAO();
	 	pnlCarros= new JPanel[9];
		lblImagenCarro= new JLabel[9];
		lblNomCarros= new JLabel[9]; 
		listaMCarros = new ArrayList<Carros>(); 
		pnlDetalles.setVisible(false);				 	
	 	pnlEditarAgregar.setVisible(false);
		pnlAutos.setBounds(0, 59, 1035, 572);
		indiceGlobal=0;
		idGlobal=0;
		listaMCarros=carroDAO.cargarCatalogoCarros();
		if(listaMCarros!=null) { 
 			crearPaneles();
 			actualizarListaCarros(); 
 			cargarMarcas();
		}else {
			JOptionPane.showMessageDialog(null, "Error al cargar los datos...", "Conexión ó Base de datos incorrecto", JOptionPane.WARNING_MESSAGE);
		}		
	}
	
	public void iniciarComponentes()
	{
		setResizable(false);
		setSize(1044,668);		
	    setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnlEditarAgregar = new JPanel();
		pnlEditarAgregar.setLayout(null);
		pnlEditarAgregar.setBackground(new Color(13, 71, 161));
		pnlEditarAgregar.setBounds(322, 41, 292, 590);
		contentPane.add(pnlEditarAgregar);
		
		JPanel pnlDetalles_1 = new JPanel();
		pnlDetalles_1.setLayout(null);
		pnlDetalles_1.setBackground(new Color(25, 118, 210));
		pnlDetalles_1.setBounds(10, 11, 270, 568);
		pnlEditarAgregar.add(pnlDetalles_1);
		
		lblIMCarroAdd = new JLabel("");
		lblIMCarroAdd.setBounds(24, 0, 221, 121);
		pnlDetalles_1.add(lblIMCarroAdd);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(6, 198, 250, 38);
		pnlDetalles_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Nombre");
		lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_3_1.setBounds(6, 181, 250, 19);
		pnlDetalles_1.add(lblNewLabel_2_3_1);
		
		JLabel lblNewLabel_2_3_1_1 = new JLabel("Marca");
		lblNewLabel_2_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_3_1_1.setBounds(6, 247, 250, 19);
		pnlDetalles_1.add(lblNewLabel_2_3_1_1);
		
		JLabel lblNewLabel_2_3_1_2 = new JLabel("Número de Puertas");
		lblNewLabel_2_3_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_3_1_2.setBounds(6, 313, 250, 19);
		pnlDetalles_1.add(lblNewLabel_2_3_1_2);
		
		txtPuertas = new JTextField();
		txtPuertas.setColumns(10);
		txtPuertas.setBounds(6, 330, 250, 38);
		pnlDetalles_1.add(txtPuertas);
		
		JLabel lblNewLabel_2_3_1_3 = new JLabel("Precio");
		lblNewLabel_2_3_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_3_1_3.setBounds(6, 377, 250, 19);
		pnlDetalles_1.add(lblNewLabel_2_3_1_3);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(6, 394, 250, 38);
		pnlDetalles_1.add(txtPrecio);
		
		JLabel lblNewLabel_2_3_1_3_1 = new JLabel("Imagen");
		lblNewLabel_2_3_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1_3_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_3_1_3_1.setBounds(6, 115, 250, 19);
		pnlDetalles_1.add(lblNewLabel_2_3_1_3_1);
		
		txtImagen = new JTextField();
		txtImagen.setForeground(new Color(128, 128, 128));
		txtImagen.setBackground(new Color(25, 118, 210));
		txtImagen.setEnabled(false);
		txtImagen.setEditable(false);
		txtImagen.setColumns(10);
		txtImagen.setBounds(6, 132, 209, 38);
		pnlDetalles_1.add(txtImagen);
		
		JLabel lblNewLabel_2_3_1_4 = new JLabel("Color");
		lblNewLabel_2_3_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_3_1_4.setBounds(6, 451, 250, 19);
		pnlDetalles_1.add(lblNewLabel_2_3_1_4);
		
		chkRojo = new JCheckBox("");
		chkRojo.setBackground(new Color(255, 0, 0));
		chkRojo.setBounds(6, 472, 41, 29);
		pnlDetalles_1.add(chkRojo);
		
		chkNegro = new JCheckBox("");
		chkNegro.setBackground(new Color(0, 0, 0));
		chkNegro.setBounds(49, 472, 41, 29);
		pnlDetalles_1.add(chkNegro);
		
		chkBlanco = new JCheckBox("");
		chkBlanco.setBackground(new Color(255, 255, 255));
		chkBlanco.setBounds(92, 472, 41, 29);
		pnlDetalles_1.add(chkBlanco);
		
		chkAzul = new JCheckBox("");
		chkAzul.setBackground(new Color(35, 84, 211));
		chkAzul.setBounds(135, 472, 41, 29);
		pnlDetalles_1.add(chkAzul);
		
		chkGris = new JCheckBox("");
		chkGris.setBackground(Color.DARK_GRAY);
		chkGris.setBounds(178, 472, 41, 29);
		pnlDetalles_1.add(chkGris);
		
		chkPlata = new JCheckBox("");
		chkPlata.setBackground(Color.GRAY);
		chkPlata.setBounds(221, 472, 41, 29);
		pnlDetalles_1.add(chkPlata);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(new Color(192, 192, 192));
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				guardarAgregar();
			}
		});
		btnAceptar.setBackground(new Color(13, 71, 161));
		btnAceptar.setBounds(33, 519, 89, 38);
		pnlDetalles_1.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) { 
				clearTxtFields();
			}
		});
		btnCancelar.setForeground(new Color(192, 192, 192));
		btnCancelar.setBackground(new Color(13, 71, 161));
		btnCancelar.setBounds(156, 519, 89, 38);
		pnlDetalles_1.add(btnCancelar);
		
		JButton btnCargarArchivo = new JButton("....");
		btnCargarArchivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				cargarArchivo();
			}
		});
		btnCargarArchivo.setForeground(new Color(255, 255, 255));
		btnCargarArchivo.setBackground(new Color(13, 71, 161));
		btnCargarArchivo.setBounds(221, 132, 35, 38);
		pnlDetalles_1.add(btnCargarArchivo); 
		
		cmbMarcas = new JComboBox();
		cmbMarcas.setBackground(new Color(255, 255, 255));
		cmbMarcas.setBounds(6, 265, 250, 37);
		pnlDetalles_1.add(cmbMarcas);
		
		pnlTop = new JPanel();
		pnlTop.setBackground(new Color(25, 118, 210)); 
		pnlTop.setBounds(0, 0, 1035, 41);
		contentPane.add(pnlTop);
		pnlTop.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar");
		lblNewLabel.setBounds(5, 11, 45, 19);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlTop.add(lblNewLabel);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(55, 5, 231, 31);
		txtBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { 
				filtrarCarros(); 
			}
		});


		txtBuscar.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtBuscar.setColumns(10);
		pnlTop.add(txtBuscar);
		
		JLabel lblNewLabel_1 = new JLabel("< Modelo ó Marca >");
		lblNewLabel_1.setBounds(296, 14, 109, 15);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlTop.add(lblNewLabel_1);
		
		btnAgregar = new JButton("Agregar Auto");
		btnAgregar.setForeground(new Color(192, 192, 192));
		btnAgregar.setBackground(new Color(13, 71, 161));
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlAutos.setVisible(false); 
				pnlLeft.setVisible(false);
				txtBuscar.setEnabled(false);
				btnAgregar.setEnabled(false); 
				pnlEditarAgregar.setBounds(0, 41, 292, 590);
				pnlEditarAgregar.setVisible(true);
				txtImagen.requestFocus();
			}
		});
		btnAgregar.setBounds(447, 11, 132, 23);
		pnlTop.add(btnAgregar); 
		
		btnAgregarMarca = new JButton("Agregar Marca");
		btnAgregarMarca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				agregarMarca();
			}
		});
		btnAgregarMarca.setForeground(Color.LIGHT_GRAY);
		btnAgregarMarca.setBackground(new Color(13, 71, 161));
		btnAgregarMarca.setBounds(610, 11, 132, 23);
		pnlTop.add(btnAgregarMarca);
		
		pnlLeft = new JPanel();
		pnlLeft.setBounds(0, 41, 292, 590);
		pnlLeft.setBackground(new Color(13, 71, 161));
		contentPane.add(pnlLeft);
		pnlLeft.setLayout(null);
		
		pnlDetalles = new JPanel();
		pnlDetalles.setLayout(null);
		pnlDetalles.setBackground(new Color(25, 118, 210));
		pnlDetalles.setBounds(10, 11, 270, 568);
		pnlLeft.add(pnlDetalles);
		
		JLabel lblcloseDetalles = new JLabel("");		 
		lblcloseDetalles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
	 			clearTxtFields();
			}
		});
		lblcloseDetalles.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon ico;
	 	ico = new ImageIcon(VentanaMain.class.getResource("/close2.png"));  
	 	ImageIcon img=new ImageIcon(ico.getImage().getScaledInstance(39,39,Image.SCALE_SMOOTH));
	 	lblcloseDetalles.setIcon(img); 	 
		lblcloseDetalles.setBounds(226, 5, 39, 39);
		pnlDetalles.add(lblcloseDetalles); 
		
		
		lblNombreAuto = new JLabel("nombre el auto");
		lblNombreAuto.setVerticalAlignment(SwingConstants.TOP);
		lblNombreAuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreAuto.setForeground(Color.LIGHT_GRAY);
		lblNombreAuto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombreAuto.setBounds(10, 204, 250, 54);
		pnlDetalles.add(lblNombreAuto);
		
		JLabel lblNewLabel_2 = new JLabel("Colores Disponibles");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(10, 286, 250, 19);
		pnlDetalles.add(lblNewLabel_2);
		
		pnlColores = new JPanel();
		pnlColores.setBackground(new Color(25, 118, 210));
		pnlColores.setBounds(51, 316, 167, 19);
		pnlDetalles.add(pnlColores);
		pnlColores.setLayout(new GridLayout(1, 6));
		
		JLabel lblNewLabel_2_1 = new JLabel("Número de puertas");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_1.setBounds(10, 375, 250, 14);
		pnlDetalles.add(lblNewLabel_2_1);
		
		lblNPuertas = new JLabel("0");
		lblNPuertas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNPuertas.setForeground(Color.LIGHT_GRAY);
		lblNPuertas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNPuertas.setBounds(10, 400, 250, 14);
		pnlDetalles.add(lblNPuertas);
		
		lblIMCarro = new JLabel("");
		lblIMCarro.setBounds(10, 11, 250, 142); 
		pnlDetalles.add(lblIMCarro);
		
		JLabel lblNewLabel_2_2 = new JLabel("Precio");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_2.setBounds(10, 449, 250, 14);
		pnlDetalles.add(lblNewLabel_2_2);
		
		lblPrecio = new JLabel("0");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setForeground(Color.LIGHT_GRAY);
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrecio.setBounds(10, 474, 250, 14);
		pnlDetalles.add(lblPrecio);
		
		btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				editarCarro();
			}
		});
		btnEditar.setForeground(Color.LIGHT_GRAY);
		btnEditar.setBackground(new Color(13, 71, 161));
		btnEditar.setBounds(23, 519, 89, 38);
		pnlDetalles.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				eliminarCarro();
			}
		});
		btnEliminar.setForeground(Color.LIGHT_GRAY);
		btnEliminar.setBackground(new Color(13, 71, 161));
		btnEliminar.setBounds(155, 519, 89, 38);
		pnlDetalles.add(btnEliminar);
		
		//pnlAutos.setLayout(new GridLayout(0, 3, 0, 0));
		
		pnlAutos = new JPanel();
		pnlAutos.setBackground(new Color(255, 255, 255)); 
		pnlAutos.setBounds(613, 41, 417, 590);
		pnlAutos.setLayout(new GridLayout(0, 3, 0, 0));
	 	contentPane.add(pnlAutos);
	}
	
	
	public static  void crearPaneles()
	{		
		for (int c = 0; c < 9; c++) {
            JPanel subPanel = new JPanel(); 
            subPanel.setBackground(new Color(255, 255, 255));
            final JLabel label1 = new JLabel("");
           // label1.setIcon(new ImageIcon(PrincipalWindow2.class.getResource("/tlx-type-s.png")));
            label1.setBackground(Color.WHITE);
            label1.setBounds(0, 0, 250, 142);
            label1.setName("lblICarro"+(c+1));
    		
            JLabel label2 = new JLabel("NAME_CAR");  
            subPanel.add(label1);
            subPanel.add(label2); 
            
            label1.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseReleased(MouseEvent e) { 
    				indiceGlobal=Integer.parseInt(label1.getName().substring(9))-1; 
    				detallesAuto();
    			}
    		});
            
            lblImagenCarro[c]=label1;
            lblNomCarros[c]=label2;
            pnlCarros[c]=subPanel;
            pnlAutos.add(subPanel);  
        }
 
	}
	
	public static void actualizarListaCarros()
	{ 
    	pnlDetalles.setVisible(false);
    	pnlLeft.setVisible(false);
    	pnlAutos.setBounds(0, 59, 1035, 572); 
		for(int c=0;c<9;c++)
		{
			if(c<listaMCarros.size())
			{
				byte[] imagenByte=listaMCarros.get(c).getFoto();  
				lblImagenCarro[c].setIcon(cargarFoto(imagenByte));				
				DecimalFormat formatear = new DecimalFormat("#,###.00");
				lblNomCarros[c].setText(listaMCarros.get(c).getModelo()+"        "+listaMCarros.get(c).getTipo());//+"     "+"$"+formatear.format(listaMCarros.get(c).getPrecio()));
			}
			else
			{
				lblImagenCarro[c].setIcon(null); 
				lblNomCarros[c].setText(null);    	
			}
			
		}
	}
	
	public static void detallesAuto() 
	{ 
		modoEdicion=false;
		if(indiceGlobal>=0)
		{	
			idGlobal=listaMCarros.get(indiceGlobal).getId();
			Carros car=carroDAO.buscarCarro(idGlobal);
			if(car!=null) { 
				pnlAutos.setBounds(287, 41, 748, 590);
				pnlDetalles.setVisible(true); 
				pnlLeft.setVisible(true);
				lblNombreAuto.setText(car.getModelo()+" "+car.getTipo());
				lblNPuertas.setText(String.valueOf(car.getNPuertas()));
				DecimalFormat formatear = new DecimalFormat("#,###.00");
				lblPrecio.setText("$"+formatear.format(car.getPrecio()) ); 			 	
				byte[] imagenByte=car.getFoto();   
				lblIMCarro.setIcon(cargarFoto(imagenByte));				
	 			String[] colores=car.getColores(); 
				JButton[] buttons= new JButton[6];
				pnlColores.removeAll();
				for (int c = 0; c < 6; c++) {
					buttons[c] = new JButton("");
					buttons[c].setEnabled(false);
					buttons[c].setFocusPainted(false);
			        buttons[c].setBorderPainted(false);
			        buttons[c].setContentAreaFilled(false); 
			        buttons[c].setBackground(new Color(25,118,210));
			        pnlColores.add(buttons[c]);
				}
				
				for (int c = 0; c < colores.length; c++) {				
					buttons[c].setContentAreaFilled(true);   
					switch(colores[c])
					{
					case "ROJO":
						buttons[c].setBackground(Color.RED);break;
					case "NEGRO":
						buttons[c].setBackground(Color.BLACK);break;
					case "BLANCO":
						buttons[c].setBackground(Color.WHITE);break;
					case "AZUL":
						buttons[c].setBackground(new Color(35, 84, 211));break;
					case "GRIS":
						buttons[c].setBackground(Color.DARK_GRAY);break;
					case "PLATA":
						buttons[c].setBackground(Color.GRAY);break; 
					}
				}
			    pnlDetalles.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Auto no encontrado...", "Conexión ó Base de datos incorrecto", JOptionPane.WARNING_MESSAGE);
		 	} 
		} 
	}
	
	public static void filtrarCarros()
	{
		listaMCarros=carroDAO.filtrarCarros(txtBuscar.getText().trim().toUpperCase());
		actualizarListaCarros();
	}
	
	public static boolean guardarAgregar()
	{
		byte[] imagenNueva=null;
		if(!validarDatos(txtImagen.getText(),false)) { //ruta de archivo vacia
			if(modoEdicion) { // editando imagen de mysql,se guarda la misma imagen
				imagenNueva=listaMCarros.get(indiceGlobal).getFoto();
			}else {// registro nuevo, ruta de archivo vacia,carga imagen default.png
				txtImagen.setText("src/main/resources/default.png");
			}
		}
	 	
		if(!validarDatos(txtNombre.getText(),false)) {
			JOptionPane.showMessageDialog(null, "Nombre incorrecto", "Advertencia", JOptionPane.WARNING_MESSAGE);
			txtNombre.requestFocus();
			return false;
		}
		if(!validarDatos(txtPuertas.getText(),true)) {
			JOptionPane.showMessageDialog(null, "Número de puertas incorrecto", "Advertencia", JOptionPane.WARNING_MESSAGE);
			txtPuertas.requestFocus();
			return false;
		}
		if(!validarDatos(txtPrecio.getText(),true)) {
			JOptionPane.showMessageDialog(null, "Precio incorrecto", "Advertencia", JOptionPane.WARNING_MESSAGE);
			txtPrecio.requestFocus();
			return false;
		}
		String strColores="";
		strColores=(chkRojo.isSelected())?"ROJO":"";
		strColores+=(chkBlanco.isSelected())?"-BLANCO":"";
		strColores+=(chkNegro.isSelected())?"-NEGRO":"";
		strColores+=(chkAzul.isSelected())?"-AZUL":"";
		strColores+=(chkPlata.isSelected())?"-PLATA":"";
		strColores+=(chkGris.isSelected())?"-GRIS":"";
		if(!strColores.isEmpty() && strColores.charAt(0)=='-'){
			strColores=strColores.substring(1);
		}
		
 		if(!validarDatos(strColores,false)) {
			JOptionPane.showMessageDialog(null, "Seleccione un color", "Advertencia", JOptionPane.WARNING_MESSAGE);
			chkRojo.requestFocus();
			return false;
		}		 
			
		try {
			if(imagenNueva==null) {
				File file = new File(txtImagen.getText());		
				BufferedImage bufferedImage;
				bufferedImage = ImageIO.read(file);
				ByteArrayOutputStream bimag = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "png", bimag);
				imagenNueva=bimag.toByteArray();
			} 
			
		    boolean res=carroDAO.agregarActualizarCarro(new Carros(idGlobal,"   "+txtNombre.getText().trim().toUpperCase(), cmbMarcas.getSelectedIndex()+1, "tipo",Double.parseDouble(txtPrecio.getText()), strColores,imagenNueva,Integer.parseInt(txtPuertas.getText())),modoEdicion);
			if(res) {
				clearTxtFields();
			}else {
				JOptionPane.showMessageDialog(null, "Error al guardar los datos...", "Conexión ó Base de datos incorrecto", JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
		} catch (IOException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al guardar la imagen...", "Archivo en formato incorrecto o dañado", JOptionPane.WARNING_MESSAGE);
			return false;
		} 
	        
		
		
		return true;
	}
	
	public static boolean validarDatos(String dato,boolean esNumero)
	{
		if(esNumero)
		{  
			try {
				Double.parseDouble(dato);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		else
		{ 
			if(!dato.isEmpty() && dato.length()>=2)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	private static void clearTxtFields()
	{
		lblIMCarroAdd.setIcon(null);
		txtImagen.setText("");
		txtNombre.setText(""); 
		txtPuertas.setText("");
		txtPrecio.setText("");
		chkRojo.setSelected(false);
		chkBlanco.setSelected(false);
		chkNegro.setSelected(false);
		chkAzul.setSelected(false);
		chkGris.setSelected(false);
		chkPlata.setSelected(false);

		pnlAutos.setVisible(true);
		txtBuscar.setText("");
		filtrarCarros();
		txtBuscar.setEnabled(true);
		btnAgregar.setEnabled(true);
		pnlEditarAgregar.setVisible(false);
		modoEdicion=false;
	}
	
	public static void editarCarro() {
		modoEdicion=true;
		pnlAutos.setVisible(false); 
		pnlLeft.setVisible(false);
		txtBuscar.setEnabled(false);
		btnAgregar.setEnabled(false); 
		pnlEditarAgregar.setBounds(0, 41, 292, 590);
		pnlEditarAgregar.setVisible(true);
		txtImagen.requestFocus();
		
		ImageIcon ico=(ImageIcon) lblIMCarro.getIcon();
		ImageIcon img=new ImageIcon(ico.getImage().getScaledInstance(221,121,Image.SCALE_SMOOTH));
		lblIMCarroAdd.setIcon(img); 
		txtNombre.setText(listaMCarros.get(indiceGlobal).getModelo()); 
		cmbMarcas.setSelectedIndex(listaMCarros.get(indiceGlobal).getMarca()-1);
		
 		txtPuertas.setText(String.valueOf(listaMCarros.get(indiceGlobal).getNPuertas()));
        txtPrecio.setText(String.valueOf(listaMCarros.get(indiceGlobal).getPrecio()) ); 
        String[] colores=listaMCarros.get(indiceGlobal).getColores();
        
        for (int c = 0; c < colores.length; c++) {	 
			switch(colores[c])
			{
			case "ROJO":
				chkRojo.setSelected(true);;break;
			case "NEGRO":
				chkNegro.setSelected(true);;break;
			case "BLANCO":
				chkBlanco.setSelected(true);;break;
			case "AZUL":
				chkAzul.setSelected(true);;break;
			case "GRIS":
				chkGris.setSelected(true);;break;
			case "PLATA":
				chkPlata.setSelected(true);;break; 
			}
		} 

	}
	
	public boolean eliminarCarro() {
		int opcion=JOptionPane.showOptionDialog(null, "¿Desea eliminar este Auto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (opcion == JOptionPane.YES_OPTION) {
			if(carroDAO.eliminarCarro(idGlobal)) {
				clearTxtFields();	
			}else {
				JOptionPane.showMessageDialog(null, "Error al eliminar el auto", "Conexión ó Base de datos incorrecto", JOptionPane.WARNING_MESSAGE);
			}	
		} else{
			return false;
		}
		return true;
	}
	
	public boolean agregarMarca() {
		String datosEntrada = JOptionPane.showInputDialog(null, "Ingrese el nombre de la nueva marca:", "Agregar Marca", JOptionPane.QUESTION_MESSAGE);
		if (datosEntrada != null && !datosEntrada.isEmpty()) { 
			MarcasDAO marcaDAO= new MarcasDAO();
			Marcas marca=new Marcas(0, datosEntrada.trim().toUpperCase());
			if(marcaDAO.agregarActualizarMARCA(marca, false)) {
				cargarMarcas();
			}else {
				JOptionPane.showMessageDialog(null, "Error al agregar una nueva marca", "Conexión ó Base de datos incorrecto", JOptionPane.WARNING_MESSAGE);
			}	
		} else{
			return false;
		}
		return true;
	}
	
	public void cargarMarcas() {
		MarcasDAO marcaDAO= new MarcasDAO();
		List<Marcas> listaMarcas;
		listaMarcas=marcaDAO.cargarCatalogoMarcas();
		if(listaMarcas!=null) { 
			cmbMarcas.removeAllItems();
			for(Marcas marca:listaMarcas) {
				cmbMarcas.addItem(marca.getNombre());
			}

		}else {
			JOptionPane.showMessageDialog(null, "Error al cargar los datos...", "Conexión ó Base de datos incorrecto", JOptionPane.WARNING_MESSAGE);
		}	

	}
	
	
	public void cargarArchivo()
	{
		 JFileChooser fileChooser = new JFileChooser();
		 FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (PNG, JPG)", "png", "jpg");
		 fileChooser.setAcceptAllFileFilterUsed(false);
	     fileChooser.setFileFilter(filter);
	     int result = fileChooser.showOpenDialog(this);
	     if (result == JFileChooser.APPROVE_OPTION) {
             File selectedFile = fileChooser.getSelectedFile();
             String imagePath = selectedFile.getAbsolutePath();
             ImageIcon imageIcon = new ImageIcon(imagePath); 
             if (imageIcon.getIconWidth() > lblIMCarroAdd.getWidth()) {
                 imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(
                		 lblIMCarroAdd.getWidth(), -1, Image.SCALE_DEFAULT));
             }
             txtImagen.setText(imagePath);
             lblIMCarroAdd.setIcon(imageIcon);
         }
	}
	
	public static ImageIcon cargarFoto(byte[] foto) {
		ImageIcon ico=null;
		ico=new ImageIcon(foto);    			
		ico=new ImageIcon(ico.getImage().getScaledInstance(250,142,Image.SCALE_SMOOTH));
		return ico;
	}
}
