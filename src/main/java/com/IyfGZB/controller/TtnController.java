package com.IyfGZB.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.io.*;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by sahil on 24/9/18.
 */
@RestController
@CrossOrigin

public class TtnController {


    @PostMapping("/ttn/compile")
    @PermitAll

    public void compileCode() throws Exception {

//        File file = new File("SampleCode.java");
//        if (file.createNewFile()) {
//            System.out.println("file created ");
//        } else {
//            System.out.println("file not created");
//        }
//
//        BufferedWriter writer = new BufferedWriter(new FileWriter("SampleCode.java"));
//        writer.write(code);
//
//        writer.close();
        execute("javac","SampleCode.java");
        execute("java","SampleCode");

    }

    public void execute(String command, String className) throws InterruptedException, IOException {
        ProcessBuilder builder = new ProcessBuilder("java","-cp",".","SampleCode");

        Map<String, String> environ = builder.environment();
        Process process = null;
        InputStream processInputStream = null;
        OutputStream processOutputStream = null;
        try {
            builder.redirectErrorStream(true);

            File output = new File("ProcessLog.txt");

            builder =builder.redirectErrorStream(true);
            process = builder.start();



            processInputStream = process.getInputStream();
            processOutputStream = process.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }



        byte[] buffer = new byte[4000];
        while (isAlive(process)) {


            int ni =  System.in.available();
            if (ni > 0) {
                int n = System.in.read(buffer, 0, Math.min(ni, buffer.length));
                processOutputStream.write(buffer, 0, n);
                processOutputStream.flush();
            }
            int no = processInputStream.available();
            if (no > 0) {
                int n = processInputStream.read(buffer, 0, Math.min(no, buffer.length));

                System.out.println(new String(buffer, 0, n));
            }

            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                System.out.println(" process intrupteed " );
            }
        }

        System.out.println(process.exitValue());
    }




    public static boolean isAlive(Process p) {
        try {
            p.exitValue();
            return false;
        }
        catch (IllegalThreadStateException e) {
            return true;
        }
    }
}

