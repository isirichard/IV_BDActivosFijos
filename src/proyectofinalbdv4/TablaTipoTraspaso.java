
package proyectofinalbdv4;

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

public class TablaTipoTraspaso extends JFrame{
	String evento="";
    JComboBox<String> ventanas;
    JLabel lblTipTraCod;
    JLabel lblTipTraNom;
    JLabel lblTipTraEstReg;
    JTextField txtTipTraCod;
    JTextField txtTipTraNom;
    JLabel lblEstReg;
    JScrollPane scrTabTipTra;
    DefaultTableModel dtm;
    JTable tabTipTra;
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
    JPanel panTipTraCod;
    JPanel panTipTraNom;
    JPanel panTipTraEstReg;

    public TablaTipoTraspaso() {
        initComponents();
        setTitle("Tabla Tipos de Trapaso");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 650);
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);
        
        setLayout(new BorderLayout());
        
        GridLayout griTop=new GridLayout(4,0);
        panArriba=new JPanel(griTop);
                
        GridLayout griBot=new GridLayout(2, 4);
        panAbajo=new JPanel(griBot);
        panVentanas=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panTipTraCod=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panTipTraNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panTipTraEstReg=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panVentanas.add(ventanas);
        panTipTraCod.add(lblTipTraCod);
        panTipTraCod.add(txtTipTraCod);
        panTipTraNom.add(lblTipTraNom);
        panTipTraNom.add(txtTipTraNom);
        panTipTraEstReg.add(lblTipTraEstReg);
        panTipTraEstReg.add(lblEstReg);
        
        panArriba.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panAbajo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        add(panArriba,BorderLayout.NORTH);
        add(scrTabTipTra,BorderLayout.CENTER);
        add(panAbajo,BorderLayout.SOUTH);
        
        panArriba.add(panVentanas);
        panArriba.add(panTipTraCod);
        panArriba.add(panTipTraNom);
        panArriba.add(panTipTraEstReg);
        
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
        final String []tabla={"Tabla Tipos de Traspaso","Tabla Activos Fijos","Tabla Cargos","Tabla Ciudades","Tabla Oficina",
                "Tabla Proveedores","Tabla Responsables","Tabla Unidad"};
        ventanas.setModel(new DefaultComboBoxModel<>(tabla));
        ventanas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JComboBox cb=(JComboBox)ae.getSource();
                String getItem=(String)cb.getSelectedItem();
                if(getItem.equals(tabla[0])){
                    new TablaTipoTraspaso();
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
                    new TablaResponsable();
                    dispose();
                }else if(getItem.equals(tabla[7])){
                    new TablaUnidad();
                    dispose();
                }
            }
        });
        
        lblTipTraCod=new JLabel("TipTraCod:       ");
        lblTipTraNom=new JLabel("TipTraNom:    ");
        lblTipTraEstReg=new JLabel("TipTraEstReg:");
        txtTipTraCod=new JTextField(8);
        txtTipTraNom=new JTextField(20);
        lblEstReg=new JLabel("A");
        
        dtm=new DefaultTableModel();
        String[] titulo={"TipTraCod","TipTraNom","TipTraEstReg"};
        dtm.setColumnIdentifiers(titulo);
        
        
        tabTipTra=new JTable();
        tabTipTra.setModel(dtm);
        
        scrTabTipTra=new JScrollPane();
        scrTabTipTra.setViewportView(tabTipTra);
        scrTabTipTra.setBorder(new EmptyBorder(5, 5, 5, 5));
        tabTipTra.setBorder(new EmptyBorder(5, 5, 5, 5));
        try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM TIPO_TRASPASO");
			//4. RECORRER EL RESULSET

			DefaultTableModel model=new DefaultTableModel(new String [] {
					"TipTraCod", "TipTraNom", "TipTraEstReg"
			},0);
			while(miResulset.next()){
				model.addRow(new Object[]{miResulset.getString("TipTraCod"),miResulset.getString("TipTraNom"),miResulset.getString("TipTraEstReg")});
			}
			tabTipTra.setModel(model);

			scrTabTipTra.setViewportView(tabTipTra);
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD!!");
			e.printStackTrace();
		}
        
        
        btnAdicionar=new JButton("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdicionarActionPerformed(evt);
				txtTipTraCod.setText(null);
				txtTipTraNom.setText(null);
				evento="adicionar";
				lblEstReg.setText("A");
				//txtTipTraCod.setEditable(false);
				txtTipTraCod.setEnabled(true);
				txtTipTraNom.setEnabled(true);
				tabTipTra.clearSelection();
			}
		});
        btnModificar=new JButton("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnModificarActionPerformed(evt); 
				int row = tabTipTra.getSelectedRow();
				int column = tabTipTra.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabTipTra.getValueAt(row, i));
                }
				 */
				if(!tabTipTra.getSelectionModel().isSelectionEmpty()){
					//txtTipTraCod.setEditable(false);
					txtTipTraCod.setEnabled(false);
					txtTipTraCod.setText((String)tabTipTra.getValueAt(row, 0));
					txtTipTraNom.setText((String)tabTipTra.getValueAt(row, 1));
					lblEstReg.setText((String)tabTipTra.getValueAt(row, 2));
					evento="modificar";
					if(lblEstReg.getText().equals("*")){
						txtTipTraCod.setEnabled(false);
						txtTipTraNom.setEnabled(false);
						evento="";
					} 
				}
			}
		});
        btnEliminar=new JButton("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEliminarActionPerformed(evt);
				int row = tabTipTra.getSelectedRow();
				int column = tabTipTra.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabTipTra.getValueAt(row, i));
                }
				 */
				if(!tabTipTra.getSelectionModel().isSelectionEmpty()){
					//txtTipTraCod.setEditable(false);
					txtTipTraCod.setEnabled(false);
					txtTipTraNom.setEnabled(false);
					txtTipTraCod.setText((String)tabTipTra.getValueAt(row, 0));
					txtTipTraNom.setText((String)tabTipTra.getValueAt(row, 1));
					lblEstReg.setText((String)tabTipTra.getValueAt(row, 2));
					evento="eliminar";
				}
			}
		});
        btnCancelar=new JButton("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
				txtTipTraCod.setText(null);
				txtTipTraNom.setText(null);
				lblEstReg.setText("");
				//txtTipTraCod.setEditable(false);
				txtTipTraCod.setEnabled(true);
				txtTipTraNom.setEnabled(true);
				evento="cancelar";
				tabTipTra.clearSelection();

			}
		});
        btnInactivar=new JButton("Inactivar");
        btnInactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInactivarActionPerformed(evt);
				int row = tabTipTra.getSelectedRow();
				int column = tabTipTra.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabTipTra.getValueAt(row, i));
                }
				 */
				if(!tabTipTra.getSelectionModel().isSelectionEmpty()){
					//txtTipTraCod.setEditable(false);
					txtTipTraCod.setEnabled(false);
					txtTipTraNom.setEnabled(false);
					txtTipTraCod.setText((String)tabTipTra.getValueAt(row, 0));
					txtTipTraNom.setText((String)tabTipTra.getValueAt(row, 1));
					lblEstReg.setText((String)tabTipTra.getValueAt(row, 2));
					evento="inactivar";
					if(lblEstReg.getText().equals("*")){
						evento="";
						txtTipTraCod.setEnabled(false);
						txtTipTraNom.setEnabled(false);
					}
				}
			}
		});
        btnReactivar=new JButton("Reactivar");
        btnReactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReactivarActionPerformed(evt);
				int row = tabTipTra.getSelectedRow();
				int column = tabTipTra.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabTipTra.getValueAt(row, i));
                }
				 */
				if(!tabTipTra.getSelectionModel().isSelectionEmpty()){
					//txtTipTraCod.setEditable(false);
					txtTipTraCod.setEnabled(false);
					txtTipTraNom.setEnabled(false);
					txtTipTraCod.setText((String)tabTipTra.getValueAt(row, 0));
					txtTipTraNom.setText((String)tabTipTra.getValueAt(row, 1));
					lblEstReg.setText((String)tabTipTra.getValueAt(row, 2));
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
					if(txtTipTraCod.getText().isEmpty()||txtTipTraNom.getText().isEmpty())
						break;
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("INSERT INTO TIPO_TRASPASO(TipTraCod,TipTraNom,TipTraEstReg) VALUES(?,?,'A')");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setInt(1,Integer.parseInt(txtTipTraCod.getText()));
						miSentencia.setString(2, txtTipTraNom.getText());
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtTipTraCod.setText(null);
						txtTipTraNom.setText(null);
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
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE TIPO_TRASPASO SET TipTraNom=?, TipTraEstReg=? WHERE TipTraCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, txtTipTraNom.getText());
						miSentencia.setString(2, lblEstReg.getText());
						miSentencia.setInt(3,Integer.parseInt(txtTipTraCod.getText()));
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtTipTraCod.setText(null);
						txtTipTraNom.setText(null);
						System.out.println("Dato Modificado!!");
					} catch (Exception e) {
						System.out.println("NO MODIFICADO!!");
						e.printStackTrace();
					}
					break;
				case "eliminar":  //monthString = "March";
					/*
	                        int row = tabTipTra.getSelectedRow();
	                        int column = tabTipTra.getColumnCount();
	                        try {
	                        	//1. Crear CONEXIÓN
	                			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
	                			//2. PREPARAR CONSULTA
	                			PreparedStatement miSentencia=miConextion.prepareStatement("DELETE FROM TIPO_TRASPASO WHERE TipTraCod=?");
	                			//3. ESTABLECER LOS PARAMETROS DE CONSULTA
	                			String num2= (String)tabTipTra.getValueAt(row, 0);
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

					int row = tabTipTra.getSelectedRow();
					int column = tabTipTra.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabTipTra.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE TIPO_TRASPASO SET TipTraEstReg=? WHERE TipTraCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "*");
						int num =Integer.parseInt((String)tabTipTra.getValueAt(row, 0));
						miSentencia.setInt(2,num);
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtTipTraCod.setText(null);
						txtTipTraNom.setText(null);
						System.out.println("Dato Eliminado!!!");
					} catch (Exception e) {
						System.out.println("NO ELIMINADO!!");
						e.printStackTrace();
					} 
					break;
				case "cancelar":  //monthString = "April";
					txtTipTraCod.setText(null);
					txtTipTraNom.setText(null);
					evento="";
					break;
				case "inactivar":  //monthString = "May";
					row = tabTipTra.getSelectedRow();
					column = tabTipTra.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabTipTra.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE TIPO_TRASPASO SET TipTraEstReg=? WHERE TipTraCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "I");
						int num =Integer.parseInt((String)tabTipTra.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtTipTraCod.setText(null);
						txtTipTraNom.setText(null);
						System.out.println("Dato Inactivado!!");
					} catch (Exception e) {
						System.out.println("NO Inactivado!!");
						e.printStackTrace();
					} 
					break;
				case "reactivar":  //monthString = "June";
					row = tabTipTra.getSelectedRow();
					column = tabTipTra.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabTipTra.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE TIPO_TRASPASO SET TipTraEstReg=? WHERE TipTraCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "A");
						int num =Integer.parseInt((String)tabTipTra.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtTipTraCod.setText(null);
						txtTipTraNom.setText(null);

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
                 			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM TIPO_TRASPASO");
                 			//4. RECORRER EL RESULSET

                 			DefaultTableModel model=new DefaultTableModel(new String [] {
             		                "TipTraCod", "TipTraNom", "TipTraEstReg"
                 			},0);
                 			while(miResulset.next()){
                 				model.addRow(new Object[]{miResulset.getString("TipTraCod"),miResulset.getString("TipTraNom"),miResulset.getString("TipTraEstReg")});
                 			}
                 			tabTipTra.setModel(model);

                 		    jScrollPane3.setViewportView(tabTipTra);

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
					//Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
					Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
					//2. CREAR OBJETO STATEMENT
					Statement miStatement=miConextion.createStatement();
					//3. EJECUTAR SQL
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM TIPO_TRASPASO");
					//4. RECORRER EL RESULSET

					DefaultTableModel model=new DefaultTableModel(new String [] {
							"TipTraCod", "TipTraNom", "TipTraEstReg"
					},0);
					while(miResulset.next()){
						model.addRow(new Object[]{miResulset.getString("TipTraCod"),miResulset.getString("TipTraNom"),miResulset.getString("TipTraEstReg")});
					}
					tabTipTra.setModel(model);
					scrTabTipTra.setViewportView(tabTipTra);
					evento="";
					txtTipTraCod.setText("");
					txtTipTraNom.setText("");
					lblEstReg.setText("");
					//txtTipTraCod.setEditable(false);
					txtTipTraCod.setEnabled(true);
					txtTipTraNom.setEnabled(true);
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
