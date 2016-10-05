/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 *
 * @author Aldo Vandus
 */
public class TimerClass implements ActionListener {


    private List <Elemento> elementi;
    private Timer time;
    private Robot robot;
    private int[] posPre; // Array di interi che memorizza le posizioni precendenti degli oggetti colorati
    private int[] posOgg; // Array di interi che memorizza le posizioni correnti degli oggetti colorati
    private JLabel statoLbl;
    private JLabel passiLbl;
    private String nomeStanza;
    private ImageIcon colori[]; // Array di ImageIcon che conterrà l'icone degli oggetti colorati
    private Labirinto labirinto;
    private Main main;
    private List <Integer> iCelle; // Questo arraylist servirà per avera una lista delle posizioni delle sole celle del labirinto
  
    /**
     *
     * @param nomeStanza Stringa nome stanza(Scenario).
     * @param robot Oggetto di tipo Robot
     * @param statoLbl JLabel statoLbl
     * @param passiLbl JLabel passiLbl
     */
    public TimerClass(String nomeStanza,Robot robot,JLabel statoLbl,JLabel passiLbl)
    {
       
        this.elementi=robot.getElementi();
        this.nomeStanza=nomeStanza;
        
        time=new Timer(800,this);

        this.posPre=new int[]{-1,-1,-1,-1}; // Inizializzazione dell'array posPre
        this.posOgg= new int[4]; // Inizializzazione dell'array posOgg
        
        this.robot=robot;
        
        this.statoLbl=statoLbl;
        this.passiLbl=passiLbl;
        
        this.creaIcone(); // Crea le icone degli oggetti colorati
        this.creaListaICelle();
        
        System.out.println(this.elementi.get(0).getSize());
    }
    
    /**
     * Metodo che viene eseguito ripetutamente dal timer ad ogni intervallo di 
     * tempo scelto al momento della creazione.
     * @param e Evento ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
     
      this.generaOggetti();
      this.animRobot();
    }
    
    /**
     * Metodo che esegue L'animazione del Robot
     */
    private void animRobot()
    {
               
       if(this.robot.getPosCorrente()==191 || this.robot.getPosCorrente()==207 || this.robot.getPosCorrente()==223) // Se il robot si trova all'uscita
       {
            this.time.stop();
            Classifica classifica= new Classifica();
            classifica.caricaPunteggi();
            classifica.registraPunteggio(new Punteggio(this.robot.getNome(),this.robot.getCognome(),this.nomeStanza,this.robot.getNumPassi()));

            int esito = JOptionPane.showConfirmDialog(null, "Il robot " + this.robot.getNome() + " " + this.robot.getCognome() + " ha effettuato " + this.robot.getNumPassi() + " passi. Vuoi visualizzare la Classifica?", "Partita Terminata!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            
            if (esito == JOptionPane.NO_OPTION) {
                this.labirinto.dispose();
                this.main.setVisible(true);
            }    
            else if (esito == JOptionPane.YES_OPTION) {
         
                this.main.setVisible(true);
                classifica.caricaPunteggi();
                classifica.mostraPunteggi();
                classifica.setVisible(true);
                this.labirinto.dispose();
            } 
           
       }
       else
       {        
            this.robot.cammina();
            this.statoLbl.setText("Stato : " + this.robot.getStato().toString());
            this.passiLbl.setText("Numero Passi : " + this.robot.getNumPassi());
       }
               
    }
    
    /**
     * Metodo che genera in modo casuale gli oggetti nel labirinto durante 
     * la partita.
     */
    private void generaOggetti()
    {
        Random random = new Random();
        
        
        
        /*
            Vengono generati quattro numeri casuali e per selezionare a caso 
            quattro celle. Le celle trovate saranno quelle dove andremo a 
            posizionare gli oggetti colorati
        */
        
        int n = iCelle.size();
        int num;
     
        
        for (int i = 0; i < 4; i++) {
            num = random.nextInt(n); //Valori compresi tra 0 e il size dei successori del vertice corrente.
            this.posOgg[i]=iCelle.get(num);
        }
        
        
        
        for(int i=0;i<this.posPre.length;i++)
        {
            if(this.posPre[i] != -1)
            { 
                this.elementi.get(this.posPre[i]).setIcon(null,this.robot.getColorPre());
            }
        }
            
            /*
                Agli elementi appena trovati viene settata l'icona di un determinato colore
            */
            this.elementi.get(this.posOgg[0]).setIcon(this.colori[0],Color.red);
            this.elementi.get(this.posOgg[1]).setIcon(this.colori[1],Color.cyan);
            this.elementi.get(this.posOgg[2]).setIcon(this.colori[2],Color.yellow);
            this.elementi.get(this.posOgg[3]).setIcon(this.colori[3],Color.green);
            
            /*
                Nell'array posPre vengono inseriti i valori correnti. Questo 
                ci serve per rimuovere l'icona delle celle precedenti.
            */
            
            this.posPre[0]=this.posOgg[0];
            this.posPre[1]=this.posOgg[1];
            this.posPre[2]=this.posOgg[2];
            this.posPre[3]=this.posOgg[3];
   
        
       
    }
    
    /**
     * Metodo che crea quattro imageIcon che vengono scalate di dimensione.
     */
    private void creaIcone()
    {
            Image tmp; // Variabile temporeanea
            
            this.colori=new ImageIcon[4];
            
            this.colori[0]= new ImageIcon("img/ogg_red.png");
            this.colori[1]= new ImageIcon("img/ogg_cyan.png");
            this.colori[2]= new ImageIcon("img/ogg_yellow.png");
            this.colori[3]= new ImageIcon("img/ogg_green.png");
            
            tmp = this.colori[0].getImage().getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
            this.colori[0]=new ImageIcon(tmp);
            
            tmp=this.colori[1].getImage().getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
            this.colori[1]=new ImageIcon(tmp);
            
            tmp=this.colori[2].getImage().getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
            this.colori[2]=new ImageIcon(tmp);
            
            tmp=this.colori[3].getImage().getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
            this.colori[3]=new ImageIcon(tmp);
    }
    
    /**
     * Metodo che aggiunge nella lista iCelle solo gli indici delle celle della lista
     * elementi, scartando quindi le pareti
     */
    private void creaListaICelle()
    {
        this.iCelle= new ArrayList<>();
        for(Elemento e : this.elementi)
        {   
            if(e instanceof Cella)
            {
                this.iCelle.add(e.getNumero());
            }   
        }
    }
 
    /**
     * Metodo che fa partire il timer.
     */
    public void start()
    {
        this.time.start();
    }
    
    /**
     * Metodo che fa fermare il timer.
     */
    public void stop()
    {
        this.time.stop();
    }
    
    /**
     * Metodo che verifica se il timer è in esecuzione
     * @return Variabile di tipo booleano
     */
    public boolean isRunning()
    {
       return this.time.isRunning();
    }

    /**
     *
     * @param labirinto Oggetto di tipo Labirinto
     */
    public void setLabirinto(Labirinto labirinto) {
        this.labirinto = labirinto;
    }

    /**
     *
     * @param main Oggetto di tipo Main
     */
    public void setMain(Main main) {
        this.main = main;
    }
    
   

  
    
}
