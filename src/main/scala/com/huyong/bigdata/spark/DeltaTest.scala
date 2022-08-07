package com.huyong.bigdata.spark

import org.apache.spark.sql.SparkSession

object DeltaTest {

  def getSparkSession : SparkSession = {
    SparkSession
      .builder()
      .master("local[*]")
      .appName("test")
      .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
      .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
      .getOrCreate()
  }

  def main(args: Array[String]): Unit = {

  }

}
