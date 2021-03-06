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
    println(test.mkString(","))
  }

  def testMap(): Unit = {
    val test = Map("hello" -> "world", "hu" -> "yong", "1 + 1" -> "2")
    val test1 = Map("2 + 2" -> "4", "3 + 3" -> "6", "1 + 1" -> "3")
    println(test)
    println(test.keys)
    println(test.values)
    println(test ++ test1)
    println(test.++(test1))
    println(test.-("hello"))
    println(test.drop(1))
    println(test.toList)
  }

  def testTuple(): Unit = {
    val test = (1,2,3,4,"hello")
    val test1 = new Tuple5(1,2,3,4,"hello")
    println(test)
    println(test1)
    println(test._1)
    test.productIterator.foreach(print)
  }

  def testOption(): Unit = {
    val test = Map(1 -> 2)
    val maybeInt : Option[Int] = test.get(1)
    println(maybeInt)
    println(test.getOrElse(2, 3))
  }

  def testIterator(): Unit = {
    val test = Iterator(1,2,3,4)
    test.foreach(print)
  }
}
object CollectionTest{


  def main(args: Array[String]): Unit = {
    val instance = new CollectionTest();
    instance.testIterator()
  }
}
