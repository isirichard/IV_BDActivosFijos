package conectaBD;
import javax.swing.*;

import java.awt.*;
import java.sql.*;



public class Aplicacion_Consulta {
	//Método principal
	public static void main(String[] args) {
		
		//Marco
		JFrame mimarco=new Marco_Aplicacion();
		//Cerrar
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Visible
		mimarco.setVisible(true);

	}

}

class Marco_Aplicacion extends JFrame{
	//contruir la interfaz gráfica
	public Marco_Aplicacion(){
		
		setTitle ("LM1001 Maestro Activo");
		
		setBounds(500,300,800,600);
		//menu
		setLayout(new BorderLayout());
		
		JPanel menus=new JPanel();
		//menu Border y dentro una lamina para los dos menus
		menus.setLayout(new FlowLayout());
		
		secciones=new JComboBox();
		
		secciones.setEditable(false);
		
		secciones.addItem("Todos");
		
		paises=new JComboBox();
		
		paises.setEditable(false);
		
		paises.addItem("Todos");
		
		resultado= new JTextArea(4,50);
		
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(secciones);
		
		menus.add(paises);	
		
		add(menus, BorderLayout.NORTH);
		
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta=new JButton("Consulta");	
		
		add(botonConsulta, BorderLayout.SOUTH);
		
		
		
	}	
		

	
	private JComboBox secciones;
	
	private JComboBox paises;
	
	private JTextArea resultado;	
	

	
}


