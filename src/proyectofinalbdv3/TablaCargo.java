
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

public class TablaCargo extends JFrame{
	String evento="";
    JComboBox<String> ventanas;
    JLabel lblCarCod;
    JLabel lblCarNom;
    JLabel lblCarEstReg;
    JTextField txtCarCod;
    JTextField txtCarNom;
    JLabel lblEstReg;
    JScrollPane scrTabCar;
    DefaultTableModel dtm;
    JTable tabCar;
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
    JPanel panCarCod;
    JPanel panCarNom;
    JPanel panCarEstReg;

    public TablaCargo() {
        initComponents();
        setTitle("LZ3001_CARGO");
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
        panCarCod=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panCarNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panCarEstReg=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panVentanas.add(ventanas);
        panCarCod.add(lblCarCod);
        panCarCod.add(txtCarCod);
        panCarNom.add(lblCarNom);
        panCarNom.add(txtCarNom);
        panCarEstReg.add(lblCarEstReg);
        panCarEstReg.add(lblEstReg);
        
        panArriba.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panAbajo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        add(panArriba,BorderLayout.NORTH);
        add(scrTabCar,BorderLayout.CENTER);
        add(panAbajo,BorderLayout.SOUTH);
        
        panArriba.add(panVentanas);
        panArriba.add(panCarCod);
        panArriba.add(panCarNom);
        panArriba.add(panCarEstReg);
        
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
        final String []tabla={"Tabla Cargos","Tabla Activos Fijos","Tabla Ciudades","Tabla Oficina",
                "Tabla Proveedores","Tabla Responsables","Tabla Tipos de Traspaso","Tabla Unidad"};
        ventanas.setModel(new DefaultComboBoxModel<>(tabla));
        ventanas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JComboBox cb=(JComboBox)ae.getSource();
                String getItem=(String)cb.getSelectedItem();
                if(getItem.equals(tabla[0])){
                    new TablaCargo();
                    dispose();
                }else if(getItem.equals(tabla[1])){
                    new TablaActivoFijo();
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
        
        lblCarCod=new JLabel("CarCod:       ");
        lblCarNom=new JLabel("CarNom:    ");
        lblCarEstReg=new JLabel("CarEstReg:");
        txtCarCod=new JTextField(8);
        txtCarNom=new JTextField(20);
        lblEstReg=new JLabel("A");
        
        dtm=new DefaultTableModel();
        String[] titulo={"CarCod","CarNom","CarEstReg"};
        dtm.setColumnIdentifiers(titulo);
        
        
        tabCar=new JTable();
        tabCar.setModel(dtm);
        
        scrTabCar=new JScrollPane();
        scrTabCar.setViewportView(tabCar);
        scrTabCar.setBorder(new EmptyBorder(5, 5, 5, 5));
        tabCar.setBorder(new EmptyBorder(5, 5, 5, 5));
        try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM CARGO");
			//4. RECORRER EL RESULSET

			DefaultTableModel model=new DefaultTableModel(new String [] {
					"CarCod", "CarNom", "CarEstReg"
			},0);
			txtCarCod.setEnabled(false);
			txtCarNom.setEnabled(false);
			while(miResulset.next()){
				model.addRow(new Object[]{miResulset.getString("CarCod"),miResulset.getString("CarNom"),miResulset.getString("CarEstReg")});
			}
			tabCar.setModel(model);

			scrTabCar.setViewportView(tabCar);
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD!!");
			e.printStackTrace();
		}
        btnAdicionar=new JButton("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdicionarActionPerformed(evt);
				txtCarCod.setText(null);
				txtCarNom.setText(null);
				evento="adicionar";
				lblEstReg.setText("A");
				//txtCarCod.setEditable(false);
				txtCarCod.setEnabled(true);
				txtCarNom.setEnabled(true);
				tabCar.clearSelection();
			}
		});
        btnModificar=new JButton("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnModificarActionPerformed(evt); 
				int row = tabCar.getSelectedRow();
				int column = tabCar.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabCar.getValueAt(row, i));
                }
				 */
				txtCarCod.setEnabled(false);
				txtCarNom.setEnabled(false);
				if(!tabCar.getSelectionModel().isSelectionEmpty()){
					//txtCarCod.setEditable(false);
					txtCarCod.setEnabled(false);
					txtCarNom.setEnabled(true);
					txtCarCod.setText((String)tabCar.getValueAt(row, 0));
					txtCarNom.setText((String)tabCar.getValueAt(row, 1));
					lblEstReg.setText((String)tabCar.getValueAt(row, 2));
					evento="modificar";
					if(lblEstReg.getText().equals("*")){
						txtCarCod.setEnabled(false);
						txtCarNom.setEnabled(false);
						evento="";
					} 
				}
			}
		});
        btnEliminar=new JButton("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEliminarActionPerformed(evt);
				int row = tabCar.getSelectedRow();
				int column = tabCar.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabCar.getValueAt(row, i));
                }
				 */
				txtCarCod.setEnabled(false);
				txtCarNom.setEnabled(false);
				if(!tabCar.getSelectionModel().isSelectionEmpty()){
					//txtCarCod.setEditable(false);
					txtCarCod.setEnabled(false);
					txtCarNom.setEnabled(false);
					txtCarCod.setText((String)tabCar.getValueAt(row, 0));
					txtCarNom.setText((String)tabCar.getValueAt(row, 1));
					lblEstReg.setText((String)tabCar.getValueAt(row, 2));
					evento="eliminar";
				}
			}
		});
        btnCancelar=new JButton("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
				txtCarCod.setText(null);
				txtCarNom.setText(null);
				lblEstReg.setText("");
				//txtCarCod.setEditable(false);
				txtCarCod.setEnabled(false);
				txtCarNom.setEnabled(false);
				evento="cancelar";
				tabCar.clearSelection();

			}
		});
        btnInactivar=new JButton("Inactivar");
        btnInactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInactivarActionPerformed(evt);
				int row = tabCar.getSelectedRow();
				int column = tabCar.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabCar.getValueAt(row, i));
                }
				 */
				txtCarCod.setEnabled(false);
				txtCarNom.setEnabled(false);
				if(!tabCar.getSelectionModel().isSelectionEmpty()){
					//txtCarCod.setEditable(false);
					txtCarCod.setEnabled(false);
					txtCarNom.setEnabled(false);
					txtCarCod.setText((String)tabCar.getValueAt(row, 0));
					txtCarNom.setText((String)tabCar.getValueAt(row, 1));
					lblEstReg.setText((String)tabCar.getValueAt(row, 2));
					evento="inactivar";
					if(lblEstReg.getText().equals("*")){
						evento="";
						txtCarCod.setEnabled(false);
						txtCarNom.setEnabled(false);
					}
				}
			}
		});
        btnReactivar=new JButton("Reactivar");
        btnReactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReactivarActionPerformed(evt);
				int row = tabCar.getSelectedRow();
				int column = tabCar.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabCar.getValueAt(row, i));
                }
				 */
				txtCarCod.setEnabled(false);
				txtCarNom.setEnabled(false);
				if(!tabCar.getSelectionModel().isSelectionEmpty()){
					//txtCarCod.setEditable(false);
					txtCarCod.setEnabled(false);
					txtCarNom.setEnabled(false);
					txtCarCod.setText((String)tabCar.getValueAt(row, 0));
					txtCarNom.setText((String)tabCar.getValueAt(row, 1));
					lblEstReg.setText((String)tabCar.getValueAt(row, 2));
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
					if(txtCarCod.getText().isEmpty()||txtCarNom.getText().isEmpty())
						break;
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("INSERT INTO CARGO(CarCod,CarNom,CarEstReg) VALUES(?,?,'A')");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setInt(1,Integer.parseInt(txtCarCod.getText()));
						miSentencia.setString(2, txtCarNom.getText());
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtCarCod.setText(null);
						txtCarNom.setText(null);
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
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE CARGO SET CarNom=?, CarEstReg=? WHERE CarCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, txtCarNom.getText());
						miSentencia.setString(2, lblEstReg.getText());
						miSentencia.setInt(3,Integer.parseInt(txtCarCod.getText()));
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtCarCod.setText(null);
						txtCarNom.setText(null);
						System.out.println("Dato Modificado!!");
					} catch (Exception e) {
						System.out.println("NO MODIFICADO!!");
						e.printStackTrace();
					}
					break;
				case "eliminar":  //monthString = "March";
					/*
	                        int row = tabCar.getSelectedRow();
	                        int column = tabCar.getColumnCount();
	                        try {
	                        	//1. Crear CONEXIÓN
	                			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
	                			//2. PREPARAR CONSULTA
	                			PreparedStatement miSentencia=miConextion.prepareStatement("DELETE FROM CARGO WHERE CarCod=?");
	                			//3. ESTABLECER LOS PARAMETROS DE CONSULTA
	                			String num2= (String)tabCar.getValueAt(row, 0);
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

					int row = tabCar.getSelectedRow();
					int column = tabCar.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabCar.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE CARGO SET CarEstReg=? WHERE CarCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "*");
						int num =Integer.parseInt((String)tabCar.getValueAt(row, 0));
						miSentencia.setInt(2,num);
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtCarCod.setText(null);
						txtCarNom.setText(null);
						System.out.println("Dato Eliminado!!!");
					} catch (Exception e) {
						System.out.println("NO ELIMINADO!!");
						e.printStackTrace();
					} 
					break;
				case "cancelar":  //monthString = "April";
					txtCarCod.setText(null);
					txtCarNom.setText(null);
					evento="";
					break;
				case "inactivar":  //monthString = "May";
					row = tabCar.getSelectedRow();
					column = tabCar.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabCar.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE CARGO SET CarEstReg=? WHERE CarCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "I");
						int num =Integer.parseInt((String)tabCar.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtCarCod.setText(null);
						txtCarNom.setText(null);
						System.out.println("Dato Inactivado!!");
					} catch (Exception e) {
						System.out.println("NO Inactivado!!");
						e.printStackTrace();
					} 
					break;
				case "reactivar":  //monthString = "June";
					row = tabCar.getSelectedRow();
					column = tabCar.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabCar.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE CARGO SET CarEstReg=? WHERE CarCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "A");
						int num =Integer.parseInt((String)tabCar.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtCarCod.setText(null);
						txtCarNom.setText(null);

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
                 			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM CARGO");
                 			//4. RECORRER EL RESULSET

                 			DefaultTableModel model=new DefaultTableModel(new String [] {
             		                "CarCod", "CarNom", "CarEstReg"
                 			},0);
                 			while(miResulset.next()){
                 				model.addRow(new Object[]{miResulset.getString("CarCod"),miResulset.getString("CarNom"),miResulset.getString("CarEstReg")});
                 			}
                 			tabCar.setModel(model);

                 		    jScrollPane3.setViewportView(tabCar);

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
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM CARGO");
					//4. RECORRER EL RESULSET

					DefaultTableModel model=new DefaultTableModel(new String [] {
							"CarCod", "CarNom", "CarEstReg"
					},0);
					while(miResulset.next()){
						model.addRow(new Object[]{miResulset.getString("CarCod"),miResulset.getString("CarNom"),miResulset.getString("CarEstReg")});
					}
					tabCar.setModel(model);
					scrTabCar.setViewportView(tabCar);
					evento="";
					txtCarCod.setText("");
					txtCarNom.setText("");
					lblEstReg.setText("");
					//txtCarCod.setEditable(false);
					txtCarCod.setEnabled(false);
					txtCarNom.setEnabled(false);
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
