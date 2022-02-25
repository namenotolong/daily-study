package com.huyong.study.test;


import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-14 9:06 下午
 */
public class Test {

    public static void main(String[] args) {
        String total = "147796,155824,156006,164398,164441,172643,172775,172831,175685,177647,180542,180543,180642,180643,180644,180649,180797,180884,180971,180974,188422,188536,188540,188613,188672,188673,188674,188697,188766,188823,188844,188907";
        Set<String> totalSet = Arrays.stream(total.split(",")).map(String::trim).collect(Collectors.toSet());


        String cmd = "ognl -x 3 '#springContext=@com.vdian.bigdata.ant.utils.ApplicationContextUtil@applicationContext,#springContext.getBean(\"marsJobService\").directPublishSchedule(%s)' -c 45b15381\n";
        for (String s : totalSet) {
            System.out.printf(cmd, s);
        }
    }
}