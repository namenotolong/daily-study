package com.huyong.bigdata.test

object EnumTest extends Enumeration {
   val ONE, TWO = Value
}
object TestForEnumTest{
  def main(args: Array[String]): Unit = {
    println(signal(2))
    println(signal.isDefinedAt(1))
    println(signal.isDefinedAt(2))
    println(signal.andThen{x : Int => x * 2 : Int}(2))
    val list = List(1,2,3,4)
    val result = list collect {
      case e: Int => e + 1;
    }
    println(result)
    println(EnumTest.ONE)
  }
  //偏函数
  val signal : PartialFunction[Int, Int] = {
    case x if x < 1 => -1;
    case x if x > 1 => 1;
  }

}
case class TestCase(age : Int, name : Int)
