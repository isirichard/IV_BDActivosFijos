
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

public class LZ3001_Oficina extends JFrame{

	JLabel lblResDni;
	JLabel lblResNom;
	JLabel lblResEstReg;
	JTextField txtOfiCod;
	JTextField txtOfiNom;
	JLabel lblEstReg;
	JScrollPane scrtabOficinas;
	JTable tabOficinas;
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

	public LZ3001_Oficina() {
		initComponents();
		setTitle("L13001 Oficina");
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
		panDni.add(txtOfiCod);
		panNom.add(lblResNom);
		panNom.add(txtOfiNom);
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
		panCentro.add(scrtabOficinas);
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
		lblResDni=new JLabel("OfiCod:       ");
		lblResNom=new JLabel("OfiNom:    ");
		lblResEstReg=new JLabel("OfiEstReg:");
		txtOfiCod=new JTextField(8);
		txtOfiNom=new JTextField(20);
		lblEstReg=new JLabel("");
		tabOficinas=new JTable();
		scrtabOficinas=new JScrollPane();
		evento="";

		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM Oficina");
			//4. RECORRER EL RESULSET

			DefaultTableModel model=new DefaultTableModel(new String [] {
					"OfiCod", "OfiNom", "OfiEstReg"
			},0);
			while(miResulset.next()){
				model.addRow(new Object[]{miResulset.getString("OfiCod"),miResulset.getString("OfiNom"),miResulset.getString("OfiEstReg")});
			}
			tabOficinas.setModel(model);

			scrtabOficinas.setViewportView(tabOficinas);
		} catch (Exception e) {
			System.out.println("NO CONECTADO A BD!!");
			e.printStackTrace();
		}

		//scrtabOficinas.setViewportView(tabOficinas);

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
				txtOfiCod.setText(null);
				txtOfiNom.setText(null);
				evento="adicionar";
				lblEstReg.setText("A");
				//txtOfiCod.setEditable(false);
				txtOfiCod.setEnabled(true);
				txtOfiNom.setEnabled(true);
				tabOficinas.clearSelection();
			}
		});


		btnModificar.setText("Modificar");
		btnModificar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnModificarActionPerformed(evt); 
				int row = tabOficinas.getSelectedRow();
				int column = tabOficinas.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabOficinas.getValueAt(row, i));
                }
				 */
				if(!tabOficinas.getSelectionModel().isSelectionEmpty()){
					//txtOfiCod.setEditable(false);
					txtOfiCod.setEnabled(false);
					txtOfiCod.setText((String)tabOficinas.getValueAt(row, 0));
					txtOfiNom.setText((String)tabOficinas.getValueAt(row, 1));
					lblEstReg.setText((String)tabOficinas.getValueAt(row, 2));
					evento="modificar";
					if(lblEstReg.getText().equals("*")){
						txtOfiCod.setEnabled(false);
						txtOfiNom.setEnabled(false);
						evento="";
					} 
				}
			}
		});

		btnEliminar.setText("Eliminar");
		btnEliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEliminarActionPerformed(evt);
				int row = tabOficinas.getSelectedRow();
				int column = tabOficinas.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabOficinas.getValueAt(row, i));
                }
				 */
				if(!tabOficinas.getSelectionModel().isSelectionEmpty()){
					//txtOfiCod.setEditable(false);
					txtOfiCod.setEnabled(false);
					txtOfiNom.setEnabled(false);
					txtOfiCod.setText((String)tabOficinas.getValueAt(row, 0));
					txtOfiNom.setText((String)tabOficinas.getValueAt(row, 1));
					lblEstReg.setText((String)tabOficinas.getValueAt(row, 2));
					evento="eliminar";
				}
			}
		});

		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
				txtOfiCod.setText(null);
				txtOfiNom.setText(null);
				lblEstReg.setText("");
				//txtOfiCod.setEditable(false);
				txtOfiCod.setEnabled(true);
				txtOfiNom.setEnabled(true);
				evento="cancelar";
				tabOficinas.clearSelection();

			}
		});

		btnInactivar.setText("Inactivar");
		btnInactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInactivarActionPerformed(evt);
				int row = tabOficinas.getSelectedRow();
				int column = tabOficinas.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabOficinas.getValueAt(row, i));
                }
				 */
				if(!tabOficinas.getSelectionModel().isSelectionEmpty()){
					//txtOfiCod.setEditable(false);
					txtOfiCod.setEnabled(false);
					txtOfiNom.setEnabled(false);
					txtOfiCod.setText((String)tabOficinas.getValueAt(row, 0));
					txtOfiNom.setText((String)tabOficinas.getValueAt(row, 1));
					lblEstReg.setText((String)tabOficinas.getValueAt(row, 2));
					evento="inactivar";
					if(lblEstReg.getText().equals("*")){
						evento="";
						txtOfiCod.setEnabled(false);
						txtOfiNom.setEnabled(false);
					}
				}
			}
		});

		btnReactivar.setText("Reactivar");
		btnReactivar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReactivarActionPerformed(evt);
				int row = tabOficinas.getSelectedRow();
				int column = tabOficinas.getColumnCount();
				lblEstReg.setText("");
				/*
                for(int i = 0; i < column; i++) {
                    System.out.println(tabOficinas.getValueAt(row, i));
                }
				 */
				if(!tabOficinas.getSelectionModel().isSelectionEmpty()){
					//txtOfiCod.setEditable(false);
					txtOfiCod.setEnabled(false);
					txtOfiNom.setEnabled(false);
					txtOfiCod.setText((String)tabOficinas.getValueAt(row, 0));
					txtOfiNom.setText((String)tabOficinas.getValueAt(row, 1));
					lblEstReg.setText((String)tabOficinas.getValueAt(row, 2));
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
					if(txtOfiCod.getText().isEmpty()||txtOfiNom.getText().isEmpty())
						break;
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("INSERT INTO Oficina(OfiCod,OfiNom,OfiEstReg) VALUES(?,?,'A')");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setInt(1,Integer.parseInt(txtOfiCod.getText()));
						miSentencia.setString(2, txtOfiNom.getText());
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtOfiCod.setText(null);
						txtOfiNom.setText(null);
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
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE Oficina SET OfiNom=?, OfiEstReg=? WHERE OfiCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, txtOfiNom.getText());
						miSentencia.setString(2, lblEstReg.getText());
						miSentencia.setInt(3,Integer.parseInt(txtOfiCod.getText()));
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtOfiCod.setText(null);
						txtOfiNom.setText(null);
						System.out.println("Dato Modificado!!");
					} catch (Exception e) {
						System.out.println("NO MODIFICADO!!");
						e.printStackTrace();
					}
					break;
				case "eliminar":  //monthString = "March";
					/*
	                        int row = tabOficinas.getSelectedRow();
	                        int column = tabOficinas.getColumnCount();
	                        try {
	                        	//1. Crear CONEXIÓN
	                			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
	                			//2. PREPARAR CONSULTA
	                			PreparedStatement miSentencia=miConextion.prepareStatement("DELETE FROM Oficina WHERE OfiCod=?");
	                			//3. ESTABLECER LOS PARAMETROS DE CONSULTA
	                			String num2= (String)tabOficinas.getValueAt(row, 0);
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

					int row = tabOficinas.getSelectedRow();
					int column = tabOficinas.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabOficinas.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE Oficina SET OfiEstReg=? WHERE OfiCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "*");
						int num =Integer.parseInt((String)tabOficinas.getValueAt(row, 0));
						miSentencia.setInt(2,num);
						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtOfiCod.setText(null);
						txtOfiNom.setText(null);
						System.out.println("Dato Eliminado!!!");
					} catch (Exception e) {
						System.out.println("NO ELIMINADO!!");
						e.printStackTrace();
					} 
					break;
				case "cancelar":  //monthString = "April";
					txtOfiCod.setText(null);
					txtOfiNom.setText(null);
					evento="";
					break;
				case "inactivar":  //monthString = "May";
					row = tabOficinas.getSelectedRow();
					column = tabOficinas.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabOficinas.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE Oficina SET OfiEstReg=? WHERE OfiCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "I");
						int num =Integer.parseInt((String)tabOficinas.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtOfiCod.setText(null);
						txtOfiNom.setText(null);
						System.out.println("Dato Inactivado!!");
					} catch (Exception e) {
						System.out.println("NO Inactivado!!");
						e.printStackTrace();
					} 
					break;
				case "reactivar":  //monthString = "June";
					row = tabOficinas.getSelectedRow();
					column = tabOficinas.getColumnCount();
					/*
	                        for(int i = 0; i < column; i++) {
	                            System.out.println(tabOficinas.getValueAt(row, i));
	                        }
					 */
					try {
						//1. Crear CONEXIÓN
						Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos?verifyServerCertificate=false&useSSL=true", "root", "");
						//2. PREPARAR CONSULTA
						PreparedStatement miSentencia=miConextion.prepareStatement("UPDATE Oficina SET OfiEstReg=? WHERE OfiCod=?");
						//3. ESTABLECER LOS PARAMETROS DE CONSULTA
						miSentencia.setString(1, "A");
						int num =Integer.parseInt((String)tabOficinas.getValueAt(row, 0));
						miSentencia.setInt(2,num);

						//4. EJECUTAR Y RECORRER CONSULTA
						miSentencia.execute();
						miSentencia.close();
						txtOfiCod.setText(null);
						txtOfiNom.setText(null);

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
                 			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM Oficina");
                 			//4. RECORRER EL RESULSET

                 			DefaultTableModel model=new DefaultTableModel(new String [] {
             		                "OfiCod", "OfiNom", "OfiEstReg"
                 			},0);
                 			while(miResulset.next()){
                 				model.addRow(new Object[]{miResulset.getString("OfiCod"),miResulset.getString("OfiNom"),miResulset.getString("OfiEstReg")});
                 			}
                 			tabOficinas.setModel(model);

                 		    jScrollPane3.setViewportView(tabOficinas);

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
					ResultSet miResulset=miStatement.executeQuery("SELECT * FROM Oficina");
					//4. RECORRER EL RESULSET

					DefaultTableModel model=new DefaultTableModel(new String [] {
							"OfiCod", "OfiNom", "OfiEstReg"
					},0);
					while(miResulset.next()){
						model.addRow(new Object[]{miResulset.getString("OfiCod"),miResulset.getString("OfiNom"),miResulset.getString("OfiEstReg")});
					}
					tabOficinas.setModel(model);
					scrtabOficinas.setViewportView(tabOficinas);
					evento="";
					txtOfiCod.setText("");
					txtOfiNom.setText("");
					lblEstReg.setText("");
					//txtOfiCod.setEditable(false);
					txtOfiCod.setEnabled(true);
					txtOfiNom.setEnabled(true);
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
		new LZ3001_Oficina();
	}
}
