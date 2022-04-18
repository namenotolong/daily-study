package com.huyong.study.aop.bytebuddy;

import java.io.*;
import java.lang.reflect.InvocationTargetException;


public class Test1 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*ByteBuddy byteBuddy = new ByteBuddy();
        IPeople iPeople = byteBuddy
                .subclass(IPeople.class)
                .method(isDeclaredBy(IPeople.class))
                .intercept(MethodDelegation.to(new RealRun()))
                .make()
                .load(Test1.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor().newInstance();
        String say = iPeople.say();
        System.out.println(say);*/
        test();

    }

    public static void test() {
       try {
           ProcessBuilder builder = new ProcessBuilder("whoami");
           builder.directory(new File("/Users/huyong/Documents/workhome/daily-study"));
           Process process = builder.start();
           System.out.println(process.info());

           final BufferedReader errReader =
                   new BufferedReader(
                           new InputStreamReader(process.getErrorStream()));
           final BufferedReader inReader =
                   new BufferedReader(
                           new InputStreamReader(process.getInputStream()));

           Thread errThread = new Thread() {
               @Override
               public void run() {
                   try {
                       String line = errReader.readLine();
                       while ((line != null) && !isInterrupted()) {
                           System.out.println("error: " + line);
                           line = errReader.readLine();
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
           };

           Thread inThread = new Thread() {
               @Override
               public void run() {
                   try {
                       String line = inReader.readLine();
                       System.out.println("input: " + line);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   super.run();
               }
           };

           try {
               errThread.start();
               inThread.start();

               int i = process.waitFor();
               System.out.println("exit :" + i);

               errThread.join();
               inThread.join();
           } catch (IllegalStateException ise) {
               ise.printStackTrace();
           }



       } catch (Exception e) {
           e.printStackTrace();
       }



    }

}
