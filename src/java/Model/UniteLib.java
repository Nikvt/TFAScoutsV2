
package Model;

public class UniteLib {
    
    private String libUnite;
    private Integer idUnite;
    
    public UniteLib (String libUnite , Integer idUnite){
        setLibUnite(libUnite);
        setIdUnite(idUnite);
    }
    
    public void setLibUnite(String libUnite){
        this.libUnite = libUnite;
    }
    
    public void setIdUnite ( Integer idUnite){
        this.idUnite = idUnite;
    }
    
    public String getLibUnite (){
        return libUnite;
    }
    
    public Integer getIdUnite (){
        return idUnite;
    }
    
    public String toString(){
        return idUnite + " " + libUnite;
    }
}
