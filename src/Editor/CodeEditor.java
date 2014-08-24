
package Editor;


import ClassLoader.ClassFileLoader;
import Compiler.CodeCompiler;
import Compiler.JavaCompiler;
import Executor.CodeExecutor;
import Executor.ExecutionStarter;
import Executor.JavaExecutor;
import java.io.*;
import java.awt.*;
import javax.swing.*;



/**
 * This is class CodeEditor a Frame  which serves as a basic code editor.
 * @author thapaliya
 */
public class CodeEditor extends JFrame 
{ 

    public JLabel jlblStatus ;
    public JTextArea jta ;
    JTextArea lineBar;
    JTextArea outputWindow ;
    JMenuBar menuBar ;
    public JFileChooser jFileChooser ;
    public CodeExecutor javaExecutor;
    public CodeCompiler javaCompiler;
    private File openedFile;
    private ClassFileLoader classLoader;
    
    public CodeEditor() 
    {       
        Font f;
        JScrollPane []scrollPane;
        JSplitPane componentSplitter ;
        LineBar line;
        Data data;
        
        modifyLooks();
        jlblStatus = new JLabel();
        jta = (JTextArea) new TextArea();
        lineBar = new LineBar();
        outputWindow = new TextArea();
        menuBar = (Menu)new Menu(this);  
        jFileChooser = new JFileChooser(new File("."));
        classLoader = new ClassFileLoader();
        
        f = new Font("Courier", Font.PLAIN, 13);
        scrollPane  = new JScrollPane[2];
        for(int i=0; i<scrollPane.length; i++) scrollPane[i]= new JScrollPane();
        componentSplitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane[0], scrollPane[1]);
        line = new LineBar();
        data = new Data();

        initJavaRunner();
        
        data.addObserver(line);
        line.setEditable(false);
        line.setBackground(Color.lightGray);
        jta.setFont(f);
        jta.setTabSize(4);
        jta.getDocument().addDocumentListener(data);
   
        line.setFont(f);
      
        scrollPane[0].setViewportView(jta);
        scrollPane[0].setRowHeaderView(line);
        scrollPane[1].setViewportView(outputWindow);
        
        componentSplitter.setDividerLocation(150);
        
        this.setJMenuBar(menuBar);
        this.add(componentSplitter, BorderLayout.CENTER);
        this.add(jlblStatus, BorderLayout.PAGE_END);
       
    }
    
    /*
     * Modifies the UI looks of this Editor
     */
    private void modifyLooks()
    {
        try
        {
           //WindowsFileChooserUI wui = new WindowsFileChooserUI(jFileChooser);
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           //wui.installUI(this.jFileChooser);

        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
    
    /*
     * Initializs java compiler and executor
     */
    private void initJavaRunner()
    {
        ExecutionStarter streamMessenger;
        
        this.javaCompiler = new JavaCompiler(classLoader.getFolderPath());
        this.javaExecutor  = new JavaExecutor();
      
        streamMessenger = new ExecutionStarter(this.outputWindow);
        
        ((JavaExecutor)this.javaExecutor).addObserver(streamMessenger);
        ((JavaCompiler)this.javaCompiler).addObserver(streamMessenger);
        
       
    }

    
    /**
     *  Need to call this method when file is opened by a user
     * @param file is the file that has been recently opened
     */
    public void openedFile(File file)
    {
        this.openedFile=file;
    }
    
    /**
     * Need to call this method when file is saved by a user
     * @param file is the file that has been recently saved 
     */
    public void savedFile(File file)
    {
        this.openedFile=file;
    }
    
    /**
     * Compiles the opened file in the selected Language
     */
    public void compile()
    {
        System.out.println("Compile()");
         this.outputWindow.setEditable(true);
        if(this.openedFile !=null && this.openedFile.exists())
        {
            this.outputWindow.setText(null);
            this.javaCompiler.compile(this.openedFile.getAbsolutePath());
        
        }
        else
        {
            this.outputWindow.setText("Nothing to compile\n");
        }
        this.outputWindow.setEditable(false);
    }
    
    /**
     *  Executes the opened file
     */
    public void execute()
    {      
        this.outputWindow.setEditable(true);
        if (this.openedFile!=null && this.openedFile.exists())
        {
            this.classLoader.loadClass(this.openedFile);
            this.outputWindow.setText(null);
            this.outputWindow.setEditable(false);
            this.javaExecutor.execute(this.classLoader.getClassExecutionPath());
        }
        else
        {
            
            this.outputWindow.setText("Nothing to run\n");
            this.outputWindow.setEditable(false);
        }
    }
    
    /**
     * This is the main method that runs the editor.
     * @param args 
     */
    public static void main(String args[]) {
        System.out.println("ClassEditor main()");
        JFrame f = new CodeEditor();
        f.setTitle("Code Editor");
        f.setSize(500, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}