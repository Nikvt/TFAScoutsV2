
package Interface;

import Controller.ApplicationController;
import Model.Membre;
import Model.UniteLib;
import Exception.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JSpinner.*;
import java.text.*;
import java.util.*;
import java.util.regex.Pattern;


public class ModifyMember extends JPanel {
    
    private Container cont;
    private JLabel lMatricule, lNom, lPrenom, lSexe, lNaisDate, lTelephone, lEmail, lPoints, lUnite, lTitle, lFicheMed, lAutParent, lColis;
    private JTextField tNom, tPrenom, tTelephone, tEmail;
    private JSpinner sPoints, sDate;
    private JComboBox tUnit;
    
    private String [] tabUnit;
    private JRadioButton rSexH , rSexF, rFicheMed, rAutParent, rColis, rFicheMedNo, rAutParentNo, rColisNo;
    private ButtonGroup sexBGroup, ficheBGroup, autParentBGroup, colisBGroup;
    private JButton butEditMemb , butBackToSearch;
    private ArrayList <UniteLib> arrayUnit;
    private Membre member;
    private Date earliestDate, latestDate;
    
    public ModifyMember(Membre member, Container cont){
        this.cont = cont;
        this.member = member;
        tabUnit = new String[]{};
        arrayUnit = new ArrayList <>(); 
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
        
        int unitIndex = 0;
        for(int i = 0; i < arrayUnit.size(); i++){
            if (arrayUnit.get(i).getIdUnite() == member.getIdUnite())
                unitIndex = i;
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
        
        lTitle = new JLabel ("<html><p><strong><span style=\"font-size: large;\">Veuillez remplir le formulaire de recherche</span></strong></p>");
        myConstraints.gridx = 1;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 2;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(lTitle,myConstraints);
        
        lMatricule = new JLabel ("Matricule : " + member.getMatricule());
        myConstraints.gridy = 1;
        add(lMatricule,myConstraints);
        
        lNom = new JLabel ("Nom : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 2;
        myConstraints.gridwidth = 1;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lNom,myConstraints);
        tNom = new JTextField(member.getNom(), 30);
        tNom.addActionListener(myAL);
        myConstraints.gridx = 2;
        add(tNom,myConstraints); 
        
        lPrenom = new JLabel("Prénom : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 3;
        add(lPrenom,myConstraints);
        tPrenom = new JTextField(member.getPrenom(), 30);
        tNom.addActionListener(myAL);
        myConstraints.gridx = 2;
        add(tPrenom,myConstraints); 
        
        lNaisDate = new JLabel ( "Date de naissance : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 4;
        add(lNaisDate,myConstraints);
        sDate = new JSpinner(new SpinnerDateModel(member.getDateNais().getTime(), earliestDate, latestDate,Calendar.MILLISECOND));
        sDate.setEditor(new DateEditor(sDate, "dd:MM:yyyy"));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sDate,myConstraints);
        
        lTelephone = new JLabel("Numéro de téléphone : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 5;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lTelephone,myConstraints);
        tTelephone = new JTextField (member.getTelephoneRes(), 30);
        tNom.addActionListener(myAL);
        myConstraints.gridx = 2;
        add(tTelephone,myConstraints);
        
        lEmail = new JLabel ( "Email : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 6;
        add(lEmail,myConstraints); 
        tEmail = new JTextField(member.getEmail(), 30);
        tNom.addActionListener(myAL);
        myConstraints.gridx = 2;
        add(tEmail,myConstraints);
        
        lPoints = new JLabel ( "Points : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 7;
        add(lPoints,myConstraints); 
        sPoints = new JSpinner(new SpinnerNumberModel(member.getPoints(),0,100,1));
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(sPoints,myConstraints);
        
        lUnite = new JLabel ("Unité : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 8;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lUnite,myConstraints); 
        tUnit = new JComboBox (tabUnit);
        tUnit.setSelectedIndex(unitIndex);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(tUnit,myConstraints);
        
        lSexe = new JLabel ("Sexe : ");
        myConstraints.gridx = 1;
        myConstraints.gridy = 9;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(lSexe,myConstraints);
        rSexH = new JRadioButton("Homme", true);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(rSexH,myConstraints);
        rSexF = new JRadioButton ("Femme");
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
        rFicheMed = new JRadioButton("Reçue", true);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(rFicheMed,myConstraints);
        rFicheMedNo = new JRadioButton ("Manquante");
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
        rAutParent = new JRadioButton("Reçue", true);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(rAutParent,myConstraints);
        rAutParentNo = new JRadioButton ("Manquante");
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
        rColis = new JRadioButton("Reçu", true);
        myConstraints.gridx = 2;
        myConstraints.anchor = GridBagConstraints.LINE_START;
        add(rColis,myConstraints);
        rColisNo = new JRadioButton ("Manquant");
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(rColisNo,myConstraints);
        colisBGroup = new ButtonGroup();
        colisBGroup.add(rColis);
        colisBGroup.add(rColisNo);
        
        butEditMemb = new JButton("Modifier");
        butEditMemb.addActionListener(myAL);
        butBackToSearch = new JButton ("Retour à la recherche");
        butBackToSearch.addActionListener(myAL);
        myConstraints.gridx = 1;
        myConstraints.gridy = 13;
        myConstraints.anchor = GridBagConstraints.LINE_END;
        add(butEditMemb,myConstraints);
        myConstraints.gridx = 2;
        myConstraints.gridy = 13;
        myConstraints.anchor = GridBagConstraints.CENTER;
        add(butBackToSearch,myConstraints);
        
        if (! member.getAutorisationP()) rAutParentNo.setSelected(true);
        if (! member.getFicheMed())rFicheMedNo.setSelected(true);
        if (! member.getColis()) rColisNo.setSelected(true);
        if (member.getSexe() == 'F') rSexF.setSelected(true);
    }
    
    private class MyActionListener implements ActionListener{
        public void actionPerformed ( ActionEvent a ) {

            if(a.getSource() == butEditMemb){
                GregorianCalendar birthG = new GregorianCalendar();
                DateFormat dF = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                Date birthD;

                try { 
                    birthD = dF.parse(sDate.getValue().toString());
                    birthG.setTime(birthD);
                } catch (ParseException ex) {}
                
                char sex;
                if(rSexH.isSelected()) sex = 'M';
                else sex = 'F';
                
                try{
                    member.setDateNais(birthG);
                    member.setNom(tNom.getText());
                    member.setPrenom(tPrenom.getText());
                    member.setEmail(tEmail.getText());
                    member.setTelephoneRes(tTelephone.getText());
                    member.setSexe(sex);
                    member.setPoints ((Integer) sPoints.getValue());
                    member.setFicheMed(rFicheMed.isSelected());
                    member.setAutorisationP(rAutParent.isSelected());
                    member.setColis(rColis.isSelected());
                    member.setIdUnite(arrayUnit.get(tUnit.getSelectedIndex()).getIdUnite());
                            
                    ApplicationController appController = new ApplicationController();
                    appController.editMembre(member);
                    
                    JOptionPane.showMessageDialog(null,"Le données du membre ont été modifiés","Modification réussie",JOptionPane.INFORMATION_MESSAGE);
                    cont.removeAll();
                    cont.add(new SearchMember(cont));
                    cont.revalidate();
                    cont.repaint();
                } catch (ConnectionException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
                } catch (MembreException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur : Membre",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(a.getSource() == butBackToSearch){
                cont.removeAll();
                cont.add(new SearchMember(cont));
                cont.revalidate();
                cont.repaint();
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
        return "SearchMember";
    }
}