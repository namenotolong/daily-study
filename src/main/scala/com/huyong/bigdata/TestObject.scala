package com.huyong.bigdata

class TestObject(age : Int, name : String) {
  var a: Int = age;
}
object TestObject{
  def main(args: Array[String]): Unit = {
    val testObject = new TestObject(1,"2")
    println(testObject.a)
  }
}
class Parent {
  def run(): Unit = {
    println("parent run")
  }
}
class Son extends Parent {
  override def run(): Unit = {
    println("son run")
  }
}
object Son {
  def main(args: Array[String]): Unit = {
    val instance = new Son()
    instance.run()
  }
}
