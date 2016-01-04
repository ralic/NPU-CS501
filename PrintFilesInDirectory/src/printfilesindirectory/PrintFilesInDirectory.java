
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printfilesindirectory;

import java.io.*;
import java.net.*;
//import java.security.*;
import java.math.*;
//import java.text.*;
import java.util.*;
//import java.lang.*;
//import java.time.*;
//import java.applet.*;
import java.nio.*;
//import java.beans.*;
//import java.rmi.*;
import java.util.logging.*;
//import java.util.concurrent.*;
//import java.util.stream.*;
//import java.util.function.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.nio.file.spi.*;
import java.nio.file.attribute.*;
import java.nio.channels.*;
import java.nio.channels.spi.*;
//import import java.awt.*; // Disabled, because of List Class conflict
//import java.sql.*; // Disbled, because of Connection Class conflict

/**
 *
 * @author raliclo
 * @Java : java version "1.8.0_65"
 */
public class PrintFilesInDirectory {

    private static final Logger LOG = Logger.getLogger(PrintFilesInDirectory.class.getName());

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
            runexec("pwd");
            String[] abc = new String[2];
            abc[0] = "./";
            run(abc);
            System.out.println("Time spent :" + (System.currentTimeMillis() - speedX) + "msec");
        } catch (IOException ex) {
            Logger.getLogger(PrintFilesInDirectory.class.getName()).log(Level.SEVERE, "IO bug", ex);
        } catch (Exception ex) {
            Logger.getLogger(PrintFilesInDirectory.class.getName()).log(Level.SEVERE, "Any bug", ex);
        }
    }

    private static void run(String[] args) throws Exception {
        File folder = new File((args.length >= 1 && args[0].length() > 0)
                ? args[0]
                : ".");

        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("Invalid directory: " + folder.getName());
        }

        int level = 0;
        System.out.println(renderFolder(folder, level, new StringBuilder(), false));
    }

    private static StringBuilder renderFolder(File folder, int level, StringBuilder sb, boolean isLast) {
        indent(sb, level, isLast).append("[D] ").append(folder.getName()).append("\n");

        File[] objects = folder.listFiles();

        for (int i = 0; i < objects.length; i++) {
            boolean last = ((i + 1) == objects.length);

            if (objects[i].isDirectory()) {
                renderFolder(objects[i], level + 1, sb, last);
            } else {
                renderFile(objects[i], level + 1, sb, last);
            }
        }

        return sb;
    }

    public static StringBuilder renderFile(File file, int level, StringBuilder sb, boolean isLast) {
        return indent(sb, level, isLast).append("--- ").append(file.getName()).append("\n");
    }

    public static StringBuilder indent(StringBuilder sb, int level, boolean isLast) {
        for (int i = 1; i < level; i++) {
            sb.append("|  ");
        }

        if (level > 0) {
            sb.append(isLast
                    ? "`-"
                    : "|-");
        }

        return sb;
    }

    /**
     * @param x for command line
     * @throws java.io.IOException
     */
    public static Object runexec(String x) throws IOException {
        Process p = Runtime.getRuntime().exec(x, null, null);
        Object ans = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        try {
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                //   For Octave integration only 
                //   if (line.startsWith("ans =")) {
                //    System.out.println(line.split("=")[1].trim());
                //     ans = line.split("=")[1].trim();
                // }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            p.waitFor();

        } catch (InterruptedException ex) {
            Logger.getLogger(PrintFilesInDirectory.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }
}
