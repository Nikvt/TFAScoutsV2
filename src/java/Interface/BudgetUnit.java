
package Interface;

import Controller.ApplicationController;
import Model.UniteLib;
import Model.Unite;
import Exception.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class BudgetUnit extends JPanel {
    
    private JLabel lList, lSelected, lTitle;
    private JList jLList, jLSelected;
    private DefaultListModel modelList, modelSelected;
    private JButton butSearchMem , butRemoveAll, butAddAll, butAdd, butRemove;
    private ArrayList <UniteLib> arrayUnit;
    
    public BudgetUnit(){
        

        arrayUnit = new ArrayList <>();
        modelSelected = new DefaultListModel();
        modelList = new DefaultListModel();
        try{
            ApplicationController appController = new ApplicationController();
            arrayUnit = appController.getAllUnite();
            for (UniteLib UniteLib : arrayUnit) {
                modelList.addElement(UniteLib);
            }
        }
        catch (ConnectionException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
        } 
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints myConstraints = new GridBagConstraints();
        myConstraints.insets = new Insets(5,5,5,5);
        myConstraints.ipady = 2;
        
        MyListSelectionListener myLSL = new MyListSelectionListener();
        MyActionListener myAL = new MyActionListener();
        
        lTitle = new JLabel ("<html><p><strong><span style=\"font-size: large;\">Veuillez effectuer une selection d'unités</span></strong></p>");
        myConstraints.gridx = 0;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 3;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(lTitle,myConstraints);
        
        lList = new JLabel ("Unités");
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 1;
        add(lList,myConstraints);

        
        lSelected = new JLabel ("Selection");
        myConstraints.gridx = 2;
        add(lSelected,myConstraints);
        
        
        jLList = new JList(modelList);
        jLList.setVisibleRowCount(10);
        jLList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jLList.addListSelectionListener(myLSL);
        myConstraints.gridx = 0;
        myConstraints.gridy = 2;
        myConstraints.ipadx = 30;
        myConstraints.gridheight = 2;
        add(new JScrollPane(jLList),myConstraints);
 
        jLSelected = new JList(modelSelected);
        jLSelected.setVisibleRowCount(10);
        jLSelected.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jLSelected.addListSelectionListener(myLSL);
        myConstraints.gridx = 2;
        add(new JScrollPane(jLSelected),myConstraints);

        butAdd = new JButton(">");
        butAdd.addActionListener(myAL);
        butAdd.setEnabled(false);
        myConstraints.gridx = 1;
        myConstraints.ipadx = 0;
        myConstraints.gridheight = 1;
        add(butAdd,myConstraints);
        
        butRemove = new JButton ("<");
        butRemove.addActionListener(myAL);
        butRemove.setEnabled(false);
        myConstraints.gridy = 3;
        add(butRemove,myConstraints);
        
        butSearchMem = new JButton("Rechercher");
        butSearchMem.addActionListener(myAL);
        butRemoveAll = new JButton ("Retirer tout");
        butRemoveAll.addActionListener(myAL);
        butAddAll = new JButton ("Ajouter tout");
        butAddAll.addActionListener(myAL);
        //myConstraints.gridx = 0;
        myConstraints.gridx = 1;
        myConstraints.gridy = 4;
        add(butSearchMem,myConstraints);
        myConstraints.gridx = 1;
        //add(butAddAll,myConstraints);
        myConstraints.gridx = 2;
        //add(butRemoveAll,myConstraints);
        
    }
    
    private class MyActionListener implements ActionListener{
        public void actionPerformed ( ActionEvent a ) {
            
            if(a.getSource() == butAdd){
                int iList = jLList.getSelectedIndex();
                modelSelected.addElement(modelList.elementAt(iList));
                modelList.remove(iList);
                
 
                int size = modelList.getSize();
                if (size == 0) {
                    butAdd.setEnabled(false);
                } 
                else {
                    if (iList == modelList.getSize()) {
                        iList--;
                    }
                    jLList.setSelectedIndex(iList);
                    jLList.ensureIndexIsVisible(iList);
                }
            }
            if(a.getSource() == butRemove){
                int iSelected = jLSelected.getSelectedIndex();
                modelList.addElement(modelSelected.elementAt(iSelected));
                modelSelected.remove(iSelected);
                
 
                int size = modelSelected.getSize();
                if (size == 0) {
                    butRemove.setEnabled(false);
                } 
                else {
                    if (iSelected == modelSelected.getSize()) {
                        iSelected--;
                    }
                    jLSelected.setSelectedIndex(iSelected);
                    jLSelected.ensureIndexIsVisible(iSelected);
                }

            }
            if(a.getSource() == butSearchMem){
                ArrayList <Integer> UnitId = new ArrayList <>();
                for (int i = 0, c; i < modelSelected.getSize(); i++){
                    c = arrayUnit.indexOf(modelSelected.elementAt(i));
                    UnitId.add(arrayUnit.get(c).getIdUnite());
                } 
                try{
                    ApplicationController appController = new ApplicationController();
                    ArrayList <Unite> aUnit = appController.getUnite(UnitId);
                    removeAll();
                    setLayout(new BorderLayout());
                    add(new UniteSearchResult(aUnit));
                    revalidate();
                    repaint();
                }
                catch (ConnectionException e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(a.getSource() == butAddAll){
                
            }
            if(a.getSource() == butRemoveAll){
                
            }
        }
    }
    private class MyListSelectionListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent e) {
                
                if(e.getSource() == jLList){

                    if (jLList.getSelectedIndex() == -1)
                        butAdd.setEnabled(false);
                        
                     else butAdd.setEnabled(true);
                }
                
                if(e.getSource() == jLSelected){
                    
                    if (jLSelected.getSelectedIndex() == -1)
                        butRemove.setEnabled(false);
                    
                    else butRemove.setEnabled(true);
                }
        }
    }
}