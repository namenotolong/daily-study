package com.huyong.bigdata;

import org.apache.commons.compress.utils.Lists;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Test111> list = Lists.newArrayList();
        Test111 t1 = new Test111();
        Test111 t2 = new Test111();
        t1.warn = true;
        list.add(t1);
        list.add(t2);
        System.out.println(list.stream().filter(e -> e.warn).collect(Collectors.toList()));
    }
}
class Test111{
    Boolean warn;
}
