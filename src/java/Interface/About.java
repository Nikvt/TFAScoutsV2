
package Interface;

import java.awt.*;
import javax.swing.*;

public class About extends JPanel{
    
    private JLabel texte;
        
    public About() {
                
        setBounds(50,50,380,200);
        
        setLayout(new FlowLayout( ));
        
        
        texte = new JLabel ("<html><p><span style=\"font-family: 'arial black', 'Je suis un programme.';\"><br /></p>\n" +
        "<p>Je m'occupe des scouts.</p>" +
        "<p>Tu veux me contacter?</p><br />"+
        "<p>C'est triste, puisque je ne te laisse pas les coordonnées pour le faire...</span></p></html>" );
        
        add( texte );

    }
    
}
