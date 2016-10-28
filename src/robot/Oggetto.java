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
public abstract class Oggetto {

    /**
     *
     */
    protected ColoreOggetto coloreOggetto;
   
    Oggetto(ColoreOggetto coloreOggetto)
    {
        this.coloreOggetto = coloreOggetto;
    }
    
    /**
     *
     * @param cella Parametro di tipo Cella
     */
    protected abstract void disegnaOggetto(Cella cella);

    /**
     *
     */
    protected abstract void rimuoviOggetto();
}
