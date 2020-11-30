package com.huyong.scala

class TestScala {

}
object TestScala{
  def test[T](num : Int, func:(Int, Int) => T) : T = {
    println(num)
    func(1,2)
  }
  def inner(a : Int, b : Int): Unit = {
    println(1)
  }
  def main(args: Array[String]): Unit = {
    val func = inner _
    test(1, func)
  }
}
