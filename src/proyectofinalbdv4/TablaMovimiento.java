
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
        initComponents();
        setTitle(contenido+" Movimiento");
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
        btnAdicionar=new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        btnModificar=new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        btnEliminar=new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        btnCancelar=new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        btnInactivar=new JButton("Inactivar");
        btnInactivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        btnReactivar=new JButton("Reactivar");
        btnReactivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        btnActualizar=new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        btnSalir=new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });  
    }
}
