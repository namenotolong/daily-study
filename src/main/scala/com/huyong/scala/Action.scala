package com.huyong.scala

object Action {
  def apply(t: Long=>Long): Long=>Long = {
    t
  }

}

class Action {

  def hello(): Unit = {
    println("hello world")
  }

}
