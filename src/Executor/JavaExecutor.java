
package Executor;

import java.util.Observable;




/**
 * This is class Java Executor -- extends Observable and implements CodeExecutor -- that executes java class files.
 * @author thapaliya
 */
public class JavaExecutor extends Observable implements CodeExecutor
{

    private String executionCommand;
    
    /**
     * 
     */
    public JavaExecutor()
    {
        this.executionCommand = "java -classpath ";
    }
    
    
    /**
     * Executes the given compiledFile.
     * @param compiledFile is the absolute path of the class file to be executed
     */
    public void execute(String compiledFile) 
    {   System.out.println("Java Execution started");
            
             //this.executionCommandPath= this.executionCommand.concat(compiledFile);
            //this.executionPath = "java -classpath C:\\Users\\thapaliya\\Documents\\NetBeansProjects\\TextEditor\\build\\classes\\ Editor.Sample ";
            //this.currentProgram = Runtime.getRuntime().exec("java -classpath C:\\Users\\thapaliya\\Documents\\NetBeansProjects\\TextEditor\\build\\classes Editor.Sample");            
            System.out.println("exec:"+ this.executionCommand);
            this.setChanged();
            this.notifyObservers(this.executionCommand.concat(compiledFile)); 
            //this.currentProgram.waitFor();
           
    }

}
