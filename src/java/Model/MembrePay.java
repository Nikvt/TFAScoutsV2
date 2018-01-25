
package Model;

import java.util.GregorianCalendar;

public class MembrePay {
    
    private GregorianCalendar date;
    private double montantTotal , montantDejaPaye;
    private String nom , prenom , telephone , localite , unite;
    
    public MembrePay(GregorianCalendar date , double montantTotal , double montantDejaPaye,
            String nom , String prenom , String telephone , String localite,String unite){
        this.date = date;
        this.montantTotal = montantTotal;
        this.montantDejaPaye = montantDejaPaye;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.localite = localite;
        this.unite = unite;
    }
    
    public MembrePay(){
        
    }
    
    public void setNom ( String nom){
        this.nom = nom;
    }
    
    public void setPrenom ( String prenom ){
        this.prenom = prenom;
    }
    
    public void setDate (GregorianCalendar date){
        this.date = date;
    }
    
    public void setMontantTotal (double montantTotal){
        this.montantTotal = montantTotal;
    }
    
    public void setMontantDejaPaye (double montantDejaPaye){
        this.montantDejaPaye = montantDejaPaye;
    }
    
    public void setTelephone (String telephone){
        this.telephone = telephone;
    }
    
    public void setLocalite (String localite){
        this.localite = localite;
    }
    
    public GregorianCalendar getDate (){
        return date;
    }
    
    public String toString(){
        return nom + "#" + prenom + "#" + telephone + "#" + unite + "#" + localite + "# #" + montantTotal + "#" + montantDejaPaye;
    }
}
