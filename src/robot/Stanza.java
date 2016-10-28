/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Aldo Vandus
 */
public class Stanza extends JPanel implements Cloneable{

    private final int rig;
    private final int col;
    private final List <Elemento> elementi = new ArrayList<>();
    private String nomeStanza;
    
    /**
     * Costruttore Stanza
     */
    public Stanza()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(900, (int) screenSize.getHeight()-200));
 
       
        this.rig=16;
        this.col=16;
    }
    
    /**
     * Metodo che riempie la Stanza di Elementi
     */
    public void riempiStanza()
    {
       
            this.setLayout(new GridLayout(this.rig,this.col));

            for(int i=0;i<this.elementi.size();i++)
            {
                this.add(this.elementi.get(i));  // gli elementi dell'arraylist "elementi" verranno caricati nel JPanel(Stanza)
            }
     
    }
    

    /**
     *  La funzione setSuccessori() automatizza l'assegnazione dei figli ai vertici del grafo(Labirinto).
     *  Per ogni cella della griglia verranno assegnati tutti i figli adiacenti ad essa.
     */
    public void setSuccessori()
    {
         int c=0;
         for(int i=1;i<=rig;i++)
         {
             for(int j=0;j<col;j++)
             {
                if(this.elementi.get(c).numero+1 > 0 && this.elementi.get(c).numero+1 < col*i)
                {
                    this.aggiungiSuccessori(c, this.elementi.get(c).numero+1, 1);
                }
                
                 if(this.elementi.get(c).numero+col < rig*col)
                {
                    this.aggiungiSuccessori(c, this.elementi.get(c).numero+col, 1);
                }
                 
                if(this.elementi.get(c).numero-col >= 0)
                {
                    this.aggiungiSuccessori(c, this.elementi.get(c).numero-col, 1);
                }
                
                if(this.elementi.get(c).numero > ((col*i)-col))
                {
                    this.aggiungiSuccessori(c, this.elementi.get(c).numero-1, 1);
                }
                if(this.elementi.get(c).numero+(col+1) < rig*col && this.elementi.get(c).numero < (col*i)-1)
                {
                    this.aggiungiSuccessori(c, this.elementi.get(c).numero+(col+1), 1.5);
                }
                
                if(this.elementi.get(c).numero+(col-1) < rig*col && this.elementi.get(c).numero > (col*i)-col)
                {
                    this.aggiungiSuccessori(c, this.elementi.get(c).numero+(col-1), 1.5);
                }
                
                if(this.elementi.get(c).numero-(col+1) >= 0 && this.elementi.get(c).numero > (col*i)-col)
                {
                    this.aggiungiSuccessori(c, this.elementi.get(c).numero-(col+1), 1.5);
                }
                
                if(this.elementi.get(c).numero-(col-1) >= 0 && this.elementi.get(c).numero < (col*i)-1)
                {
                    this.aggiungiSuccessori(c, this.elementi.get(c).numero-(col-1), 1.5);
                }
                    c++;
                }
        }      
    }

    
    /**
     * La funzione "creaPareti" crea una serie di pareti che andranno inserite prima
     * all'interno dell'arraylist elementi e successivamente nel JPanel per poi
     * essere quindi mostrate a video graficamente.
     * @param indexPareti Array di interi che indicano la posizione delle pareti
     * @param factory Oggetto di tipo LabirintoFactory
     */
    public void creaPareti(int indexPareti[],LabirintoFactory factory)
    {
        Parete parete;
         
        for(int i=0;i<indexPareti.length;i++)
        {
            parete=factory.creaParete();
            parete.setNumero(this.elementi.get(indexPareti[i]).numero);
            this.elementi.set(indexPareti[i], parete);
            
        }
    }
      
    /**
     *
     * @param pos Intero che indica la posizione della cella nella quale verrano creati i successori
     * @param num Intero che indica il numero del successore della Cella
     * @param c Variabile di tipo double che indica il costo della Cella
     */
    public void aggiungiSuccessori(int pos,int num,double c)
    {
        Cella cella = new Cella();
        if(this.elementi.get(num) instanceof Cella && this.elementi.get(pos) instanceof Cella)
        {
            Cella tmp=(Cella) cella.clone();
            tmp.setNumero(num);
            tmp.costo=c;
            ((Cella)this.elementi.get(pos)).getSucc().add(tmp);
        }
    }
    
    /**
     *
     * @param elemento Oggetto di tipo Elemento
     */
    public void aggiungiElemento(Elemento elemento)
    {
        this.elementi.add(elemento);
    }
    
    /**
     *
     * @param nome Stringa del nome della Stanza(scenario)
     */
    public void setNomeStanza(String nome)
    {  
        this.nomeStanza=nome;
    }
    
    /**
     *
     * @return Stringa del nome della Stanza(scenario)
     */
    public String getNomeStanza()
    {  
        return this.nomeStanza;
    }
    
    /**
     *
     * @return Lista che contiene oggetti di tipo Elemento
     */
    public List<Elemento> getElementi()
    {
        return this.elementi;
    }
    
    /**
     *
     * @return Intero delle righe della griglia
     */
    public int getRig()
    {
        return this.rig;
    }
    
    /**
     *
     * @return Intero delle colonne della griglia
     */
    public int getCol()
    {
        return this.col;
    }
    
    /**
     *
     * @return Oggetto di tipo Stanza
     */
    @Override
    protected Stanza clone() {
        
        try{
        Stanza copy = (Stanza)super.clone();
        return copy;
        }
        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
            return null;
        }
        
    }

    
   
    
    
}
