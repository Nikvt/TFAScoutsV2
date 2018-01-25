package Interface;

import java.awt.*;
import javax.swing.*;

import Model.Depense;
import java.util.ArrayList;

public class PayementSearchResult extends JPanel{
        
    public PayementSearchResult(ArrayList <Depense> arrayDepense) {
        
        String[] columnNames = {"Budget", "Montant Alloué", "Id", "Montant", "Date", "Nom du memebre", "Prénom du membre", "Validé"};
        
        MyTableModel model = new MyTableModel();
        model.setColumnIdentifiers(columnNames);
        int i = 0;
        for (Depense depense : arrayDepense) {
            System.out.println(depense);
            model.addRow(depense.toString().split("#"));
            model.setValueAt(depense.getValide(), i, 7);
            model.setValueAt(depense.getDate().getTime(), i++, 4);
        }
        JTable table = new JTable(model);
        
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        add(scrollPane);

    }
    
}
