
package ClassLoader;


import java.io.File;

/**
 *  This is class ClassFileLoader that loads class file directory to be executed.
 *  Searches for the compiled class for the java file in question. 
 *  First it looks up the class folder of this editor application.
 *  If found then it sets up the execution path -- for the class file to run -- as such.
 *  If not found then looks up the directory of the java file and if found there then copies that compiled code to the class folder.
 *  Then sets up the execution path.
 * @author thapaliya
 */
public class ClassFileLoader 
{
    private ClassFolder folder;
    private String folderPath, classExecutionPath, className;
    private File folderFile;

    
    public ClassFileLoader()
    {
        folder = new ClassFolder();
        folderPath = ClassFolder.getAbsolutePath();
        folderFile = new File(folderPath);
    }
 
    /**
     * Loads the compiled file of the java code to be executed
     * @param file is the java file that needs to be executed
     */
    public void loadClass(File file)
    {
        String fileName;
        fileName = file.getName();
       
        //check if file is in the folder
        if(fileName.contains(".java"))
        {
            className = fileName.replace(".java", " ").trim();
            className = className.concat(".class");
            if(isClassInFolder(fileName))
            {
                
                classExecutionPath  = this.folderPath.concat(" "+className.replace(".class"," ").trim());
                System.out.println("classexecPath:"+ this.classExecutionPath);
            }
            else classNotInEditorFolder(file);
        
        }
        
    }
    
    /*
     * returns true if the class file is in the application  folder 
     * or else false.
     */
    private boolean isClassInFolder(String fileName)
    {
        String[] files;
        //check if file is in the folder

        files = folderFile.list();

        for (int i = 0; i < files.length; i++) 
        {
            if (this.className.equals(files[i])) 
            {
                return true;
            }
        }

        return false;
    }
    
    /*
     * Needs to be called when class file is not in the application's  class folder
     * This looks up the directory of the java file for its class file.
     * file is the java file that needs to be executed
     */
    private void classNotInEditorFolder(File file)
    {
        String filePath;
        File fileDir;
        
        filePath = file.getParent();
        fileDir = new File(filePath);
        
        if(fileDir.exists() && fileDir.isDirectory())
        {
            System.out.println("Class Not in editor");
            if(isClassInDir(fileDir))
            {
                file = new File(file.getAbsolutePath().replace(".java", ".class"));
                //copy file to the class folder
                FileCopier.copyTo(file, this.folderPath.concat("\\"+this.className));
                this.classExecutionPath = this.folderPath.concat(" "+this.className.replace(".class", " ").trim());
            }
        }
    }
    
    
    /*
     * returns true if the class file is found in the java files folder/directory
     * or else false
     * file is the filepath file of the java file or the a parent of the java file that has the path of its directory.
     */
    private boolean isClassInDir(File file)
    {
        String []files;
        files = file.list();
        
        for(int i=0; i< files.length; i++)
        {
            if(files[i].equals(this.className)) return true;
        }
        return false;
    }
    
    /**
     * 
     * @return the execution path or the class path for execution 
     */
    public String getClassExecutionPath()
    {
        return this.classExecutionPath;
    }
    
    /**
     * 
     * @return the application's class folders path 
     */
    public String getFolderPath()
    {
        return this.folderPath;
    }
    
    
}
