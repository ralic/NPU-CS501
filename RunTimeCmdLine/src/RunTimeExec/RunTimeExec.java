
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunTimeExec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raliclo
 */
public class RunTimeExec {

    private static final Logger LOG = Logger.getLogger(RunTimeExec.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            /**
             * @param speedX to monitor program runtime
             */
            // TODO code application logic here
            long speedX = System.currentTimeMillis();
            runexec("ls -l");
            System.out.println("Time spent :" + (System.currentTimeMillis() - speedX));
        } catch (IOException ex) {
            Logger.getLogger(RunTimeExec.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param x for command line
     * @throws java.io.IOException
     */
    public static void runexec(String x) throws IOException {
        //final 
        Process p = Runtime.getRuntime().exec(x);
//-------------lambda Codes-------------
        new Thread(() -> {
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
// Print out input stream.
            try {
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
//-------------Original Codes-------------
//        Process p2 = Runtime.getRuntime().exec(x);
//        new Thread(new Runnable() {
//            public void run() {
//                BufferedReader input = new BufferedReader(new InputStreamReader(p2.getInputStream()));
//                String line = null;
//
//                try {
//                    while ((line = input.readLine()) != null) {
//                        System.out.println(line);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
// Wait for.
        try {
            p.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(RunTimeExec.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
