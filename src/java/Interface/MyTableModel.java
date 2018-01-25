
package Interface;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
    

    MyTableModel() {
        super();
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }
        
    public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
    }
}
