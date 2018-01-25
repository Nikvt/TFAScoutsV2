

package Interface;

import java.awt.*;
import javax.swing.*;

import Controller.ApplicationController;
import Model.Membre;
import Exception.*;
import Model.UniteLib;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MemberList extends JPanel {
    
    private Container cont;
    private JTable table;
    private ArrayList <Membre> arrayMember;
        
    public MemberList(ArrayList <Membre> arrayMember, Container cont) {
        
        this.arrayMember = arrayMember;
        this.cont = cont;
        
        String[] columnNames = {"Nom", "Prénom", "Sexe", "Date de naissance", "Téléphone", "Email", "Colis", "Unité"};
        ApplicationController appCont = new ApplicationController();
        ArrayList <UniteLib> arrayUnit = new ArrayList<>();
        try {
            arrayUnit = appCont.getAllUnite();
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
        }
        
        MyTableModel model = new MyTableModel();
        model.setColumnIdentifiers(columnNames);
        int i = 0;
        for (Membre member : arrayMember) {
            model.addRow(member.toString().split("#"));
            model.setValueAt(member.getColis(), i, 6);
            model.setValueAt(member.getDateNais().getTime(), i, 3);
            for (UniteLib unit : arrayUnit) {
                if (member.getIdUnite() == unit.getIdUnite()) {
                    model.setValueAt(unit.getLibUnite(), i, 7);
                }
            }
            i++;
            System.out.println(member.getMatricule());
        }
        
        table = new JTable(model);
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            { if (e.getClickCount() == 2) actionWindow(); }
        });
        add(scrollPane);

    }
    
    private void actionWindow(){
        int choice = JOptionPane.showOptionDialog(null, "Que voulez vous faire?", "Modification", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Modifier", "Supprimer", "Retourner à la liste"}, "Modifier");
        if(choice == JOptionPane.YES_OPTION) modify();
        if(choice == JOptionPane.NO_OPTION) delete();
    }
    
    private void modify(){
        cont.removeAll();
        cont.add(new ModifyMember(arrayMember.get(table.getSelectedRow()), cont));
        cont.revalidate();
        cont.repaint();
    }
    
    private void delete(){
        try {
            ApplicationController appCont = new ApplicationController();
            appCont.delMembre(arrayMember.get(table.getSelectedRow()).getMatricule());
            ((MyTableModel)table.getModel()).removeRow(table.getSelectedRow());
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
        }
    }
    
}