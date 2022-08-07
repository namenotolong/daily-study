package com.huyong.bigdata.spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import java.util.Scanner

object HiveTest {


  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:/download_data/hadoop-common-2.2.0-bin-master/hadoop-common-2.2.0-bin-master")

      //.setMaster("local[*]")
    val session = SparkSession.builder()
      .master("yarn")
      .config("spark.sql.warehouse.dir", "hdfs://192.168.36.151:9000/user/hive/warehouse")
      .config("spark.hadoop.fs.defaultFS","hdfs://192.168.36.151:9000")
      .config("spark.hadoop.yarn.resourcemanager.address","hdfs://192.168.36.151:8032")
      .enableHiveSupport()
      .getOrCreate()
    //session.sql("show databases").show()
    //session.sql("select * from default.u_data").show()
    session.sql("select count(userid) + sum(userid) from default.u_data").show()
    //session.sql("select max(movieid) from default.u_data").show()
    //session.sql("create database test2")
    val scanner = new Scanner(System.in);
    while (scanner.hasNext) {
      val sql = scanner.nextLine()
      val startTime = System.currentTimeMillis()
      session.sql(sql).show()
      println(s"during  cost  ${System.currentTimeMillis() - startTime}")
      Thread.sleep(1000)
    }
  }

}
