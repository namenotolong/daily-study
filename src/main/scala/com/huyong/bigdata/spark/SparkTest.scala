package com.huyong.bigdata.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

class SparkTest {

}
object SparkTest {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR);
    val sparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("test")
      .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
      .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
      .getOrCreate()
    sparkSession.sql("select 1").show()
  }
}
