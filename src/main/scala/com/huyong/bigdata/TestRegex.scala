package com.huyong.bigdata

class TestRegex {
  def test(): Unit = {
    val pattern = "Scala".r
    val str = "Scala is Scalable and cool"
    println(pattern findFirstIn str)
    val iterator = pattern.findAllIn(str)
    iterator.foreach(println)
    println(pattern.findFirstIn(str).get)
  }
}
object TestRegex {
  def main(args: Array[String]): Unit = {
    val instance = new TestRegex
    instance.test()
  }
}
