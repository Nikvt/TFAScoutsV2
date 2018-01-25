
package Interface;

import Controller.ApplicationController;
import Model.Membre;
import Model.UniteLib;
import Exception.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JSpinner.*;
import java.util.*;
import java.text.*;


public class SearchMember extends JPanel {
    
    private Container cont;
    private JLabel lMatricule, lNom, lPrenom, lSexe, lNaisDate, lTelephone, lEmail, lPoints, lUnite, lTitle, lFicheMed, lAutParent, lColis;
    private JTextField tNom, tPrenom, tTelephone, tEmail;
    private JSpinner sMat, sPoints, sDate;
    private JComboBox tUnit;
    private JCheckBox bMat, bDate, bPoint, bUnit;
    
    private String [] tabUnit;
    private JRadioButton rSexH , rSexF, rFicheMed, rAutParent, rColis, rFicheMedNo, rAutParentNo, rColisNo;
    private ButtonGroup sexBGroup, ficheBGroup, autParentBGroup, colisBGroup;
    private JButton butSearchMem , butReinitialisation;
    private ArrayList <UniteLib> arrayUnit;
    private ArrayList <Membre> arrayMember;
    private Date earliestDate, latestDate;
    
    public SearchMember(Container container){
        cont = container;
        tabUnit = new String[]{};
        arrayUnit = new ArrayList <UniteLib>(); 
        try{
            ApplicationController appController = new ApplicationController();
            arrayUnit = appController.getAllUnite();
            tabUnit = new String[arrayUnit.size()];
            for (int i = 0; i < arrayUnit.size(); i++){
                tabUnit [i] = arrayUnit.get(i).getLibUnite();
            }
        } catch (ConnectionException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
        } 
        
        try {
            earliestDate = new SimpleDateFormat( "yyyy-MM-dd").parse( "1900-01-01");
        } catch (ParseException e){}
        
        latestDate = new Date();
        
        setLayout(new GridBagLayout());
        GridBagConstraints myConstraints = new GridBagConstraints();
        myConstraints.insets = new Insets(5,5,5,5);
        myConstraints.ipady = 2;
        
        MyItemList myIL = new MyItemList();
        MyActionListener myAL = new MyActionListener();
        
        lTitle = new JLabel ("<html><p><strong><span style=\"font-size: large;\">Veuillez remplir le formulaire de recherche</span></strong></p>");
        myConstraints.gridx = 1;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 2;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(lTitle,myConstraints);
        
        lMatricule = new JLabel ("Matricule : ");
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 1;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lMatricule,myConstraints);
        sMat = new JSpinner(new SpinnerNumberModel(10000,10000,99999,1));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sMat,myConstraints);
        bMat = new JCheckBox ("Toute matricule");
        bMat.addItemListener(myIL);
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(bMat,myConstraints);
        
        lNom = new JLabel ("Nom : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 2;
        add(lNom,myConstraints);
        tNom = new JTextField(30);
        tNom.addActionListener(myAL);
        myConstraints.gridx = 2;
        add(tNom,myConstraints); 
        
        lPrenom = new JLabel("Prénom : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 3;
        add(lPrenom,myConstraints);
        tPrenom = new JTextField(30);
        tPrenom.addActionListener(myAL);
        myConstraints.gridx = 2;
        add(tPrenom,myConstraints); 
        
        lNaisDate = new JLabel ( "Date de naissance : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 4;
        add(lNaisDate,myConstraints);
        sDate = new JSpinner(new SpinnerDateModel(latestDate, earliestDate, latestDate,Calendar.MILLISECOND));
        sDate.setEditor(new DateEditor(sDate, "dd:MM:yyyy"));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sDate,myConstraints);
        bDate = new JCheckBox ("Toute date");
        bDate.addItemListener(myIL);
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(bDate,myConstraints);
        
        lTelephone = new JLabel("Numéro de téléphone : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 5;
        add(lTelephone,myConstraints);
        tTelephone = new JTextField (30);
        tTelephone.addActionListener(myAL);
        myConstraints.gridx = 2;
        add(tTelephone,myConstraints);
        
        lEmail = new JLabel ( "Email : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 6;
        add(lEmail,myConstraints); 
        tEmail = new JTextField(30);
        tEmail.addActionListener(myAL);
        myConstraints.gridx = 2;
        add(tEmail,myConstraints);
        
        lPoints = new JLabel ( "Points : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 7;
        add(lPoints,myConstraints); 
        sPoints = new JSpinner(new SpinnerNumberModel(0,0,100,1));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sPoints,myConstraints);
        bPoint = new JCheckBox ("Sans points");
        bPoint.addItemListener(myIL);
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(bPoint,myConstraints);
        
        lUnite = new JLabel ("Unité : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 8;
        add(lUnite,myConstraints); 
        tUnit = new JComboBox (tabUnit);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(tUnit,myConstraints);
        bUnit = new JCheckBox ("Sans Unité");
        bUnit.addItemListener(myIL);
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(bUnit,myConstraints);
        
        lSexe = new JLabel ("Sexe : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 9;
        add(lSexe,myConstraints);
        rSexH = new JRadioButton("Homme",false);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(rSexH,myConstraints);
        rSexF = new JRadioButton ("Femme",false);
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(rSexF,myConstraints);
        sexBGroup = new ButtonGroup();
        sexBGroup.add(rSexH);
        sexBGroup.add(rSexF);
        
        lFicheMed = new JLabel ("Fiche medicale : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 10;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lFicheMed,myConstraints);
        rFicheMed = new JRadioButton("Reçue",false);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(rFicheMed,myConstraints);
        rFicheMedNo = new JRadioButton ("Manquante",false);
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(rFicheMedNo,myConstraints);
        ficheBGroup = new ButtonGroup();
        ficheBGroup.add(rFicheMed);
        ficheBGroup.add(rFicheMedNo);
                
        lAutParent = new JLabel ("Autorisation parentale : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 11;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lAutParent,myConstraints);
        rAutParent = new JRadioButton("Reçue",false);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(rAutParent,myConstraints);
        rAutParentNo = new JRadioButton ("Manquante",false);
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(rAutParentNo,myConstraints); 
        autParentBGroup = new ButtonGroup();
        autParentBGroup.add(rAutParent);
        autParentBGroup.add(rAutParentNo);
        
        lColis = new JLabel ("Colis : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 12;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lColis,myConstraints);
        rColis = new JRadioButton("Reçu",false);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(rColis,myConstraints);
        rColisNo = new JRadioButton ("Manquant",false);
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(rColisNo,myConstraints);
        colisBGroup = new ButtonGroup();
        colisBGroup.add(rColis);
        colisBGroup.add(rColisNo);
        
        butSearchMem = new JButton("Rechercher");
        butSearchMem.addActionListener(myAL);
        butReinitialisation = new JButton ("Réinitialisation");
        butReinitialisation.addActionListener(myAL);
        myConstraints.gridx = 1;
        myConstraints.gridy = 13;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(butSearchMem,myConstraints);
        myConstraints.gridx = 2;
        myConstraints.gridy = 13;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(butReinitialisation,myConstraints);
        
    }
    
    private Boolean myRButton(JRadioButton rButton, JRadioButton rButtonNo){
        Boolean state = null;
        if (rButton.isSelected()) state = true;
        else if (rButtonNo.isSelected()) state = false;
        return state;
    }
    
    private class MyItemList implements ItemListener{
        public void itemStateChanged (ItemEvent e){
            if(e.getSource()==bMat && e.getStateChange ( ) == ItemEvent.SELECTED){
                sMat.setVisible(false);
            }
            if(e.getSource()==bMat && e.getStateChange ( ) == ItemEvent.DESELECTED){
                sMat.setVisible(true);
            }
            if(e.getSource()==bDate && e.getStateChange ( ) == ItemEvent.SELECTED){
                sDate.setVisible(false);
            }
            if(e.getSource()==bDate && e.getStateChange ( ) == ItemEvent.DESELECTED){
                sDate.setVisible(true);
            }
            if(e.getSource()==bPoint && e.getStateChange ( ) == ItemEvent.SELECTED){
                sPoints.setVisible(false);
            }
            if(e.getSource()==bPoint && e.getStateChange ( ) == ItemEvent.DESELECTED){
                sPoints.setVisible(true);
            }
            if(e.getSource()==bUnit && e.getStateChange ( ) == ItemEvent.SELECTED){
                tUnit.setVisible(false);
            }
            if(e.getSource()==bUnit && e.getStateChange ( ) == ItemEvent.DESELECTED){
                tUnit.setVisible(true);
            }
        }
    }
    
    private class MyActionListener implements ActionListener{
        public void actionPerformed ( ActionEvent a ) {

            if(a.getSource() == butSearchMem){
                Integer matricule = null;
                Integer points = null;
                Integer UnitId = null;
                GregorianCalendar birthG = null;
                Character sex = null;
                arrayMember = new ArrayList <Membre>();
                
                if (bDate.isSelected() == false){
                    birthG = new GregorianCalendar();
                    DateFormat dF = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    Date birthD;
                    
                    try { 
                        birthD = dF.parse(sDate.getValue().toString());
                        System.out.println(sDate.getValue().toString() + "==" + birthD);
                        birthG.setTime(birthD);
                    } catch (ParseException ex) {}
                }
                
                if (bMat.isSelected() == false) 
                matricule = (Integer) sMat.getValue();

                if (bPoint.isSelected() == false) 
                points = (Integer) sPoints.getValue();
                
                if(rSexH.isSelected()) sex = 'M';
                else if (rSexH.isSelected())sex = 'F';

                if (bUnit.isSelected() == false)
                UnitId = arrayUnit.get(tUnit.getSelectedIndex()).getIdUnite();
                
                try{
                    ApplicationController appController = new ApplicationController();
                    arrayMember = appController.getMembre(matricule, tNom.getText(), tPrenom.getText(), sex, birthG, tTelephone.getText(), myRButton(rFicheMed, rFicheMedNo), myRButton(rAutParent, rAutParentNo), tEmail.getText(), myRButton(rColis, rColisNo), points, UnitId);
                    cont.removeAll();
                    cont.setLayout(new BorderLayout());
                    cont.add(new MemberList(arrayMember, cont));
                    cont.revalidate();
                    cont.repaint();
                }
                catch (ConnectionException e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
                } 
                catch (MembreException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : creation membre",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(a.getSource() == butReinitialisation){
                reset();
            }
            if(a.getSource() == tNom){
                tPrenom.requestFocusInWindow();
            }
            if(a.getSource() == tPrenom){
                tTelephone.requestFocusInWindow();
            }
            if(a.getSource() == tTelephone){
                tEmail.requestFocusInWindow();
            }
            if(a.getSource() == tEmail){
                sPoints.requestFocusInWindow();
            }
        }
    }
    
    public void reset(){
        for (Component C : getComponents()){    
            if (C instanceof JTextField) ((JTextField) C).setText("");
            if (C instanceof JComboBox) ((JComboBox) C).setSelectedIndex(0);
            if (C instanceof JCheckBox) ((JCheckBox) C).setSelected(false);
        }
        sMat.setValue(10000);
        sPoints.setValue(0);
        sDate.setValue(latestDate);
        sexBGroup.clearSelection();
        ficheBGroup.clearSelection();
        autParentBGroup.clearSelection();
        colisBGroup.clearSelection();
        revalidate();
        repaint();
    }
}