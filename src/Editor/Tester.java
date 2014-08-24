package Editor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thapaliya
 */
public class Tester {

    public static void main(String[] args) throws IOException 
    {
        File file = new File(".");
      //  file = file.getAbsoluteFile();
  
        String current = file.getAbsolutePath();
        System.out.println("Current dir:"+current);
       
        String currentDir = System.getProperty("user.dir");
        
        System.out.println("Current dir using System:" +currentDir);
        try {
            Process p1, p2;
            InputCopier in;
            p1 = Runtime.getRuntime().exec("java -classpath C:\\Users\\thapaliya\\Desktop\\copy CompileTest");
           // p1 =  Runtime.getRuntime().exec("javac C:\\Users\\thapaliya\\Desktop\\codes\\CompileTest.java");
            in = new InputCopier(getChannel(System.in), p1.getOutputStream());
            Thread outThread = new Thread(new StreamCopier(p1.getInputStream(), System.out));
            Thread errThread = new Thread(new StreamCopier(p1.getErrorStream(), System.err));
            Thread inThread = new Thread(in);
            
            outThread.start();
            errThread.start();
            inThread.start();

            
            p1.waitFor();
            
            System.out.println("Complete " +inThread.isAlive());
            inThread.interrupt();
            //System.in.close();
            //System.exit(1);
            /* InputStream out = p1.getInputStream(); 
             OutputStream in = p1.getOutputStream();
             InputStreamReader isr = new InputStreamReader(out);
             OutputStreamWriter osw = new OutputStreamWriter(in);
             BufferedReader reader = new BufferedReader(isr);
             BufferedWriter writer = new BufferedWriter(osw);
          
             //p1.waitFor();
             //writer.write(1);
             int ch=0;
             while((ch= reader.read())!=-1)System.out.print((char)ch);
          
             System.out.println("Here");*/

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static FileChannel getChannel(InputStream in)
            throws NoSuchFieldException, IllegalAccessException {
        Field f = FilterInputStream.class.getDeclaredField("in");
        f.setAccessible(true);
        while (in instanceof FilterInputStream) {
            in = (InputStream) f.get((FilterInputStream) in);
        }
        return ((FileInputStream) in).getChannel();
    }
}

class StreamCopier implements Runnable {

    private InputStream in;
    private OutputStream out;

    public StreamCopier(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        try {
            InputStreamReader ins = new InputStreamReader(in); 
            /*int c = 1;
            while (c > 0) {
                c = ins.read();
                System.out.print((char) c);

            }
            ins.close();
            in.close();*/
           int n;
             byte[] buffer = new byte[4096]; 
             while ((n = in.read(buffer)) != -1) { 
             //for(int i=0; i<buffer.length; i++)
             out.write(buffer, 0, n); 
             out.flush();
             }
             System.out.println("Ouputing complete");
        } catch (IOException e) {
            System.out.println(e+" output");
        }
    }
}

class InputCopier implements Runnable {

    private FileChannel in;
    private OutputStream out;
    public boolean  open;
    
    public InputCopier(FileChannel in, OutputStream out) {
        this.in = in;
        this.out = out;
        this.open=true;
    }

    public void run() {
        try {
            OutputStreamWriter os = new OutputStreamWriter(out);
            InputStreamReader ir = new InputStreamReader(System.in);

            
            char[] c = new char[4096];
            while ((ir.read(c)) != -1 && !Thread.interrupted()) {
                //for(int i=0; i< c.length;i++) System.out.print((int)c[i]+",");

                for(int i=0; (int)c[i]>0; i++)os.write(c[i]); 
           
               // os.write(c);
                os.flush(); 
                out.flush();
                Thread.sleep(10);
               // c[0]=0;
            }
            os.close();
            out.close();
            /*int n;
             ByteBuffer buffer = ByteBuffer.allocate(100);
             while ((n = in.read(buffer)) != -1) { 
             out.write(buffer.array(), 0, n);
             out.flush();
             }
             out.close();*/
        } catch (AsynchronousCloseException e) { 
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e+" input");
        } catch (InterruptedException ex) {
            Logger.getLogger(InputCopier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
