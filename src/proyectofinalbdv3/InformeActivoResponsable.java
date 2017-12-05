
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

public class InformeActivoResponsable extends JFrame{
    JScrollPane scrTabActRes;
    DefaultTableModel dtm;
    JTable tabActRes;

    public InformeActivoResponsable(String string){
        initComponents();
        setTitle("Informe Activo Fijo - Responsable");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)(dimension.getWidth()/2-getWidth()/2);
        int y=(int)(dimension.getHeight()/2-getHeight()/2);
        setLocation(x,y);
        
        setLayout(new BorderLayout());
        add(scrTabActRes,BorderLayout.CENTER);
        
        
        setVisible(true);
    }

    private void initComponents() {
        dtm=new DefaultTableModel();
        String[] titulo={"ActCod","ActNom","ActDes","ActNumSer","ActFac","ActCueCon","ActFec","ActEstReg"};
        dtm.setColumnIdentifiers(titulo);
        tabActRes=new JTable();
        tabActRes.setModel(dtm);
        scrTabActRes=new JScrollPane();
        scrTabActRes.setViewportView(tabActRes);
    }
    
}
