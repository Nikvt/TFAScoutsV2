
package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import Exception.*;

public interface DataAccess {
    
    public void addMembre( Membre membre ) throws ConnectionException ;
    
    public void editMembre ( Membre membre  )throws ConnectionException ;
    
    public ArrayList<Membre> getAllMembre ()throws ConnectionException , MembreException;
    
    public ArrayList<Membre> getMembre (Integer matriculeQ , String nomQ , String prenomQ , 
            Character sexeQ , GregorianCalendar dateNaisQ , String telephoneResQ , 
            Boolean ficheMedQ , Boolean autorisationPQ, String emailQ , Boolean colisQ ,
            Integer pointsQ , Integer idUnite) throws ConnectionException  , MembreException;
    
    public ArrayList<Depense> getDepense ( int idUnite , boolean fonctionnel,
            boolean Exceptionnel , boolean quotidien , double minMontant ,
            double maxMontant , boolean valide )throws ConnectionException ; 
    
    public ArrayList<PayementInscription> getPayementInscription ( int idUnite,
            GregorianCalendar dateMin , GregorianCalendar dateMAx ,
            double minMontant , double maxMontant )throws ConnectionException ;
    
    public ArrayList<PayementOrdre> getPayementOrdre ()throws ConnectionException ;
    
    public void VerifMembre ( String nom , String prenom)throws ConnectionException ,MembreExistantException;
    
    public ArrayList<Unite> getUnite ( ArrayList<Integer> listeIdUnite)throws ConnectionException ;
    
    public ArrayList <UniteLib> getAllUnite ()throws ConnectionException ;
    
    public void delMembre ( int matricule)throws ConnectionException ;
}
