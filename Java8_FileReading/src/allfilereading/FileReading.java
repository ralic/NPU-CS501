
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allfilereading;

import java.io.*;
import java.net.*;
import java.security.*;
import java.math.*;
import java.awt.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.lang.*;
import java.time.*;
import java.applet.*;
import java.nio.*;
import java.beans.*;
import java.rmi.*;
import java.util.logging.*;
import java.util.concurrent.*;
import java.util.stream.*;
import java.util.function.*;

import java.nio.file.*;
import java.nio.charset.*;
import java.nio.file.spi.*;
import java.nio.file.attribute.*;
import java.nio.channels.*;
import java.nio.channels.spi.*;

/**
 *
 * @author raliclo
 */
public class FileReading {

    private static final Logger LOG = Logger.getLogger(FileReading.class.getName());

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
            Path path = Paths.get("./build.xml");
            fileReading(path);
            System.out.println("Time spent :" + (System.currentTimeMillis() - speedX));
        } catch (IOException ex) {
            Logger.getLogger(FileReading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void fileReading(Path path) {
        try {
            Files.lines(path).distinct()
                    .forEach(s -> System.out.println(s));
        } catch (Exception ex) {
            Logger.getLogger(FileReading.class.getName())
                    .log(Level.SEVERE, "IO Error", ex);
        }
    }

    /**
     * @param x for command line
     * @throws java.io.IOException
     */
    public static void runexec(String x) throws IOException {
        final Process p = Runtime.getRuntime().exec(x);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        try {
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            p.waitFor();

        } catch (InterruptedException ex) {
            Logger.getLogger(FileReading.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
