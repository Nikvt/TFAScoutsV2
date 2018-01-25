
package Model;

import java.util.GregorianCalendar;

public class Depense {
    
    private String budget , nom , prenom;
    private Integer idPayement;
    private double montant, total;
    private GregorianCalendar dateAchat;
    private boolean valide;
    
    public Depense (String budget , String nom , String prenom , int idPayement,
    double montant, double total , GregorianCalendar dateAchat , boolean valide){
        this.total = total;
        this.budget = budget;
        this. nom = nom;
        this.prenom = prenom;
        this. idPayement = idPayement;
        this.montant = montant;
        this.dateAchat = dateAchat;
        this.valide = valide;
    }
    
    public boolean getValide(){
        return valide;
    }
    
    public GregorianCalendar getDate(){
        return dateAchat;
    }
    
    public String toString (){
        return budget + "#" + total + "#" + idPayement + "#" + montant + "# #" + nom + "#" + prenom;
    }
}
