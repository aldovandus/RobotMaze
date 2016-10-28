/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Aldo Vandus
 */
public class Labirinto extends javax.swing.JFrame {

    private TimerClass tc;
    private LabirintoFactory factory;
    private Stanza stanza;
    private JButton iniziaBtn;
    private JLabel statoLbl;
    private JLabel passiLbl;
    private Main main; // Attributo che contiene il riferimento all'oggetto Main
    private Robot robot;
   
    /**
     * Costruttore di Labirinto
     * @param factory Oggetto di tipo LabirintoFactory
     * @param robot Oggetto di tipo Robot
     */
    public Labirinto(LabirintoFactory factory,Robot robot)
    {
        stanza=factory.creaStanza();
        this.factory=factory;
        this.robot=robot;
    }
    
    

    /**
     * La funzione "crea()" crea il labirinto andando ad inserire le varie celle e pareti nell'arraylist "elementi" di stanza
     * @param scenario Variabile di tipo Stringa passata dalla JComboBox per selezionare il tipo di scenario
     */
    
    public void crea(String scenario)
    {
        this.stanza.setNomeStanza(scenario);
        Cella temp;
        for(int i=0;i<this.stanza.getRig()*this.stanza.getCol();i++)
        {
            temp=this.factory.creaCella(i);
            temp.nuoviSucc();
                
            this.stanza.aggiungiElemento(temp);
         }
        
        
       this.stanza.creaPareti(new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}, factory);
       this.stanza.creaPareti(new int[] {16,32,48,64,80,96,112,128,144,160,176,192,208,224},factory);
       this.stanza.creaPareti(new int[] {240,241,242,243,244,245,246,247,248,249,250,251,251,252,253,254,255},factory);
       this.stanza.creaPareti(new int[] {31,47,63,79,95,111,127,143,159,175,239},factory);
       
       
      /*
       A seconda dello scenario vengono create Mura differenti per aumentare 
       la difficoltà della partita
       */
       if(this.stanza.getNomeStanza().equals("Facile"))
       {
           this.stanza.creaPareti(new int[]{171,172,173},factory);
       }
       else if(this.stanza.getNomeStanza().equals("Normale"))
       {
           this.stanza.creaPareti(new int[]{135,151,167,183,199,215,231,171,172,173},factory);
       }
       else if(this.stanza.getNomeStanza().equals("Difficile"))
       {
           this.stanza.creaPareti(new int[]{35,51,67,83,99,115,131,147,55,56,57,58,59,60,61,62,135,151,167,183,199,215,231,171,172,173},factory);
       }
       
      /*
       Setta successori crea la lista successori per ogni Cella
       */ 
      stanza.setSuccessori();
       
        
    }
    

   /*
    */ 

    /**
     * La funzione generaGriglia() genera una griglia grafica (il labirinto vero e proprio). 
     * Per primo viene invocato il metodo crea, poi con il metodo riempiStanza() saranno inseriti 
     * tutti gli elementi dell'arraylist elementi nella stanza e successivamente la stanza sarà
     * aggiunta al JFrame insieme ad altro componenti grafici e quindi mostrata a video.
     * @param scenario Variabile di tipo Stringa passata dalla JComboBox per selezionare il tipo di scenario
     */
 
    
   public void generaGriglia(String scenario)
   {
      
            this.crea(scenario);
    
            this.stanza.riempiStanza();
            
            this.robot.setElementi(this.stanza.getElementi());
            this.robot.setPosIniziale(this.stanza,33);
  
            this.statoLbl = new JLabel();
            this.passiLbl=new JLabel();
             
            this.statoLbl.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            this.statoLbl.setFont(new Font("Verdana", Font.PLAIN,16));
            this.passiLbl.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            this.passiLbl.setFont(new Font("Verdana", Font.PLAIN,16));
  
            this.iniziaBtn = new JButton();
            iniziaBtn.setBackground(Color.decode("#B0B1B6"));
            iniziaBtn.setPreferredSize(new Dimension(200,50));
            iniziaBtn.setFocusable(false);
            iniziaBtn.setForeground(Color.white);
            iniziaBtn.setFont(new Font("Verdana",Font.ROMAN_BASELINE,15));
        
            iniziaBtn.setText("Inizia Partita");
        
       iniziaBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniziaBtnClick(evt);
            }
 
        });
       
        
       
     
    /*
     Viene utilizzato un altro JPanel per posizionare il JButton che servirà
     a far iniziare la partita.
    */ 
    
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(200, 50));
    panel.setLayout(new GridLayout(1,2));
    panel.add(statoLbl);
    panel.add(passiLbl);
    
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    
    this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
    
  
    this.add(this.stanza,BorderLayout.NORTH);
    this.add(panel,BorderLayout.CENTER);
    this.add(iniziaBtn,BorderLayout.SOUTH);
    
    this.setTitle("Labirinto - " + this.stanza.getNomeStanza());
    
    this.pack();
    this.setVisible(true);
   }
    
   /**
    * Metodo che viene lanciato al momento del click sul JButton iniziaBtn.
    * Lancia l'algoritmo di Dijkstra, avvia il Timer e disabilita iniziaBtn.
    * @param evt Evento di tipo ActionEvent
    */   
   private void iniziaBtnClick(ActionEvent evt) {
       
            this.robot.setStack(Dijkstra.findMin(Labirinto.this.stanza.getElementi(), 33, 191));
            this.avviaTimer();
            this.iniziaBtn.setEnabled(false);
    }
   
    /**
    * Metodo che viene lanciato al momento della chiusura del JFrame corrente.
    * Se Il timer è nullo oppure non è in esecuzione, allora la finestra verrà 
    * chiusa direttamente. Altrimenti Verrà mostrato un avviso con JOptionPane
    * che ci chiederà se vogliamo annullare la partita oppure continuare.
    * @param evt Evento di tipo WindowEvent
    */
    private void formWindowClosing(java.awt.event.WindowEvent evt) { 
     
     if(this.tc==null || !this.tc.isRunning())
     {
         this.dispose();
         this.main.setVisible(true);
     }
     else
     {
         this.tc.stop();
     
        int esito = JOptionPane.showConfirmDialog(null, "Vuoi terminare la partita corrente?", "Attenzione!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
     
         if (esito == JOptionPane.NO_OPTION) {
             this.tc.start();
             this.setVisible(true);
        }    
        else if (esito == JOptionPane.YES_OPTION) {
         
            this.tc.stop();
            this.dispose();
            this.main.setVisible(true);
        } 
         
     }
     
    }
        
    /**
     * Metodo che permette la creazione e l'avvio Timer che animerà la partita.
     */
    private void avviaTimer()
    {  
       this.tc=new TimerClass(this.stanza.getNomeStanza(),this.robot,this.statoLbl,this.passiLbl);
       this.tc.setMain(this.main);
       this.tc.setLabirinto(this);
       this.tc.start();
    }
   
    /**
     *
     * @return Oggetto di tipo Stanza
     */
    public Stanza getStanza()
    {
       return this.stanza;
    }

    
    /**
     *
     * @return Oggetto di tipo JLabel
     */
    public JLabel getStatoLbl() {
       return this.statoLbl;
    }
    
    /**
     *
     * @return Un oggetto di tipo JLabel
     */
    public JLabel getPassiLbl() {
       return this.passiLbl;
    }

    /**
     *
     * @return Oggetto di tipo Main
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
    
    /**
     *
     * @return Oggetto di tipo Robot
     */
    public Robot getRobot()
    {
       return this.robot;
    }
   
    /**
     *
     * @param robot Oggetto di tipo Robot
     */
    public void setRobot(Robot robot) {
        this.robot=robot;
    }
    
    
     
    
    
    
}
