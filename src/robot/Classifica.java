package robot;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Aldo Vandus
 */
public class Classifica extends JFrame{
    
    private String filePath;
    private FileInputStream file;
    private List <Punteggio> punteggi;
    private Main main;
    private JTable table;
    private DefaultTableModel tableModel;
    private String[] nomeColonneTable;

    /**
     *  Array di stringhe nomeColonne viene passato successivamente come argomento
     *  del model della nostra JTable , così da poter visualizzare i nomi delle
     *  colonne della classifica.
     */


    
    Classifica()
    {
        this.nomeColonneTable= new String []{"Nome", "Cognome", "Scenario", "Num.Passi"};
        this.filePath="punteggi.dat";
        this.punteggi = new ArrayList<Punteggio>();
        
        this.generaGrafica();
        this.caricaPunteggi();
        this.mostraPunteggi();
        
        table.setEnabled(false); // Rende la nostra tabella non editabile.
        
        /* Settaggio Font si per il TableHeader che per le righe del JTable */
        this.table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 16));
        this.table.setFont(new Font("Arial", Font.ROMAN_BASELINE, 15));
        
        

        this.table.setRowHeight(25); // Settaggio altezza righe

        /* Settaggio alliniamento centrale delle colonne */
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( JLabel.CENTER );

   
        /* Il TableRowSorter permette di ordinare le righe in base ad una colonna
            in questo caso in base al numero di passi.
        */
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
 
        int columnIndexToSort = 3; // Viene preso l'indice della colonna numero 3
        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING)); // Ordinata in ordine crescente
 
        sorter.setSortKeys(sortKeys);
        sorter.sort();
 
    }
    
    /**
     *  La funzione "registraPunteggio" viene utilizzata per salvare l'ultimo 
     *  punteggio su file. L'argomento viene inserito all'interno dell'arraylist
     *  punteggi e quest'ultima successivamente viene scritta nel file punteggi.dat 
     * @param p Oggetto di tipo Punteggio
     */

    public void registraPunteggio(Punteggio p)
    {
      
        ObjectOutputStream out = null;
        
        try {
            this.punteggi.add(p);
            out = new ObjectOutputStream(new FileOutputStream(this.filePath));
            out.writeObject(this.punteggi);
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
        
    }
    
    /**
     * Carica dal file i punteggi e li inserisce nella lista
     */
    public void caricaPunteggi()
    {
        ObjectInputStream in;
        
        File file = new File(this.filePath); 
        
            
           try {
            if(file.exists() && file.length() > 0) {
                this.file=new FileInputStream(this.filePath);
                in = new ObjectInputStream(this.file);
                this.punteggi=(List<Punteggio>) in.readObject();
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
     
    }

    /**
     * Preleva tutti i punteggi dell'arrayList punteggi e li carica nel model della tabella rendendoli quindi visibili 
     * graficamente.
     */

    public void mostraPunteggi()
    {
        this.tableModel.setRowCount(0);
        for(Punteggio p : this.punteggi)
        {
            this.tableModel.addRow(new String[]{p.getNome(),p.getCognome(),p.getScenario(),Integer.toString(p.getPassi())});
        }
    }

    /**
     *  La funzione generaGrafica() genera gli oggetti grafici all'interno del
     *   JFrame. In questo caso ci viene in aiuto l'oggetto JTable che ci serve
     *   proprio per mostrare la classfica dei robot.
     */

    
    public void generaGrafica()
    {
        this.tableModel = new DefaultTableModel(null,nomeColonneTable);
        this.table = new JTable(this.tableModel);
        JPanel newPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 500));
        this.add(scrollPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        
        this.setTitle("Classifica Partite");

        
        
    }
    
    /**
     *
     * @return Stringa filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     *
     * @return Lista di tipo Putneggio
     */

    public List<Punteggio> getPunteggi() {
        return punteggi;
    }
    
    /**
     *
     * @param filePath Setta la stringa filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @param punteggi Lista di tipo Punteggio
     */
    public void setPunteggi(List<Punteggio> punteggi) {
        this.punteggi = punteggi;
    }

    /**
     *
     * @param main Oggetto di tipo Main
     */
    public void setMain(Main main) {
        this.main = main;
    }
    
    
}
