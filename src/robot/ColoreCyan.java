/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Aldo Vandus
 */
public class ColoreCyan implements ColoreOggetto{
    
    private ImageIcon icona;
    private Color colore;

    /**
     *  Costruttore ColoreCyan
     */
    public ColoreCyan() {
        this.colore = Color.cyan;
        Image tmp;
        this.icona = new ImageIcon("img/ogg_cyan.png");
        tmp = this.icona.getImage().getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
        this.icona = new ImageIcon(tmp); 
    }

    @Override
    public Color getColore() {
        return this.colore;
    }

    @Override
    public ImageIcon getIcon() {
        return this.icona;
    }
    
    
}
