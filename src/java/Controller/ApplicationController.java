
package Controller;

import Model.*;
import BusinessLogic.*;
import Exception.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ApplicationController {
    
    private Manager membreManager;
    
    public ApplicationController (){
        membreManager = new Manager();
    }
    
    public void addMembre( Membre membre ) throws ConnectionException {
        membreManager.addMembre(membre);
    }
    
    public void editMembre ( Membre membre  ) throws ConnectionException{
        membreManager.editMembre(membre );
    }
    
    public ArrayList<Membre> getAllMembre () throws ConnectionException , MembreException{
        return membreManager.getAllMembre();
    }
    
    public ArrayList<Membre> getMembre (Integer matriculeQ , String nomQ , String prenomQ , 
            Character sexeQ , GregorianCalendar dateNaisQ , String telephoneResQ , 
            Boolean ficheMedQ , Boolean autorisationPQ, String emailQ , Boolean colisQ ,
            Integer pointsQ , Integer idUniteQ ) throws ConnectionException  , MembreException{
        return membreManager.getMembre(matriculeQ , nomQ , prenomQ , sexeQ , dateNaisQ , telephoneResQ , ficheMedQ , autorisationPQ , emailQ , colisQ , pointsQ , idUniteQ);
    }
    
    public ArrayList<Depense> getDepense ( int idUnite , boolean fonctionnel , boolean exceptionnel , boolean quotidien , double minMontant , double maxMontant , boolean valide ) throws ConnectionException{
        return membreManager.getDepense (idUnite , fonctionnel , exceptionnel , quotidien , minMontant , maxMontant , valide);
    }
    
    public ArrayList<PayementInscription> getPayementInscription ( int idUnite , GregorianCalendar dateMin , GregorianCalendar dateMax , double minMontant , double maxMontant ) throws ConnectionException{
        return membreManager.getPayementInscription ( idUnite , dateMin , dateMax , minMontant , maxMontant);
    }
    
    public void VerifMembre ( String nom , String prenom)throws ConnectionException ,MembreExistantException{
        membreManager.VerifMembre(nom,prenom);
    }
    
    public ArrayList<Unite> getUnite ( ArrayList<Integer> listeIdUnite) throws ConnectionException{
        return membreManager.getUnite( listeIdUnite );
    }
    
    public ArrayList<UniteLib> getAllUnite () throws ConnectionException{
        return membreManager.getAllUnite();
    }
    
    public void delMembre ( int matricule) throws ConnectionException{
        membreManager.delMembre (matricule);
    }
    
    public ArrayList<MembrePay> enOrdre ( ) throws ConnectionException{
        return membreManager.enOrdre();
    }
}
