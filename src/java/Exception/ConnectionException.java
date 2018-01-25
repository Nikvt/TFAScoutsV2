
package Exception;

public class ConnectionException extends Exception{
    
    private String message;
    
    
    public ConnectionException(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return  message;
    }
}
