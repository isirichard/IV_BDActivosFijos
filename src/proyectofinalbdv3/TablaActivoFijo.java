
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.TableModel;

public class TablaActivoFijo extends JFrame{
	String evento="";
	//JTable target;
    JComboBox<String> ventanas;
    JLabel lblActCod;
    JTextField txtActCod;
    JLabel lblActNom;
    JTextField txtActNom;
    JLabel lblActDes;
    JTextField txtActDes;
    JLabel lblActNumSer;
    JTextField txtActNumSer;
    JLabel lblActFac;
    JTextField txtActFac;
    JLabel lblActCueCon;
    JTextField txtActCueCon;
    JLabel lblActFecAni;
    JTextField txtActFecAni;
    JLabel lblActFecMes;
    JTextField txtActFecMes;
    JLabel lblActFecDia;
    JTextField txtActFecDia;
    JLabel lblUniNom;
    JComboBox<String> comUniNom;
    JLabel lblProNom;
    JComboBox<String> comProNom;
    JLabel lblActEstReg;
    JLabel lblEstReg;
    
    JScrollPane scrTabAct;
    DefaultTableModel dtm;
    JTable tabAct;
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
    JPanel panVentanas;
    JPanel panActCod;
    JPanel panActNom;
    JPanel panActDes;
    JPanel panActNumSer;
    JPanel panActFac;
    JPanel panActCueCon;
    JPanel panActFec;
    JPanel panUniNom;
    JPanel panProNom;
    JPanel panEstReg;
    

    public TablaActivoFijo() {
        initComponents();
        setTitle("LZ3001_MAESTRO_ACTIVOS_FIJOS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);
        
        setLayout(new BorderLayout());
        
        GridLayout griTop=new GridLayout(11,0);
        panArriba=new JPanel(griTop);
                
        GridLayout griBot=new GridLayout(2, 4);
        panAbajo=new JPanel(griBot);
        panVentanas=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panActCod=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panActNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panActDes=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panActNumSer=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panActFac=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panActCueCon=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panActFec=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panUniNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panProNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panEstReg=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panVentanas.add(ventanas);
        panActCod.add(lblActCod);
        panActCod.add(txtActCod);
        panActNom.add(lblActNom);
        panActNom.add(txtActNom);
        panActDes.add(lblActDes);
        panActDes.add(txtActDes);
        panActNumSer.add(lblActNumSer);
        panActNumSer.add(txtActNumSer);
        panActFac.add(lblActFac);
        panActFac.add(txtActFac);
        panActCueCon.add(lblActCueCon);
        panActCueCon.add(txtActCueCon);
        panActFec.add(lblActFecAni);
        panActFec.add(txtActFecAni);
        panActFec.add(lblActFecMes);
        panActFec.add(txtActFecMes);
        panActFec.add(lblActFecDia);
        panActFec.add(txtActFecDia);
        panUniNom.add(lblUniNom);
        panUniNom.add(comUniNom);
        panProNom.add(lblProNom);
        panProNom.add(comProNom);
        
        panEstReg.add(lblActEstReg);
        panEstReg.add(lblEstReg);
        
        panArriba.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panAbajo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        add(panArriba,BorderLayout.NORTH);
        add(scrTabAct,BorderLayout.CENTER);
        add(panAbajo,BorderLayout.SOUTH);
        
        panArriba.add(panVentanas);
        panArriba.add(panActCod);
        panArriba.add(panActNom);
        panArriba.add(panActDes);
        panArriba.add(panActNumSer);
        panArriba.add(panActFac);
        panArriba.add(panActCueCon);
        panArriba.add(panActFec);
        panArriba.add(panUniNom);
        panArriba.add(panProNom);
        panArriba.add(panEstReg);
        
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
        
    	ventanas=new JComboBox<>();
        final String []tabla={"Tabla Activos Fijos","Tabla Cargos","Tabla Ciudades","Tabla Oficina",
                "Tabla Proveedores","Tabla Responsables","Tabla Tipos de Traspaso","Tabla Unidad"};
        ventanas.setModel(new DefaultComboBoxModel<>(tabla));
        ventanas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JComboBox cb=(JComboBox)ae.getSource();
                String getItem=(String)cb.getSelectedItem();
                if(getItem.equals(tabla[0])){
                    new TablaActivoFijo();
                    dispose();
                }else if(getItem.equals(tabla[1])){
                    new TablaCargo();
                    dispose();
                }else if(getItem.equals(tabla[2])){
                    new TablaCiudad();
                    dispose();
                }else if(getItem.equals(tabla[3])){
                    new TablaOficina();
                    dispose();
                }else if(getItem.equals(tabla[4])){
                    new TablaProveedor();
                    dispose();
                }else if(getItem.equals(tabla[5])){
                    new TablaResponsable();
                    dispose();
                }else if(getItem.equals(tabla[6])){
                    new TablaTipoTraspaso();
                    dispose();
                }else if(getItem.equals(tabla[7])){
                    new TablaUnidad();
                    dispose();
                }
            }
        });
        
        lblActCod=new JLabel("ActCod:        ");
        txtActCod=new JTextField(2);
        lblActNom=new JLabel("ActNom:       ");
        txtActNom=new JTextField(20);
        lblActDes=new JLabel("ActDes:        ");
        txtActDes=new JTextField(20);
        lblActNumSer=new JLabel("ActNumSer:");
        txtActNumSer=new JTextField(6);
        lblActFac=new JLabel("ActFac:        ");
        txtActFac=new JTextField(6);
        lblActCueCon=new JLabel("ActCueCon:");
        txtActCueCon=new JTextField(20);
        lblActFecAni=new JLabel("ActFec:        ");
        txtActFecAni=new JTextField(2);
        lblActFecMes=new JLabel("/");
        txtActFecMes=new JTextField(2);
        lblActFecDia=new JLabel("/");
        txtActFecDia=new JTextField(2);
        
        lblActEstReg=new JLabel("ResEstReg:");
        lblEstReg=new JLabel("A");
        
        lblUniNom=new JLabel("UniNom:      ");
        comUniNom=new JComboBox<>();
        String []tabUniNom={"Cajas","Cilindros","Bolsas","Bidones","Litros","Kilos"};
        comUniNom.setModel(new DefaultComboBoxModel<>(tabUniNom));
        
        lblProNom=new JLabel("ProNom:     ");
        comProNom=new JComboBox<>();
        String []tabProNom={"Ferreteria","Botica","Farmacia","Contructora","Laboratorio"};
        comProNom.setModel(new DefaultComboBoxModel<>(tabProNom));
        
        dtm=new DefaultTableModel();
        String[] titulo={"ActCod","ActNom","ActDes","ActNumSer","ActFac","ActCueCon","ActFec","ActEstReg"};
        dtm.setColumnIdentifiers(titulo);
        
        
        tabAct=new JTable();
        tabAct.setModel(dtm);
        
        //Permite que la tabla pueda selecionarse pero no se pueda editar
        tabAct.setDefaultEditor(Object.class,null);
        
        tabAct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if(me.getClickCount()==2){
                	JTable target=(JTable)me.getSource();
                    int row=target.getSelectedRow();
                    
                    TableModel tableModel=target.getModel();
                    
                    StringBuffer sb=new StringBuffer();
                    sb.append(tableModel.getValueAt(row, 0));
                    TablaMovimiento tblMov=new TablaMovimiento(sb.toString());
                    tblMov.setVisible(true);
                }
            }
            
        });
        
        scrTabAct=new JScrollPane();
        scrTabAct.setViewportView(tabAct);
        scrTabAct.setBorder(new EmptyBorder(5, 5, 5, 5));
        tabAct.setBorder(new EmptyBorder(5, 5, 5, 5));
        try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM UNIDAD");
			//4. RECORRER EL RESULSET
			comUniNom.removeAllItems();
			while(miResulset.next()){
				comUniNom.addItem(miResulset.getString("UNINOM"));
			}
			//System.out.println(comCiuNom.getItemAt(1));
			//System.out.println("CONECTADO A BD UNIDAD!!");
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD UNIDAD!!");
			e.printStackTrace();
		}
		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM PROVEEDOR");
			//4. RECORRER EL RESULSET
			comProNom.removeAllItems();
			while(miResulset.next()){
				comProNom.addItem(miResulset.getString("PRONOM"));
			}
			//System.out.println(comCarNom.getItemAt(1));
			//System.out.println("CONECTADO A BD PROVEE!!");
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD PROVEE!!");
			e.printStackTrace();
		}
		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM ACTIVOS_FIJOS_CAB INNER JOIN UNIDAD ON ACTIVOS_FIJOS_CAB.UNICOD=UNIDAD.UNICOD INNER JOIN PROVEEDOR ON ACTIVOS_FIJOS_CAB.PROCOD=PROVEEDOR.PROCOD");
			//4. RECORRER EL RESULSET

			DefaultTableModel model=new DefaultTableModel(new String [] {
					"ActCod","ActNom","ActDes","ActNumSer","ActFac","ActCueCon","ActFec","Unidad","Proveedor","ActEstReg"
			},0);
			lblEstReg.setText("");
			txtActCod.setEnabled(false);
			txtActNom.setEnabled(false);
			txtActDes.setEnabled(false);
			txtActNumSer.setEnabled(false);
			txtActFac.setEnabled(false);
			txtActCueCon.setEnabled(false);
			txtActFecAni.setEnabled(false);
			txtActFecMes.setEnabled(false);
			txtActFecDia.setEnabled(false);
			comUniNom.setEnabled(false);
			comProNom.setEnabled(false);
			//comUniNom.setEnabled(false);
			//comProNom.setEnabled(false);
			while(miResulset.next()){
				model.addRow(new Object[]{miResulset.getString("ACTCOD"),miResulset.getString("ACTNOM"),miResulset.getString("ACTDES"),miResulset.getString("ACTNUMSERIE"),miResulset.getString("ACTFAC"),miResulset.getString("ACTCUECON")
						,miResulset.getString("ACTFECAÑO")+"/"+miResulset.getString("ACTFECMES")+"/"+miResulset.getString("ACTFECDIA"),miResulset.getString("UNINOM"),miResulset.getString("PRONOM"),miResulset.getString("ACTESTREG")});
			}
			tabAct.setModel(model);
			//System.out.println(miResulset.getString("CIUNOM"));
			scrTabAct.setViewportView(tabAct);
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD!!");
			e.printStackTrace();
		}
        
        btnAdicionar=new JButton("Adicionar");
        /*
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dtm.addRow(new String[]{"01","Angel","Choquehuanca"});
            }
        });
        */
		btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdicionarActionPerformed(evt);
				txtActCod.setText(null);
				txtActNom.setText(null);
				txtActDes.setText(null);
				txtActNumSer.setText(null);
				txtActFac.setText(null);
				txtActCueCon.setText(null);
				txtActFecAni.setText(null);
				txtActFecMes.setText(null);
				txtActFecDia.setText(null);
				
				evento="adicionar";
				lblEstReg.setText("A");
				//txtActCod.setEditable(false);
				txtActCod.setEnabled(true);
				txtActNom.setEnabled(true);
				txtActDes.setEnabled(true);
				txtActNumSer.setEnabled(true);
				txtActFac.setEnabled(true);
				txtActCueCon.setEnabled(true);
				txtActFecAni.setEnabled(true);
				txtActFecMes.setEnabled(true);
				txtActFecDia.setEnabled(true);
				comUniNom.setEnabled(true);
				comProNom.setEnabled(true);
				tabAct.clearSelection();
			}
		});
        
        btnModificar=new JButton("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnModificarActionPerformed(evt); 
				int row = tabAct.getSelectedRow();
				int column = tabAct.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(target.getValueAt(row, i));
                }
				 */
				txtActCod.setEnabled(false);
				txtActNom.setEnabled(false);
				txtActDes.setEnabled(false);
				txtActNumSer.setEnabled(false);
				txtActFac.setEnabled(false);
				txtActCueCon.setEnabled(false);
				txtActFecAni.setEnabled(false);
				txtActFecMes.setEnabled(false);
				txtActFecDia.setEnabled(false);
				comUniNom.setEnabled(false);
				comProNom.setEnabled(false);
				if(!tabAct.getSelectionModel().isSelectionEmpty()){
					//txtActCod.setEditable(false);
					txtActCod.setEnabled(false);
					txtActNom.setEnabled(true);
					txtActDes.setEnabled(true);
					txtActNumSer.setEnabled(true);
					txtActFac.setEnabled(true);
					txtActCueCon.setEnabled(true);
					txtActFecAni.setEnabled(true);
					txtActFecMes.setEnabled(true);
					txtActFecDia.setEnabled(true);
					comUniNom.setEnabled(true);
					comProNom.setEnabled(true);
					txtActCod.setText((String)tabAct.getValueAt(row, 0));
					txtActNom.setText((String)tabAct.getValueAt(row, 1));
					txtActDes.setText((String)tabAct.getValueAt(row, 2));
					txtActNumSer.setText((String)tabAct.getValueAt(row, 3));
					txtActFac.setText((String)tabAct.getValueAt(row, 4));
					txtActCueCon.setText((String)tabAct.getValueAt(row, 5));
					String fec = (String)tabAct.getValueAt(row, 6);
					String[] parts = fec.split("/");
					txtActFecAni.setText(parts[0]);
					txtActFecMes.setText(parts[1]);
					txtActFecDia.setText(parts[2]);
					comUniNom.setSelectedItem(tabAct.getValueAt(row, 7));
					comProNom.setSelectedItem(tabAct.getValueAt(row, 8));
					lblEstReg.setText((String)tabAct.getValueAt(row, 9));
					evento="modificar";
					if(lblEstReg.getText().equals("*")){
						txtActCod.setEnabled(false);
						txtActNom.setEnabled(false);
						txtActDes.setEnabled(false);
						txtActNumSer.setEnabled(false);
						txtActFac.setEnabled(false);
						txtActCueCon.setEnabled(false);
						txtActFecAni.setEnabled(false);
						txtActFecMes.setEnabled(false);
						txtActFecDia.setEnabled(false);
						comUniNom.setEnabled(false);
						comProNom.setEnabled(false);
						evento="";
					} 
				}
			}
		});
        
        
        btnEliminar=new JButton("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEliminarActionPerformed(evt);
				int row = tabAct.getSelectedRow();
				int column = tabAct.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(target.getValueAt(row, i));
                }
				 */
				txtActCod.setEnabled(false);
				txtActNom.setEnabled(false);
				txtActDes.setEnabled(false);
				txtActNumSer.setEnabled(false);
				txtActFac.setEnabled(false);
				txtActCueCon.setEnabled(false);
				txtActFecAni.setEnabled(false);
				txtActFecMes.setEnabled(false);
				txtActFecDia.setEnabled(false);
				comUniNom.setEnabled(false);
				comProNom.setEnabled(false);
				if(!tabAct.getSelectionModel().isSelectionEmpty()){
					//txtActCod.setEditable(false);
					txtActCod.setEnabled(false);
					txtActNom.setEnabled(false);
					txtActDes.setEnabled(false);
					txtActNumSer.setEnabled(false);
					txtActFac.setEnabled(false);
					txtActCueCon.setEnabled(false);
					txtActFecAni.setEnabled(false);
					txtActFecMes.setEnabled(false);
					txtActFecDia.setEnabled(false);
					comUniNom.setEnabled(false);
					comProNom.setEnabled(false);
					txtActCod.setText((String)tabAct.getValueAt(row, 0));
					txtActNom.setText((String)tabAct.getValueAt(row, 1));
					txtActDes.setText((String)tabAct.getValueAt(row, 2));
					txtActNumSer.setText((String)tabAct.getValueAt(row, 3));
					txtActFac.setText((String)tabAct.getValueAt(row, 4));
					txtActCueCon.setText((String)tabAct.getValueAt(row, 5));
					String fec = (String)tabAct.getValueAt(row, 6);
					String[] parts = fec.split("/");
					txtActFecAni.setText(parts[0]);
					txtActFecMes.setText(parts[1]);
					txtActFecDia.setText(parts[2]);
					comUniNom.setSelectedItem(tabAct.getValueAt(row, 7));
					comProNom.setSelectedItem(tabAct.getValueAt(row, 8));
					lblEstReg.setText((String)tabAct.getValueAt(row, 9));
					evento="eliminar";
				}
			}
		});
        
        btnCancelar=new JButton("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
				txtActCod.setText(null);
				txtActNom.setText(null);
				txtActDes.setText(null);
				txtActNumSer.setText(null);
				txtActFac.setText(null);
				txtActCueCon.setText(null);
				txtActFecAni.setText(null);
				txtActFecMes.setText(null);
				txtActFecDia.setText(null);
				
				lblEstReg.setText("");
				//txtActCod.setEditable(false);
				txtActCod.setEnabled(false);
				txtActNom.setEnabled(false);
				txtActDes.setEnabled(false);
				txtActNumSer.setEnabled(false);
				txtActFac.setEnabled(false);
				txtActCueCon.setEnabled(false);
				txtActFecAni.setEnabled(false);
				txtActFecMes.setEnabled(false);
				txtActFecDia.setEnabled(false);
				comUniNom.setEnabled(false);
				comProNom.setEnabled(false);
				evento="cancelar";
				tabAct.clearSelection();

			}
		});
        
        
        btnInactivar=new JButton("Inactivar");
        btnInactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInactivarActionPerformed(evt);
				int row = tabAct.getSelectedRow();
				int column = tabAct.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(target.getValueAt(row, i));
                }
				 */
				txtActCod.setEnabled(false);
				txtActNom.setEnabled(false);
				txtActDes.setEnabled(false);
				txtActNumSer.setEnabled(false);
				txtActFac.setEnabled(false);
				txtActCueCon.setEnabled(false);
				txtActFecAni.setEnabled(false);
				txtActFecMes.setEnabled(false);
				txtActFecDia.setEnabled(false);
				comUniNom.setEnabled(false);
				comProNom.setEnabled(false);
				if(!tabAct.getSelectionModel().isSelectionEmpty()){
					//txtActCod.setEditable(false);
					txtActCod.setEnabled(false);
					txtActNom.setEnabled(false);
					txtActDes.setEnabled(false);
					txtActNumSer.setEnabled(false);
					txtActFac.setEnabled(false);
					txtActCueCon.setEnabled(false);
					txtActFecAni.setEnabled(false);
					txtActFecMes.setEnabled(false);
					txtActFecDia.setEnabled(false);
					comUniNom.setEnabled(false);
					comProNom.setEnabled(false);
					txtActCod.setText((String)tabAct.getValueAt(row, 0));
					txtActNom.setText((String)tabAct.getValueAt(row, 1));
					txtActDes.setText((String)tabAct.getValueAt(row, 2));
					txtActNumSer.setText((String)tabAct.getValueAt(row, 3));
					txtActFac.setText((String)tabAct.getValueAt(row, 4));
					txtActCueCon.setText((String)tabAct.getValueAt(row, 5));
					String fec = (String)tabAct.getValueAt(row, 6);
					String[] parts = fec.split("/");
					txtActFecAni.setText(parts[0]);
					txtActFecMes.setText(parts[1]);
					txtActFecDia.setText(parts[2]);
					comUniNom.setSelectedItem(tabAct.getValueAt(row, 7));
					comProNom.setSelectedItem(tabAct.getValueAt(row, 8));
					lblEstReg.setText((String)tabAct.getValueAt(row, 9));
					evento="inactivar";
					if(lblEstReg.getText().equals("*")){
						evento="";
						txtActCod.setEnabled(false);
						txtActNom.setEnabled(false);
						txtActDes.setEnabled(false);
						txtActNumSer.setEnabled(false);
						txtActFac.setEnabled(false);
						txtActCueCon.setEnabled(false);
						txtActFecAni.setEnabled(false);
						txtActFecMes.setEnabled(false);
						txtActFecDia.setEnabled(false);
						comUniNom.setEnabled(false);
						comProNom.setEnabled(false);
					}
				}
			}
		});
        
        
        btnReactivar=new JButton("Reactivar");
        btnReactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReactivarActionPerformed(evt);
				int row = tabAct.getSelectedRow();
				int column = tabAct.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(target.getValueAt(row, i));
                }
				 */
				txtActCod.setEnabled(false);
				txtActNom.setEnabled(false);
				txtActDes.setEnabled(false);
				txtActNumSer.setEnabled(false);
				txtActFac.setEnabled(false);
				txtActCueCon.setEnabled(false);
				txtActFecAni.setEnabled(false);
				txtActFecMes.setEnabled(false);
				txtActFecDia.setEnabled(false);
				comUniNom.setEnabled(false);
				comProNom.setEnabled(false);
				if(!tabAct.getSelectionModel().isSelectionEmpty()){
					//txtActCod.setEditable(false);
					txtActCod.setEnabled(false);
					txtActNom.setEnabled(false);
					txtActDes.setEnabled(false);
					txtActNumSer.setEnabled(false);
					txtActFac.setEnabled(false);
					txtActCueCon.setEnabled(false);
					txtActFecAni.setEnabled(false);
					txtActFecMes.setEnabled(false);
					txtActFecDia.setEnabled(false);
					comUniNom.setEnabled(false);
					comProNom.setEnabled(false);
					txtActCod.setText((String)tabAct.getValueAt(row, 0));
					txtActNom.setText((String)tabAct.getValueAt(row, 1));
					txtActDes.setText((String)tabAct.getValueAt(row, 2));
					txtActNumSer.setText((String)tabAct.getValueAt(row, 3));
					txtActFac.setText((String)tabAct.getValueAt(row, 4));
					txtActCueCon.setText((String)tabAct.getValueAt(row, 5));
					String fec = (String)tabAct.getValueAt(row, 6);
					String[] parts = fec.split("/");
					txtActFecAni.setText(parts[0]);
					txtActFecMes.setText(parts[1]);
					txtActFecDia.setText(parts[2]);
					comUniNom.setSelectedItem(tabAct.getValueAt(row, 7));
					comProNom.setSelectedItem(tabAct.getValueAt(row, 8));
					lblEstReg.setText((String)tabAct.getValueAt(row, 9));
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
					if(txtActCod.getText().isEmpty()||txtActNom.getText().isEmpty())
						break;
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("INSERT INTO ACTIVOS_FIJOS_CAB(ACTCOD,ACTNOM,ACTDES,ACTNUMSERIE,ACTFAC,ACTCUECON,ACTFECAÑO,ACTFECMES,ACTFECDIA,ACTESTREG,UNICOD,PROCOD) VALUES(?,?,?,?,?,?,?,?,?,'A',?,?)");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setInt(1,Integer.parseInt(txtActCod.getText()));
						miSentencia.setString(2, txtActNom.getText());
						miSentencia.setString(3, txtActDes.getText());
						miSentencia.setString(4, txtActNumSer.getText());
						miSentencia.setInt(5, Integer.parseInt(txtActFac.getText()));
						miSentencia.setInt(6, Integer.parseInt(txtActCueCon.getText()));
						miSentencia.setInt(7, Integer.parseInt(txtActFecAni.getText()));
						miSentencia.setInt(8, Integer.parseInt(txtActFecMes.getText()));
						miSentencia.setInt(9, Integer.parseInt(txtActFecDia.getText()));
						miSentencia.setInt(10, comUniNom.getSelectedIndex()+1);
						miSentencia.setInt(11, comProNom.getSelectedIndex()+1);
						System.out.println((String)(comUniNom.getSelectedItem()));
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtActCod.setText(null);
						txtActNom.setText(null);
						System.out.println("Datos Adicionado!!");
					} catch (Exception e) {
						System.out.println("NO ADICIONADO!!");
						e.printStackTrace();
					}
					break;       
				case "modificar":  //monthString = "February";
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE ACTIVOS_FIJOS_CAB SET ACTNOM=?, ACTDES=?, ACTNUMSERIE=?, ACTFAC=?, ACTCUECON=?, ACTFECAÑO=?, ACTFECMES=?, ACTFECDIA=?, ACTESTREG=?, UNICOD=?, PROCOD=? WHERE ACTCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, txtActNom.getText());
						miSentencia.setString(2, txtActDes.getText());
						miSentencia.setString(3, txtActNumSer.getText());
						miSentencia.setInt(4, Integer.parseInt(txtActFac.getText()));
						miSentencia.setInt(5, Integer.parseInt(txtActCueCon.getText()));
						miSentencia.setInt(6, Integer.parseInt(txtActFecAni.getText()));
						miSentencia.setInt(7, Integer.parseInt(txtActFecMes.getText()));
						miSentencia.setInt(8, Integer.parseInt(txtActFecDia.getText()));
						miSentencia.setString(9, lblEstReg.getText());
						miSentencia.setInt(10, comUniNom.getSelectedIndex()+1);
						miSentencia.setInt(11, comProNom.getSelectedIndex()+1);
						
						miSentencia.setInt(12,Integer.parseInt(txtActCod.getText()));
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtActCod.setText(null);
						txtActNom.setText(null);
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

					int row = tabAct.getSelectedRow();
					int column = tabAct.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(target.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE ACTIVOS_FIJOS_CAB SET ACTESTREG=? WHERE ACTCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "*");
						int num =Integer.parseInt((String)tabAct.getValueAt(row, 0));
						miSentencia.setInt(2,num);
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtActCod.setText(null);
						txtActNom.setText(null);
						System.out.println("Dato Eliminado!!!");
					} catch (Exception e) {
						System.out.println("NO ELIMINADO!!");
						e.printStackTrace();
					} 
					break;
				case "cancelar":  //monthString = "April";
					txtActCod.setText(null);
					txtActNom.setText(null);
					evento="";
					break;
				case "inactivar":  //monthString = "May";
					row = tabAct.getSelectedRow();
					column = tabAct.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(target.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE ACTIVOS_FIJOS_CAB SET ACTESTREG=? WHERE ACTCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "I");
						int num =Integer.parseInt((String)tabAct.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtActCod.setText(null);
						txtActNom.setText(null);
						System.out.println("Dato Inactivado!!");
					} catch (Exception e) {
						System.out.println("NO Inactivado!!");
						e.printStackTrace();
					} 
					break;
				case "reactivar":  //monthString = "June";
					row = tabAct.getSelectedRow();
					column = tabAct.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(target.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE ACTIVOS_FIJOS_CAB SET ACTESTREG=? WHERE ACTCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "A");
						int num =Integer.parseInt((String)tabAct.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtActCod.setText(null);
						txtActNom.setText(null);

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
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM UNIDAD");
					//4. RECORRER EL RESULSET
					comUniNom.removeAllItems();
					while(miResulset.next()){
						comUniNom.addItem(miResulset.getString("UNINOM"));
					}
					//System.out.println(comCiuNom.getItemAt(1));
					//System.out.println("CONECTADO A BD UNIDAD!!");
				} catch (Exception e) {
					System.out.println("NO CONECTADO A BD UNIDAD!!");
					e.printStackTrace();
				}
				try {
					//1. Crear CONEXIÓN
					Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
					//2. CREAR OBJETO STATEMENT
					Statement miStatement=miConextion.createStatement();
					//3. EJECUTAR SQL
					//Responsable y ciudad
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM PROVEEDOR");
					//4. RECORRER EL RESULSET
					comProNom.removeAllItems();
					while(miResulset.next()){
						comProNom.addItem(miResulset.getString("PRONOM"));
					}
					//System.out.println(comCarNom.getItemAt(1));
					//System.out.println("CONECTADO A BD PROVEE!!");
				} catch (Exception e) {
					System.out.println("NO CONECTADO A BD PROVEE!!");
					e.printStackTrace();
				}
				try {
					//1. Crear CONEXIÓN
					//Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
					Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
					//2. CREAR OBJETO STATEMENT
					Statement miStatement=miConextion.createStatement();
					//3. EJECUTAR SQL
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM ACTIVOS_FIJOS_CAB INNER JOIN UNIDAD ON ACTIVOS_FIJOS_CAB.UNICOD=UNIDAD.UNICOD INNER JOIN PROVEEDOR ON ACTIVOS_FIJOS_CAB.PROCOD=PROVEEDOR.PROCOD");
					//4. RECORRER EL RESULSET

					DefaultTableModel model=new DefaultTableModel(new String [] {
							"ActCod","ActNom","ActDes","ActNumSer","ActFac","ActCueCon","ActFec","Unidad","Proveedor","ActEstReg"
					},0);
					while(miResulset.next()){
						model.addRow(new Object[]{miResulset.getString("ACTCOD"),miResulset.getString("ACTNOM"),miResulset.getString("ACTDES"),miResulset.getString("ACTNUMSERIE"),miResulset.getString("ACTFAC"),miResulset.getString("ACTCUECON")
								,miResulset.getString("ACTFECAÑO")+"/"+miResulset.getString("ACTFECMES")+"/"+miResulset.getString("ACTFECDIA"),miResulset.getString("UNINOM"),miResulset.getString("PRONOM"),miResulset.getString("ACTESTREG")});
					}
					tabAct.setModel(model);
					//scrtarget.setViewportView(target);
					evento="";
					txtActCod.setText("");
					txtActNom.setText("");
					txtActDes.setText("");
					txtActNumSer.setText("");
					txtActFac.setText("");
					txtActCueCon.setText("");
					txtActFecAni.setText("");
					txtActFecMes.setText("");
					txtActFecDia.setText("");
					lblEstReg.setText("");
					//txtActCod.setEditable(false);
					txtActCod.setEnabled(false);
					txtActNom.setEnabled(false);
					txtActDes.setEnabled(false);
					txtActNumSer.setEnabled(false);
					txtActFac.setEnabled(false);
					txtActCueCon.setEnabled(false);
					txtActFecAni.setEnabled(false);
					txtActFecMes.setEnabled(false);
					txtActFecDia.setEnabled(false);
					comUniNom.setEnabled(false);
					comProNom.setEnabled(false);
					System.out.println("DATOS ACTUALIZADOS (TABLA)!!!");
				} catch (Exception e) {
					System.out.println("NO CONECTADO A BD (TABLA)!!");
					e.printStackTrace();
				}
			}
		});
        btnSalir=new JButton("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSalirActionPerformed(evt);
				System.exit(0);

			}
		});

        
        
        
        /*
        try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM ACTIVOS_FIJOS_CAB");
			//4. RECORRER EL RESULSET

			DefaultTableModel model=new DefaultTableModel(new String [] {
					"ProCod", "ProNom", "ProEstReg"
			},0);
			while(miResulset.next()){
				model.addRow(new Object[]{miResulset.getString("PROCOD"),miResulset.getString("PRONOM"),miResulset.getString("PROESTREG")});
			}
			target.setModel(model);

			scrtarget.setViewportView(target);
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD!!");
			e.printStackTrace();
		}
		*/
        
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
    public static void main(String[] args) {
        new TablaActivoFijo();
    }
}
