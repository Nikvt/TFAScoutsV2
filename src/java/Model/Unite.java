
package Model;

public class Unite {
    
    private String libUnite , localite;
    private double quotidien , exceptionnel , fonctionnel;
    
    public Unite (String libUnite , String localite , double quotidien , 
    double exceptionnel ,double fonctionnel){
        this.libUnite = libUnite;
        this.localite = localite;
        this.quotidien = quotidien;
        this.exceptionnel = exceptionnel;
        this.fonctionnel = fonctionnel;
    }
    
    public String toString(){
        return  libUnite + "#" + localite + "#" + quotidien + "#" + fonctionnel + "#" + exceptionnel;
    }
}
