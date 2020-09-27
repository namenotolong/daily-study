package com.huyong.bigdata

class TestObject(val age : Int, name : String) {
}
object TestObject{
  def main(args: Array[String]): Unit = {
    val testObject = new TestObject(1,"2")
    println(testObject.age)
  }
}
class Parent(val a : Int) {
  def run(): Unit = {
    println("parent run")
  }
}
class Son(override val a : Int) extends Parent(a : Int) {
  override def run(): Unit = {
    println("son run")
    super.run()
  }
}
object Son {
  def test(): Unit = {

  }

  def apply(a: Int): Son = new Son(a)
  def main(args: Array[String]): Unit = {
    val instance = new Son(1)
    instance.run()
    println(Son)
    Son test
  }
}
