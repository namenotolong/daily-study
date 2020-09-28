package com.huyong.bigdata

class CommonTest {

}
object CommonTest{
  def test(): Unit = {
    val data = List("hello world", "a sdf adf", "world wold", "hello")
    data.flatMap(e => e.split(" ")).groupBy(identity).foreach(e => println(e._1 + ":" + e._2.length))
  }

  def testMap(): Unit = {
    val data = Map(1 -> List(1,2,3,4), 2 -> List(1,2,3,4))
    println(data)
    println(data.toList)
    data.foreach(e => {
      println(e._1)
      println(e._2)
    })
    data.foreach(e => {
      println(e._1 + ":" + e._2.size)
    })
  }

  def func(a : Int)(b : Int) : Int = a

  def main(args: Array[String]): Unit = {
    CommonTest.test()
  }
}