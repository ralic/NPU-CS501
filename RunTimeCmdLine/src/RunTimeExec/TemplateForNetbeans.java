<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">

<#include "${project.licensePath}">

<#if package?? && package != "">
package ${package};

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
</#if>
/**
 *
 * @author ${user}
 */
public class ${name} {

    private static final Logger LOG = Logger.getLogger(${name}.class.getName());

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
            Logger.getLogger(${name}.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param x for command line
     * @throws java.io.IOException
     */
    public static void runexec(String x) throws IOException {
        final Process p = Runtime.getRuntime().exec(x);
//-------------lambda Codes to print InputStream-------------
        new Thread(() -> {
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            try {
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            p.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(${name}.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}