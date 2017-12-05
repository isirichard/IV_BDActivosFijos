
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

public class InformeActivoResponsable extends JFrame{
    JScrollPane scrTabActRes;
    DefaultTableModel dtm;
    JTable tabActRes;
    String cadena="";

    public InformeActivoResponsable(String string){
    	System.out.println(string+"/////////////////////////////////////////////////");
    	cadena=string;
        initComponents();
        setTitle("Informe Activo Fijo - Responsable");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);
        
        setLayout(new BorderLayout());
        add(scrTabActRes,BorderLayout.CENTER);
        
        setVisible(true);
    }

    private void initComponents() {
        dtm=new DefaultTableModel();
        String[] titulo={"ActCod","ActNom","ActDes","ActNumSer","ActFac","ActCueCon","ActFec","ActEstReg"};
        dtm.setColumnIdentifiers(titulo);
        tabActRes=new JTable();
        tabActRes.setModel(dtm);
        scrTabActRes=new JScrollPane();
        scrTabActRes.setViewportView(tabActRes);
        try {
        	//1. Crear CONEXIÓN
        	Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
        	//2. CREAR OBJETO STATEMENT
        	//Statement miStatement=miConextion.createStatement();

        	//Responsable y ciudad
        	//String uno = "CREATE VIEW MOV_AQP_MES3 ";
        	String dos="SELECT * ";
        	String tres="FROM ACTIVOS_FIJOS_CAB, ACTIVOS_FIJOS_DET, RESPONSABLE ";
        	String cuatro="WHERE  ACTIVOS_FIJOS_CAB.ACTCOD=ACTIVOS_FIJOS_DET.ACTCOD AND ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI ";
        	String cinco="AND RESNOM=?";
        	String sql=dos+tres+cuatro+cinco;
        	//ResultSet miResulset=miStatement.executeQuery("SELECT  TIPTRANOM, CIUNOM, ACTFECAÑO, ACTFECMES, ACTFECDIA FROM ACTIVOS_FIJOS_CAB, ACTIVOS_FIJOS_DET, RESPONSABLE, CIUDAD, TIPO_TRASPASO WHERE  ACTIVOS_FIJOS_CAB.ACTCOD=ACTIVOS_FIJOS_DET.ACTCOD AND TIPO_TRASPASO.TIPTRACOD=ACTIVOS_FIJOS_DET.TIPTRACOD AND ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI AND RESPONSABLE.CIUCOD= CIUDAD.CIUCOD");
        	PreparedStatement miSentencia=miConextion.prepareStatement(sql);
        	//ResultSet miResulset=miStatement.executeQuery(sql);
        	miSentencia.setString(1,cadena);
        	//3. EJECUTAR SQL
        	ResultSet miResulset=miSentencia.executeQuery();
        	//4. RECORRER EL RESULSET
        	tabActRes.removeAll();
        	DefaultTableModel model=new DefaultTableModel(new String [] {
            		"ActCod","Nombre Activo","Día","Mes","Año","Responsable"
    		},0);
        	while(miResulset.next()){
            	model.addRow(new Object[]{miResulset.getString("ACTCOD"),miResulset.getString("ACTNOM"),miResulset.getString("ACTFECAÑO"),miResulset.getString("ACTFECMES"),miResulset.getString("ACTFECDIA"),miResulset.getString("RESNOM")});
			}
        	tabActRes.setModel(model);
        	//System.out.println(miResulset.getString("CIUNOM"));
        	scrTabActRes.setViewportView(tabActRes);
        	
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
