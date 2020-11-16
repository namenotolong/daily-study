package com.huyong.scala

import com.huyong.TestUtils

/**
 * scala中下划线的作用
 * 1.给一个函数传递可变参数时，用_*指定类型传递集合
 * 2.[_]相当于java的泛型<?>，类型通配符
 * 3.模式匹配中表示default，遇到就匹配
 * 4.在集合中配合模式匹配使用，赋值/判断集合特征
 */
class Test {

}
object Test{
  def testParameter(): Unit = {
    val list = List("1", "2")
    TestUtils.printArgs(list : _*)
  }
  def testCast(arr : List[_]): Unit = {

  }
  def testMatch(): Unit = {
    val list = List(0, 1, 2, 3)
    list match {
      case List(0, _, _) => println("0开头长度为3的集合")
      case List(0, _*) => println("0开头任意长度的集合")
      case _ => println("default")
    }
    val List(head, _*) = List("a")
    println(head)
  }
  def main(args: Array[String]): Unit = {
    testMatch()
  }
}
