
package Interface;

import java.awt.Container;

public class ThreadVerif extends Thread {
    
    private Container cont;
    private Window window;
    
    public ThreadVerif ( Window window , Container cont){
        this.cont = cont;
        this.window = window;
    }
    public void run(){
        while ( 1==1 ){
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            //TRAITEMENT
            window.refreshThread();
            for (int i =0 ; i< cont.getComponentCount(); i++){
                System.out.println(cont.getComponent(i).toString());
                if (cont.getComponent(i).toString().equals("NewMember")==true)
                    window.verifNewMember();
                if (cont.getComponent(i).toString().equals("ModifyMemeber")==true)
                    window.verifModifyMemeber();
            }
        }
    }
    
    public void refreshThread ( Window window , Container cont){
        this.cont = cont;
        this.window = window;
    }
}