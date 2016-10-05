/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

/**
 * LabirintoFactory
 * @author Aldo Vandus
 */


public class LabirintoFactory {
   
    private Cella cella;
    private Parete parete;
    private Stanza stanza;
   
    /**
     * Il costruttore prende come argomento gli oggetti da clonare
     * @param cella Oggetto di tipo Cella
     * @param parete Oggetto di tipo Parete
     * @param stanza Oggetto di tipo Stanza
     */
    LabirintoFactory(Cella cella,Parete parete,Stanza stanza)
    {
        this.cella=cella;
        this.parete=parete;
        this.stanza=stanza;
    }
    
    /**
     *
     * @param i è l'indice della Cella che sarà clonata
     * @return un oggetto di tipo Cella
     */
    public Cella creaCella(int i)
    {
        this.cella.numero=i;
        return (Cella) this.cella.clone();
    }
    
    /**
     *
     * @return un oggetto di tipo Parete
     */
    public Parete creaParete()
    {
        return (Parete) this.parete.clone();
    }

    /**
     *
     * @return un oggetto di tipo Stanza
     */
    public Stanza creaStanza()
    {
        return (Stanza) this.stanza.clone();
    }
    
}
