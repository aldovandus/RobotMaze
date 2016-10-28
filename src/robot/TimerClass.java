/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private JLabel statoLbl;
    private JLabel passiLbl;
    private String nomeStanza;
    private Labirinto labirinto;
    private Main main;
    private List <Integer> iCelle; // Questo arraylist servirà per avera una lista delle posizioni delle sole celle del labirinto
    private List <Oggetto> oggettiColorati;
  
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
        this.oggettiColorati = new ArrayList<>();
        time=new Timer(800,this);
        
        this.robot=robot;
        
        this.statoLbl=statoLbl;
        this.passiLbl=passiLbl;
        
        this.creaListaICelle();
        
        this.oggettiColorati.add(new OggettoColorato(new ColoreRosso()));
        this.oggettiColorati.add(new OggettoColorato(new ColoreVerde()));
        this.oggettiColorati.add(new OggettoColorato(new ColoreCyan()));
        this.oggettiColorati.add(new OggettoColorato(new ColoreGiallo()));
        
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
        
        /*
            Vengono prima rimossi gli oggetti dal labirinto se ci sono.
        */
        
        for(int i=0;i<this.oggettiColorati.size();i++)
        {
            this.oggettiColorati.get(i).rimuoviOggetto();
        }
     
        
        for (int i = 0; i < this.oggettiColorati.size(); i++) {
            num = random.nextInt(n); //Valori compresi tra 0 e il size dei successori del vertice corrente.    
            /*
                Ricaviamo una cella con il numero casuale num e gli disegnamo l'oggetto colorato.
            */
            this.oggettiColorati.get(i).disegnaOggetto((Cella)this.elementi.get(iCelle.get(num)));
        }
  
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
