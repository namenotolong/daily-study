package com.huyong.scala

import com.huyong.TestUtils

/**
 * scala中下划线的作用
 * 1.给一个函数传递可变参数时，用_*指定类型传递集合
 * 2.[_]相当于java的泛型<?>，类型通配符
 * 3.模式匹配中表示default，遇到就匹配
 * 4.在集合中配合模式匹配使用，赋值/判断集合特征
 * 5.访问tuple的元素，._1
 * 6.简写函数字面量，如果函数的参数只出现过一次，可以用下划线代替
 * 7.定义一元操作符，在scala中所有运算符都是通过函数实现的
 * 8.定义部分应用函数，test(a :Int, b :String) => replace(5, _String) replace("hello") => test(5, "hello")
 * 9.将方法转化为函数，在scala中方法不能作为参数进行传递，也无法赋值给变量，而函数可以
 * 10.定义成员变量默认值
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
  def visitTuple(): Unit = {
    val test = (1,2,3,4)
    println(test._1)
  }
  def simpleParameter(): Unit = {
    val test = Map(1 ->"one", 2 ->"two")
    val testFunc = (_ : Int) + (_ : Int)
    println(testFunc(1, 2))
  }
  def test(): Unit = {
    val test = print _
    test(1)
  }
  def main(args: Array[String]): Unit = {
    test()
  }
}
