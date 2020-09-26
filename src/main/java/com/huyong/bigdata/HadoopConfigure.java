package com.huyong.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HadoopConfigure {
    public static Configuration getConf() {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.43.199:9000");
        return conf;
    }

    public static void test(String file, Configuration conf) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        Path remotePath = new Path(file);
        FSDataInputStream in = fs.open(remotePath);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));
        d.lines().forEach(System.out :: println);
        d.close();
        in.close();
        fs.close();
    }

    public static void testJob(Configuration conf) throws IOException, ClassNotFoundException, InterruptedException {
        Job instance = Job.getInstance(conf);
        instance.setMapperClass(WorldMap.class);
        instance.setReducerClass(WorldReduce.class);
        instance.setMapOutputKeyClass(Text.class);
        instance.setMapOutputValueClass(LongWritable.class);
        instance.setOutputKeyClass(Text.class);
        instance.setOutputValueClass(Long.class);
        instance.setJobName("worldCount");
        FileInputFormat.addInputPath(instance, new Path("/user/huyong/world.txt"));
        FileOutputFormat.setOutputPath(instance, new Path("/user/huyong/testWorldCount"));
        boolean b = instance.waitForCompletion(true);
        if (b) {
            System.out.println("执行成功");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        //test("/user/huyong/world.txt", getConf());
        testJob(getConf());
    }
}
