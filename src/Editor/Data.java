
package Editor;


import java.util.Observable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * This is class Data -- extends Observable and implements DocumentListener -- that represents a DocumentListener for a text component
 * @author thapaliya
 */
public class Data extends Observable implements DocumentListener{

    /**
     * Notifies the observer about the insertion in data and passes the document from which it is listening to the observer
     * @param de 
     */
    public void insertUpdate(DocumentEvent de) 
    {
        int length;
        Document textDoc;
        textDoc= de.getDocument();
    
      
        this.setChanged();
        this.notifyObservers(textDoc);
     
        
    }

    /**
     * Notifies the observer about the removal data and passes the document from which it is listening to the observer
     * @param de 
     */
    public void removeUpdate(DocumentEvent de) 
    {
        Document textDoc;
        textDoc= de.getDocument();
        System.out.println("Remove Update");
        this.setChanged();
        this.notifyObservers(textDoc);
    }

    @Override
    public void changedUpdate(DocumentEvent de) 
    {
        System.out.println("Changed Update");
    }
    
}
