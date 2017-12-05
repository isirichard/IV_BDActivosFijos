
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

public class Informes extends JFrame{
    JLabel lblResponsable;
    JComboBox<String> comResponsable;
    JButton btnSiguiente;
    JPanel panSeleccion;
    JPanel panResponsables;
    JPanel panSiguiente;

    public Informes() {
        initComponents();
        setTitle("Informes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 170);
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);
        
        setLayout(new BorderLayout());
        panSeleccion.add(lblResponsable);
        panResponsables.add(comResponsable);
        panSiguiente.add(btnSiguiente);
        add(panSeleccion,BorderLayout.NORTH);
        add(panResponsables,BorderLayout.CENTER);
        add(panSiguiente,BorderLayout.SOUTH);
        setVisible(true);
    }

    private void initComponents() {
        panSeleccion=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panResponsables=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panSiguiente=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblResponsable=new JLabel("Seleccione Informe:");
        comResponsable=new JComboBox<>();
        final String []tabla={"Informe Activo Fijo - Responsable","Informe Activo Fijo - Proveedor","Informe Movimiento - Mes"};
        comResponsable.setModel(new DefaultComboBoxModel<>(tabla));
        btnSiguiente=new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String temp=comResponsable.getSelectedItem().toString();
                if(temp.equals("Informe Activo Fijo - Responsable")){
                    new SeleccionResponsable();
                    dispose();
                }
            }
        });
    }
}
