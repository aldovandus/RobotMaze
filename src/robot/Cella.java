
package robot;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/*
    La classe Cella estende la classe Elemento . Le celle sono la zona del 
    labirinto dove il robot può muoversi.
 */

/**
 *
 * @author Aldo Vandus
 */


public class Cella extends Elemento{
    
    private List <Cella> successori;
   
    /**
     * Costruttore per la classe Cella.
     * Setta il colore del background del JButton di bianco e del bordo di grigio
     */

    Cella() {
    
        Border border = new LineBorder(Color.gray, 1);
        this.setOpaque(true);
        this.colore=Color.white;
        this.setBackground(this.colore);
        this.setBorder(border);
    }
    
    /**
     * Crea un ArrayList di successori
     */
    public void nuoviSucc()
    {
        this.successori= new ArrayList<>();
    }
    
    /**
     *
     * @return un una lista di tipo Cella
     */
    public List <Cella> getSucc()
    {
        return this.successori;
    }

  
   
    
    
    
   
    
    
}
