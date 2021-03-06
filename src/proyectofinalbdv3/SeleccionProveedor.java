
package proyectofinalbdv3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeleccionProveedor extends JFrame{
    JLabel lblResponsable;
    JComboBox<String> comResponsable;
    JButton btnSiguiente;
    JPanel panSeleccion;
    JPanel panResponsables;
    JPanel panSiguiente;

    public SeleccionProveedor() {
        initComponents();
        setTitle("Seleccion de Proveedor de Activos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);
        
        setLayout(new BorderLayout());
        panSeleccion.add(lblResponsable);
        panResponsables.add(comResponsable);
        panSiguiente.add(btnSiguiente);
        add(panSeleccion,BorderLayout.NORTH);
        add(panResponsables,BorderLayout.CENTER);
        add(panSiguiente,BorderLayout.SOUTH);
        setVisible(true);
    }
     private void initComponents() {
        panSeleccion=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panResponsables=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panSiguiente=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblResponsable=new JLabel("Seleccione Proveedor:");
        comResponsable=new JComboBox<>();
        try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM PROVEEDOR");
			//4. RECORRER EL RESULSET
			comResponsable.removeAllItems();
			while(miResulset.next()){
				comResponsable.addItem(miResulset.getString("PRONOM"));
			}
			//System.out.println(comCarNom.getItemAt(1));
			//System.out.println("CONECTADO A BD PROVEE!!");
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD PROVEEDOR!!");
			e.printStackTrace();
		}
        //final String []tabla={"Ferreteria San Diego","Farmacia San Luis","Constructora Hnos. Alvarez"};
        //comResponsable.setModel(new DefaultComboBoxModel<>(tabla));
        btnSiguiente=new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String temp=comResponsable.getSelectedItem().toString();
                //if(temp.equals(tabla[0])){
                    StringBuffer sb=new StringBuffer();
                    sb.append(comResponsable.getSelectedItem());
                    new InformeActivoProveedor(sb.toString());
                    dispose();
                //}
                
            }
        });
    }
}
