package com.huyong.scala


object Test {
  def apply(unit: Unit) = ???


  def main(args: Array[String]): Unit = {
    println(1)
    Seq(1,2,3).foreach(e=>println(e))
    Seq(1,2,3).foreach(println)
    //Seq[Int](1,2,3).foreach({case 1 => println(1)})

    def a =  com.huyong.scala.Action
  }

}
