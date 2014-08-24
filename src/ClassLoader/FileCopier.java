
package ClassLoader;


import Editor.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * This is a FileCopier class that copies a file
 * @author thapaliya
 */
public class FileCopier 
{
   static  FileInputStream inputStream ;
   static  FileOutputStream outputStream;
    
    public FileCopier()
    {
    
    }

    /**
     * Copies file to filePath
     * @param file is the file to be copied
     * @param filePath is the path of the new file
     */
    public static void copyTo(File file, String filePath)
    {
        System.out.println("Copying file "+file.getAbsolutePath()+ " to "+filePath);
        //System.exit(0);
        try 
        {
            int b;
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(filePath);
          
            if(!file.canRead()) return;
  
            while((b=inputStream.read())!=-1)
            {
                outputStream.write(b);
            }
            
           inputStream.close();
           outputStream.close();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static void main(String[]args)
    {
        File f = new File("C:\\Users\\thapaliya\\Desktop\\codes\\CompileTest.class");
        String f2 = "C:\\Users\\thapaliya\\Desktop\\copy\\CompileTest.class";
        
        FileCopier.copyTo(f, f2);
    }
}
