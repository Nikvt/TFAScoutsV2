package Model;

import java.util.GregorianCalendar;

public class PayementOrdre {
    private GregorianCalendar date;
    private double montant;
    private int matricule;
    private String nom , prenom , telephone , localite , libUnite;
    
    public PayementOrdre(int matricule, GregorianCalendar date , double montant ,
            String nom , String prenom , String telephone , String localite , String libUnite){
        this.matricule = matricule;
        this.date = date;
        this.montant = montant;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.localite = localite;
        this.libUnite = libUnite;
    }
    
    public String getUnite(){
        return libUnite;
    }
    
    public GregorianCalendar getDate (){
        return date;
    }
    
    public double getMontant (){
        return montant;
    }
    
    public String getNom(){
        return nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public String getTelephone(){
        return telephone;
    }
    
    public String getLocalite(){
        return localite;
    }
    
    public int getMatricule(){
        return matricule;
    }
}
