
package Interface;

import java.awt.*;
import javax.swing.*;

import Model.Unite;
import java.util.ArrayList;

public class UniteSearchResult extends JPanel{
        
    public UniteSearchResult(ArrayList <Unite> arrayUnit) {
        
        String[] columnNames = {"Unité", "Localité", "Budget quotidien", "Budget exceptionnel", "Budget fonctionnel"};
        
        MyTableModel model = new MyTableModel();
        model.setColumnIdentifiers(columnNames);
        for (Unite unit : arrayUnit) {
            model.addRow(unit.toString().split("#"));
        }
        JTable table = new JTable(model);
        
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        add(scrollPane);

    }
    
}

