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
public interface Stato {

    /**
     * Metodo che verrà eseguito da ogni Classe Stato in modo diverso
     * @param robot Oggetto di tipo robot
     */
    public void cammina(Robot robot);
    
    /**
     * Metodo che permetterà al robot di passare da uno stato all'altro
     * @param robot Oggetto di tipo robot
     * @param colore Oggetto di tipo Colore
     */
    public void goNext(Robot robot,Color colore);

}
