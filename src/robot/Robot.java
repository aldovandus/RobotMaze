/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Color;
import java.awt.Image;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 *
 * @author Aldo Vandus
 */
public class Robot {
    private String nome;
    private String cognome;
    private int posCorrente;
    private int numPassi;
    private Color colorePre;
    private Deque<Integer> stack;
    private List <Elemento> elementi;
    private ImageIcon icone[];
    private Stato stato;
    
    /**
     * Costruttore Robot
     * @param nome Stringa nome
     * @param cognome Stringa cognome
     */
    Robot(String nome,String cognome)
    {
       
        this.nome=nome;
        this.cognome=cognome;
        this.creaIcone();
        this.numPassi=0;
        this.colorePre=Color.white;
        this.stato=new PursuitStato();
        
 
    }
    
    /**
     * Metodo che crea quattro icone del robot con diverse ruotate ognuna di
     * novanta gradi, e successivamente vengono scalate di dimensione.
     */
    
    private void creaIcone()
    {       
            this.icone=new ImageIcon[4];
            this.icone[0] = new ImageIcon("img/robot_giu.png");
            this.icone[1] = new ImageIcon("img/robot_su.png");
            this.icone[2] = new ImageIcon("img/robot_destra.png");
            this.icone[3] = new ImageIcon("img/robot_sinistra.png");
            
            Image tmp;
            
            tmp = this.icone[0].getImage().getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH) ;
            this.icone[0]=new ImageIcon(tmp);
            tmp = this.icone[1].getImage().getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH) ;
            this.icone[1]=new ImageIcon(tmp);
            tmp = this.icone[2].getImage().getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH) ;
            this.icone[2]=new ImageIcon(tmp);
            tmp = this.icone[3].getImage().getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH) ;
            this.icone[3]=new ImageIcon(tmp);
    }
    
    /**
     *
     * @return Stringa nome
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @return Stringa cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     *
     * @param nome Stringa nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @param cognome Stringa cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    /**
     *
     * @param stanza Oggetto di tipo Stanza
     * @param pos Intero che indica la posizione
     */
    public void setPosIniziale(Stanza stanza,int pos) {
       
        Elemento tmp=(Elemento)this.elementi.get(pos);
        tmp.setAlignmentX(SwingConstants.CENTER);
        tmp.setIcon(this.icone[0]);
        this.posCorrente = pos;
        
    }
    
    /**
     *
     * @param pos Intero che indica la posizione
     */
    public void setPosCorrente(int pos) {
        this.posCorrente = pos;
      
    }
    
    /**
     *
     * @return Intero che indica la posizione
     */
    public int getPosCorrente()
    {
        return this.posCorrente;
    }
    


    /**
     *  La funzione muovi permette al robot di muoversi nel labirinto. Prende come
     *  argomento un intero "pos" che sarebbe la posizione di destinazione.
     * @param pos Intero che indica l'indice della cella di destinazione del labirinto
     */

    public void muovi(int pos)
    {
             
            /*
                All'elemento della posizione precedente verrà rimossa l'icona
            */
            Elemento tmp;
            tmp=(Elemento)this.elementi.get(this.getPosCorrente());
            tmp.setIcon(null);
            
            /*
                Mentre viene setta a quella corrente 
            */
            tmp=(Elemento)this.elementi.get(pos);
            tmp.setAlignmentX(SwingConstants.CENTER);
            tmp.setIcon(this.direzioneRobot(pos));
            this.setPosCorrente(pos);
            
            
            numPassi++; // Viene incrementata ad ogni passo per poi poter alla fine effettuare il conteggio dei passi totali.
    }
    
    /**
     * Metodo che setta un'icona del robot a seconda della posizione in cui si
     * muoverà, simulando cosi una rotazione.
     * @param pos Intero che indica l'indice della cella di destinazione del labirinto
     * @return 
     */
    
    private ImageIcon direzioneRobot(int pos)
    {
        ImageIcon temp = null;
        
        if((pos - this.getPosCorrente()) == 1 || (pos - this.getPosCorrente()) == 2 )
        {
            temp=this.icone[2]; // Va verso destra
        }
        else if((pos - this.getPosCorrente()) == -1 || (pos - this.getPosCorrente()) == -2)
        {
            temp=this.icone[3]; // Va verso sinistra
        }
       else if((pos - this.getPosCorrente()) > 1)
        {
            temp=this.icone[0]; // Va giu
        }
        else if((pos - this.getPosCorrente()) < 1)
        {
            temp=this.icone[1]; // Va su
        }
        else 
        {
            temp=this.icone[0]; // Va giu negli altri casi
        }
        
        return temp;
    }
    
    /**
     * Metodo che ricava una cella a caso tra i successori della cella corrente
     * @return Un intero che indica l'indice del successore della cella corrente ottenuto in modo casuale
     */
    public int getSuccRand()
    {
        Random random = new Random();
        int n = ((Cella)this.elementi.get(this.posCorrente)).getSucc().size();
        int k = random.nextInt(n);  //Valori compresi tra 0 e il size dei successori del vertice corrente.
        int pos= ((Cella)this.elementi.get(this.posCorrente)).getSucc().get(k).getNumero();
        return pos;
    }
    
    /**
     * Metodo che lancia il metodo cammina dello stato corrente. Molto utile per
     * l'utilizzo del Design Pattern State.
     */
    public void cammina()
    {
        this.stato.cammina(this);
        this.checkColor();
    }
    
    /**
     *
     * @param stato Oggetto di tipo Stato
     */
    public void setStato(Stato stato) {
        this.stato=stato;
    }
    
    /**
     *
     * @return Oggetto di tipo Stato
     */
    public Stato getStato() {
        return this.stato;
    }
    
    /**
     *
     * @param color Oggetto di tipo Color
     */
    public void goNext(Color color)
    {
        this.stato.goNext(this,color);
    }

    /**
     * La funzione "checkColor" verifica se nelle celle adiacenti alla posizione
     *  corrente del robot, sono apparsi casualmente celle colorate
     */

    public void checkColor()
    {
        
        Cella cellaCorr=(Cella)this.elementi.get(this.posCorrente);
        Color color = this.colorePre;
        
        for(Cella ce : cellaCorr.getSucc())
        {
            if(this.elementi.get(ce.getNumero()).getColore()!= this.colorePre)
            {
                color=this.elementi.get(ce.getNumero()).getColore();
            }
        }
        
        
            /*
                Se il color è diverso da bianco significa che è stato trovato un
                altro colore nelle celle adiacenti e quindi il robot cambierà
                stato(Pattern State).
            */
            
            if(color != this.colorePre) 
            {
                this.goNext(color);
            }
  
        
    }
    
    /**
     *
     * @return Intero che indica il numero di passi
     */
    public int getNumPassi()
    {
        return this.numPassi;
    }

    /**
     *
     * @return Uno stack di interi
     */
    public Deque<Integer> getStack() {
        return stack;
    }
    
    /**
     *
     * @param stack Uno stack di interi che contiene i passi da fare del robot
     */
    public void setStack(Deque<Integer> stack) {
        stack.pop();
        this.stack = stack;
    }

    /**
     *
     * @return Una lista che contiene oggetti di tipo Elemento
     */
    public List<Elemento> getElementi() {
        return elementi;
    }

    /**
     *
     * @param elementi Una lista che contiene oggetti di tipo Elemento
     */
    public void setElementi(List<Elemento> elementi) {
        this.elementi = elementi;
    }
    
    /**
     *
     * @return Un oggetto di tipo Colore
     */
    public Color getColorPre()
    {
        return this.colorePre;
    }
    
  
}
