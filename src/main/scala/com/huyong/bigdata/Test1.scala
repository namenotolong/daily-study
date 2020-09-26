package com.huyong.bigdata

import java.util

class Test1 {
  val a = 3;
  def addNum(a : Int, b : Int): Int = {
    a + b
  }
  def topFunc(f : Int => String, a : Int): String = {
    f(a)
  }
  def stringFunc(a : Int): String = {
    a + "hello world"
  }
}
object Test1{

  def main(args: Array[String]): Unit = {

    val list = new util.ArrayList[String]()
    print(list)
    list.add("hello world")
    print(list)
    for (a <- 1 until  10 if a < 9; if a > 6) {
      println(a)
    }
    val temp = for{ a <- 1 to 10 if a < 10} yield a
    println(temp)
    temp.foreach(print)
    println()
    val test = new Test1()
    print(test.addNum(1,2))
    val v = (a : Int) => a + 1;
    println(v(1))
    println(test.topFunc(test.stringFunc, 1))
    println("hello world".hashCode)
  }
}
