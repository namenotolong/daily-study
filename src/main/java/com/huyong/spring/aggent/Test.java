package com.huyong.spring.aggent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        VirtualMachine attach = VirtualMachine.attach("80874");
        System.out.println(1);
        attach.loadAgent("/Users/huyong/Documents/workhome/agent-test/target/agent-test-1.0-SNAPSHOT.jar");
    }
}
