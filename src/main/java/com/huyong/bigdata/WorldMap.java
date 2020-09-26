package com.huyong.bigdata;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WorldMap extends Mapper<LongWritable, Text, Text, LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (value != null) {
            String s = value.toString();
            if (s != null) {
                String[] split = s.split(",");
                for (String s1 : split) {
                    context.write(new Text(s1), new LongWritable(1));
                }
            }
        }
    }
}
