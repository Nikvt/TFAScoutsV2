package Interface;

import Controller.ApplicationController;
import Model.UniteLib;
import Model.PayementInscription;
import Exception.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JSpinner.*;
import java.util.*;
import java.text.*;


public class BudgetPayementInscription extends JPanel {
       
    private JLabel lTitle, lUnit, lBeginDate, lEndDate, lMinPay, lMaxPay;
    private JSpinner sBeginDate, sEndDate, sMinPay, sMaxPay;
    private JComboBox tUnit;
    private String [] tabUnit;
    private JButton butSearchPay , butReinitialisation;
    private ArrayList <UniteLib> arrayUnit;
    private ArrayList <PayementInscription>  arrayPayIns;
    private Date earliestDate, latestDate, setDate;
    
    public BudgetPayementInscription(){
        
        tabUnit = new String[]{};
        arrayUnit = new ArrayList <UniteLib>(); 
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
        
        try {
            earliestDate = new SimpleDateFormat( "yyyy-MM-dd").parse( "1900-01-01");
            setDate = new SimpleDateFormat( "yyyy-MM-dd").parse( "1999-01-01");
        } catch (ParseException e){}
        
        latestDate = new Date();
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints myConstraints = new GridBagConstraints();
        myConstraints.insets = new Insets(5,5,5,5);
        myConstraints.ipady = 2;
        
        MyActionListener myAL = new MyActionListener();
        
        lTitle = new JLabel ("<html><p><strong><span style=\"font-size: large;\">Recherche de payements d'inscriptions</span></strong></p>");
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
        
        lBeginDate = new JLabel ( "A� partir de : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 2;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lBeginDate,myConstraints);
        sBeginDate = new JSpinner(new SpinnerDateModel(setDate, earliestDate, latestDate,Calendar.MILLISECOND));
        sBeginDate.setEditor(new DateEditor(sBeginDate, "dd:MM:yyyy"));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sBeginDate,myConstraints); 
        
        lEndDate = new JLabel ( "jusque : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 3;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lEndDate,myConstraints);
        sEndDate = new JSpinner(new SpinnerDateModel(latestDate, earliestDate, latestDate,Calendar.MILLISECOND));
        sEndDate.setEditor(new DateEditor(sEndDate, "dd:MM:yyyy"));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sEndDate,myConstraints); 
        
        lMinPay = new JLabel("pour un montant de : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 4;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lMinPay,myConstraints);
        sMinPay = new JSpinner(new SpinnerNumberModel(0.0,0.0,1000.0,0.01));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sMinPay,myConstraints);
        
        lMaxPay = new JLabel ( "à : ");
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(lMaxPay,myConstraints); 
        sMaxPay = new JSpinner(new SpinnerNumberModel(1000.0,0.0,1000.0,0.01));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(sMaxPay,myConstraints);
        
        butSearchPay = new JButton("Rechercher");
        butSearchPay.addActionListener(myAL);
        butReinitialisation = new JButton ("Réinitialisation");
        butReinitialisation.addActionListener(myAL);
        myConstraints.gridx = 1;
        myConstraints.gridy = 5;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(butSearchPay,myConstraints);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(butReinitialisation,myConstraints);
        
    }
    
    private class MyActionListener implements ActionListener{
        public void actionPerformed ( ActionEvent a ) {

            if(a.getSource() == butSearchPay){
                
                int UnitId = arrayUnit.get(tUnit.getSelectedIndex()).getIdUnite();
                arrayPayIns = new ArrayList <>(); 
                
                try{
                    ApplicationController appController = new ApplicationController();
                    DateFormat dF = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
                    Date beginD = dF.parse(sBeginDate.getValue().toString());
                    Date endD = dF.parse(sEndDate.getValue().toString());
                    if (beginD.before(endD) && (Double) sMinPay.getValue() < (Double) sMaxPay.getValue() ){
                        GregorianCalendar beginG = new GregorianCalendar();
                        beginG.setTime(beginD);
                        GregorianCalendar endG = new GregorianCalendar();
                        endG.setTime(endD);
                        arrayPayIns = appController.getPayementInscription(UnitId, beginG, endG,(Double) sMinPay.getValue(),(Double) sMaxPay.getValue());
                        removeAll();
                        setLayout(new BorderLayout());
                        add(new InscriptionSearchResult(arrayPayIns));
                        revalidate();
                        repaint();
                    }
                    else JOptionPane.showMessageDialog(null,"Les champs montants ou dates sont incorrectes","Erreur : Dates/Montnats incorrectes",JOptionPane.WARNING_MESSAGE);
                } 
                catch (ConnectionException e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                } 
            }
            if(a.getSource() == butReinitialisation){
                tUnit.setSelectedIndex(0);
                sMinPay.setValue(0.0);
                sMaxPay.setValue(1000.0);
                sBeginDate.setValue(setDate);
                sEndDate.setValue(latestDate);
                revalidate();
                repaint();
            }
        }
    }   
}