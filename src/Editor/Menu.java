/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



/**
 * This is a class Menu -- extends JMenuBar -- that serves as a Menu for the editor.
 * @author thapaliya
 */
public class Menu extends JMenuBar
{
    public Object editor;
    
    public Menu(JFrame frame)
    {
       
    }
   
    /**
     * 
     * @param editor is this menu's frame  
     */
    public Menu( CodeEditor editor)
    {  
        this.editor = editor;
        initMenu();
    }
   
   
    /*
     * Initializes the Menu with all of its components
     */
    private void initMenu()
    {
        JMenu [] menu  = new JMenu[3];
        menu[0]= new JMenu("File");
        menu[1]= new JMenu("Edit");
        menu[2]= new JMenu("Execute");
        JMenuItem jmiOpen = new JMenuItem("Open");
        JMenuItem jmiSave = new JMenuItem("Save");
        JMenuItem jmiClear = new JMenuItem("Clear");
        JMenuItem jmiExit = new JMenuItem("Exit");
        JMenuItem jmiForeground = new JMenuItem("Foreground");
        JMenuItem jmiBackground = new JMenuItem("Background");
        JMenuItem jmiCaret = new JMenuItem("Caret");
        JMenuItem compileProject = new JMenuItem("Compile Program");
        JMenuItem runProject = new JMenuItem("Run Program");
        
        menu[0].add(jmiOpen);
        menu[0].add(jmiSave);
        menu[0].add(jmiClear);
        menu[0].addSeparator();
        menu[0].add(jmiExit);

        menu[1].add(jmiBackground);
        menu[1].add(jmiForeground);
        menu[1].add(jmiCaret);
        
        menu[2].add(compileProject);
        menu[2].add(runProject);
        
        
        this.add(menu[0]);
        this.add(menu[1]);
        this.add(menu[2]);
       
        
        jmiOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });
        jmiSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        jmiClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                ((CodeEditor)editor).jta.setText(null);
                
            }
        });
        jmiExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        jmiForeground.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                Color selectedColor;
                selectedColor = JColorChooser.showDialog(null, "Choose Foreground Color", ((CodeEditor)editor).jta.getForeground());
                if (selectedColor != null) { 
                    
                    ((CodeEditor)editor).jta.setForeground(selectedColor);
                }
            }
        });
        jmiBackground.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor =
                        JColorChooser.showDialog(null, "Choose Background Color", ((CodeEditor)editor).jta.getBackground());
                if (selectedColor != null) {
                    ((CodeEditor)editor).jta.setBackground(selectedColor);
                }
            }
        });
        jmiCaret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor =
                        JColorChooser.showDialog(null, "Choose Caret Color", ((CodeEditor)editor).jta.getCaretColor());
                if (selectedColor != null) {
                    ((CodeEditor)editor).jta.setCaretColor(selectedColor);
                }
            }
        });
        compileProject.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ((CodeEditor)editor).compile();
            }
        });
        runProject.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {   System.out.println("Java Run Clicked");
                ((CodeEditor)editor).execute();
            }
        
        });


    }
   
   
    /*
     * Opens a jFileChosser to select a file
     */
    private void open() {
        if (((CodeEditor)editor).jFileChooser.showOpenDialog(this)
                == JFileChooser.APPROVE_OPTION) {
            open(((CodeEditor)editor).jFileChooser.getSelectedFile()); 
        }
    }

    /*
     * Opens file in the editor
     */
    private void open(File file) {
        try { 
            System.out.println(file.getAbsolutePath()); 
            System.out.println(file.getName());
            BufferedInputStream in =
                    new BufferedInputStream(new FileInputStream(file));
            byte[] b = new byte[in.available()];
            in.read(b, 0, b.length);
            ((CodeEditor)editor).jta.setText(null);
            ((CodeEditor)editor).jta.append(new String(b, 0, b.length));
            ((CodeEditor)editor).openedFile(file);
            
            in.close();

            ((CodeEditor)editor).jlblStatus.setText(file.getName() + " opened.");
        } catch (IOException ex) {
            ((CodeEditor)editor).jlblStatus.setText("Error opening " + file.getName());
        }
    }

    /*
     *  Opens a jfileChooser to save a file
     */
    private void save() {
        if (((CodeEditor)editor).jFileChooser.showSaveDialog(this)
                == JFileChooser.APPROVE_OPTION) {
            save(((CodeEditor)editor).jFileChooser.getSelectedFile());
        }
    }

    /*
     * Saves the given file
     */
    private void save(File file) {
        try {
            BufferedOutputStream in =
                    new BufferedOutputStream(new FileOutputStream(file));
            byte[] b = (((CodeEditor)editor).jta.getText()).getBytes();
            in.write(b, 0, b.length);
            in.close();

            ((CodeEditor)editor).jlblStatus.setText(file.getName() + " saved.");
            ((CodeEditor)editor).savedFile(file);
        } catch (IOException ex) {
            ((CodeEditor)editor).jlblStatus.setText("Error saving " + file.getName());
        }
    }
   
   
    
}
