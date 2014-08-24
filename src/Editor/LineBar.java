
package Editor;


import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextArea;

import javax.swing.text.Document;

/**
 * This is a class LineBar -- extends JTextArea and implements Observer -- that represents a LineBar of an editor.
 * @author thapaliya
 */
public class LineBar extends JTextArea implements Observer
{
    private final String newLine = System.getProperty("line.separator");
    
    /**
     * 
     * @param o
     * @param o1 should be the doucment that has been changed
     */
    public void update(Observable o, Object o1) 
    {
        Document doc;
        doc=(Document)o1;
        int caretRowPosition = doc.getDefaultRootElement().getElementIndex(doc.getLength());
        printLine(caretRowPosition);
       
    }
    
    /*
     * numbers the line number each in differnt row 
     */
    private void printLine(int caretRowPosition)
    {
        String index="1"+newLine;
        
        for(int i=2; i<=(caretRowPosition+1); i++) index+= Integer.toString(i)+newLine;
        this.setText(index);
    }
    
}
