
package Compiler;

import Editor.*;
import java.util.Observable;

/**
 * This is a class JavaCompiler -- extends Observable and implements CodeCompiler -- that compiles java source codes.
 * @author thapaliya
 */
public class JavaCompiler extends Observable implements CodeCompiler
{
    private String command ;
    
    /**
     * 
     * @param destinationDirectory is the absolute path of the folder that will receive the compiled class files.
     */
    public JavaCompiler(String destinationDirectory)
    {
        this.command = "javac -d ";
        this.command = (this.command.concat(destinationDirectory)+" ");
    }

    /**
     * Compiles the given sourceFile.
     * @param sourceFile is the absolute path of the java file to be compiled
     */
    public void compile(String sourceFile) 
    {
       // System.out.println("File dir: "+ sourceFile);
        sourceFile = this.command.concat(sourceFile);
        //System.out.println("Final Command:"+ sourceFile);
       // this.sourceFile 
        this.setChanged();
        this.notifyObservers(sourceFile);
    }
    
}
