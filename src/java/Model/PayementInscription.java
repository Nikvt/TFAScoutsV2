
package Model;

import java.util.GregorianCalendar;

public class PayementInscription {
    
    private GregorianCalendar date;
    private double montant;
    private String nom , prenom , telephone , localite;
    
    public PayementInscription(GregorianCalendar date , double montant ,
            String nom , String prenom , String telephone , String localite){
        this.date = date;
        this.montant = montant;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.localite = localite;
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
    
    public String toString (){
        return " #" + montant + "#" + nom + "#" + prenom + "#" + telephone + "#" + localite;
    }
}
