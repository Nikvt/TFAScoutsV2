
package DataAccess;

import Exception.*;
import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqlDBAccess implements DataAccess {
    
    public SqlDBAccess (){
        
    }
        
    public void addMembre( Membre membre ) throws ConnectionException  { 
        
        Connection connection = ConnectionSimple.getInstance();
        String instructionSQL = "insert into Membre (nom , prenom , sexe , "
                + "dateNais , telephoneRes , ficheMed , autorisationP , email ,"
                + "colis , points , idUnite)"
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
            prepStat.setString(1,membre.getNom());
            prepStat.setString(2,membre.getPrenom());
            prepStat.setString(3,membre.getSexe() + "" );
            prepStat.setDate(4,new java.sql.Date(membre.getDateNais().getTimeInMillis()));
            prepStat.setString(5,membre.getTelephoneRes());
            prepStat.setBoolean(6,membre.getFicheMed());
            prepStat.setBoolean(7,membre.getAutorisationP());
            prepStat.setString(8,membre.getEmail());
            prepStat.setBoolean(9, membre.getColis());
            prepStat.setInt(10,membre.getPoints());
            prepStat.setInt(11,membre.getIdUnite());
            int nbLignes = prepStat.executeUpdate();
        }
        catch (SQLException e){
            throw new ConnectionException(e.getMessage());
        }
    }
    
    public void editMembre ( Membre membre )throws ConnectionException {
        
        Connection connection = ConnectionSimple.getInstance();
        String instructionSQL = "update Membre "
                                + " set nom = ?,"
                                + " prenom = ?,"
                                + " sexe = ?,"
                                + " dateNais = ?,"
                                + " telephoneRes = ?,"
                                + " ficheMed = ?,"
                                + " autorisationP = ?,"
                                + " email = ?,"
                                + " colis = ?,"
                                + " points = ?,"
                                + " idUnite = ?"
                                + " where matricule = ?";
        try{
            PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
            prepStat.setString(1,membre.getNom());
            prepStat.setString(2,membre.getPrenom());
            prepStat.setString(3,membre.getSexe()+"");
            prepStat.setDate(4,new java.sql.Date(membre.getDateNais().getTimeInMillis()));
            prepStat.setString(5,membre.getTelephoneRes());
            prepStat.setBoolean(6,membre.getFicheMed());
            prepStat.setBoolean(7,membre.getAutorisationP());
            prepStat.setString(8,membre.getEmail());
            prepStat.setBoolean(9, membre.getColis());
            prepStat.setInt(10,membre.getPoints());
            prepStat.setInt(11,membre.getIdUnite());
            prepStat.setInt(12,membre.getMatricule());
            int nbLignes = prepStat.executeUpdate();
        }
        catch (SQLException e){
            throw new ConnectionException("Il y a eu un problème lors de la modification de ce memrbe : "+e.getMessage());
        }
    }
    
    public ArrayList<Membre> getAllMembre ()throws ConnectionException , MembreException{
        
        ArrayList <Membre> arrayMembres = new ArrayList <Membre> ();
        List <Membre> membres = Collections.synchronizedList(arrayMembres);
        
        Connection connection = ConnectionSimple.getInstance();
        String instructionSQL = "select * from membre";
        
        try{
            PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
            ResultSet donnees = prepStat.executeQuery();
            String nom , prenom , telephoneRes , email;
            Integer points ;
            int matricule , idUnite;
            Character sexe;
            Boolean autorisationP;
            boolean  colis, ficheMed ;
            GregorianCalendar dateNais;

            while(donnees.next()){
                matricule = donnees.getInt("matricule");
                nom = donnees.getString("Nom");
                prenom = donnees.getString("Prenom");
                sexe = donnees.getString("Sexe").charAt(0);
                dateNais = new GregorianCalendar();
                dateNais.setTime(donnees.getDate("DateNais"));
                telephoneRes = donnees.getString("TelephoneRes");
                ficheMed = donnees.getBoolean("FicheMed");
                autorisationP = donnees.getBoolean("AutorisationP");
                email = donnees.getString("Email");
                colis = donnees.getBoolean("Colis");
                points = donnees.getInt("Points");
                idUnite = donnees.getInt("idUnite");
                membres.add (new Membre(matricule ,nom , prenom , sexe , dateNais 
                        , telephoneRes , email , points , ficheMed , autorisationP , colis , idUnite ));
            }
        }
        catch (SQLException e){
            throw new ConnectionException("Il y a eu un problème lors de la "
                    + "création de la liste de tous les membres :"+e.getMessage());
        }
        return arrayMembres;
    }
    
    public ArrayList<Membre> getMembre (Integer matriculeQ , String nomQ , String prenomQ , 
            Character sexeQ , GregorianCalendar dateNaisQ , String telephoneResQ , 
            Boolean ficheMedQ , Boolean autorisationPQ, String emailQ , Boolean colisQ ,
            Integer pointsQ , Integer idUniteQ ) throws ConnectionException , MembreException {
        if (nomQ == null)
            nomQ = "";
        if (prenomQ == null)
            prenomQ = "";
        if (telephoneResQ ==null)
            telephoneResQ = "";
        if (emailQ == null)
            emailQ = "";
        ArrayList <Membre> arrayMembres = new ArrayList <Membre> ();
        List <Membre> membres = Collections.synchronizedList(arrayMembres);
        Connection connection = ConnectionSimple.getInstance();
        String instructionSQL = "select * from membre";
        instructionSQL +=" where nom like ? "
                + " and prenom like ? "
                + " and telephoneRes like ? ";
        if (emailQ != "")
            instructionSQL += " and email like ? ";
        if (matriculeQ != null)
            instructionSQL += " and matricule = ? ";
        if (sexeQ != null)
            instructionSQL += " and sexe = ? ";
        if (dateNaisQ != null)
            instructionSQL += " and dateNais = ? ";
        if (ficheMedQ != null)
            instructionSQL += " and ficheMed = ? ";
        if (autorisationPQ != null)
            instructionSQL += " and autorisationP = ? ";
        if ( colisQ != null)
            instructionSQL += " and colis = ? ";
        if (pointsQ != null)
            instructionSQL += " and points = ? ";
        if (idUniteQ != null)
            instructionSQL += " and idUnite = ? ";
        try{
            PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
            // ici je commence a Remplacer les ?
            int i = 1;
            prepStat.setString(i,"%"+nomQ+"%");
            i++;
            prepStat.setString(i, "%"+prenomQ+"%");
            i++;
            prepStat.setString(i , "%"+telephoneResQ+"%");
            i++;
            if (emailQ != ""){
            prepStat.setString(i , "%"+emailQ+"%");
            i++;
            }
            if (matriculeQ != null){
                prepStat.setInt(i , matriculeQ );
                i++;
            }
            if (sexeQ != null){
                prepStat.setString(i, sexeQ+"");
                i++;
            }
            if (dateNaisQ != null){
                prepStat.setDate(i, new java.sql.Date(dateNaisQ.getTimeInMillis()));
                i++;
            }
            if (ficheMedQ != null){
                prepStat.setBoolean(i,ficheMedQ);
                i++;
            }
            if (autorisationPQ != null){
                prepStat.setBoolean(i,autorisationPQ);
                i++;
            }
            if ( colisQ != null){
                prepStat.setBoolean(i,colisQ);
                i++;
            }
            if (pointsQ != null){
                prepStat.setInt(i,pointsQ);
                i++;
            }
            if (idUniteQ != null){
                prepStat.setInt(i,idUniteQ);
                i++;
            }
            
            ResultSet donnees = prepStat.executeQuery();
            String nom , prenom , telephoneRes , email;
            Integer points ;
            int matricule , idUnite;
            Character sexe;
            Boolean autorisationP;
            boolean  colis, ficheMed ;
            GregorianCalendar dateNais;

            while(donnees.next()){
                matricule = donnees.getInt("matricule");
                nom = donnees.getString("Nom");
                prenom = donnees.getString("Prenom");
                sexe = donnees.getString("Sexe").charAt(0);
                dateNais = new GregorianCalendar();
                dateNais.setTime(donnees.getDate("DateNais"));
                telephoneRes = donnees.getString("TelephoneRes");
                ficheMed = donnees.getBoolean("FicheMed");
                autorisationP = donnees.getBoolean("AutorisationP");
                email = donnees.getString("Email");
                colis = donnees.getBoolean("Colis");
                points = donnees.getInt("Points");
                idUnite = donnees.getInt("idUnite");
                membres.add (new Membre(matricule ,nom , prenom , sexe , dateNais , telephoneRes , email , points , ficheMed , autorisationP , colis , idUnite ));
            }
        }
        catch (SQLException e){
            throw new MembreException ("Il y a eu une erreur lors de la "
                    + "recherche par membre : "+e.getMessage());
        }
        return arrayMembres;
    }
    
    public ArrayList<Depense> getDepense ( int idUnite , boolean fonctionnel,
            boolean exceptionnel , boolean quotidien , double minMontant ,
            double maxMontant , boolean valide )throws ConnectionException {
        ArrayList <Depense> arrayDepenses = new ArrayList <Depense> ();
        List <Depense> depenses = Collections.synchronizedList(arrayDepenses);
        Connection connection = ConnectionSimple.getInstance();
        String instructionSQL = " select b.libelleBud , m.nom , m.prenom , p.idPayement , p.montant ,b.total, p.dateAchat  , p.validation\n" +
                " from Payement  p" +
                " join Budget b on (p.libelleBud = b.libelleBud and p.idUnite = b.idUnite)" +
                " join Membre m on (p.matricule = m.matricule)"+
                " where p.montant between ? and ? "+
                " and p.validation= ?"+
                " and p.idUnite = ? ";
        
        if (fonctionnel==false){
            instructionSQL += " and b.libelleBud != 'Fonctionnel'";
        }
        if (exceptionnel == false){
            instructionSQL += " and b.libelleBud != 'Exceptionnel'";
        }
        if (quotidien == false){
            instructionSQL += " and b.libelleBud != 'Quotidien'";
        }
        try{
            PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
            prepStat.setDouble(1, minMontant);
            prepStat.setDouble(2, maxMontant);
            prepStat.setBoolean(3, valide);
            prepStat.setInt(4,idUnite);
            ResultSet donnees = prepStat.executeQuery();
            
            String budget , nom , prenom;
            Integer idPayement;
            double montant , total;
            boolean validation;
            GregorianCalendar dateAchat;
            while (donnees.next()){
                budget = donnees.getString(1);
                nom = donnees.getString(2);
                prenom = donnees.getString(3);
                idPayement = donnees.getInt(4);
                montant = donnees.getDouble(5);
                total = donnees.getDouble(6);
                dateAchat = new GregorianCalendar();
                dateAchat.setTime(donnees.getDate(7));
                valide = donnees.getBoolean(8);
                depenses.add(new Depense(budget , nom , prenom , idPayement , 
                        montant , total , dateAchat , valide));
            }
        }
        catch (SQLException e){
            throw new ConnectionException("Il y a eu une erreur lors de la "
                    + "création de la liste des dépenses"+e.getMessage());
        }
        return arrayDepenses;
    }
    
    public ArrayList<PayementInscription> getPayementInscription ( int idUnite,
            GregorianCalendar dateMin , GregorianCalendar dateMax ,
            double minMontant , double maxMontant )throws ConnectionException {
        ArrayList <PayementInscription> arrayPayementInscriptions = new ArrayList <PayementInscription> ();
        List <PayementInscription> payementInscriptions = Collections.synchronizedList(arrayPayementInscriptions);
        Connection connection = ConnectionSimple.getInstance();
        String instructionSQL = "select distinct m.nom, m.prenom, p.datePay , p.montant , "
                + " m.telephoneRes , a.commune"
                + " from Payement_Inscription p"
                + " join Membre m on (p.matricule = m.matricule)"
                + " join Adresse a on (m.idAdr = a.idAdr)"
                + " where m.idUnite = ?"
                + " and p.datePay between ? and ?"
                + " and montant between ? and ?";
        try{
            PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
            prepStat.setInt(1,idUnite);
            
            prepStat.setDate(2, new java.sql.Date(dateMin.getTimeInMillis())); //DATE
            prepStat.setDate(3, new java.sql.Date(dateMax.getTimeInMillis())); //DATE
            prepStat.setDouble(4, minMontant);
            prepStat.setDouble(5, maxMontant);
            ResultSet donnees = prepStat.executeQuery();

            GregorianCalendar date;
            double montant;
            String nom, prenom, telephone, localite;

            while (donnees.next()){
                nom = donnees.getString(1);
                prenom = donnees.getString(2);
                date = new GregorianCalendar();
                date.setTime(donnees.getDate(3));
                montant = donnees.getDouble(4);
                telephone = donnees.getString(5);
                localite = donnees.getString(6);
                payementInscriptions.add(new PayementInscription(date , montant , nom , prenom , telephone , localite));
            }
        }
        catch (SQLException e){
            throw new ConnectionException("Il y a eu un problème lors de la création "
                    + "de la liste des payements réservés à la partie inscription : "+e.getMessage());
        }
        return arrayPayementInscriptions;
    }
    
    public ArrayList<PayementOrdre> getPayementOrdre ()throws ConnectionException{
        ArrayList <PayementOrdre> arrayPayementInscriptions = new ArrayList <PayementOrdre> ();
        List <PayementOrdre> payementInscriptions = Collections.synchronizedList(arrayPayementInscriptions);
        Connection connection = ConnectionSimple.getInstance();
        String instructionSQL = " select m.matricule, m.nom , m.prenom , m.telephoneRes , "
                + " a.commune , p.montant , u.libelleUni , p.datePay"
                + " from Membre m"
                + " join Payement_Inscription p on ( m.matricule = p.matricule )"
                + " join Unite u on ( m.idUnite = u.idUnite )"
                + " join Adresse a on (m.idAdr = a.idAdr)";
        try {
            PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
            ResultSet donnees = prepStat.executeQuery();
            int matricule;
            GregorianCalendar date;
            double montant;
            String nom , prenom , telephone , localite , libUnite;
                   
            while (donnees.next()){
                matricule = donnees.getInt(1);
                nom = donnees.getString(2);
                prenom = donnees.getString(3);
                telephone = donnees.getString(4);
                localite= donnees.getString(5);
                montant = donnees.getDouble(6);
                libUnite = donnees.getString(7);
                date = new GregorianCalendar();
                date.setTime(donnees.getDate(8));
                payementInscriptions.add(new PayementOrdre(matricule ,date , montant , nom , prenom , telephone, localite, libUnite));
            }
        }
        catch (SQLException e){
            throw new ConnectionException("Il y a eu un problème lors de la création de la liste des personnes qui ne sont pas en ordre :" + e.getMessage());
        }
        return arrayPayementInscriptions;
    }
    
    public void VerifMembre ( String nom , String prenom)throws ConnectionException ,MembreExistantException{
        int i=0;
        Connection connection = ConnectionSimple.getInstance();
        String instructionSQL = " select * from membre"
                + " where nom = ? "
                + " and prenom = ?";
        try{
            PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
            prepStat.setString(1,nom);
            prepStat.setString(2,prenom);
            ResultSet donnees = prepStat.executeQuery();
            while (donnees.next()){
                i++;
            }
        }
        catch (SQLException e){
            throw new ConnectionException("Il y a eu un problème lors de la "
                    + "vérification des paramètres introduits dans la base de donnée.");
        }
        if (i > 0){
            throw new MembreExistantException(i);
        }
    }
    
    public ArrayList<Unite> getUnite ( ArrayList<Integer> listeIdUnite)throws ConnectionException {
        
        String libelleUni , commune ;
        double qTotal , eTotal , fTotal;
        
        ArrayList <Unite> arrayUnites = new ArrayList <Unite> ();
        List <Unite> unites = Collections.synchronizedList(arrayUnites);
        Connection connection = ConnectionSimple.getInstance();
            
        for (int i = 0 ; i<listeIdUnite.size() ; i++){
            String instructionSQL = "select distinct u.libelleUni , a.commune , q.total ,"
                    + " e.total , f.total "
                    + " from Unite u"
                    + " join Adresse a on (u.idAdr = a.idAdr)"
                    + " join Budget q on (u.idUnite = q.idUnite and q.libelleBud = 'Quotidien')"
                    + " join Budget e on (u.idUnite = e.idUnite and e.libelleBud = 'Exceptionnel')"
                    + " join Budget f on (u.idUnite = f.idUnite and f.libelleBud = 'Fonctionnel')"
                    + " where u.idUnite = ?";

            try{
                PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
                prepStat.setInt(1, listeIdUnite.get(i));
                ResultSet donnees = prepStat.executeQuery();
                donnees.next();
                libelleUni = donnees.getString(1);
                commune = donnees.getString(2);
                qTotal = donnees.getDouble(3);
                eTotal = donnees.getDouble(4);
                fTotal = donnees.getDouble(5);
                unites.add(new Unite(libelleUni , commune , qTotal , eTotal , fTotal));
            }
            catch (SQLException e){
                throw new ConnectionException ( "Il y a eu un problème de traitement des unités : "+e.getMessage());
            }
        }
        return arrayUnites;
    }

    public ArrayList <UniteLib> getAllUnite ()throws ConnectionException {

        ArrayList <UniteLib> arrayUnites = new ArrayList <UniteLib> ();
        List <UniteLib> unites = Collections.synchronizedList(arrayUnites);
        Connection connection = ConnectionSimple.getInstance();
        String instructionSQL = "select * from Unite";
        try{
            PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
            ResultSet donnees = prepStat.executeQuery();
            Integer idUnite , idAdr;
            String libelleUnite;
            while (donnees.next()){
                    idUnite = donnees.getInt("idUnite");
                    libelleUnite = donnees.getString ("libelleUni");
                    unites.add(new UniteLib( libelleUnite , idUnite));
            }
        }
        catch (SQLException e){
            throw new ConnectionException("Il y a eu un problème lors de la création de la liste de toutes les unités : "+ e.getMessage() );
        }
        return arrayUnites;
    }
    
    public void delMembre ( int matricule)throws ConnectionException {
        Connection connection = ConnectionSimple.getInstance();
        String instructionSQL = "delete from Membre "
                + "where matricule = ?";
        try{
            PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
            prepStat.setInt (1 , matricule);
            int nbLignes = prepStat.executeUpdate();
        }
        catch (SQLException e){
            throw new ConnectionException ("Il y a eu une erreur lors de la suppression de ce membre : "+ e.getMessage() );
        }
    }
}
