package com.huyong.scala.implicittest

object Test {
  def test(b : String)(implicit a : Int): Unit = {
    print(a + b)
  }

  implicit def test1(implicit a : String, b : Int): String = {
    a + b
  }

  def testZipCollection(): Unit = {
    val arr = Array("one", "two", "three", "four", "five")
    arr.zipWithIndex.foreach{ case (str, i) => println(s"$str,$i")}
    arr.zip(Stream.from(1, 2)).foreach{ case (str, i) => println(s"$str,$i")}
  }

  def main(args: Array[String]): Unit = {
    testZipCollection
  }
}
class Parent1
trait Parent2
class Son extends Parent1 with Parent2
