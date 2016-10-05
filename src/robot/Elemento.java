/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
La classe Elemento estende JButton ed implementa Cloneable per sfruttare il 
 pattern di tipo Prototype
 */

/**
 *
 * @author Aldo Vandus
 */

public class Elemento extends JButton implements Cloneable {
    
    /**
     * Numero identifica il numero di elemento
     */
    protected int numero;

    /**
     * Il costo serve per calcolarci il percorso minimo con Dijkstra
     */
    protected double costo;  

    /**
     * Ogni elemento ha un determinato colore
     */
    protected Color colore;
     
    
    /**
     *
     * @return Oggetto di tipo Elemento
     */
    public Object clone()
    {
        try{
        Elemento copy = (Elemento)super.clone();
        return copy;
        }
        catch(CloneNotSupportedException e)
        {
            return null;
        }
    }   

    /**
     *
     * @return Un numero intero
     */
    public int getNumero()
    {
        return this.numero;
    }

    /**
     *
     * @param numero è il parametro per settare il numero
     */
    public void setNumero(int numero)
    {
        this.numero=numero;
    }

    /**
     * Overraiding del metodo SetIcon. Oltre a settare l'icona setta anche il 
     * colore. Ci sarà utile in seguito per veficare la presenza di oggetti 
     * colorati durante il percorso del robot.
     * @param icona Oggetto di tipo Icon
     * @param colore Oggetto di tipo Color
     */
    public void setIcon(ImageIcon icona,Color colore)
    {
        this.setIcon(icona);
        this.colore=colore;
    }
 
    /**
     *
     * @param colore Setta l'attributo colore
     */
    public void setColore(Color colore) {
        this.colore = colore;
  }

    /**
     *
     * @return Oggetto di tipo Color
     */
    public Color getColore() {
        return colore;
}



}
