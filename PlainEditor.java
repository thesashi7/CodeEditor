import java.awt.*;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.*;

public class PlainEditor extends JApplet{
	private JTextArea jta = new JTextArea();
	private JMenuItem jmiOpen = new JMenuItem("Open");
	private JMenuItem jmiSave = new JMenuItem("Save");
	
	public PlainEditor(){
		JMenu jMenu1 = new JMenu("File");
		jMenu1.add(jmiSave);
		
		JMenuBar jMenuBar1 = new JMenuBar();
		jMenuBar1.add(jMenu1);
		setJMenuBar(jMenuBar1);
		
		jmiSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					BufferedOutputStream in = 
							new BufferedOutputStream(new FileOutputStream("filename"));
					byte[] b = (jta.getText()).getBytes();
					in.write(b, 0, b.length);
					in.close();
				}catch(IOException ex){
					
				}
			}
		});
		
		add(new JScrollPane(jta), BorderLayout.CENTER);
	}
	
}
