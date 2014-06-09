import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CodeEditor extends JFrame{ //JApplet{
	
	public static void main(String args[]){
		JFrame f = new CodeEditor();
		f.setTitle("Code Editor");
		f.setSize(500, 300);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	private JMenuItem jmiOpen = new JMenuItem("Open");
	private JMenuItem jmiSave = new JMenuItem("Save");
	private JMenuItem jmiClear = new JMenuItem("Clear");
	private JMenuItem jmiExit = new JMenuItem("Exit");
	private JMenuItem jmiForeground = new JMenuItem("Foreground");
	private JMenuItem jmiBackground = new JMenuItem("Background");
	private JMenuItem jmiCaret = new JMenuItem("Caret");
	
	private JLabel jlblStatus = new JLabel();
	
	private JFileChooser jFileChooser = new JFileChooser(new File("."));
	
	private JTextArea jta = new JTextArea();
	
	//--------------------------------------
	public CodeEditor(){
		JMenu jMenu1 = new JMenu("File");
		jMenu1.add(jmiOpen);
		jMenu1.add(jmiSave);
		jMenu1.add(jmiClear);
		jMenu1.addSeparator();
		jMenu1.add(jmiExit);
		
		JMenu jMenu2 = new JMenu("Edit");
		jMenu2.add(jmiBackground);
		jMenu2.add(jmiForeground);
		jMenu2.add(jmiCaret);
		
		JMenuBar jMenuBar1 = new JMenuBar();
		jMenuBar1.add(jMenu1);
		jMenuBar1.add(jMenu2);
		
		setJMenuBar(jMenuBar1);
		
		jmiOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				open();
			}
		});
		jmiSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				save();
			}
		});
		jmiClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jta.setText(null);
			}
		});
		jmiExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(1);
			}
		});
		jmiForeground.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Color selectedColor =
						JColorChooser.showDialog(null, "Choose Foreground Color", jta.getForeground());
				if(selectedColor != null)
					jta.setForeground(selectedColor);
			}
		});
		jmiBackground.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Color selectedColor =
						JColorChooser.showDialog(null, "Choose Background Color", jta.getBackground());
				if(selectedColor != null)
					jta.setBackground(selectedColor);
			}
		});
		jmiCaret.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Color selectedColor =
						JColorChooser.showDialog(null, "Choose Caret Color", jta.getCaretColor());
				if(selectedColor != null)
					jta.setCaretColor(selectedColor);
			}
		});
		
		Font f = new Font("Courier", Font.PLAIN, 12);
		jta.setFont(f);
		jta.setTabSize(4);
		add(jlblStatus, BorderLayout.SOUTH);
		add(new JScrollPane(jta), BorderLayout.CENTER);
	}
	
	private void open(){
		if(jFileChooser.showOpenDialog(this) == 
				JFileChooser.APPROVE_OPTION)
			open(jFileChooser.getSelectedFile());
	}
	private void open(File file){
		try{
			BufferedInputStream in = 
					new BufferedInputStream(new FileInputStream(file));
			byte[] b = new byte[in.available()];
			in.read(b, 0, b.length);
			jta.append(new String(b, 0, b.length));
			in.close();
			
			jlblStatus.setText(file.getName() + " opened.");
		}catch(IOException ex){
			jlblStatus.setText("Error opening " + file.getName());
		}
	}
	private void save(){
		if(jFileChooser.showSaveDialog(this) == 
				JFileChooser.APPROVE_OPTION)
			save(jFileChooser.getSelectedFile());
	}
	private void save(File file){
		try{
			BufferedOutputStream in = 
					new BufferedOutputStream(new FileOutputStream(file));
			byte[] b = (jta.getText()).getBytes();
			in.write(b, 0, b.length);
			in.close();
			
			jlblStatus.setText(file.getName() + " saved.");
		}catch(IOException ex){
			jlblStatus.setText("Error saving " + file.getName());
		}
	}
	
	
	
}