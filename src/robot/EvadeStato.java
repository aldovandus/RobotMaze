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
public class EvadeStato implements Stato{

    /**
     * Metodo che permette al robot di camminare secondo il suo stato
     * @param robot Parametro di tipo Robot
     */
    @Override
    public void cammina(Robot robot) {
            int s=robot.getSuccRand();
            robot.muovi(s);
    }

    /**
     * Metodo che permette al robot di cambiare dallo stato corrente ad un altro
     * @param robot Oggetto di tipo Robot
     * @param color Oggetto di tipo Colore
     */
    @Override
    public void goNext(Robot robot, Color color) {
        
        robot.setStack(Dijkstra.findMin(robot.getElementi(),robot.getPosCorrente(),191));
        
        if(color==Color.green)
        {
            robot.setStato(new PursuitStato()); 
        }
        else if(color==Color.yellow)
        {
            robot.setStato(new FleeStato());
        }
        
        else if(color==Color.red)
        {
            robot.setStato(new SeekStato());
        }
    }

    /**
     *
     * @return Il nome dello stato corrente come stringa
     */
    @Override
    public String toString() {
        return "Evade";
    }


    
    
    
    
}
