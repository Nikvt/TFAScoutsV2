
package Exception;


public class MembreException extends Exception{
    private String message;
    
    public MembreException(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return "Erreur lors de la création du membre\n"+
                message;
    }
}
