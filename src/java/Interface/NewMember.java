
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
import java.util.ArrayList;
import java.text.*;
import java.util.regex.Pattern;


public class NewMember extends JPanel {
    
    private JLabel lNom, lPrenom, lSexe, lNaisDate, lTelephone, lEmail, lPoints, lUnite, lTitle;
    private JTextField tNom, tPrenom, tTelephone, tEmail;
    private JSpinner sPoints, sDate;
    private JCheckBox bFicheMed, bAutParent, bColis;
    private JComboBox tUnit;
    private String [] tabUnit;
    private JRadioButton rSexH , rSexF;
    private ButtonGroup sexBGroup;
    private JButton butAddMem , butReinitialisation;
    private ArrayList <UniteLib> arrayUnit;
    private Date earliestDate, latestDate;    
    
    public NewMember(){
        
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
        } catch (ParseException e){}
        
        latestDate = new Date();
        
        setLayout(new GridBagLayout());
        GridBagConstraints myConstraints = new GridBagConstraints();
        myConstraints.insets = new Insets(5,5,5,5);
        myConstraints.ipady = 2;
        
        MyActionListener myAL = new MyActionListener();
        
        lTitle = new JLabel ("<html><p><strong><span style=\"font-size: large;\">Veuillez remplir le formulaire d'inscription</span></strong></p>");
        myConstraints.gridx = 1;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 2;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(lTitle,myConstraints);
        
        lNom = new JLabel ("Nom : ");
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 1;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lNom,myConstraints);
        tNom = new JTextField(30);
        tNom.addActionListener(myAL); 
        myConstraints.gridx = 2;
        add(tNom,myConstraints); 
        
        lPrenom = new JLabel("Prénom : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 2;
        add(lPrenom,myConstraints);
        tPrenom = new JTextField(30);
        tPrenom.addActionListener(myAL); 
        myConstraints.gridx = 2;
        add(tPrenom,myConstraints);
        
        lNaisDate = new JLabel ( "Date de naissance : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 3;
        add(lNaisDate,myConstraints);
        sDate = new JSpinner(new SpinnerDateModel(latestDate, earliestDate, latestDate,Calendar.MILLISECOND));
        sDate.setEditor(new DateEditor(sDate, "dd:MM:yyyy"));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sDate,myConstraints); 
        
        lTelephone = new JLabel("Numéro de téléphone : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 4;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lTelephone,myConstraints);
        tTelephone = new JTextField (30);
        tTelephone.addActionListener(myAL);
        myConstraints.gridx = 2;
        add(tTelephone,myConstraints);
        
        lEmail = new JLabel ( "Email : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 5;
        add(lEmail,myConstraints); 
        tEmail = new JTextField(30);
        tEmail.addActionListener(myAL);
        myConstraints.gridx = 2;
        add(tEmail,myConstraints);
        
        lPoints = new JLabel ( "Points : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 6;
        add(lPoints,myConstraints); 
        sPoints = new JSpinner(new SpinnerNumberModel(0,0,100,1));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sPoints,myConstraints);
        
        lUnite = new JLabel ("Unité : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 7;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lUnite,myConstraints); 
        tUnit = new JComboBox (tabUnit);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(tUnit,myConstraints);
        
        lSexe = new JLabel ("Sexe : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 8;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lSexe,myConstraints);
        rSexH = new JRadioButton("Homme",true);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(rSexH,myConstraints);
        rSexF = new JRadioButton ("Femme",false);
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(rSexF,myConstraints);
        sexBGroup = new ButtonGroup();
        sexBGroup.add(rSexH);
        sexBGroup.add(rSexF);
        
        bFicheMed = new JCheckBox ("Fiche médicale");
        myConstraints.gridy = 9;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(bFicheMed,myConstraints); 
        bAutParent = new JCheckBox ("Autorisation parentale");
        myConstraints.gridy = 10;
        add(bAutParent,myConstraints); 
        bColis = new JCheckBox ("Colis");
        myConstraints.gridy = 11;
        add(bColis,myConstraints);
        
        butAddMem = new JButton("Enregistrer");
        butAddMem.addActionListener(myAL);
        butReinitialisation = new JButton ("Réinitialisation");
        butReinitialisation.addActionListener(myAL);
        myConstraints.gridx = 1;
        myConstraints.gridy = 12;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(butAddMem,myConstraints);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(butReinitialisation,myConstraints);
        
    }

    
    private class MyActionListener implements ActionListener{
        public void actionPerformed ( ActionEvent a ) {

            if(a.getSource() == butAddMem){
                
                char sex;
                if(rSexH.isSelected()) sex = 'M';
                else sex = 'F';
                int UnitId = arrayUnit.get(tUnit.getSelectedIndex()).getIdUnite();
                
                try{
                    ApplicationController appController = new ApplicationController();
                    DateFormat dF = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
                    Date birthD = dF.parse(sDate.getValue().toString());
                    GregorianCalendar birthG = new GregorianCalendar();
                    birthG.setTime(birthD);
                    Membre membre = new Membre (0,tNom.getText(), tPrenom.getText(), sex, birthG, tTelephone.getText(), tEmail.getText(),(Integer) sPoints.getValue(), bFicheMed.isSelected(), bAutParent.isSelected(), bColis.isSelected(), UnitId);
                    appController.addMembre(membre);
                    JOptionPane.showMessageDialog(null,"Le membre a été rajouté","Ajout réussi",JOptionPane.INFORMATION_MESSAGE);
                } 
                catch (ConnectionException e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
                } 
                catch (MembreException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : creation membre",JOptionPane.WARNING_MESSAGE);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(a.getSource() == butReinitialisation){
                for (Component C : getComponents()){    
                    if (C instanceof JTextField) ((JTextField) C).setText("");
                    if (C instanceof JComboBox) ((JComboBox) C).setSelectedIndex(0);
                    if (C instanceof JCheckBox) ((JCheckBox) C).setSelected(false);
                }
                sPoints.setValue(0);
                sDate.setValue(latestDate);
                rSexH.setSelected(true);
                revalidate();
                repaint();
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
    
    public void verif(){
        if(Pattern.matches("^[A-Z].[a-zçéèàùïëê]*([-\\s][A-Z][a-zçéèàùïëê]+)*$", tPrenom.getText()) == false){
            tPrenom.setForeground(Color.RED);
        }
        else tPrenom.setForeground(Color.BLACK);
        
        if(Pattern.matches("^[A-Z].[a-zçéèàùïëê]*([-\\s][A-Z][a-zçéèàùïëê]+)*$", tNom.getText()) == false){
            tNom.setForeground(Color.RED);
        }
        else tNom.setForeground(Color.BLACK);
        
        if (Pattern.matches("^[0-9]+([\\/][0-9]+)*$", tTelephone.getText()) == false){
            tTelephone.setForeground(Color.RED);
        }
        else tTelephone.setForeground(Color.BLACK);
        
        if(Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", tEmail.getText().toLowerCase())==false){
            tEmail.setForeground(Color.RED);
        }
        else tEmail.setForeground(Color.BLACK);
    }
    
    public String toString(){
        return "NewMember";
    }
}