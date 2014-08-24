
package Executor;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JTextArea;

/**
 * This is class OutputWriter -- implements Runnable -- extracts Output from one stream to be passed onto a TextArea.
 * @author thapaliya
 */
public class OutputWriter implements Runnable
{
    InputStream in;
    JTextArea out;
    Color c;
    
    /**
     * 
     * @param in is the InputStream from where the piped output is read to be written onto the textarea
     * @param out is the textarea where the output will be written/appended
     * @param c  is the text color to write the stream
     */
    public OutputWriter(InputStream in, JTextArea out, Color c)
    {
        this.in=in;
        this.out=out; 
        this.c=c;
        
    }
    
    @Override
    public void run() 
    {
        System.out.println("Subprocess Ouput RUN");
        try {
            
            String text ;
            int n;
            byte[] buffer;
            
            text="";
            n=0;
            buffer= new byte[9096];
            System.out.println("Ok");
            out.setEditable(true);
            while ((n = in.read(buffer)) != -1) 
            {
               // System.out.println("Loop");
                text = new String(buffer, 0, n);
                out.setForeground(c);
                out.append(text);
                
                this.out.setCaretPosition(this.out.getDocument().getLength());
                //System.out.println("Returning loop");
            }
            
            in.close();
            System.out.print("Subprocess output:\n" + text);
            /*OutputStream out = System.out;
             int n;
             byte[] buffer = new byte[4096]; 
             while ((n = in.read(buffer)) != -1) { 
             //for(int i=0; i<buffer.length; i++)
             out.write(buffer, 0, n); 
             out.flush();
             }*/
        } 
        catch (IOException e) 
        {
            System.out.println(e+" output");
        }   
    }
    
}