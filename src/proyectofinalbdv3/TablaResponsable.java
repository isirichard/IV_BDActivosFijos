
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

public class TablaResponsable extends JFrame{
	String evento="";
	JComboBox<String> ventanas;
	JLabel lblResDni;
	JLabel lblResNom;
	JLabel lblCiuNom;
	JLabel lblCarNom;
	JLabel lblResEstReg;
	JTextField txtResDni;
	JTextField txtResNom;
	JComboBox<String> comCiuNom;
	JComboBox<String> comCarNom;
	JLabel lblEstReg;
	JScrollPane scrTabRes;
	DefaultTableModel dtm;
	JTable tabRes;
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
	JPanel panResDni;
	JPanel panResNom;
	JPanel panCiuNom;
	JPanel panCarNom;
	JPanel panResEstReg;

	public TablaResponsable() {
		initComponents();
		setTitle("LZ3001_RESPONSABLES");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 650);
		Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);

		setLayout(new BorderLayout());

		GridLayout griTop=new GridLayout(6,0);
		panArriba=new JPanel(griTop);

		GridLayout griBot=new GridLayout(2, 4);
		panAbajo=new JPanel(griBot);
		panVentanas=new JPanel(new FlowLayout(FlowLayout.CENTER));
		panResDni=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panResNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panCiuNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panCarNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panResEstReg=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panVentanas.add(ventanas);
		panResDni.add(lblResDni);
		panResDni.add(txtResDni);
		panResNom.add(lblResNom);
		panResNom.add(txtResNom);
		panCiuNom.add(lblCiuNom);
		panCiuNom.add(comCiuNom);
		panCarNom.add(lblCarNom);
		panCarNom.add(comCarNom);
		panResEstReg.add(lblResEstReg);
		panResEstReg.add(lblEstReg);

		panArriba.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panAbajo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		add(panArriba,BorderLayout.NORTH);
		add(scrTabRes,BorderLayout.CENTER);
		add(panAbajo,BorderLayout.SOUTH);

		panArriba.add(panVentanas);
		panArriba.add(panResDni);
		panArriba.add(panResNom);
		panArriba.add(panCiuNom);
		panArriba.add(panCarNom);
		panArriba.add(panResEstReg);

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
		final String []tabla={"Tabla Responsables","Tabla Activos Fijos","Tabla Cargos","Tabla Ciudades","Tabla Oficina",
				"Tabla Proveedores","Tabla Tipos de Traspaso","Tabla Unidad"};
		ventanas.setModel(new DefaultComboBoxModel<>(tabla));
		ventanas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JComboBox cb=(JComboBox)ae.getSource();
				String getItem=(String)cb.getSelectedItem();
				if(getItem.equals(tabla[0])){
					new TablaResponsable();
					dispose();
				}else if(getItem.equals(tabla[1])){
					new TablaActivoFijo();
					dispose();
				}else if(getItem.equals(tabla[2])){
					new TablaCargo();
					dispose();
				}else if(getItem.equals(tabla[3])){
					new TablaCiudad();
					dispose();
				}else if(getItem.equals(tabla[4])){
					new TablaOficina();
					dispose();
				}else if(getItem.equals(tabla[5])){
					new TablaProveedor();
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

		lblResDni=new JLabel("ResDni:       ");
		lblResNom=new JLabel("ResNom:    ");
		lblResEstReg=new JLabel("ResEstReg:");
		txtResDni=new JTextField(8);
		txtResNom=new JTextField(20);

		lblCiuNom=new JLabel("CiuNom:      ");
		comCiuNom=new JComboBox<>();
		//String []tabCiu={"Arequipa","Lima","Cuzco","Tacna"};
		//comCiuNom.setModel(new DefaultComboBoxModel<>(tabCiu));

		lblCarNom=new JLabel("CarNom:      ");
		comCarNom=new JComboBox<>();
		//String []tabCar={"Almacenero","Administrador","Contador","Dueño"};
		//comCarNom.setModel(new DefaultComboBoxModel<>(tabCar));

		lblEstReg=new JLabel("A");

		dtm=new DefaultTableModel();
		String[] titulo={"ResDni","ResNom","CiuNom","CarNom","ResEstReg"};
		dtm.setColumnIdentifiers(titulo);


		tabRes=new JTable();
		tabRes.setModel(dtm);

		scrTabRes=new JScrollPane();
		scrTabRes.setViewportView(tabRes);
		scrTabRes.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabRes.setBorder(new EmptyBorder(5, 5, 5, 5));
		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM CIUDAD");
			//4. RECORRER EL RESULSET
			comCiuNom.removeAllItems();
			while(miResulset.next()){
				comCiuNom.addItem(miResulset.getString("CIUNOM"));
			}
			//System.out.println(comCiuNom.getItemAt(1));
			System.out.println("CONECTADO A BD CIUDAD!!");
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD CIUDAD!!");
			e.printStackTrace();
		}
		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM CARGO");
			//4. RECORRER EL RESULSET
			comCarNom.removeAllItems();
			while(miResulset.next()){
				comCarNom.addItem(miResulset.getString("CARNOM"));
			}
			//System.out.println(comCarNom.getItemAt(1));
			System.out.println("CONECTADO A BD CARGO!!");
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD CARGO!!");
			e.printStackTrace();
		}
		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			//Responsable y ciudad
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM RESPONSABLE INNER JOIN CIUDAD ON RESPONSABLE.CIUCOD=CIUDAD.CIUCOD INNER JOIN CARGO ON RESPONSABLE.CARCOD=CARGO.CARCOD");
			//4. RECORRER EL RESULSET

			DefaultTableModel model=new DefaultTableModel(new String [] {
					"ResDni", "ResNom","CiuNom","CarNom", "ResEstReg"
			},0);
			lblEstReg.setText("");
			txtResDni.setEnabled(false);
			txtResNom.setEnabled(false);
			comCarNom.setEnabled(false);
			comCiuNom.setEnabled(false);
			while(miResulset.next()){
				model.addRow(new Object[]{miResulset.getString("RESDNI"),miResulset.getString("RESNOM"),miResulset.getString("CIUNOM"),miResulset.getString("CARNOM"),miResulset.getString("RESESTREG")});
			}
			tabRes.setModel(model);
			//System.out.println(miResulset.getString("CIUNOM"));
			scrTabRes.setViewportView(tabRes);
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD!!");
			e.printStackTrace();
		}


		btnAdicionar=new JButton("Adicionar");
		btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdicionarActionPerformed(evt);
				txtResDni.setText(null);
				txtResNom.setText(null);
				evento="adicionar";
				lblEstReg.setText("A");
				//txtResDni.setEditable(false);
				txtResDni.setEnabled(true);
				txtResNom.setEnabled(true);
				comCarNom.setEnabled(true);
				comCiuNom.setEnabled(true);
				tabRes.clearSelection();
			}
		});
		btnModificar=new JButton("Modificar");
		btnModificar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnModificarActionPerformed(evt); 
				int row = tabRes.getSelectedRow();
				int column = tabRes.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabRes.getValueAt(row, i));
                }
				 */
				txtResDni.setEnabled(false);
				txtResNom.setEnabled(false);
				comCarNom.setEnabled(false);
				comCiuNom.setEnabled(false);
				if(!tabRes.getSelectionModel().isSelectionEmpty()){
					//txtResDni.setEditable(false);
					txtResDni.setEnabled(false);
					txtResDni.setEnabled(true);
					txtResNom.setEnabled(true);
					comCarNom.setEnabled(true);
					comCiuNom.setEnabled(true);
					txtResDni.setText((String)tabRes.getValueAt(row, 0));
					txtResNom.setText((String)tabRes.getValueAt(row, 1));
					//comCiuNom.getSelectedItem();
					comCiuNom.setSelectedItem(tabRes.getValueAt(row, 2));
					//tabRes.getValueAt(row, 2)
					//tabRes.getValueAt(row, 3)
					comCarNom.setSelectedItem(tabRes.getValueAt(row, 3));
					lblEstReg.setText((String)tabRes.getValueAt(row, 4));
					evento="modificar";
					if(lblEstReg.getText().equals("*")){
						txtResDni.setEnabled(false);
						txtResNom.setEnabled(false);
						comCarNom.setEnabled(false);
						comCiuNom.setEnabled(false);
						evento="";
					} 
				}
			}
		});
		btnEliminar=new JButton("Eliminar");
		btnEliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEliminarActionPerformed(evt);
				int row = tabRes.getSelectedRow();
				int column = tabRes.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabRes.getValueAt(row, i));
                }
				 */
				txtResDni.setEnabled(false);
				txtResNom.setEnabled(false);
				comCarNom.setEnabled(false);
				comCiuNom.setEnabled(false);
				if(!tabRes.getSelectionModel().isSelectionEmpty()){
					//txtResDni.setEditable(false);
					txtResDni.setEnabled(false);
					txtResNom.setEnabled(false);
					comCarNom.setEnabled(false);
					comCiuNom.setEnabled(false);
					txtResDni.setText((String)tabRes.getValueAt(row, 0));
					txtResNom.setText((String)tabRes.getValueAt(row, 1));
					comCiuNom.setSelectedItem(tabRes.getValueAt(row, 2));
					comCarNom.setSelectedItem(tabRes.getValueAt(row, 3));
					lblEstReg.setText((String)tabRes.getValueAt(row, 4));
					evento="eliminar";
				}
			}
		});
		btnCancelar=new JButton("Cancelar");
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
				txtResDni.setText(null);
				txtResNom.setText(null);
				
				lblEstReg.setText("");
				//txtResDni.setEditable(false);
				txtResDni.setEnabled(false);
				txtResNom.setEnabled(false);
				comCarNom.setEnabled(false);
				comCiuNom.setEnabled(false);
				evento="cancelar";
				tabRes.clearSelection();

			}
		});
		btnInactivar=new JButton("Inactivar");
		btnInactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInactivarActionPerformed(evt);
				int row = tabRes.getSelectedRow();
				int column = tabRes.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabRes.getValueAt(row, i));
                }
				 */
				txtResDni.setEnabled(false);
				txtResNom.setEnabled(false);
				comCarNom.setEnabled(false);
				comCiuNom.setEnabled(false);
				if(!tabRes.getSelectionModel().isSelectionEmpty()){
					//txtResDni.setEditable(false);
					txtResDni.setEnabled(false);
					txtResNom.setEnabled(false);
					comCarNom.setEnabled(false);
					comCiuNom.setEnabled(false);
					txtResDni.setText((String)tabRes.getValueAt(row, 0));
					txtResNom.setText((String)tabRes.getValueAt(row, 1));
					comCiuNom.setSelectedItem(tabRes.getValueAt(row, 2));
					comCarNom.setSelectedItem(tabRes.getValueAt(row, 3));
					lblEstReg.setText((String)tabRes.getValueAt(row, 4));
					evento="inactivar";
					if(lblEstReg.getText().equals("*")){
						evento="";
						txtResDni.setEnabled(false);
						txtResNom.setEnabled(false);
						comCarNom.setEnabled(false);
						comCiuNom.setEnabled(false);
					}
				}
			}
		});
		btnReactivar=new JButton("Reactivar");
		btnReactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReactivarActionPerformed(evt);
				int row = tabRes.getSelectedRow();
				int column = tabRes.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabRes.getValueAt(row, i));
                }
				 */
				txtResDni.setEnabled(false);
				txtResNom.setEnabled(false);
				comCarNom.setEnabled(false);
				comCiuNom.setEnabled(false);
				if(!tabRes.getSelectionModel().isSelectionEmpty()){
					//txtResDni.setEditable(false);
					txtResDni.setEnabled(false);
					txtResNom.setEnabled(false);
					comCarNom.setEnabled(false);
					comCiuNom.setEnabled(false);
					txtResDni.setText((String)tabRes.getValueAt(row, 0));
					txtResNom.setText((String)tabRes.getValueAt(row, 1));
					comCiuNom.setSelectedItem(tabRes.getValueAt(row, 2));
					comCarNom.setSelectedItem(tabRes.getValueAt(row, 3));
					lblEstReg.setText((String)tabRes.getValueAt(row, 4));
					evento="reactivar";
				}
			}
		});
		btnActualizar=new JButton("Actualizar");
		btnActualizar.setText("Actualizar");
		btnActualizar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnActualizarActionPerformed(evt);
				//radBtnA.setActionCommand("A");
				//radBtnI.setActionCommand("I");
				//radBtnE.setActionCommand("*");
				//System.out.println("output"+" "+btnGrpResEstReg.getSelection().getActionCommand());

				switch (evento) {
				case "adicionar":  /*monthString = "January";*/
					if(txtResDni.getText().isEmpty()||txtResNom.getText().isEmpty())
						break;
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("INSERT INTO RESPONSABLE(RESDNI,RESNOM,RESUNI,RESESTREG,CIUCOD,CARCOD) VALUES(?,?,?,'A',?,?)");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setInt(1,Integer.parseInt(txtResDni.getText()));
						miSentencia.setString(2, txtResNom.getText());
						miSentencia.setString(3, "");
						miSentencia.setInt(4,comCiuNom.getSelectedIndex()+1);
						miSentencia.setInt(5,comCarNom.getSelectedIndex()+1);
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtResDni.setText(null);
						txtResNom.setText(null);
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
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE RESPONSABLE SET RESNOM=?, CIUCOD=?, CARCOD=?, RESESTREG=? WHERE RESDNI=?");
						//PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE RESPONSABLE INNER JOIN CIUDAD ON RESPONSABLE.CIUCOD=CIUDAD.CIUCOD INNER JOIN CARGO ON RESPONSABLE.CARCOD=CARGO.CARCOD SET RESNOM=?, CIUNOM=?, CARNOM=?, RESESTREG=? WHERE RESDNI=?");
						//SELECT * FROM RESPONSABLE INNER JOIN CIUDAD ON RESPONSABLE.CIUCOD=CIUDAD.CIUCOD INNER JOIN CARGO ON RESPONSABLE.CARCOD=CARGO.CARCOD
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						
						miSentencia.setString(1, txtResNom.getText());
						miSentencia.setInt(2, comCiuNom.getSelectedIndex()+1);
						miSentencia.setInt(3, comCarNom.getSelectedIndex()+1);
						miSentencia.setString(4, lblEstReg.getText());
						miSentencia.setInt(5,Integer.parseInt(txtResDni.getText()));
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtResDni.setText(null);
						txtResNom.setText(null);
						System.out.println("Dato Modificado!!");
					} catch (Exception e) {
						System.out.println("NO MODIFICADO!!");
						e.printStackTrace();
					}
					break;
				case "eliminar":  //monthString = "March";
					/*
	                        int row = tabRes.getSelectedRow();
	                        int column = tabRes.getColumnCount();
	                        try {
	                        	//1. Crear CONEXIÓN
	                			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
	                			//2. PREPARAR CONSULTA
	                			PreparedStatement miSentencia=miConextion.prepareStatement("DELETE FROM PROVEEDOR WHERE PROCOD=?");
	                			//3. ESTABLECER LOS PARAMETROS DE CONSULTA
	                			String num2= (String)tabRes.getValueAt(row, 0);
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

					int row = tabRes.getSelectedRow();
					int column = tabRes.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabRes.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE RESPONSABLE SET RESESTREG=? WHERE RESDNI=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "*");
						int num =Integer.parseInt((String)tabRes.getValueAt(row, 0));
						miSentencia.setInt(2,num);
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtResDni.setText(null);
						txtResNom.setText(null);
						System.out.println("Dato Eliminado!!!");
					} catch (Exception e) {
						System.out.println("NO ELIMINADO!!");
						e.printStackTrace();
					} 
					break;
				case "cancelar":  //monthString = "April";
					txtResDni.setText(null);
					txtResNom.setText(null);
					lblEstReg.setText("");
					evento="";
					break;
				case "inactivar":  //monthString = "May";
					row = tabRes.getSelectedRow();
					column = tabRes.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabRes.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE RESPONSABLE SET RESESTREG=? WHERE RESDNI=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "I");
						int num =Integer.parseInt((String)tabRes.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtResDni.setText(null);
						txtResNom.setText(null);
						System.out.println("Dato Inactivado!!");
					} catch (Exception e) {
						System.out.println("NO Inactivado!!");
						e.printStackTrace();
					} 
					break;
				case "reactivar":  //monthString = "June";
					row = tabRes.getSelectedRow();
					column = tabRes.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabRes.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE RESPONSABLE SET RESESTREG=? WHERE RESDNI=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "A");
						int num =Integer.parseInt((String)tabRes.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtResDni.setText(null);
						txtResNom.setText(null);

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
                 			tabRes.setModel(model);

                 		    jScrollPane3.setViewportView(tabRes);

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
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM CIUDAD");
					//4. RECORRER EL RESULSET
					comCiuNom.removeAllItems();
					while(miResulset.next()){
						comCiuNom.addItem(miResulset.getString("CIUNOM"));
					}
					//System.out.println(comCiuNom.getItemAt(1));
					System.out.println("CONECTADO A BD CIUDAD!!");
				} catch (Exception e) {
					System.out.println("NO CONECTADO A BD CIUDAD!!");
					e.printStackTrace();
				}
				try {
					//1. Crear CONEXIÓN
					Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
					//2. CREAR OBJETO STATEMENT
					Statement miStatement=miConextion.createStatement();
					//3. EJECUTAR SQL
					//Responsable y ciudad
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM CARGO");
					//4. RECORRER EL RESULSET
					comCarNom.removeAllItems();
					while(miResulset.next()){
						comCarNom.addItem(miResulset.getString("CARNOM"));
					}
					//System.out.println(comCarNom.getItemAt(1));
					System.out.println("CONECTADO A BD CARGO!!");
				} catch (Exception e) {
					System.out.println("NO CONECTADO A BD CARGO!!");
					e.printStackTrace();
				}
				try {
					//1. Crear CONEXIÓN
					Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
					//2. CREAR OBJETO STATEMENT
					Statement miStatement=miConextion.createStatement();
					//3. EJECUTAR SQL
					//Responsable y ciudad
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM RESPONSABLE INNER JOIN CIUDAD ON RESPONSABLE.CIUCOD=CIUDAD.CIUCOD INNER JOIN CARGO ON RESPONSABLE.CARCOD=CARGO.CARCOD");
					//4. RECORRER EL RESULSET

					DefaultTableModel model=new DefaultTableModel(new String [] {
							"ResDni", "ResNom","CiuNom","CarNom", "ResEstReg"
					},0);
					while(miResulset.next()){
						model.addRow(new Object[]{miResulset.getString("RESDNI"),miResulset.getString("RESNOM"),miResulset.getString("CIUNOM"),miResulset.getString("CARNOM"),miResulset.getString("RESESTREG")});
					}
					tabRes.setModel(model);

					scrTabRes.setViewportView(tabRes);
					evento="";
					txtResDni.setText("");
					txtResNom.setText("");
					lblEstReg.setText("");
					//txtResDni.setEditable(false);
					txtResDni.setEnabled(false);
					txtResNom.setEnabled(false);
					comCarNom.setEnabled(false);
					comCiuNom.setEnabled(false);
					System.out.println("DATOS ACTUALIZADOS (TABLA)!!!");
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
				System.exit(0);

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
