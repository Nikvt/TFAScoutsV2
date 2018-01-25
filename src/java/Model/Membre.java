
package Model;

import java.util.*;
import Exception.*;
import static java.lang.Character.*;
import java.util.regex.Pattern;


public class Membre {
    
    private String nom , prenom , telephoneRes , email;
    private Integer points ;
    private int matricule , idUnite;
    private Character sexe;
    private Boolean autorisationP;
    private boolean  colis, ficheMed ;
    private GregorianCalendar dateNais;
    
    public Membre ( int matricule , String nom , String prenom , Character sexe ,
    GregorianCalendar dateNais , String telephoneRes , String email , Integer points,
    Boolean ficheMed , Boolean autorisationP , boolean colis , int idUnite) throws MembreException{
        setMatricule (matricule);
        setNom(nom);
        setPrenom(prenom);
        setSexe(sexe);
        setDateNais(dateNais);
        setTelephoneRes(telephoneRes);
        setEmail(email);
        setPoints(points);
        this.ficheMed = ficheMed;  
        this.autorisationP = autorisationP;
        this.colis = colis;
        this.idUnite = idUnite;
    }
    
    public void setMatricule( int matricule ){
        this.matricule = matricule;
    }
    
    public void setNom(String nom) throws MembreException{
       if(Pattern.matches("^[A-Z].[a-zçéèàùïëê]*([-\\s][A-Z][a-zçéèàùïëê]+)*$", nom) == false){
            throw new MembreException( "Le nom "+nom+" ne convient pas." );
        }
        this.nom = nom;
    }
    
    public void setPrenom (String prenom) throws MembreException {
        if(Pattern.matches("^[A-Z].[a-zçéèàùïëê]*([-\\s][A-Z][a-zçéèàùïëê]+)*$", prenom) == false){
            throw new MembreException( "Le prenom "+ prenom +" ne convient pas." );
        }
        this.prenom = prenom;
    }
    
    public void setSexe (char sexe)throws MembreException {
        if (sexe != 'f' && sexe != 'm' && sexe != 'F' && sexe != 'M'){
            throw new MembreException("Le sexe "+sexe+" ne convient pas.");
        } 
        this.sexe = sexe;
    }
    
    public void setDateNais( GregorianCalendar dateNais){
        this.dateNais = dateNais;
    }
    
    public void setTelephoneRes(String telephoneRes)throws MembreException {
        if(telephoneRes != null && telephoneRes.length()<6 )
            throw new MembreException("Le numéro de téléphone est trop court");
        if (telephoneRes != null && Pattern.matches("^[0-9]+([\\/][0-9]+)*$", telephoneRes) == false){
            throw new MembreException("Le numéro de téléphone ne doit contenir que des nombres ou /");
        }
        this.telephoneRes = telephoneRes;
    }
    
    public void setEmail (String email)throws MembreException{
        if(email != null && Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", email.toLowerCase()) == false){
        // ^  début de texte , +  1 caractère ou plus , . dans le texte , *  0 ou plus , $ en fin de texte
        throw new MembreException(" L'email "+ email +" n'est pas bien composé.");
        }
        this.email = email;
    }
    
    public void setPoints (int points)throws MembreException{
        if (points < 0 || points > 100){
            throw new MembreException("Le nombre de points doit se trouver entre 0 et 100");
        }
        this.points = points;
    }
    
    public void setFicheMed (boolean ficheMed){
        this.ficheMed = ficheMed;
    }
    
    public void setColis (boolean colis){
        this.colis = colis;
    }
    
    public void setAutorisationP (boolean autorisationP){
        this.autorisationP = autorisationP;
    }
    
    public void setIdUnite (int idUnite){
        this.idUnite = idUnite;
    }
    
    public String getNom(){
        return nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public Integer getMatricule(){
        return matricule;
    }
    
    public boolean getAutorisationP(){
        return autorisationP;
    }
    
    public boolean getFicheMed(){
        return ficheMed;
    }
    
    public boolean getColis(){
        return colis;
    }
    
    public char getSexe(){
        return sexe;
    }
    
    public int getPoints(){
        return points;
    }
    
    public String getTelephoneRes(){
        return telephoneRes;
    }
    
    public String getEmail(){
        return email;
    }
    
    public int getIdUnite(){
        return idUnite;
    }
    
    public GregorianCalendar getDateNais (){
        return dateNais;
    }
    
    public String toString (){
        String sex;
        if (sexe == 'f' || sexe == 'F') sex = "Femme";
        else sex = "Homme";
        return nom + "#" + prenom + "#" + sex + "# #" + telephoneRes + "#" + email;
    }
    
}
