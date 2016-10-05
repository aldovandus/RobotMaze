package robot;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aldo Vandus
 */
public class Punteggio implements Serializable{
    
    private final String nome;
    private final String cognome;
    private final String scenario;
    private final int passi;
    
    /**
     * Costruttore Punteggio
     * @param nome Strina nome
     * @param cognome Stringa cognome
     * @param scenario Stringa scenario
     * @param passi Intero numero dei passi
     */
    
    Punteggio(String nome,String cognome,String scenario,int passi)
    {
        this.nome=nome;
        this.cognome=cognome;
        this.scenario=scenario;
        this.passi=passi;
    }
    
    /**
     *
     * @return Stringa del nome
     */
    public String getNome()
    {
        return this.nome;
    }
    
    /**
     *
     * @return Stringa del cognome
     */
    public String getCognome()
    {
        return this.cognome;
    }
    
    /**
     *
     * @return Intero del numero dei passi
     */
    public int getPassi()
    {
        return this.passi;
    }
    
    /**
     *
     * @return Il nome dello scenario come stringa
     */
    public String getScenario()
    {
        return this.scenario;
    }
    
}
