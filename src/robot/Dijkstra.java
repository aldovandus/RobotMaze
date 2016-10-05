/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Aldo Vandus
 */
final class Dijkstra  {
    
    /**
     *
     * @param elementi Lista che contiene gli elementi del labirinto
     * @param v1 Intero dell'elemento di partenza
     * @param v2 Intero dell'elemento di destinazione
     * @return Oggetto di tipo Deque
     */
    public static Deque<Integer> findMin(List <Elemento> elementi , int v1,int v2) 
    {
        

        Elemento s=(Elemento)elementi.get(v1);
        Elemento d=(Elemento)elementi.get(v2);
        
        
        
       List <Elemento> q = ((List) ((ArrayList) elementi).clone());

       ListIterator <Elemento> it = (ListIterator <Elemento>) q.listIterator();
        
        double[] dist = new double[256];
        int[] pre = new int[256];
        int c=0;
        
        while(it.hasNext())
        {
            dist[c]=196;
            it.next();
            c++;
        }
        
        dist[s.getNumero()]=0;   
        it=q.listIterator();
       
        while(!q.isEmpty())
        {

           int t=trovaIndiceMin(q,dist);
           
           
           Cella u = (Cella)q.get(t);

           q.remove(t);
          
           if(u==d)
           {
               break;
           }
         
                for(Cella v : u.getSucc())
                {
                    double alt=dist[u.getNumero()] + distTra(u, v);
               
                    if (alt < dist[v.getNumero()])
                    {
                        dist[v.getNumero()]=alt;
                        pre[v.getNumero()]=u.getNumero();
                  
                    }
                }
        }
        
        
        
       return Genera(pre, s, d);
    
    }
    
    /**
     *
     * @param q Lista di elementi
     * @param dist array di tipo double per le distanze
     * @return Un intero i_min
     */
    public static int trovaIndiceMin(List <Elemento> q,double[] dist)
    {
        double min=dist[q.get(0).getNumero()];
        int i_min=0;
        int c=0;
        Elemento v = q.get(0);
        ListIterator <Elemento> it = (ListIterator <Elemento>) q.listIterator();
        
       for(Elemento t : q )
        {
                if (dist[t.getNumero()]<min)
                {
                    min=dist[t.getNumero()];
                    v=(Elemento) t;
                    i_min=c;
                }
            
            c++;
        }

        
        return i_min;
        
         
    }
    
    /**
     *
     * @param pre Array di Interi
     * @param s Oggetto di tipo Elemento
     * @param d Oggetto di tipo Elemento
     * @return Una Deque di interi che contiene i passi del percorso minimo
     */
    public static Deque<Integer> Genera(int[] pre,Elemento s,Elemento d)
    {
        Deque<Integer> stack = new ArrayDeque<>();
        int i=d.getNumero();
       
        while(i!=s.getNumero())
        {
               stack.push(i);
               i=pre[i];
        }
        
        stack.push(s.getNumero());
        
       return stack;
    }

    /**
     * Funzione che calcola la differenza di costo tra due Celle
     * @param v1 Oggetto di tipo cella
     * @param v2 Oggetto di tipo cella
     * @return Il valore assoluto della differenza del costo tra le celle v1 e v2
     */
    public static double distTra(Cella v1 , Cella v2)
    {
        return Math.abs(v1.costo-v2.costo);
    }
    
}
