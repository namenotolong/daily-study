package com.huyong.bigdata

class CollectionTest {
  def apply(): CollectionTest = new CollectionTest()
  def testList(): Unit = {
    val list = List(1,2,3,4,5)
    val builder = new StringBuilder()
    val builder1 = list.addString(builder)
    println(builder)
    println(builder1)
    println(list)
    val list1 = 1 :: (2 :: (3 :: (4 :: Nil)))
    println(list1)
    println(list.++(list1))
    println(List.concat(list, list1))
    println(list)
    println(list.head)
    println(list.apply(1))
  }

  def testSet(): Unit = {
    val test = Set(1,2,3,4,5,6,6)
    val test1 = Set(7,8,9,10,1,12,13)
    println(test)
    println(test1)
    println(test ++ test1)
  }
}
object CollectionTest{


  def main(args: Array[String]): Unit = {
    val instance = new CollectionTest();
    instance.testSet()
  }
}
