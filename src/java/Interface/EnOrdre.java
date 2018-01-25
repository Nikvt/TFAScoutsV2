
package Interface;

import java.awt.*;
import javax.swing.*;

import Model.MembrePay;
import java.util.ArrayList;

public class EnOrdre extends JPanel {
        
    public EnOrdre(ArrayList <MembrePay> arrayMembrePay) {
        
        String[] columnNames = {"Nom", "Prénom", "Téléphone", "Unité", "Localité", "Date", "Montant Total", "Montant déjà payé"};
        
        MyTableModel model = new MyTableModel();
        model.setColumnIdentifiers(columnNames);
        int i = 0;
        for (MembrePay membrePay : arrayMembrePay) {
            model.addRow(membrePay.toString().split("#"));
            model.setValueAt(membrePay.getDate().getTime(), i++, 5);
        }
        JTable table = new JTable(model);
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        add(scrollPane);

    }
    
}