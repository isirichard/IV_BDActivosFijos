
package proyectofinalbdv3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InformeMovimientoMes extends JFrame{
    JScrollPane scrTabMovMes;
    DefaultTableModel dtm;
    JTable tabMovMes;
    String cadena="";
    String mes="";
    String ani="";

    public InformeMovimientoMes(String string){
        cadena=string;
        String[] parts = string.split(" ");
        mes = parts[0];
        ani = parts[1];
        System.out.println(mes);
    	initComponents();
        setTitle("Informe Movimiento - mes:"+string);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);
        
        setLayout(new BorderLayout());
        add(scrTabMovMes,BorderLayout.CENTER);
        
        
        setVisible(true);
    }

    private void initComponents() {
        dtm=new DefaultTableModel();
        String[] titulo={"MovCOd","TipTraNom","ResNom","OfiNom","MovEstReg"};
        dtm.setColumnIdentifiers(titulo);
        tabMovMes=new JTable();
        tabMovMes.setModel(dtm);
        scrTabMovMes=new JScrollPane();
        scrTabMovMes.setViewportView(tabMovMes);
        try {
        	//1. Crear CONEXIÓN
        	Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
        	//2. CREAR OBJETO STATEMENT
        	//Statement miStatement=miConextion.createStatement();

        	//Responsable y ciudad
        	//String uno = "CREATE VIEW MOV_AQP_MES3 ";
        	String dos="SELECT * ";
        	String tres="FROM ACTIVOS_FIJOS_CAB, ACTIVOS_FIJOS_DET, RESPONSABLE, CIUDAD, TIPO_TRASPASO ";
        	String cuatro="WHERE  ACTIVOS_FIJOS_CAB.ACTCOD=ACTIVOS_FIJOS_DET.ACTCOD AND TIPO_TRASPASO.TIPTRACOD=ACTIVOS_FIJOS_DET.TIPTRACOD AND ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI AND RESPONSABLE.CIUCOD= CIUDAD.CIUCOD ";
        	String cinco="AND ACTFECMES=? AND ACTFECDIA=?";
        	String sql=dos+tres+cuatro+cinco;
        	//ResultSet miResulset=miStatement.executeQuery("SELECT  TIPTRANOM, CIUNOM, ACTFECAÑO, ACTFECMES, ACTFECDIA FROM ACTIVOS_FIJOS_CAB, ACTIVOS_FIJOS_DET, RESPONSABLE, CIUDAD, TIPO_TRASPASO WHERE  ACTIVOS_FIJOS_CAB.ACTCOD=ACTIVOS_FIJOS_DET.ACTCOD AND TIPO_TRASPASO.TIPTRACOD=ACTIVOS_FIJOS_DET.TIPTRACOD AND ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI AND RESPONSABLE.CIUCOD= CIUDAD.CIUCOD");
        	PreparedStatement miSentencia=miConextion.prepareStatement(sql);
        	//ResultSet miResulset=miStatement.executeQuery(sql);
        	miSentencia.setInt(1,Integer.parseInt(mes));
        	miSentencia.setInt(2,Integer.parseInt(ani));
        	System.out.println(sql);
        	//3. EJECUTAR SQL
        	ResultSet miResulset=miSentencia.executeQuery();
        	//4. RECORRER EL RESULSET
        	tabMovMes.removeAll();
        	DefaultTableModel model=new DefaultTableModel(new String [] {
        			"Mes","Año","Responsable","TIPTRANOM","Nombre Activo"
    		},0);
        	while(miResulset.next()){
            	model.addRow(new Object[]{miResulset.getString("ACTFECMES"),miResulset.getString("ACTFECDIA"),miResulset.getString("RESNOM"),miResulset.getString("TIPTRANOM"),miResulset.getString("ACTNOM")});
			}
        	tabMovMes.setModel(model);
        	//System.out.println(miResulset.getString("CIUNOM"));
        	scrTabMovMes.setViewportView(tabMovMes);
        	
        	miResulset.close();
        	//miStatement.close();
        	miConextion.close();

        	//System.out.println(comCarNom.getItemAt(1));
        	//System.out.println("CONECTADO A BD PROVEE!!");
        	} catch (Exception e) {
        	System.out.println("NO CONECTADO A BD VISTA 1!!");
        	e.printStackTrace();
        	}
        
    }
    
}
