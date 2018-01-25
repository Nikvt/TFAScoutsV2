
package BusinessLogic;

import Model.*;
import DataAccess.*;
import Exception.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class Manager {
    
    private DataAccess da;
    
    public Manager (){
        this.da = new SqlDBAccess();
    }
    
    public void addMembre( Membre membre ) throws ConnectionException  {
        da.addMembre(membre);
    }
    
    public void editMembre ( Membre membre  ) throws ConnectionException{
        da.editMembre(membre );
    }
    
    public ArrayList<Membre> getAllMembre () throws ConnectionException , MembreException{
        return da.getAllMembre();
         
    }
    
    public ArrayList<Membre> getMembre (Integer matriculeQ , String nomQ , String prenomQ , 
            Character sexeQ , GregorianCalendar dateNaisQ , String telephoneResQ , 
            Boolean ficheMedQ , Boolean autorisationPQ, String emailQ , Boolean colisQ ,
            Integer pointsQ , Integer idUniteQ) throws ConnectionException  , MembreException{
        return da.getMembre(matriculeQ , nomQ , prenomQ , sexeQ , dateNaisQ , telephoneResQ , ficheMedQ , autorisationPQ , emailQ , colisQ , pointsQ , idUniteQ);
    }
    
    public ArrayList<Depense> getDepense ( int idUnite , boolean fonctionnel , boolean exceptionnel , boolean quotidien , double minMontant , double maxMontant , boolean valide ) throws ConnectionException{
        return da.getDepense (idUnite , fonctionnel , exceptionnel , quotidien , minMontant , maxMontant , valide);
         
    }
    
    public ArrayList<PayementInscription> getPayementInscription ( int idUnite , GregorianCalendar dateMin , GregorianCalendar dateMax , double minMontant , double maxMontant ) throws ConnectionException{
        return da.getPayementInscription ( idUnite , dateMin , dateMax , minMontant , maxMontant);
    }
    
    public void VerifMembre ( String nom , String prenom)throws ConnectionException ,MembreExistantException{
        da.VerifMembre(nom,prenom);
    }
    
    public ArrayList<Unite> getUnite ( ArrayList<Integer> listeIdUnite) throws ConnectionException{
        return da.getUnite( listeIdUnite );
    }
    
    public void delMembre ( int matricule) throws ConnectionException{
        da.delMembre(matricule);
    }
    
    public ArrayList<UniteLib> getAllUnite () throws ConnectionException{
        return da.getAllUnite();
    }
    
    public ArrayList<MembrePay> enOrdre ( ) throws ConnectionException{
        ArrayList <PayementOrdre> listePayements = da.getPayementOrdre();
        ArrayList <MembrePay> listeMembres = new ArrayList <MembrePay> ();
        List <MembrePay> membres = Collections.synchronizedList(listeMembres);
        double montantTotal , montantDejaPaye;
        String nom , prenom , localite , telephone, unite;
        GregorianCalendar date;
        int saveMatricule;
        
        for (  int i = 0; i < listePayements.size() ; ){
            montantTotal = 100;//listePayements.get(i).getDate().YEAR*0.05;
            montantDejaPaye = 0;
            nom = listePayements.get(i).getNom();
            prenom = listePayements.get(i).getPrenom();
            date = listePayements.get(i).getDate();
            localite = listePayements.get(i).getLocalite();
            telephone = listePayements.get(i).getTelephone();
            unite = listePayements.get(i).getUnite();
            saveMatricule = listePayements.get(i).getMatricule();
            while ( i < listePayements.size() && saveMatricule == listePayements.get(i).getMatricule() ){
                montantDejaPaye += listePayements.get(i).getMontant();
                i++;
            }
            if (montantTotal >= montantDejaPaye){
                membres.add(new MembrePay (date , montantTotal , montantDejaPaye , nom ,prenom , telephone , localite , unite ));
            }
        }
        return listeMembres;
    }
}
