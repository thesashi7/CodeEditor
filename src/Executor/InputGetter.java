
package Executor;



import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.AsynchronousCloseException;

import javax.swing.JTextArea;


/**
 * This is class InputGetter -- implements Runnable and KeyListener.
 * Extracts input from one input stream to be passed to another stream
 * @author thapaliya
 */
public class InputGetter implements Runnable, KeyListener
{
    OutputStream out;
    JTextArea window;
    String input;
    boolean inputRecieved, open;
    
    /**
     * 
     * @param ot is the output Stream of a process to which an input data will be piped
     * @param win is the text area from where the input data will be taken.
     */
    public InputGetter(OutputStream ot, JTextArea win)
    {
        this.out=ot;
        this.window=win;
        this.input="";
        this.open=true;
        this.window.addKeyListener(this);

    }
    
    
    @Override
    public void run() 
    {   System.out.println("Input run");
        this.window.setEditable(true);

        try 
        {
            OutputStreamWriter os;
            os = new OutputStreamWriter(out);


            while (this.open) {
                System.out.println("input loop");
                
                if (this.inputRecieved) {
                    this.window.setForeground(Color.GREEN);
                    os.write(input);
                    os.flush();
                    out.flush();
                    this.inputRecieved=false;
                    this.input="";
                    //Thread.sleep(10);
                }
            }
            System.out.println("Input Done!");
        }
        catch (AsynchronousCloseException e) 
        {
            
        } catch (IOException e) {
            System.out.println(e + " input");
        } /*catch (InterruptedException ex) {
            Logger.getLogger(InputGetter.class.getName()).log(Level.SEVERE, null, ex);
        } /*catch (InterruptedException ex) {
            Logger.getLogger(InputGetter.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }
    
    public boolean open()
    {
        this.open=true;
        return true;
    }
    
    public boolean close()
    {
        this.open=false;
        return true;
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
       
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        //synchronized(this)
        //{
        this.input = this.input.concat(Character.toString(ke.getKeyChar()));
        if(ke.getKeyChar() == KeyEvent.VK_BACK_SPACE ) if(this.input.length()>=2)this.input= this.input.substring(0, this.input.length()-2);
        if(ke.getKeyChar() ==KeyEvent.VK_ENTER)
        { 
             System.out.println("Enter");
             this.inputRecieved=true;
        }
  
        //}
        

       
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
       
    }
    
    public boolean isOpen()
    {
        return this.open;
    }

    
}
