package com.huyong.bigdata.spark

import java.sql.DriverManager
import java.sql.SQLException

object HiveJdbc {

  @throws[SQLException]
  def main(args: Array[String]): Unit = {
    val url = "jdbc:hive2://master:10000/default"
    try Class.forName("org.apache.hive.jdbc.HiveDriver")
    catch {
      case e: ClassNotFoundException =>
        e.printStackTrace()
    }
    val conn = DriverManager.getConnection(url)
    val stmt = conn.createStatement
    val sql = "select count(userid) + sum(userid) from u_data"
    val res = stmt.executeQuery(sql)
    while ( {
      res.next
    }) {
      val data = res.getMetaData
      val count = data.getColumnCount
      for (i <- 1 to count) {
         print(s" ${data.getColumnName(i)} : ${res.getObject(i)}")
      }
      println()
    }
  }

}
