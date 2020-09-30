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

  def test1[T] (param : String)(f: =>T): T = {
    println(param)
    println(f)
    f
  }
  def test2[T] (param : String, f: =>T): T = {
    println(param)
    println(f)
    f
  }
  def testName() : Int = {
    println("i am invoked")
    100
  }

  lazy val ana = testMap()

  type aaa = String

  def func(a : Int)(b : Int) : Int = a

  def main(args: Array[String]): Unit = {
    CommonTest.test1("hello world") {
      println("i am invoked")
      100
    }
    CommonTest.test2("hello world", {
      println("i am invoked")
      100
    })
    val data = List(1,2,3,4,5,6)
    val data1 = List(1,2,3,4,5,6)
    println(CommonTest.testList(data,data1))
    val list1 = List(List(1,2,3),List(3,4,5),List(2),List(0))
    println(list1.aggregate(2)(_+_.max,_-_))
  }

  def testList(list1 : List[Int], list2 : List[Int]): List[Int] = {
    list1 ::: list2
  }
}