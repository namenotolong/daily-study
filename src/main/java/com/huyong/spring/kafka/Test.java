package com.huyong.spring.kafka;

import com.google.common.collect.Maps;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.metrics.Metrics;
import org.apache.kafka.common.metrics.Sensor;
import org.apache.kafka.common.metrics.stats.Avg;
import org.apache.kafka.common.metrics.stats.Max;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Metrics metrics = new Metrics(); // this is the global repository of metrics and sensors
        Sensor sensor = metrics.sensor("message-sizes");
        MetricName metricName = new MetricName("message-size-avg", "producer-metrics", "desc", Maps.newHashMap());
        sensor.add(metricName, new Avg());
        metricName = new MetricName("message-size-max", "producer-metrics", "desc", Maps.newHashMap());
        sensor.add(metricName, new Max());

        // as messages are sent we record the sizes
        sensor.record(10);
        sensor.record(5);

        Thread.sleep(1000000L);
    }
}
