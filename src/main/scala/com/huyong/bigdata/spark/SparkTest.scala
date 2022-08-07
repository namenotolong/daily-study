package com.huyong.bigdata.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

class SparkTest {

}
object SparkTest {
  //Logger.getLogger("org").setLevel(Level.ERROR);

  def getSparkSession : SparkSession = {
    SparkSession
      .builder()
      .master("local[*]")
      .appName("test")
      .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
      .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
      .getOrCreate()
  }

  def wordCount = {
    val sparkSession = getSparkSession;
    sparkSession.sparkContext.textFile("word.txt")
      .flatMap(_.split(" "))
      .flatMap(_.split("\n"))
      .map(word => (word, 1))
      .reduceByKey((e1, e2) => e1 + e2)
      .foreach(println)
  }

  def commonTest(): Unit = {
    val count = getSparkSession.read.textFile("word.txt").count()
    println(count)
  }

  def testInit(f: => Int): Int = {
    f
  }

  def test(): Int = {
    2
  }

  def main(args: Array[String]): Unit = {
    //commonTest()
    wordCount
    //println(testInit(test()))
  }
}
