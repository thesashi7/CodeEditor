
package Executor;

import Editor.*;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 * This is class ExecutionThread -- implements Runnable -- which serves as the execution thread of a program.
 * @author thapaliya
 */
public class ExecutionThread implements Runnable
{
    private JTextArea streamObserver;
    private Thread output;
    private Thread error;
    private Thread input;
    private String executionPath;
    
    /**
     * 
     * @param consoleWindow is the console window of the program to be run 
     * @param programPath  is the absolute path of the program to be run 
     */
    public ExecutionThread(Object consoleWindow, String programPath )
    {
        this.streamObserver = (TextArea)consoleWindow;
        this.executionPath = programPath;
    }
    
    @Override
    public void run() 
    {
        try 
        {  
            Process p1;
            OutputWriter outputWriter;
            OutputWriter errorWriter;
            InputGetter inputGetter;
            
            p1 =  Runtime.getRuntime().exec(executionPath);
            outputWriter= new OutputWriter(p1.getInputStream(), this.streamObserver, Color.BLUE);
            errorWriter= new OutputWriter(p1.getErrorStream(), this.streamObserver, Color.red);
            inputGetter = new InputGetter(p1.getOutputStream(), this.streamObserver);
            this.output = new Thread(outputWriter);
            this.input = new Thread(inputGetter);
            this.error = new Thread (errorWriter);
            
            System.out.println("Thread started");
            output.start();
            input.start();
            error.start();
            
            p1.waitFor();

            //input.interrupt();
            //Thread.sleep(30);
            inputGetter.close();
            
            //this.streamObserver.append("\nBuild Finished");
            this.streamObserver.setEditable(false);
 
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();

        } catch (InterruptedException ex) {
            Logger.getLogger(ExecutionThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
