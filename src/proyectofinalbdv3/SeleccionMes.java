
package proyectofinalbdv3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeleccionMes extends JFrame{
    JLabel lblSel;
    JLabel lblMes;
    JLabel lblAni;
    JComboBox<String> comMes;
    JComboBox<String> comAni;
    JButton btnSiguiente;
    JPanel panSeleccion;
    JPanel panMes;
    JPanel panSiguiente;

    public SeleccionMes() {
        initComponents();
        setTitle("Seleccion de Mes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);
        
        setLayout(new BorderLayout());
        panSeleccion.add(lblSel);
        panMes.add(lblMes);
        panMes.add(comMes);
        panMes.add(lblAni);
        panMes.add(comAni);
        panSiguiente.add(btnSiguiente);
        add(panSeleccion,BorderLayout.NORTH);
        add(panMes,BorderLayout.CENTER);
        add(panSiguiente,BorderLayout.SOUTH);
        setVisible(true);
    }
     private void initComponents() {
        panSeleccion=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panMes=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panSiguiente=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblSel=new JLabel("Seleccione Proveedor:");
        lblMes=new JLabel("Mes:");
        comMes=new JComboBox<>();
        final String []tablaMes={"01","02","03","04","05","06","07","08","09","10","11","12",};
        comMes.setModel(new DefaultComboBoxModel<>(tablaMes));
        lblAni=new JLabel("Anio:");
        comAni=new JComboBox<>();
        final String []tablaAni={"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999",
                                "2000","2001","2002","2003","2004","2005","2006","2007","2008","2009",
                                "2010","2011","2012","2013","2014","2015","2016","2017","2018","2019",};
        comAni.setModel(new DefaultComboBoxModel<>(tablaAni));
        
        
        btnSiguiente=new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String temp=comMes.getSelectedItem().toString();
                new InformeMovimientoMes(comMes.getSelectedItem()+" "+comAni.getSelectedItem());
                dispose(); 
            }
        });
    }
}
