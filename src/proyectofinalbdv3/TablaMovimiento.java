
package proyectofinalbdv3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TablaMovimiento extends JFrame{
	String evento="";
	String contenidoA="";
    JLabel lblActMovCod;
    JTextField txtActMovCod;
    JLabel lblTipTrapNom;
    JComboBox<String> comTipTraNom;
    JLabel lblResNom;
    JComboBox<String> comResNom;
    JLabel lblOfiNom;
    JComboBox<String> comOfiNom;
    JLabel lblMovEstReg;
    JLabel lblEstReg;
    JScrollPane scrTabMov;
    DefaultTableModel dtm;
    JTable tabMov;
    JButton btnAdicionar;
    JButton btnModificar;
    JButton btnEliminar;
    JButton btnCancelar;
    JButton btnInactivar;
    JButton btnReactivar;
    JButton btnActualizar;
    JButton btnSalir;
    JPanel panArriba;
    JPanel panAbajo;
    JPanel panActMovCod;
    JPanel panTipTraNom;
    JPanel panResNom;
    JPanel panOfiNom;
    JPanel panMovEstReg;

    public TablaMovimiento(String contenido) {
    	contenidoA=contenido;
    	//System.out.println(contenido);
        initComponents();
        setTitle("LZ3001_MOVIMIENTO "+ contenido);
        setSize(600, 650);
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);
        
        setLayout(new BorderLayout());
        
        GridLayout griTop=new GridLayout(5,0);
        panArriba=new JPanel(griTop);
                
        GridLayout griBot=new GridLayout(2, 4);
        panAbajo=new JPanel(griBot);
        
        panActMovCod=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panTipTraNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panResNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panOfiNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panMovEstReg=new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        panActMovCod.add(lblActMovCod);
        panActMovCod.add(txtActMovCod);
        panTipTraNom.add(lblTipTrapNom);
        panTipTraNom.add(comTipTraNom);
        panResNom.add(lblResNom);
        panResNom.add(comResNom);
        panOfiNom.add(lblOfiNom);
        panOfiNom.add(comOfiNom);
        panMovEstReg.add(lblMovEstReg);
        panMovEstReg.add(lblEstReg);
        
        panArriba.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panAbajo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        add(panArriba,BorderLayout.NORTH);
        add(scrTabMov,BorderLayout.CENTER);
        add(panAbajo,BorderLayout.SOUTH);
        
        panArriba.add(panActMovCod);
        panArriba.add(panTipTraNom);
        panArriba.add(panResNom);
        panArriba.add(panOfiNom);
        panArriba.add(panMovEstReg);
        
        panAbajo.add(btnAdicionar);
        panAbajo.add(btnModificar);
        panAbajo.add(btnEliminar);
        panAbajo.add(btnCancelar);
        panAbajo.add(btnInactivar);
        panAbajo.add(btnReactivar);
        panAbajo.add(btnActualizar);
        panAbajo.add(btnSalir);
        
        setVisible(true);
    }

    private void initComponents() {
        lblActMovCod=new JLabel("MovCod:     ");
        txtActMovCod=new JTextField(2);
        
        lblTipTrapNom=new JLabel("TipTraNom:");
        comTipTraNom=new JComboBox<>();
        String []tabTipTraNom={"Entrega","Recepción","Traspaso","Aceptación","Destrucción"};
        comTipTraNom.setModel(new DefaultComboBoxModel<>(tabTipTraNom));
        
        lblResNom=new JLabel("ResNom:    ");
        comResNom=new JComboBox<>();
        String []tabResNom={"Juan","Pedro","Ciro","Juana","Laura"};
        comResNom.setModel(new DefaultComboBoxModel<>(tabResNom));
        
        lblOfiNom=new JLabel("OfiNom:      ");
        comOfiNom=new JComboBox<>();
        String []tabOfiNom={"001","002","003","004","005"};
        comOfiNom.setModel(new DefaultComboBoxModel<>(tabOfiNom));
        
        lblMovEstReg=new JLabel("MovEstReg:");
        lblEstReg=new JLabel("A");
        
        dtm=new DefaultTableModel();
        String[] titulo={"MovCOd","TipTraNom","ResNom","OfiNom","MovEstReg"};
        dtm.setColumnIdentifiers(titulo);
        
        
        tabMov=new JTable();
        tabMov.setModel(dtm);
        
        scrTabMov=new JScrollPane();
        scrTabMov.setViewportView(tabMov);
        scrTabMov.setBorder(new EmptyBorder(5, 5, 5, 5));
        tabMov.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM TIPO_TRASPASO");
			//4. RECORRER EL RESULSET
			comTipTraNom.removeAllItems();
			while(miResulset.next()){
				comTipTraNom.addItem(miResulset.getString("TIPTRANOM"));
			}
			//System.out.println(comCiuNom.getItemAt(1));
			//System.out.println("CONECTADO A BD TRASPASO!!");
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD TRASPASO!!");
			e.printStackTrace();
		}
		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM RESPONSABLE");
			//4. RECORRER EL RESULSET
			comResNom.removeAllItems();
			while(miResulset.next()){
				comResNom.addItem(miResulset.getString("RESNOM"));
			}
			//System.out.println(comCarNom.getItemAt(1));
			//System.out.println("CONECTADO A BD RESPON!!");
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD RESPON!!");
			e.printStackTrace();
		}
		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM OFICINA");
			//4. RECORRER EL RESULSET
			comOfiNom.removeAllItems();
			while(miResulset.next()){
				comOfiNom.addItem(miResulset.getString("OFINOM"));
			}
			//System.out.println(comCarNom.getItemAt(1));
			//System.out.println("CONECTADO A BD OFICINA!!");
			miResulset.close();
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD OFICINA!!");
			e.printStackTrace();
		}
		int dni=0;
		try {
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. PREPARAR CONSULTA
			PreparedStatement miSentencia=miConextion.prepareStatement("SELECT RESDNI FROM RESPONSABLE WHERE RESNOM=?");
			//3. ESTABLECER LOS PARAMETROS DE CONSULTA
			//System.out.println((String)comResNom.getSelectedItem()+" ");
			miSentencia.setString(1, (String)comResNom.getSelectedItem());
			ResultSet miResulset=miSentencia.executeQuery();
			miResulset.next();
			//System.out.println(miResulset.getInt("RESDNI"));
			dni=miResulset.getInt("RESDNI");
			miResulset.close();
			
		} catch (Exception e) {
			System.out.println("no conectado RESPONSABLE");
			e.printStackTrace();
		}
		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			//ResultSet miResulset=miStatement.executeQuery("SELECT * FROM ACTIVOS_FIJOS_DET INNER JOIN TIPO_TRASPASO ON ACTIVOS_FIJOS_DET.TIPTRACOD=TIPO_TRASPASO.TIPTRACOD INNER JOIN RESPONSABLE ON ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI INNER JOIN OFICINA ON ACTIVOS_FIJOS_DET.OFICOD=OFICINA.OFICOD");
			
			//ResultSet miResulset=miStatement.executeQuery("SELECT * FROM ACTIVOS_FIJOS_DET INNER JOIN TIPO_TRASPASO ON ACTIVOS_FIJOS_DET.TIPTRACOD=TIPO_TRASPASO.TIPTRACOD INNER JOIN RESPONSABLE ON ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI INNER JOIN OFICINA ON ACTIVOS_FIJOS_DET.OFICOD=OFICINA.OFICOD WHERE ACTCOD=?");
			//PreparedStatement miSentencia=miConextion.prepareStatement("SELECT * FROM ACTIVOS_FIJOS_DET INNER JOIN TIPO_TRASPASO ON ACTIVOS_FIJOS_DET.TIPTRACOD=TIPO_TRASPASO.TIPTRACOD INNER JOIN RESPONSABLE ON ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI INNER JOIN OFICINA ON ACTIVOS_FIJOS_DET.OFICOD=OFICINA.OFICOD WHERE ACTCOD=?");
			PreparedStatement miSentencia=miConextion.prepareStatement("SELECT * FROM ACTIVOS_FIJOS_DET INNER JOIN TIPO_TRASPASO ON ACTIVOS_FIJOS_DET.TIPTRACOD=TIPO_TRASPASO.TIPTRACOD INNER JOIN OFICINA ON ACTIVOS_FIJOS_DET.OFICOD=OFICINA.OFICOD INNER JOIN RESPONSABLE ON ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI WHERE ACTCOD=?");

			miSentencia.setInt(1, Integer.parseInt(contenidoA));
			ResultSet miResulset=miSentencia.executeQuery();
			
			//4. RECORRER EL RESULSET

			DefaultTableModel model=new DefaultTableModel(new String [] {
					"MovCod","TipTraNom","ResNom","OfiNom","MovEstReg"
			},0);
			lblEstReg.setText("");
			txtActMovCod.setEnabled(false);
			comOfiNom.setEnabled(false);
			comResNom.setEnabled(false);
			comTipTraNom.setEnabled(false);
			//comUniNom.setEnabled(false);
			//comProNom.setEnabled(false);
			while(miResulset.next()){
				//System.out.println("DATOS");
				model.addRow(new Object[]{miResulset.getString("ACTMOVCOD"),miResulset.getString("TIPTRANOM"),miResulset.getString("RESNOM"),miResulset.getString("OFINOM"),miResulset.getString("ACTMOVESTREG")});
				//model.addRow(new Object[]{miResulset.getString("ACTMOVCOD"),miResulset.getString("ACTMOVESTREG")});

			}
			tabMov.setModel(model);
			//System.out.println(miResulset.getString("CIUNOM"));
			scrTabMov.setViewportView(tabMov);
			
			miSentencia.close();
			
			System.out.println("BD MOVIMIENTOS CARGADOS!!");
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD!!");
			e.printStackTrace();
		}
        
        btnAdicionar=new JButton("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdicionarActionPerformed(evt);
				txtActMovCod.setText(null);
				
				evento="adicionar";
				lblEstReg.setText("A");
				//txtActCod.setEditable(false);
				txtActMovCod.setEnabled(true);
				comOfiNom.setEnabled(true);
				comResNom.setEnabled(true);
				comTipTraNom.setEnabled(true);
				tabMov.clearSelection();
			}
		});
        btnModificar=new JButton("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnModificarActionPerformed(evt); 
				int row = tabMov.getSelectedRow();
				int column = tabMov.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(target.getValueAt(row, i));
                }
				 */
				txtActMovCod.setEnabled(false);
				comOfiNom.setEnabled(false);
				comResNom.setEnabled(false);
				comTipTraNom.setEnabled(false);
				if(!tabMov.getSelectionModel().isSelectionEmpty()){
					//txtActCod.setEditable(false);
					txtActMovCod.setEnabled(false);
					comOfiNom.setEnabled(true);
					comResNom.setEnabled(true);
					comTipTraNom.setEnabled(true);
					
					txtActMovCod.setText((String)tabMov.getValueAt(row, 0));
					comTipTraNom.setSelectedItem(tabMov.getValueAt(row, 1));
					comResNom.setSelectedItem(tabMov.getValueAt(row, 2));
					comOfiNom.setSelectedItem(tabMov.getValueAt(row, 3));
					lblEstReg.setText((String)tabMov.getValueAt(row, 4));
					evento="modificar";
					if(lblEstReg.getText().equals("*")){
						txtActMovCod.setEnabled(false);
						comOfiNom.setEnabled(false);
						comResNom.setEnabled(false);
						comTipTraNom.setEnabled(false);
						evento="";
					} 
				}
			}
		});
        btnEliminar=new JButton("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEliminarActionPerformed(evt);
				int row = tabMov.getSelectedRow();
				int column = tabMov.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(target.getValueAt(row, i));
                }
				 */
				txtActMovCod.setEnabled(false);
				comOfiNom.setEnabled(false);
				comResNom.setEnabled(false);
				comTipTraNom.setEnabled(false);
				if(!tabMov.getSelectionModel().isSelectionEmpty()){
					//txtActCod.setEditable(false);
					txtActMovCod.setEnabled(false);
					comOfiNom.setEnabled(false);
					comResNom.setEnabled(false);
					comTipTraNom.setEnabled(false);
					txtActMovCod.setText((String)tabMov.getValueAt(row, 0));
					comTipTraNom.setSelectedItem(tabMov.getValueAt(row, 1));
					comResNom.setSelectedItem(tabMov.getValueAt(row, 2));
					comOfiNom.setSelectedItem(tabMov.getValueAt(row, 3));
					lblEstReg.setText((String)tabMov.getValueAt(row, 4));
					evento="eliminar";
				}
			}
		});
        btnCancelar=new JButton("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
				txtActMovCod.setText(null);
				
				lblEstReg.setText("");
				//txtActCod.setEditable(false);
				txtActMovCod.setEnabled(false);
				comOfiNom.setEnabled(false);
				comResNom.setEnabled(false);
				comTipTraNom.setEnabled(false);
				evento="cancelar";
				tabMov.clearSelection();

			}
		});
        btnInactivar=new JButton("Inactivar");
        btnInactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInactivarActionPerformed(evt);
				int row = tabMov.getSelectedRow();
				int column = tabMov.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(target.getValueAt(row, i));
                }
				 */
				txtActMovCod.setEnabled(false);
				comOfiNom.setEnabled(false);
				comResNom.setEnabled(false);
				comTipTraNom.setEnabled(false);
				if(!tabMov.getSelectionModel().isSelectionEmpty()){
					//txtActCod.setEditable(false);
					txtActMovCod.setEnabled(false);
					comOfiNom.setEnabled(false);
					comResNom.setEnabled(false);
					comTipTraNom.setEnabled(false);
					txtActMovCod.setText((String)tabMov.getValueAt(row, 0));
					comTipTraNom.setSelectedItem(tabMov.getValueAt(row, 1));
					comResNom.setSelectedItem(tabMov.getValueAt(row, 2));
					comOfiNom.setSelectedItem(tabMov.getValueAt(row, 3));
					lblEstReg.setText((String)tabMov.getValueAt(row, 4));
					evento="inactivar";
					if(lblEstReg.getText().equals("*")){
						evento="";
						txtActMovCod.setEnabled(false);
						comOfiNom.setEnabled(false);
						comResNom.setEnabled(false);
						comTipTraNom.setEnabled(false);
					}
				}
			}
		});
        btnReactivar=new JButton("Reactivar");
        btnReactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReactivarActionPerformed(evt);
				int row = tabMov.getSelectedRow();
				int column = tabMov.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(target.getValueAt(row, i));
                }
				 */
				txtActMovCod.setEnabled(false);
				comOfiNom.setEnabled(false);
				comResNom.setEnabled(false);
				comTipTraNom.setEnabled(false);
				if(!tabMov.getSelectionModel().isSelectionEmpty()){
					//txtActCod.setEditable(false);
					txtActMovCod.setEnabled(false);
					comOfiNom.setEnabled(false);
					comResNom.setEnabled(false);
					comTipTraNom.setEnabled(false);
					
					txtActMovCod.setText((String)tabMov.getValueAt(row, 0));
					comTipTraNom.setSelectedItem(tabMov.getValueAt(row, 1));
					comResNom.setSelectedItem(tabMov.getValueAt(row, 2));
					comOfiNom.setSelectedItem(tabMov.getValueAt(row, 3));
					lblEstReg.setText((String)tabMov.getValueAt(row, 4));
					evento="reactivar";
				}
			}
		});
        btnActualizar=new JButton("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnActualizarActionPerformed(evt);
				//radBtnA.setActionCommand("A");
				//radBtnI.setActionCommand("I");
				//radBtnE.setActionCommand("*");
				//System.out.println("output"+" "+btnGrpResEstReg.getSelection().getActionCommand());

				switch (evento) {
				case "adicionar":  /*monthString = "January";*/
					int dni=0;
					if(txtActMovCod.getText().isEmpty())
						break;
					try {
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("SELECT RESDNI FROM RESPONSABLE WHERE RESNOM=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						//System.out.println((String)comResNom.getSelectedItem()+" ");
						miSentencia.setString(1, (String)comResNom.getSelectedItem());
						ResultSet miResulset=miSentencia.executeQuery();
						miResulset.next();
						//System.out.println(miResulset.getInt("RESDNI"));
						dni=miResulset.getInt("RESDNI");
						miResulset.close();
						
					} catch (Exception e) {
						System.out.println("no conectado RESPONSABLE");
						e.printStackTrace();
					}
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("INSERT INTO ACTIVOS_FIJOS_DET(ACTCOD,ACTMOVCOD,TIPTRACOD,RESDNI,OFICOD,ACTMOVESTREG) VALUES(?,?,?,?,?,'A')");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setInt(1, Integer.parseInt(contenidoA));
						miSentencia.setInt(2,Integer.parseInt(txtActMovCod.getText()));
						miSentencia.setInt(3, comTipTraNom.getSelectedIndex()+1);
						miSentencia.setInt(4, dni);
						miSentencia.setInt(5, comOfiNom.getSelectedIndex()+1);
					
						//System.out.println((String)(comUniNom.getSelectedItem()));
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtActMovCod.setText(null);
						//txtActNom.setText(null);
						System.out.println("Datos Adicionado!!");
					} catch (Exception e) {
						System.out.println("NO ADICIONADO!!");
						e.printStackTrace();
					}
					break;       
				case "modificar":  //monthString = "February";
					dni=0;
					try {
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("SELECT RESDNI FROM RESPONSABLE WHERE RESNOM=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						//System.out.println((String)comResNom.getSelectedItem()+" ");
						miSentencia.setString(1, (String)comResNom.getSelectedItem());
						ResultSet miResulset=miSentencia.executeQuery();
						miResulset.next();
						//System.out.println(miResulset.getInt("RESDNI"));
						dni=miResulset.getInt("RESDNI");
						miResulset.close();
						
					} catch (Exception e) {
						System.out.println("no conectado RESPONSABLE");
						e.printStackTrace();
					}
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE ACTIVOS_FIJOS_DET SET TIPTRACOD=?, RESDNI=?, OFICOD=?,ACTMOVESTREG=? WHERE ACTCOD=? AND ACTMOVCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						
						
						miSentencia.setInt(1, comTipTraNom.getSelectedIndex()+1);
						miSentencia.setInt(2, dni);
						miSentencia.setInt(3, comOfiNom.getSelectedIndex()+1);
						miSentencia.setString(4, lblEstReg.getText());
						miSentencia.setInt(5, Integer.parseInt(contenidoA));
						miSentencia.setInt(6,Integer.parseInt(txtActMovCod.getText()));
						
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtActMovCod.setText(null);
						//txtActNom.setText(null);
						System.out.println("Dato Modificado!!");
					} catch (Exception e) {
						System.out.println("NO MODIFICADO!!");
						e.printStackTrace();
					}
					break;
				case "eliminar":  //monthString = "March";
					/*
	                        int row = target.getSelectedRow();
	                        int column = target.getColumnCount();
	                        try {
	                        	//1. Crear CONEXIÓN
	                			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
	                			//2. PREPARAR CONSULTA
	                			PreparedStatement miSentencia=miConextion.prepareStatement("DELETE FROM PROVEEDOR WHERE PROCOD=?");
	                			//3. ESTABLECER LOS PARAMETROS DE CONSULTA
	                			String num2= (String)target.getValueAt(row, 0);
	                			int num=Integer.parseInt(num2);
	                			miSentencia.setInt(1,num);
	                			//4. EJECUTAR Y RECORRER CONSULTA
	                			miSentencia.execute();
	                			miSentencia.close();
	                			System.out.println("Dato Borrado Correctamente");
	                		} catch (Exception e) {
	                			System.out.println("NO BORRADO!!");
	                			e.printStackTrace();
	                		}
					 */

					int row = tabMov.getSelectedRow();
					int column = tabMov.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(target.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE ACTIVOS_FIJOS_DET SET ACTMOVESTREG=? WHERE ACTCOD=? AND ACTMOVCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "*");
						miSentencia.setInt(2, Integer.parseInt(contenidoA));
						int num =Integer.parseInt((String)tabMov.getValueAt(row, 0));
						miSentencia.setInt(3,num);
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtActMovCod.setText(null);
						//txtActNom.setText(null);
						System.out.println("Dato Eliminado!!!");
					} catch (Exception e) {
						System.out.println("NO ELIMINADO!!");
						e.printStackTrace();
					} 
					break;
				case "cancelar":  //monthString = "April";
					txtActMovCod.setText(null);
					//.setText(null);
					evento="";
					break;
				case "inactivar":  //monthString = "May";
					dni=0;
					try {
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("SELECT RESDNI FROM RESPONSABLE WHERE RESNOM=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						//System.out.println((String)comResNom.getSelectedItem()+" ");
						miSentencia.setString(1, (String)comResNom.getSelectedItem());
						ResultSet miResulset=miSentencia.executeQuery();
						miResulset.next();
						//System.out.println(miResulset.getInt("RESDNI"));
						dni=miResulset.getInt("RESDNI");
						miResulset.close();
						
					} catch (Exception e) {
						System.out.println("no conectado RESPONSABLE");
						e.printStackTrace();
					}
					row = tabMov.getSelectedRow();
					column = tabMov.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(target.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE ACTIVOS_FIJOS_DET SET ACTMOVESTREG=? WHERE ACTCOD=? AND ACTMOVCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "I");
						int num =Integer.parseInt((String)tabMov.getValueAt(row, 0));
						miSentencia.setInt(2, Integer.parseInt(contenidoA));
						miSentencia.setInt(3,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtActMovCod.setText(null);
						//txtActNom.setText(null);
						System.out.println("Dato Inactivado!!");
					} catch (Exception e) {
						System.out.println("NO Inactivado!!");
						e.printStackTrace();
					} 
					break;
				case "reactivar":  //monthString = "June";
					row = tabMov.getSelectedRow();
					column = tabMov.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(target.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE ACTIVOS_FIJOS_DET SET ACTMOVESTREG=? WHERE ACTCOD=? AND ACTMOVCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "A");
						int num =Integer.parseInt((String)tabMov.getValueAt(row, 0));
						miSentencia.setInt(2, Integer.parseInt(contenidoA));
						miSentencia.setInt(3,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtActMovCod.setText(null);
						//txtActNom.setText(null);

						System.out.println("Dato Reactivado!!");
					} catch (Exception e) {
						System.out.println("NO Reactivado!!");
						e.printStackTrace();
					} 
					break;
					/*
                    case "actualizar":  monthString = "July";
                             break;
					 */

					/*default: //monthString = "Invalid month"
                    	 try {
                         	//1. Crear CONEXIÓN
                 			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
                 			//2. CREAR OBJETO STATEMENT
                 			Statement miStatement=miConextion.createStatement();
                 			//3. EJECUTAR SQL
                 			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM PROVEEDOR");
                 			//4. RECORRER EL RESULSET

                 			DefaultTableModel model=new DefaultTableModel(new String [] {
             		                "ProCod", "ProNom", "ProEstReg"
                 			},0);
                 			while(miResulset.next()){
                 				model.addRow(new Object[]{miResulset.getString("PROCOD"),miResulset.getString("PRONOM"),miResulset.getString("PROESTREG")});
                 			}
                 			target.setModel(model);

                 		    jScrollPane3.setViewportView(target);

                 		    System.out.println("DATOS ACTUALIZADOS!!!");
                 		} catch (Exception e) {
                 			System.out.println("NO CONECTADO A BD!!");
                 			e.printStackTrace();
                 		}
                        break;
					 */
				}
				try {
					//1. Crear CONEXIÓN
					Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
					//2. CREAR OBJETO STATEMENT
					Statement miStatement=miConextion.createStatement();
					//3. EJECUTAR SQL
					//Responsable y ciudad
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM TIPO_TRASPASO");
					//4. RECORRER EL RESULSET
					comTipTraNom.removeAllItems();
					while(miResulset.next()){
						comTipTraNom.addItem(miResulset.getString("TIPTRANOM"));
					}
					//System.out.println(comCiuNom.getItemAt(1));
					//System.out.println("CONECTADO A BD TRASPASO!!");
				} catch (Exception e) {
					System.out.println("NO CONECTADO A BD TRASPASO!!");
					e.printStackTrace();
				}
				try {
					//1. Crear CONEXIÓN
					Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
					//2. CREAR OBJETO STATEMENT
					Statement miStatement=miConextion.createStatement();
					//3. EJECUTAR SQL
					//Responsable y ciudad
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM RESPONSABLE");
					//4. RECORRER EL RESULSET
					comResNom.removeAllItems();
					while(miResulset.next()){
						comResNom.addItem(miResulset.getString("RESNOM"));
					}
					//System.out.println(comCarNom.getItemAt(1));
					//System.out.println("CONECTADO A BD RESPON!!");
				} catch (Exception e) {
					System.out.println("NO CONECTADO A BD RESPON!!");
					e.printStackTrace();
				}
				try {
					//1. Crear CONEXIÓN
					Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
					//2. CREAR OBJETO STATEMENT
					Statement miStatement=miConextion.createStatement();
					//3. EJECUTAR SQL
					//Responsable y ciudad
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM OFICINA");
					//4. RECORRER EL RESULSET
					comOfiNom.removeAllItems();
					while(miResulset.next()){
						comOfiNom.addItem(miResulset.getString("OFINOM"));
					}
					//System.out.println(comCarNom.getItemAt(1));
					//System.out.println("CONECTADO A BD OFICINA!!");
					miResulset.close();
				} catch (Exception e) {
					System.out.println("NO CONECTADO A BD OFICINA!!");
					e.printStackTrace();
				}
				int dni=0;
				try {
					Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
					//2. PREPARAR CONSULTA
					PreparedStatement miSentencia=miConextion.prepareStatement("SELECT RESDNI FROM RESPONSABLE WHERE RESNOM=?");
					//3. ESTABLECER LOS PARAMETROS DE CONSULTA
					//System.out.println((String)comResNom.getSelectedItem()+" ");
					miSentencia.setString(1, (String)comResNom.getSelectedItem());
					ResultSet miResulset=miSentencia.executeQuery();
					miResulset.next();
					//System.out.println(miResulset.getInt("RESDNI"));
					dni=miResulset.getInt("RESDNI");
					miResulset.close();
					
				} catch (Exception e) {
					System.out.println("no conectado RESPONSABLE");
					e.printStackTrace();
				}
				try {
					//1. Crear CONEXIÓN
					Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
					//2. CREAR OBJETO STATEMENT
					Statement miStatement=miConextion.createStatement();
					//3. EJECUTAR SQL
					//Responsable y ciudad
					//ResultSet miResulset=miStatement.executeQuery("SELECT * FROM ACTIVOS_FIJOS_DET INNER JOIN TIPO_TRASPASO ON ACTIVOS_FIJOS_DET.TIPTRACOD=TIPO_TRASPASO.TIPTRACOD INNER JOIN RESPONSABLE ON ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI INNER JOIN OFICINA ON ACTIVOS_FIJOS_DET.OFICOD=OFICINA.OFICOD");
					
					//ResultSet miResulset=miStatement.executeQuery("SELECT * FROM ACTIVOS_FIJOS_DET INNER JOIN TIPO_TRASPASO ON ACTIVOS_FIJOS_DET.TIPTRACOD=TIPO_TRASPASO.TIPTRACOD INNER JOIN RESPONSABLE ON ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI INNER JOIN OFICINA ON ACTIVOS_FIJOS_DET.OFICOD=OFICINA.OFICOD WHERE ACTCOD=?");
					//PreparedStatement miSentencia=miConextion.prepareStatement("SELECT * FROM ACTIVOS_FIJOS_DET INNER JOIN TIPO_TRASPASO ON ACTIVOS_FIJOS_DET.TIPTRACOD=TIPO_TRASPASO.TIPTRACOD INNER JOIN RESPONSABLE ON ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI INNER JOIN OFICINA ON ACTIVOS_FIJOS_DET.OFICOD=OFICINA.OFICOD WHERE ACTCOD=?");
					PreparedStatement miSentencia=miConextion.prepareStatement("SELECT * FROM ACTIVOS_FIJOS_DET INNER JOIN TIPO_TRASPASO ON ACTIVOS_FIJOS_DET.TIPTRACOD=TIPO_TRASPASO.TIPTRACOD INNER JOIN OFICINA ON ACTIVOS_FIJOS_DET.OFICOD=OFICINA.OFICOD INNER JOIN RESPONSABLE ON ACTIVOS_FIJOS_DET.RESDNI=RESPONSABLE.RESDNI WHERE ACTCOD=?");

					miSentencia.setInt(1, Integer.parseInt(contenidoA));
					ResultSet miResulset=miSentencia.executeQuery();
					
					//4. RECORRER EL RESULSET

					DefaultTableModel model=new DefaultTableModel(new String [] {
							"MovCod","TipTraNom","ResNom","OfiNom","MovEstReg"
					},0);
					lblEstReg.setText("");
					txtActMovCod.setEnabled(false);
					comOfiNom.setEnabled(false);
					comResNom.setEnabled(false);
					comTipTraNom.setEnabled(false);
					//comUniNom.setEnabled(false);
					//comProNom.setEnabled(false);
					while(miResulset.next()){
						//System.out.println("DATOS");
						model.addRow(new Object[]{miResulset.getString("ACTMOVCOD"),miResulset.getString("TIPTRANOM"),miResulset.getString("RESNOM"),miResulset.getString("OFINOM"),miResulset.getString("ACTMOVESTREG")});
						//model.addRow(new Object[]{miResulset.getString("ACTMOVCOD"),miResulset.getString("ACTMOVESTREG")});

					}
					tabMov.setModel(model);
					//System.out.println(miResulset.getString("CIUNOM"));
					scrTabMov.setViewportView(tabMov);
					
					miSentencia.close();
					
					System.out.println("BD MOVIMIENTOS CARGADOS!!");
				} catch (Exception e) {
					System.out.println("NO CONECTADO A BD!!");
					e.printStackTrace();
				}
			}
		});
        btnSalir=new JButton("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSalirActionPerformed(evt);
				dispose();

			}
		}); 
    }
    
    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
 		// TODO add your handling code here:
 	}//GEN-LAST:event_btnAdicionarActionPerformed

 	private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
 		// TODO add your handling code here:
 	}//GEN-LAST:event_btnModificarActionPerformed

 	private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
 		// TODO add your handling code here:
 	}//GEN-LAST:event_btnEliminarActionPerformed

 	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
 		// TODO add your handling code here:
 	}//GEN-LAST:event_btnCancelarActionPerformed

 	private void btnInactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInactivarActionPerformed
 		// TODO add your handling code here:
 	}//GEN-LAST:event_btnInactivarActionPerformed

 	private void btnReactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReactivarActionPerformed
 		// TODO add your handling code here:
 	}//GEN-LAST:event_btnReactivarActionPerformed

 	private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
 		// TODO add your handling code here:
 	}//GEN-LAST:event_btnActualizarActionPerformed

 	private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
 		// TODO add your handling code here:
 	}//GEN-LAST:event_btnSalirActionPerformed
}
