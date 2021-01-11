package com.huyong.scala.collection

class Test {

}
object Test {
  def main(args: Array[String]): Unit = {
    val tuples = (1,2)
    println(tuples._1)
    println(tuples.getClass)
    val collect = List(1,2,3,4)
    println(collect.mkString(","))
    println(collect.drop(2))

  }
}