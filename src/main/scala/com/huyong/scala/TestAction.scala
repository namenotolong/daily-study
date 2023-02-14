package com.huyong.scala

import scala.concurrent.Future


class TestAction {

  def echo(): Long=>Long = Action ((e: Long) => e + 1)


}

object TestAction  {
  def main(args: Array[String]): Unit = {
    val func = new TestAction().echo()
    println(func(2))
    //new TestAction().echo().hello()

    Future.successful({
      println(1)
    })
    println(23)
  }
}
