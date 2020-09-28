package com.huyong.bigdata

import java.io.{FileNotFoundException, FileReader}


class TestMatch {
  def testException(): Unit = {
    val f = try {
      new FileReader("/sdf/1/1/1/1/1//1/1")
    } catch {
      case ex : FileNotFoundException => printf("%s\n", ex.getCause)
    }
  }
  def test(a : Int): Unit = {
    a match {
      case 1 => println("one")
      case 2 | 3 => println("two")
      case 3 => println("three")
      case x if x == 5 || x < 10  => println("haha")
      case _ =>
    }
  }
  def testCaseClass(instance : BaseTestCase): Unit = {
    instance match {
      case TestCase1(num, _) => println(num)
      case TestCase2(_, name) => println(name)
      case _ => println("other")
    }
  }
}
object TestMatch{
  def main(args: Array[String]): Unit = {
    val test1 = TestCase1(4, "hello")
    val test2 = TestCase2(9, "world")
    test1.run();
    val instance = new TestMatch
    instance.testCaseClass(test1)
    instance.testCaseClass(test2)
    instance.testException()
    println(s"hello world $test1")
  }
}
case class TestCase1(num : Int, name : String) extends BaseTestCase {
  def run(): Unit = {
    println("run")
  }
}

case class TestCase2(num : Int, name : String) extends BaseTestCase
trait BaseTestCase
