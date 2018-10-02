//package com.IyfGZB.controller;
//
//import java.io.*;
//
//public class Test1 {
//    private static PrintStream out;
//
//    public static void main(String[] args) {
//        String hostName = "testpc";
//        String[] commands = {"getmac", "/s", hostName,
//                "/nh"};
//        ProcessBuilder builder = new ProcessBuilder(commands);
//
//        // builder.inheritIO(); // I avoid this. It was messing me up.
//
//        try {
//            Process proc = builder.start();
//            InputStream errStream = proc.getErrorStream();
//            InputStream inStream = proc.getInputStream();
//            OutputStream outStream = proc.getOutputStream();
//
//            new Thread(new StreamGobbler("in", out, inStream)).start();
//            new Thread(new StreamGobbler("err", out, errStream)).start();
//
//            out = new PrintStream(new BufferedOutputStream(outStream));
//            int errorCode = proc.waitFor();
//            System.out.println("error code: " + errorCode);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                out.close();
//            }
//        }
//    }
//}
