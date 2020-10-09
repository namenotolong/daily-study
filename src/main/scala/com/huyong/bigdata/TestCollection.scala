package com.huyong.bigdata

import org.apache.spark.{SparkConf, SparkContext}

class TestCollection {

}
object TestCollection{
  def main(args: Array[String]): Unit = {
    val data = Array("hello", "hello", "world", "haha")
    val conf = new SparkConf();
    conf.setAppName("test").setMaster("local");
    val sc = new SparkContext(conf);
    val rdd = sc.parallelize(data);
    println(rdd.collect())
  }
}
