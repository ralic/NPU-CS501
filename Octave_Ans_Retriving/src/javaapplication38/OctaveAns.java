
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication38;

import java.io.*;
import java.net.*;
import java.security.*;
import java.math.*;
import java.awt.*;
import java.sql.*;
import java.text.*;
import java.lang.*;
import java.time.*;
import java.applet.*;
import java.beans.*;
import java.rmi.*;

import java.util.*;
import java.util.logging.*;
import java.util.concurrent.*;
import java.util.stream.*;
import java.util.function.*;

import java.nio.*;
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
public class OctaveAns {

    private static final Logger LOG = Logger.getLogger(OctaveAns.class.getName());

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
            int i = 0;
            Object x1 = runexec("/usr/local/Cellar/octave/4.0.0_1/bin/octave-4.0.0 ~/a.m");
            System.out.println(x1);
            System.out.println("Time spent :" + (System.currentTimeMillis() - speedX));
        } catch (IOException ex) {
            Logger.getLogger(OctaveAns.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                if (line.startsWith("ans =")) {
                    ans = line.split("=")[1].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            p.waitFor();

        } catch (InterruptedException ex) {
            Logger.getLogger(OctaveAns.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }
}
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
//-------------Original Codes-------------
//            System.out.println(System.getenv("PATH"));
//            Script to get System Path Variable.
//            Map<String, String> env = System.getenv();
//            String[] keys = new String[env.size()];
//            String[] values = new String[env.size()];
//            for (String envName : env.keySet()) {
//                System.out.format("%s=%s%n", envName, env.get(envName));
//                keys[i] = envName;
//                values[i] = env.get(envName);
//                i++;
//            }
