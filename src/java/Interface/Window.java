
package Interface;

import Controller.ApplicationController;
import Exception.*;
import Model.Membre;
import Model.MembrePay;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class Window extends JFrame {
    
    private Container cont;
    private NewMember panelNewMember;
    private SearchMember panelSearchMember;
    // private ModifyMember panelModifyMember;
    private JPanel panelWelcome, panelAbout;
    private JMenu menuMenu, menuMembre, menuSearch;
    private JMenuItem itemWelcome, itemAbout, itemQuit, itemNewMember, itemSearchMember, 
                      itemMemberList, itemBudPayment, itemBudUnit , 
                      itemBudInscription, itemEnOrdre;
    private JMenuBar bar;
    private ThreadVerif thread;

    public Window (){
        
        super ("Scouts - Gestion v1.0");
        setSize(1000, 700);
        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        
        cont = getContentPane();
        
        thread = new ThreadVerif(this,cont);
        thread.start();
        MyActionListener myAL = new MyActionListener();       
        MyWindowListener w = new MyWindowListener();
        addWindowListener(w);
        
        panelWelcome = new Welcome();
        panelAbout = new About();
        panelNewMember = new NewMember();
        panelSearchMember = new SearchMember (cont);
        
        cont.add(panelWelcome);
        
        bar = new JMenuBar();
        setJMenuBar(bar);
        
        itemWelcome = new JMenuItem ("Acceuil");
        itemWelcome.addActionListener(myAL);
        itemAbout = new JMenuItem ("A propos");
        itemAbout.addActionListener(myAL);
        itemQuit = new JMenuItem ("Quitter");
        itemQuit.addActionListener(myAL);
        itemNewMember = new JMenuItem ("Ajout d'un nouveau membre");
        itemNewMember.addActionListener(myAL);
        itemSearchMember = new JMenuItem ("Recherche des membres");
        itemSearchMember.addActionListener(myAL);
        itemMemberList = new JMenuItem ("Liste des membres");
        itemMemberList.addActionListener(myAL);        
        itemBudPayment = new JMenuItem ("Recherche dans les depenses");
        itemBudPayment.addActionListener(myAL);
        itemBudUnit = new JMenuItem ("Recherche dans les budgets d'unités");
        itemBudUnit.addActionListener(myAL);
        itemBudInscription = new JMenuItem ("Recherche dans les payements d'inscription");
        itemBudInscription.addActionListener(myAL);
        itemEnOrdre = new JMenuItem ("Liste des membres pas en ordre");
        itemEnOrdre.addActionListener(myAL);
        
        menuMenu = new JMenu ("Menu");
        menuMenu.add(itemWelcome);
        menuMenu.add(itemAbout);
        menuMenu.add(itemQuit);
        bar.add(menuMenu);
                
        menuMembre = new JMenu ("Membres");
        menuMembre.add(itemNewMember);
        menuMembre.add(itemMemberList);
        menuMembre.add(itemEnOrdre);
        bar.add(menuMembre);
        
        menuSearch = new JMenu ("Recherches");
        menuSearch.add(itemSearchMember);
        menuSearch.add(itemBudPayment);
        menuSearch.add(itemBudUnit);
        menuSearch.add(itemBudInscription);
        bar.add(menuSearch);
        
        setVisible(true);
    }
    
    private class MyWindowListener extends WindowAdapter {
            public void windowClosing( WindowEvent e){
            System.exit(0);
            }
    }

    private class MyActionListener implements ActionListener{
        public void actionPerformed ( ActionEvent e ) {
            if(e.getSource() == itemWelcome){
                cont.removeAll();
                cont.add(panelWelcome);
                cont.revalidate();
                cont.repaint();
            }
            if(e.getSource() == itemAbout){
                cont.removeAll();
                cont.add(panelAbout);
                cont.revalidate();
                cont.repaint();
            }
            if(e.getSource() == itemQuit){
                int exit = JOptionPane.showOptionDialog(null, 
                            "Etes-vous sur de vouloir quitter?", 
                            "Quitter le programme", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.WARNING_MESSAGE, 
                            null, new String[]{"Oui", "Non"}, "Non");
                if(exit == 0) System.exit(0);
            }
            if(e.getSource() == itemNewMember){
                cont.removeAll();
                cont.add(panelNewMember);
                cont.revalidate(); 
                cont.repaint();
            }
            if(e.getSource() == itemSearchMember){
                cont.removeAll();
                panelSearchMember.reset();
                cont.add(panelSearchMember);
                cont.revalidate();
                cont.repaint();
            }
            if(e.getSource() == itemMemberList){
                try {
                    ApplicationController appCont = new ApplicationController();
                     ArrayList<Membre> arrayMembre = appCont.getAllMembre();
                    cont.removeAll();
                    cont.add(new MemberList(arrayMembre, cont));
                    cont.revalidate();
                    cont.repaint();
                } catch (ConnectionException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
                }catch (MembreException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Erreur : creation membre",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(e.getSource() == itemBudPayment){
                cont.removeAll();
                cont.add(new BudgetPayment());
                cont.revalidate();
                cont.repaint();
            }
            if(e.getSource() == itemBudUnit){
                cont.removeAll();
                cont.add(new BudgetUnit());
                cont.revalidate();
                cont.repaint();
            }
            if(e.getSource() == itemBudInscription){
                cont.removeAll();
                cont.add(new BudgetPayementInscription());
                cont.revalidate(); 
                cont.repaint();
            }
            if(e.getSource() == itemEnOrdre){
                try {
                    ApplicationController appCont = new ApplicationController();
                    ArrayList<MembrePay> arrayMembrePay = appCont.enOrdre();
                    cont.removeAll();
                    cont.add(new EnOrdre(arrayMembrePay));
                    cont.revalidate(); 
                    cont.repaint();
                } catch (ConnectionException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Erreur : connection BD",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    
    
    
    public void verifNewMember(){
        panelNewMember.verif();
    }
    public void verifModifyMemeber(){
        //panelModifyMember.verif();
    }
    public void refreshThread(){
        thread.refreshThread( this ,cont);
    }

}
