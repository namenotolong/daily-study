package com.huyong.bigdata

trait TestTrait {

  def add(a : Int, b: Int): Int
  def reduce(a : Int, b : Int) : Int = {
    a - b
  }
}
trait TestTrait1 {

  def add(a : Int, b: Int): Int
  def reduce(a : Int, b : Int) : Int = {
    a * b
  }
}
class TestCommon{

}
class TraitSon extends TestCommon with TestTrait1 {
  override def add(a: Int, b: Int): Int = {
    a + b
  }
}
object TraitSon {
  def main(args: Array[String]): Unit = {
    val instance = new TraitSon();
    println(instance.reduce(2, 6))
    println(instance.add(2, 6))
  }
}
