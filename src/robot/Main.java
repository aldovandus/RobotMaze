/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Aldo Vandus
 */

public class Main  extends JFrame{


    private final JLabel nomeLbl = new JLabel("Nome: ");
    private final JLabel cognomeLbl = new JLabel("Cognome: ");
    private final JLabel titoloLbl = new JLabel("ROBOT");
    private final JTextField nomeTxt = new JTextField(20);
    private final JTextField passwordTxt = new JTextField(20);
    private final JButton nuovaBtn = new JButton("Nuova Partita");
    private final JButton classificaBtn = new JButton("Classifica");
    private final JButton esciBtn = new JButton("Esci");
    
    /**
     * Costruttore Main
     */
    Main()
    {
        this.generaGrafica();
        this.setVisible(true);
    }
    
    public static void main(String[] args)  {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    /**
     * Metodo che genera la grafica del Menù Principale(Main)
     */
    public void generaGrafica()
    {
        
        Locale locale = new Locale("it","It"); // Imposta la lingua Italiana al testo di JOptionPane
        JOptionPane.setDefaultLocale(locale);

        JPanel newPanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 20, 20, 20);
        
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(titoloLbl, constraints);
        
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(nuovaBtn, constraints);
        
        
        
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(classificaBtn, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(esciBtn, constraints);
        
        titoloLbl.setFont(new Font("Segoe",Font.BOLD,50));
        
        nuovaBtn.setBackground(Color.decode("#B0B1B6"));
        nuovaBtn.setPreferredSize(new Dimension(200,50));
        nuovaBtn.setFocusable(false);
        nuovaBtn.setForeground(Color.white);
        nuovaBtn.setFont(new Font("Verdana",Font.ROMAN_BASELINE,15));
        nuovaBtn.setBorderPainted(false);
     
       
        classificaBtn.setBackground(Color.decode("#B0B1B6"));
        classificaBtn.setPreferredSize(new Dimension(200,50));
        classificaBtn.setFocusable(false);
        classificaBtn.setForeground(Color.white);
        classificaBtn.setFont(new Font("Verdana",Font.ROMAN_BASELINE,15));
        classificaBtn.setBorderPainted(false);
        
        esciBtn.setBackground(Color.decode("#B0B1B6"));
        esciBtn.setPreferredSize(new Dimension(200,50));
        esciBtn.setFocusable(false);
        esciBtn.setForeground(Color.white);
        esciBtn.setFont(new Font("Verdana",Font.ROMAN_BASELINE,15));
        esciBtn.setBorderPainted(false);
        
    
         
        add(newPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400,600));
        this.setTitle("Robot - Aldo Vandus");
        this.
        
        pack();
        setLocationRelativeTo(null);
        
        
        this.nuovaBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuovaBtnActionPerformed(evt);
            }
        });
        
         this.classificaBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classificaBtnActionPerformed(evt);
            }
        });
         
         this.esciBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        
        
    }
    
    /**
    * Metodo che viene lanciato al momento del click sul JButton nuovaBtn.
    * @param evt Evento di tipo ActionEvent
    */  
    public void nuovaBtnActionPerformed(ActionEvent evt)
    {
         Partita partita = new Partita();
         partita.setMain(this); // Viene passato il reference all'oggetto partita in modo da poter ritornare al main
         this.setVisible(false);
         partita.setVisible(true); 
    }
    
    /**
    * Metodo che viene lanciato al momento del click sul JButton classificaBtn.
    * @param evt Evento di tipo ActionEvent
    */
    public void classificaBtnActionPerformed(ActionEvent evt)
    {
         Classifica classifica = new Classifica();
         classifica.setVisible(true);
    }
    
    
}
