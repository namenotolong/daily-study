package com.huyong.study.udp;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-26 10:40 下午
 */
public class Test {
    public static void main(String[] args) {
        String str = "base\n" +
                "biga\n" +
                "bi\n" +
                "bizop\n" +
                "cdm\n" +
                "di\n" +
                "dw\n" +
                "fbi\n" +
                "ferrari\n" +
                "fin\n" +
                "flink\n" +
                "fuxi\n" +
                "gather\n" +
                "im\n" +
                "impala\n" +
                "log\n" +
                "mbr\n" +
                "middleware\n" +
                "noops\n" +
                "ods\n" +
                "park\n" +
                "qa\n" +
                "rectech\n" +
                "resys\n" +
                "sc\n" +
                "spp\n" +
                "techfin\n" +
                "vc\n" +
                "wpp";
        String[] split = str.split("\n");
        List<String> list  = Lists.newArrayList();
        list.addAll(Arrays.asList(split));
        System.out.println(list);
        List<String> collect = list.stream().map(e -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Name", e);
            jsonObject.put("Value", e);
            return jsonObject.toJSONString();
        }).collect(Collectors.toList());
        System.out.println(collect);
    }
}
