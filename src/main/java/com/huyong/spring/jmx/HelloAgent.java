package com.huyong.spring.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class HelloAgent {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        mBeanServer.registerMBean(new Hello(), helloName);
        /*ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        mBeanServer.registerMBean(adapter, adapterName);
        adapter.start();*/
        Thread.sleep(10000000);
    }
}
