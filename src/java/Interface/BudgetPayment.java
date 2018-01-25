
package Interface;

import Controller.ApplicationController;
import Model.UniteLib;
import Model.Depense;
import Exception.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class BudgetPayment extends JPanel {
       
    private JLabel lTitle, lUnit, lBudget, lMinPay, lMaxPay;
    private JSpinner sMinPay, sMaxPay;
    private JCheckBox bFonct, bExcep, bEveryday, bValid;
    private JComboBox tUnit;
    private String [] tabUnit;
    private JButton butSearchPay , butReinitialisation;
    private ArrayList <UniteLib> arrayUnit;
    private ArrayList <Depense>  arrayDepense;
    
    public BudgetPayment(){
        
        tabUnit = new String[]{};
        arrayUnit = new ArrayList <>(); 
        try{
            ApplicationController appController = new ApplicationController();
            arrayUnit = appController.getAllUnite();
            tabUnit = new String[arrayUnit.size()];
            for (int i = 0; i < arrayUnit.size(); i++){
                tabUnit [i] = arrayUnit.get(i).getLibUnite();
            }
        }
        catch (ConnectionException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
        } 
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints myConstraints = new GridBagConstraints();
        myConstraints.insets = new Insets(5,5,5,5);
        myConstraints.ipady = 2;
        
        MyActionListener myAL = new MyActionListener();
        
        lTitle = new JLabel ("<html><p><strong><span style=\"font-size: large;\">Recherche de payements</span></strong></p>");
        myConstraints.gridx = 1;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 2;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(lTitle,myConstraints);
        
        lUnit = new JLabel ("Unité : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 1;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lUnit,myConstraints); 
        tUnit = new JComboBox (tabUnit);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(tUnit,myConstraints);
        
        lBudget = new JLabel ("Budget : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 2;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lBudget,myConstraints); 
        bFonct = new JCheckBox ("Fonctionnel",true);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(bFonct,myConstraints); 
        bExcep = new JCheckBox ("Exceptionnel",true);
        myConstraints.gridy = 3;
        add(bExcep,myConstraints); 
        bEveryday = new JCheckBox ("Quotidien",true);
        myConstraints.gridy = 4;
        add(bEveryday,myConstraints);
        
        lMinPay = new JLabel("pour un montant de : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 5;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lMinPay,myConstraints);
        sMinPay = new JSpinner(new SpinnerNumberModel(0.0,0.0,90000.0,0.01));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sMinPay,myConstraints);
        
        lMaxPay = new JLabel ( "à : ");
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lMaxPay,myConstraints); 
        sMaxPay = new JSpinner(new SpinnerNumberModel(10000.0,0.0,90000.0,0.01));
        myConstraints.gridx = 3;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sMaxPay,myConstraints);
        
        bValid = new JCheckBox ("Validé");
        myConstraints.gridx = 2;
        myConstraints.gridy = 6;
        add(bValid,myConstraints);
        
        butSearchPay = new JButton("Rechercher");
        butSearchPay.addActionListener(myAL);
        butReinitialisation = new JButton ("Réinitialisation");
        butReinitialisation.addActionListener(myAL);
        myConstraints.gridx = 1;
        myConstraints.gridy = 7;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(butSearchPay,myConstraints);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(butReinitialisation,myConstraints);
        
    }
    
    private class MyActionListener implements ActionListener{
        public void actionPerformed ( ActionEvent a ) {
            if(a.getSource() == butSearchPay){
                if ((Double) sMinPay.getValue() < (Double) sMaxPay.getValue()){
                    int UnitId = arrayUnit.get(tUnit.getSelectedIndex()).getIdUnite();
                    arrayDepense = new ArrayList <>(); 

                    try{
                        ApplicationController appController = new ApplicationController();
                        arrayDepense = appController.getDepense(UnitId, bFonct.isSelected(), bExcep.isSelected(), bEveryday.isSelected(), (Double) sMinPay.getValue(),(Double) sMaxPay.getValue(), bValid.isSelected());
                        removeAll();
                        setLayout(new BorderLayout());
                        add(new PayementSearchResult(arrayDepense));
                        revalidate();
                        repaint();
                    } 
                    catch (ConnectionException e){
                        JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
                    } 
                }
                else JOptionPane.showMessageDialog(null,"Le montants sont incorrectes","Erreur : Montants incorrectes",JOptionPane.WARNING_MESSAGE);
            }
            if(a.getSource() == butReinitialisation){
                
                for (Component C : getComponents()){
                    if (C instanceof JComboBox) ((JComboBox) C).setSelectedIndex(0);
                    if (C instanceof JCheckBox) ((JCheckBox) C).setSelected(true);
                }
                bValid.setSelected(false);
                sMaxPay.setValue(10000.0);
                sMinPay.setValue(0.0);
                revalidate();
                repaint();
                
            }
        }
    }   
}