
package proyectofinalbdv3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InformeMovimientoMes extends JFrame{
    JScrollPane scrTabMovMes;
    DefaultTableModel dtm;
    JTable tabMovMes;

    public InformeMovimientoMes(String string){
        initComponents();
        setTitle("Informe Movimiento - mes:"+string);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);
        
        setLayout(new BorderLayout());
        add(scrTabMovMes,BorderLayout.CENTER);
        
        
        setVisible(true);
    }

    private void initComponents() {
        dtm=new DefaultTableModel();
        String[] titulo={"MovCOd","TipTraNom","ResNom","OfiNom","MovEstReg"};
        dtm.setColumnIdentifiers(titulo);
        tabMovMes=new JTable();
        tabMovMes.setModel(dtm);
        scrTabMovMes=new JScrollPane();
        scrTabMovMes.setViewportView(tabMovMes);
    }
    
}
