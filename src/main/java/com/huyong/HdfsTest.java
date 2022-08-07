package com.huyong;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsTest {
    public static void main(String[] args) {
        try{
            System.setProperty("hadoop.home.dir", "D:/download_data/hadoop-common-2.2.0-bin-master/hadoop-common-2.2.0-bin-master");
            //System.setProperty("HADOOP_HOME", "D:/download_data/hadoop-common-2.2.0-bin-master/hadoop-common-2.2.0-bin-master");


            String fileName = "/test";
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://192.168.36.150:9000");
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
            FileSystem fs = FileSystem.get(conf);
            if(fs.exists(new Path(fileName))){
                System.out.println("文件存在");
            }else{
                System.out.println("文件不存在");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
