
package Interface;

import Model.PayementInscription;
import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;

public class InscriptionSearchResult extends JPanel{
        
    public InscriptionSearchResult(ArrayList <PayementInscription> arrayPayInsc) {
        
        String[] columnNames = {"Date", "Montant", "Nom", "Prénom", "Téléphone", "Localité"};
       
        MyTableModel model = new MyTableModel();
        model.setColumnIdentifiers(columnNames);
        int i = 0;
        for (PayementInscription payInsc : arrayPayInsc) {
            model.addRow(payInsc.toString().split("#"));
            model.setValueAt(payInsc.getDate().getTime(), i++, 0);
        }
        
        JTable table = new JTable(model);
        
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        add(scrollPane);

    }
    
}