package com.huyong.study.test;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-24 11:25 上午
 */
public class Test3 {
    public static void main(String[] args) throws IOException {
        //getLines("/Users/weidian/Desktop/mars.txt");
        //read("/Users/weidian/Desktop/mars.txt", "utf-8");
        //System.out.println("3".matches("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)?"));
        String str = "select maps_total as mapNum, \n" + "reduces_total as reduceNum, \n" + "apply_memory_gb_hour as applyMem, \n"
                + "max_memory_gb_hour as realMem, \n" + "apply_resource as resourceCost, \n"
                + "concat_ws('_',submit_system,owner,submit_type,script_name,run_batch) as yarnName  \n"
                + "from biga.glance_dws_script_batch_metrics_df where pt='" + "2020-09-26" + "' and submit_type=\"schedule\" and submit_system=\"mars\" limit ";
        System.out.println(str);
    }

    public static void read(String filename, String charset) {

        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(filename, "r");
            long len = rf.length();
            long start = rf.getFilePointer();
            long nextend = start + len - 1;
            String line;
            rf.seek(nextend);
            int c = -1;
            while (nextend > start) {
                c = rf.read();
                if (c == '\n' || c == '\r') {
                    line = rf.readLine();
                    if (line != null) {
                        System.out.println(new String(line
                                .getBytes("ISO-8859-1"), charset));
                    } else {
                        System.out.println(line);
                    }
                    nextend--;
                }
                nextend--;
                rf.seek(nextend);
                if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                    // System.out.println(rf.readLine());
                    System.out.println(new String(rf.readLine().getBytes(
                            "ISO-8859-1"), charset));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rf != null)
                    rf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getLines(String name) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(name, "r");
        long start = randomAccessFile.getFilePointer();
        long end = randomAccessFile.length();
        System.out.println(start);
        System.out.println(end);
        String line;
        long filePointer = 0;
        while ((line = randomAccessFile.readLine()) != null) {
            if (line.startsWith("32")) {
                filePointer = randomAccessFile.getFilePointer();
                System.out.println(filePointer);
            }
            System.out.println(new String(line.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        RandomAccessFile t = new RandomAccessFile(name, "r");
        t.seek(filePointer);
        String s = t.readLine();
        System.out.println(new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }


}
