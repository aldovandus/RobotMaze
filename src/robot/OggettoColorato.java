/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Color;

/**
 *
 * @author Aldo Vandus
 */
public class OggettoColorato extends Oggetto {
    
    private Cella cella;
    
    /**
     *
     * @param coloreOggetto Oggetto di tipo ColoreOggetto
     */
    public OggettoColorato(ColoreOggetto coloreOggetto) {
        super(coloreOggetto);
    }
    
    /**
     * Metodo che disegna l'oggetto colorato sulla cella
     * @param cella Oggetto di tipo Cella
     */
        
    public void disegnaOggetto(Cella cella)
    {
        this.cella=cella;
        this.cella.setColore(coloreOggetto.getColore());
        this.cella.setIcon(coloreOggetto.getIcon());
    }
    /**
     * Metodo che rimuove l'oggetto dalla cella
     */
    public void rimuoviOggetto()
    {
        if(this.cella != null)
        {   
            this.cella.setColore(Color.white);
            this.cella.setBackground(Color.white);
            this.cella.setIcon(null);
        }
        
    }
}
