package com.huyong.spring.yarmmermetrics;

import com.codahale.metrics.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * https://www.orchome.com/1435#item-1
 */
public class Test {


    static MetricRegistry metricRegistry = new MetricRegistry();

    static ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(10, new ThreadFactory() {
        @Override
        public Thread newThread(@NotNull Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("yarmmermetrics test");
            return thread;
        }
    });

    static int count = 0;

    public static String getRealValue() {
        return "hello world" + count++;
    }


    public static void main(String[] args) {
        startReport();
        //Meter度量 count、rate
        Meter requests = metricRegistry.meter("meter test");
        requests.mark();


        //Gauge计量表
        metricRegistry.register(MetricRegistry.name("gauge test"), (Gauge<String>) Test::getRealValue);

        //counter计数器
        Counter counter = metricRegistry.counter("counter test");

        //histograms直方图 柱状图  最小值，最大值，平均值，中位数
        Histogram histogram = metricRegistry.histogram("histogram test");

        executor.scheduleAtFixedRate(() -> {
            counter.inc();
            histogram.update(counter.getCount());
        }, 1,1, TimeUnit.SECONDS);

        //定时器timer 测量特定代码段的调用速率和持续时间的分布。
        Timer timer = metricRegistry.timer("timer test");
        timer.time(() -> System.out.println("hello world"));

        //Health Checks（健康验证）


        try {
            Thread.sleep(5*10000);
        }
        catch(InterruptedException e) {}
    }

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }
}
