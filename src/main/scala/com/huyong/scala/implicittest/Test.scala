package com.huyong.scala.implicittest

import scala.reflect.ClassTag

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
  def testCase(): Unit = {
    val list = List(1, 2, "hello", "ww")
    list.foreach{
      case e : Int => print(e)
      case _ => None
    }
  }

  /**
   * classTag在runtime保存泛型
   */
  def testClassTag(): Unit = {
    object obj {
      def extract[T](list: List[Any])(implicit tag : ClassTag[T]): Seq[Any] =
        list.flatMap {
          case element: T => Some(element)
          case _ => None
        }
    }
    val list: List[Any] = List(1, "string1", List(), "string2")
    val result = obj.extract[String](list)
    println(result) // List(string1, string2)
  }


  def main(args: Array[String]): Unit = {

  }
}
class Parent1
trait Parent2
class Son extends Parent1 with Parent2
