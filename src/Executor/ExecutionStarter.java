
package Executor;

import Editor.*;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextArea;

/**
 * This is a class ExecutionStarter -- implements Observer -- that starts an execution thread to run a program
 * @author thapaliya
 */
public class ExecutionStarter implements Observer
{
    public JTextArea streamObserver;
    private Thread executionThread;
    
    /**
     * 
     * @param window is the object that will get the standard input,  standard output, and standard error of the executing progra, 
     */
    public ExecutionStarter(Object window)
    {
        this.streamObserver= (TextArea) window;
    }
 
    
    @Override
    public void update(Observable o, Object o1) 
    {  System.out.println("Starting Streams transportation");

        startExecution((String)o1);
        
    }
    
    /*
     * Starts a thread of execution for the given process
     * execution is the full command -- as in a terminal -- for the execution of a proces like javac file.java
     */
    private void startExecution( String execution)
    {
        ExecutionThread exec;
        
        //if(new File(execution).exists())
        exec = new ExecutionThread(this.streamObserver, execution);
        this.executionThread = new Thread(exec);
        
        this.executionThread.start();
        
    }
}
