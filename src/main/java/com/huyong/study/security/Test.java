package com.huyong.study.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-09 6:49 下午
 */
public class Test {
    public static void main(String[] args) throws IOException {
        SecurityManager securityManager = System.getSecurityManager();
        System.out.println(securityManager);
        if (securityManager == null) {
            System.setSecurityManager(new SecurityManager());
        }

        File file = new File("./test.txt");
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            try {
                new FileReader(file).read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
