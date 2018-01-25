
package Interface;

import java.awt.*;
import javax.swing.*;

public class Welcome extends JPanel{
    
    private JLabel texte;
        
    public Welcome() {
                
        setBounds(50,50,380,200);
        setBorder (BorderFactory.createLineBorder (Color.RED) );
        
        setLayout(new FlowLayout( ));
        
        
        texte = new JLabel ("<html><p style=\"text-align: center;\"><strong><span style=\"font-family: 'arial black', 'avant garde'; color: #ff0000; font-size: large;\">Bienvenue</span></strong></p>\n" +
        "<p><span style=\"font-family: 'arial black', 'avant garde';\"><br /></p>\n" +
        "<p>Ce programme sert à gérer la partie administrative des scouts de Namur.</p>" +
        "<p>Il est écrit par la société Nyu-Corporation dont les représentants sont 2 jeunes programmeurs très prommetteurs (F. ANDRE et N. TEREKHOV).</p><br />"+
        "<p>Nyu</span></p></html>" );
        
        add( texte );

    }
    
}
