
package Exception;

public class MembreExistantException extends Exception{
    
    private int nombreM;
    
    public MembreExistantException ( int nombreM){
        this.nombreM = nombreM;
    }
    
    public String getMessage(){
        return "Attention il existe déjà"+nombreM+" membre(s) avec ce nom et ce prénom";
    }
}
