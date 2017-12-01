
package conectaBD;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class LZ3001_Proveedor extends JFrame{

	JLabel lblResDni;
	JLabel lblResNom;
	JLabel lblResEstReg;
	JTextField txtProCod;
	JTextField txtProNom;
	JLabel lblEstReg;
	JScrollPane scrtabProveedores;
	JTable tabProveedores;
	JButton btnAdicionar;
	JButton btnModificar;
	JButton btnEliminar;
	JButton btnCancelar;
	JButton btnInactivar;
	JButton btnReactivar;
	JButton btnActualizar;
	JButton btnSalir;
	JPanel panArriba;
	JPanel panCentro;
	JPanel panAbajo;
	JPanel panDni;
	JPanel panNom;
	JPanel panEstReg;
	private String evento;

	public LZ3001_Proveedor() {
		initComponents();
		setTitle("L13001 Proveedor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 650);
		setVisible(true);
		setLayout(new BorderLayout());

		GridLayout griTop=new GridLayout(3,0);
		panArriba=new JPanel(griTop);
		panCentro=new JPanel();
		GridLayout griBot=new GridLayout(2, 4);
		panAbajo=new JPanel(griBot);
		panDni=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panNom=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panEstReg=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panDni.add(lblResDni);
		panDni.add(txtProCod);
		panNom.add(lblResNom);
		panNom.add(txtProNom);
		panEstReg.add(lblResEstReg);
		panEstReg.add(lblEstReg);

		panArriba.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panCentro.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panAbajo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		add(panArriba,BorderLayout.NORTH);
		add(panCentro,BorderLayout.CENTER);
		add(panAbajo,BorderLayout.SOUTH);


		panArriba.add(panDni);
		panArriba.add(panNom);
		panArriba.add(panEstReg);
		panCentro.add(scrtabProveedores);
		panAbajo.add(btnAdicionar);
		panAbajo.add(btnModificar);
		panAbajo.add(btnEliminar);
		panAbajo.add(btnCancelar);
		panAbajo.add(btnInactivar);
		panAbajo.add(btnReactivar);
		panAbajo.add(btnActualizar);
		panAbajo.add(btnSalir);
	}

	private void initComponents() {
		lblResDni=new JLabel("ProCod:       ");
		lblResNom=new JLabel("ProNom:    ");
		lblResEstReg=new JLabel("ProEstReg:");
		txtProCod=new JTextField(8);
		txtProNom=new JTextField(20);
		lblEstReg=new JLabel("");
		tabProveedores=new JTable();
		scrtabProveedores=new JScrollPane();
		evento="";

		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
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
			tabProveedores.setModel(model);

			scrtabProveedores.setViewportView(tabProveedores);
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD!!");
			e.printStackTrace();
		}

		//scrtabProveedores.setViewportView(tabProveedores);

		btnAdicionar=new JButton("Adicionar");
		btnModificar=new JButton("Modificar");
		btnEliminar=new JButton("Eliminar");
		btnCancelar=new JButton("Cancelar");
		btnInactivar=new JButton("Inactivar");
		btnReactivar=new JButton("Reactivar");
		btnActualizar=new JButton("Actualizar");
		btnSalir=new JButton("Salir");


		btnAdicionar.setText("Adicionar");
		btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdicionarActionPerformed(evt);
				txtProCod.setText(null);
				txtProNom.setText(null);
				evento="adicionar";
				lblEstReg.setText("A");
				//txtProCod.setEditable(false);
				txtProCod.setEnabled(true);
				txtProNom.setEnabled(true);
				tabProveedores.clearSelection();
			}
		});


		btnModificar.setText("Modificar");
		btnModificar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnModificarActionPerformed(evt); 
				int row = tabProveedores.getSelectedRow();
				int column = tabProveedores.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabProveedores.getValueAt(row, i));
                }
				 */
				if(!tabProveedores.getSelectionModel().isSelectionEmpty()){
					//txtProCod.setEditable(false);
					txtProCod.setEnabled(false);
					txtProCod.setText((String)tabProveedores.getValueAt(row, 0));
					txtProNom.setText((String)tabProveedores.getValueAt(row, 1));
					lblEstReg.setText((String)tabProveedores.getValueAt(row, 2));
					evento="modificar";
					if(lblEstReg.getText().equals("*")){
						txtProCod.setEnabled(false);
						txtProNom.setEnabled(false);
						evento="";
					} 
				}
			}
		});

		btnEliminar.setText("Eliminar");
		btnEliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEliminarActionPerformed(evt);
				int row = tabProveedores.getSelectedRow();
				int column = tabProveedores.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabProveedores.getValueAt(row, i));
                }
				 */
				if(!tabProveedores.getSelectionModel().isSelectionEmpty()){
					//txtProCod.setEditable(false);
					txtProCod.setEnabled(false);
					txtProNom.setEnabled(false);
					txtProCod.setText((String)tabProveedores.getValueAt(row, 0));
					txtProNom.setText((String)tabProveedores.getValueAt(row, 1));
					lblEstReg.setText((String)tabProveedores.getValueAt(row, 2));
					evento="eliminar";
				}
			}
		});

		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
				txtProCod.setText(null);
				txtProNom.setText(null);
				lblEstReg.setText("");
				//txtProCod.setEditable(false);
				txtProCod.setEnabled(true);
				txtProNom.setEnabled(true);
				evento="cancelar";
				tabProveedores.clearSelection();

			}
		});

		btnInactivar.setText("Inactivar");
		btnInactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInactivarActionPerformed(evt);
				int row = tabProveedores.getSelectedRow();
				int column = tabProveedores.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabProveedores.getValueAt(row, i));
                }
				 */
				if(!tabProveedores.getSelectionModel().isSelectionEmpty()){
					//txtProCod.setEditable(false);
					txtProCod.setEnabled(false);
					txtProNom.setEnabled(false);
					txtProCod.setText((String)tabProveedores.getValueAt(row, 0));
					txtProNom.setText((String)tabProveedores.getValueAt(row, 1));
					lblEstReg.setText((String)tabProveedores.getValueAt(row, 2));
					evento="inactivar";
					if(lblEstReg.getText().equals("*")){
						evento="";
						txtProCod.setEnabled(false);
						txtProNom.setEnabled(false);
					}
				}
			}
		});

		btnReactivar.setText("Reactivar");
		btnReactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReactivarActionPerformed(evt);
				int row = tabProveedores.getSelectedRow();
				int column = tabProveedores.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabProveedores.getValueAt(row, i));
                }
				 */
				if(!tabProveedores.getSelectionModel().isSelectionEmpty()){
					//txtProCod.setEditable(false);
					txtProCod.setEnabled(false);
					txtProNom.setEnabled(false);
					txtProCod.setText((String)tabProveedores.getValueAt(row, 0));
					txtProNom.setText((String)tabProveedores.getValueAt(row, 1));
					lblEstReg.setText((String)tabProveedores.getValueAt(row, 2));
					evento="reactivar";
				}
			}
		});

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
					if(txtProCod.getText().isEmpty()||txtProNom.getText().isEmpty())
						break;
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("INSERT INTO PROVEEDOR(PROCOD,PRONOM,PROESTREG) VALUES(?,?,'A')");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setInt(1,Integer.parseInt(txtProCod.getText()));
						miSentencia.setString(2, txtProNom.getText());
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtProCod.setText(null);
						txtProNom.setText(null);
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
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE PROVEEDOR SET PRONOM=?, PROESTREG=? WHERE PROCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, txtProNom.getText());
						miSentencia.setString(2, lblEstReg.getText());
						miSentencia.setInt(3,Integer.parseInt(txtProCod.getText()));
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtProCod.setText(null);
						txtProNom.setText(null);
						System.out.println("Dato Modificado!!");
					} catch (Exception e) {
						System.out.println("NO MODIFICADO!!");
						e.printStackTrace();
					}
					break;
				case "eliminar":  //monthString = "March";
					/*
	                        int row = tabProveedores.getSelectedRow();
	                        int column = tabProveedores.getColumnCount();
	                        try {
	                        	//1. Crear CONEXIÓN
	                			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
	                			//2. PREPARAR CONSULTA
	                			PreparedStatement miSentencia=miConextion.prepareStatement("DELETE FROM PROVEEDOR WHERE PROCOD=?");
	                			//3. ESTABLECER LOS PARAMETROS DE CONSULTA
	                			String num2= (String)tabProveedores.getValueAt(row, 0);
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

					int row = tabProveedores.getSelectedRow();
					int column = tabProveedores.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabProveedores.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE PROVEEDOR SET PROESTREG=? WHERE PROCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "*");
						int num =Integer.parseInt((String)tabProveedores.getValueAt(row, 0));
						miSentencia.setInt(2,num);
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtProCod.setText(null);
						txtProNom.setText(null);
						System.out.println("Dato Eliminado!!!");
					} catch (Exception e) {
						System.out.println("NO ELIMINADO!!");
						e.printStackTrace();
					} 
					break;
				case "cancelar":  //monthString = "April";
					txtProCod.setText(null);
					txtProNom.setText(null);
					evento="";
					break;
				case "inactivar":  //monthString = "May";
					row = tabProveedores.getSelectedRow();
					column = tabProveedores.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabProveedores.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE PROVEEDOR SET PROESTREG=? WHERE PROCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "I");
						int num =Integer.parseInt((String)tabProveedores.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtProCod.setText(null);
						txtProNom.setText(null);
						System.out.println("Dato Inactivado!!");
					} catch (Exception e) {
						System.out.println("NO Inactivado!!");
						e.printStackTrace();
					} 
					break;
				case "reactivar":  //monthString = "June";
					row = tabProveedores.getSelectedRow();
					column = tabProveedores.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabProveedores.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE PROVEEDOR SET PROESTREG=? WHERE PROCOD=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "A");
						int num =Integer.parseInt((String)tabProveedores.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtProCod.setText(null);
						txtProNom.setText(null);

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
                 			tabProveedores.setModel(model);

                 		    jScrollPane3.setViewportView(tabProveedores);

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
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM PROVEEDOR");
					//4. RECORRER EL RESULSET

					DefaultTableModel model=new DefaultTableModel(new String [] {
							"ProCod", "ProNom", "ProEstReg"
					},0);
					while(miResulset.next()){
						model.addRow(new Object[]{miResulset.getString("PROCOD"),miResulset.getString("PRONOM"),miResulset.getString("PROESTREG")});
					}
					tabProveedores.setModel(model);
					scrtabProveedores.setViewportView(tabProveedores);
					evento="";
					txtProCod.setText("");
					txtProNom.setText("");
					lblEstReg.setText("");
					//txtProCod.setEditable(false);
					txtProCod.setEnabled(true);
					txtProNom.setEnabled(true);
					System.out.println("DATOS ACTUALIZADOS (TABLA)!!!");
				} catch (Exception e) {
					System.out.println("NO CONECTADO A BD (TABLA)!!");
					e.printStackTrace();
				}
			}
		});

		btnSalir.setText("Salir");
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

	public static void main(String[] args) {
		new LZ3001_Proveedor();
	}
}
