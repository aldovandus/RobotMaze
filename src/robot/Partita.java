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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Aldo Vandus
 */
public class Partita extends javax.swing.JFrame{
    
    private final JLabel nomeLbl = new JLabel("Nome: ");
    private final JLabel cognomeLbl = new JLabel("Cognome: ");
    private final JLabel livelloLbl = new JLabel("Livello Difficoltà:");
    private final JTextField nomeTxt = new JTextField(20);
    private final JTextField cognomeTxt = new JTextField(20);
    private JComboBox livelliCombo=new JComboBox();
    private final JButton scegliBtn = new JButton("Scegli");
    private Main main;
    
    /**
     * Costruttore di Partita. Crea la grafica del JFrame.
     */
    public Partita() {
        super("Nuova Partita");
         
        
        this.livelliCombo.addItem("Facile");
        this.livelliCombo.addItem("Normale");
        this.livelliCombo.addItem("Difficile");
        
        this.livelliCombo.setMaximumSize(new Dimension(200,200));
        
        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;     
        newPanel.add(this.nomeLbl, constraints);
 
        constraints.gridx = 1;
        newPanel.add(this.nomeTxt, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        newPanel.add(this.cognomeLbl, constraints);
         
        constraints.gridx = 1;
        newPanel.add(this.cognomeTxt, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(this.livelloLbl, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 2;
        newPanel.add(this.livelliCombo, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(this.scegliBtn, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        
        
        livelliCombo.setPreferredSize(new Dimension(220,30));
        livelliCombo.setBackground(Color.decode("#B0B1B6"));
        livelliCombo.setForeground(Color.white);
        livelliCombo.setFocusable(false);
        livelliCombo.setVerifyInputWhenFocusTarget(false);
        
        scegliBtn.setBackground(Color.decode("#B0B1B6"));
        scegliBtn.setPreferredSize(new Dimension(200,50));
        scegliBtn.setFocusable(false);
        scegliBtn.setForeground(Color.white);
        scegliBtn.setFont(new Font("Verdana",Font.ROMAN_BASELINE,15));
        
        
        
       scegliBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniziaBtnClick(evt);
            }        
        });
       
       this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
       
       
         
        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Modalità di gioco"));
         
        // add the panel to this frame
        add(newPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(400,300));
        
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Al click del JButton inizia partita vengono creati gli oggetti :
     * robot,factory e il labirinto.
     * @param evt Variabile WindowEvent
     */
    
    private void iniziaBtnClick(ActionEvent evt) {
                
                if(this.nomeTxt.getText().isEmpty() || this.cognomeTxt.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"I campi Nome e Cognome non possono essere vuoti!" , "Attenzione", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    Robot robot=new Robot(this.nomeTxt.getText(),this.cognomeTxt.getText());
                    LabirintoFactory factory = new LabirintoFactory(new Cella(),new Parete(),new Stanza());
                    Labirinto gr = new Labirinto(factory,robot);
                    gr.setMain(this.main);
                    gr.generaGriglia(this.livelliCombo.getSelectedItem().toString());
                    this.setVisible(false); 
                }   
     }
    
    /**
     * Evento che viene lanciato alla chiusura della finestra.
     * @param evt Variabile WindowEvent
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) { 
     
     this.dispose();
     this.main.setVisible(true); // La finestra del menù principale viene resa visibile
         
    }

    /**
     *
     * @return Oggetto di tipo Main. Utile per effettuare operazione sul menu
     *  principale.
     */
    public Main getMain() {
        return main;
    }

    /**
     *
     * @param main Oggetto di tipo Main
     */
    public void setMain(Main main) {
        this.main = main;
    }
    
        
        
      
    
}

