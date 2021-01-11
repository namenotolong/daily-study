package com.huyong.scala.utils

import org.apache.spark.sql.SparkSession

object SparkUtils {

  def createSparkSession(appName : String = "default", config : Map[String, String] = null): SparkSession = {
    val session = SparkSession.builder().appName(appName)
    if (config != null && config.nonEmpty) {

    }
    session.getOrCreate()
  }

}
