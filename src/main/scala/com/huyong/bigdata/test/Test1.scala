package com.huyong.bigdata.test

class Test1 {

}
object Test1{
  def main(args: Array[String]): Unit = {
    val str =  s"""
                  |add jar hdfs:///tmp/shine/udf/verification-udf.jar;
                  |CREATE TEMPORARY FUNCTION GenericUDFRowToString AS 'com.vdian.hive.udf.GenericUDFRowToString';
                  |CREATE TEMPORARY FUNCTION md5 AS 'com.vdian.hive.udf.Md5';
                  |select min(debug__), max(debug__)
                  |from (
                  |select
                  |md5(GenericUDFRowToString(*)) as tblmd5,
                  |GenericUDFRowToString(*) as debug__
                  |from
                  |union all
                  |select
                  |md5(GenericUDFRowToString(*)) as tblmd5,
                  |GenericUDFRowToString(*) as debug__
                  |from
                  |) tmp group by tblmd5 having count(*) == 1 limit 1;
    """.stripMargin;
    println(str)

  }
}
