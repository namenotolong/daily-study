package com.huyong.scala.implicittest

/**
 * note
 * 1.scala的类参数不加val/var那么该参数只能当做该类的一个不可变参数使用，加上才会被当做成员变量
 */
class FunctionTest {

}

/**
 * scala implicit 隐式转换
 * 隐式转换函数、隐式类、隐式参数、隐式对象、context bound
 * 1.隐式转换函数：在不符合类型的时，编译器会在作用域中查找隐式修饰可以转换的成需要类型函数
 * e是一个S类型的表达式，而需要的却是T类型，编译器会寻找S=>T的隐式转换
 * e是一个S类型的表达式，使用点号访问e.m时，m不是类型S的成员，编译器会寻找合适的隐式转换使e.m合法
 * 只支持一个参数，不能存在二义性，不能嵌套隐式转换
 * 2.隐式类：个人理解相当于中途提供了一个隐式转换函数，该函数返回该类对象，可以扩展输入参数对象的方法
 * 只能在别的类/trait/对象内部定义
 * 构造函数只能携带一个非隐式传参对象（虽然我们可以创建带有多个非隐式参数的隐式类，但这些类无法用于隐式转换）
 * implicit关键字不能用于case类
 * 3.隐式参数：在函数定义的时候，支持在最后一组参数使用 implicit，表明这是一组隐式参数。
 * 在调用该函数的时候，可以不用传递隐式参数，而编译器会自动寻找一个implicit标记过的合适的值作为该参数。
 * 4.隐式值：implicit val x: Int = 0
 * 5.隐式对象：implicit object obj {}
 * 6.context bound
 */
object FunctionTest {

  /*隐式函数*/
  implicit def concertInt2String(a : Int) : String = {
    a + " test"
  }

  implicit def convertUser2Work(user: User): Work = {
    Work(user.name)
  }
  def testStringInput(s : String): Unit = {
    println(s)
  }
  def testConvertFunction(): Unit = {
    testStringInput(1)
    val age = new User("age", 1)
    println(age.desc)
  }
  /*隐式类*/
  implicit class TestImplicitClass(par:String) {
    val value:String = par
    def sayHi() : Unit = println(s"Hi $value!")
  }
  //implicit def int2SayHi(x:Int) = new TestImplicitClass(x)
  def testImplicitClass(): Unit = {
    "123".sayHi()
  }
  /*隐式参数*/
  def testImplicitParameter(one : Int, two : Int)(implicit three : String): Unit = {
    println(one + two + three)
  }

  def testImplicitParameterTest(): Unit = {
    implicit val a : String = "hello world"
    testImplicitParameter(1,2)
  }

  /*context bound 下面三个等价*/
  def compare2[T: Ordering](x: T, y: T): Int = {
    //使用implicitly拿到具体的隐式值
    val ord = implicitly[Ordering[T]]
    ord.compare(x, y)
  }

  def compare4[T: Ordering](x: T, y: T): Int = {
    def helper(implicit ord:Ordering[T]): Int = ord.compare(x, y)
    helper
  }

  def compare3[T](x: T, y: T)(implicit ord:Ordering[T]): Int = {
    ord.compare(x, y)
  }

  def main(args: Array[String]): Unit = {

    testImplicitParameterTest()
  }
}
class User(val name : String,val age : String)
class Work(var desc : String)
object Work {
  def apply(desc: String): Work = new Work(desc)
}

