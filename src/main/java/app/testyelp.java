package app;
import java.io.*;
public class testyelp {
   
    public void pythonrun(String args) throws IOException, InterruptedException
    {
       
        String pythonScriptPath = "msp.py";
        String[] cmd = new String[3];
        cmd[0] = "python";
        cmd[1] = pythonScriptPath;
        cmd[2] = args;
       
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(cmd);
        pr.waitFor();
     
        BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while((line = bfr.readLine()) != null) {
        System.out.println(line);
        }
    }
   
    public static void main(String args[]) throws IOException, InterruptedException {
       
    testyelp demo = new testyelp();
    demo.pythonrun("tandoori-oven-san-jose-2");
    }
    }