
package Editor;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * This is class Filter
 * @author thapaliya
 */
public class Filter extends DocumentFilter
{
  public void insertString(final FilterBypass fb, final int offset, final String string, final AttributeSet attr)
            throws BadLocationException {
            System.out.println("Inserting string");
            System.exit(1);
            super.insertString(fb, offset, string, attr);
        
    }

    public void remove(final FilterBypass fb, final int offset, final int length) throws BadLocationException {
       
            super.remove(fb, offset, length);

    }

    public void replace(final FilterBypass fb, final int offset, final int length, final String text, final AttributeSet attrs)
            throws BadLocationException {
  
            super.replace(fb, offset, length, text, attrs);
        
    }    
}