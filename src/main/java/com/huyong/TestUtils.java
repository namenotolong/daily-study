package com.huyong;


public class TestUtils {
    public static void printArgs(String ... args){
        for(Object elem: args){
            System.out.println(elem + " ");
        }
    }

    public static void main(String[] args) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (final StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.getClassName() + ","
            + stackTraceElement.getFileName() + "," + stackTraceElement.getMethodName()
                    + "," + stackTraceElement.getLineNumber());
        }
    }
}
