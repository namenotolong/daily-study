package com.huyong.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}

class Test {

}
object Test {
  def test(appName : String, master : String): Unit = {
    val conf = new SparkConf().setAppName(appName).setMaster(master)
    new SparkContext(conf)
  }
  def main(args: Array[String]): Unit = {
    Test.test("test", "master")
  }
}
