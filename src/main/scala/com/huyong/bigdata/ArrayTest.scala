package com.huyong.bigdata

class ArrayTest() {

  def apply(a: Int): ArrayTest = new ArrayTest()

  def testOfDim(): Unit = {
    val test = Array.ofDim[Int](3, 3)
    test.foreach(e => {
      e.foreach(f => print(f + " "))
      println()
    })
    val arr = Array.range(1, 10)
    arr.foreach(print)
    val function = Array.fill(3)(3)
    function.foreach(print)
  }

  def test(): Unit = {
    var arr = new Array[String](3)
    arr(1) = "hello world"
    arr(4 / 2) = "hello world222"
    arr.foreach(println)
    for(a <- arr) {
      println(a)
    }
  }
}
object ArrayTest {

  def main(args: Array[String]): Unit = {
    var arr = Array("1", "23", "234")
    print(arr(1))
    var instance = new ArrayTest;
    instance.testOfDim()
  }
}
