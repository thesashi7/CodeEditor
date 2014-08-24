
package ClassLoader;

import Editor.*;
import java.io.File;
import java.io.IOException;


/**
 * This is class ClassFolder that denotes the editor applications actual class folder.
 * @author thapaliya
 */
public class ClassFolder
{
    private static String path;
    private File file;
    
    /**
     * Creates the class file folder in the editor applications path if it doesn't already exist
     * or else gets the classfile folder and its path
     */
    public ClassFolder()
    {
        try 
        {
            file= new File( "." );
            path = file.getCanonicalPath();
            path = path.concat("\\classFile");
            file = new File(path);
            
            //checks if the folder exists and if it is a directory and then creates a new folder if itdoesn't exist
            // or if it is not a directory
            if(file.exists() && file.isDirectory()) System.out.println("exists");
            else new File(path).mkdir(); 
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * 
     * @return the absolute path of the clasfile folder of the editor application. 
     */
    public static String getAbsolutePath()
    {
        return path;
    }
    
    public static void main(String[]args)
    {
        try 
        {
            //new ClassFolder();
            System.out.println(ClassFolder.getAbsolutePath());
            File f = new File("C:\\Users\\thapaliya\\Desktop\\codes");
            System.out.println("Abs File Path: "+f.getAbsolutePath());
            System.out.println("File Name: "+f.getName());
            System.out.println("File path: "+f.getParent());
            String [] files ;
            files = f.list();
            for (int i=0; i< files.length; i++) 
            {
                System.out.print(files[i]+" ");
                if(i==10) System.out.println();
            }
            
            String file ="file.java";
            file = file.replace(".java", " ");
            file = file.trim();
            System.out.println("\nfile="+file);
            System.out.println(file.length()); 
            
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    
    }
    
}
