package com.huyong.bigdata.implicitown

class Test {

}
class SwingType{
  def  wantLearned(sw : String) = println("兔子已经学会了"+sw)
}
object swimming{
  implicit def learningType(s : AminalType) = new SwingType
}
class AminalType
object AminalType extends  App{
  import com.huyong.bigdata.implicitown.swimming._
  val rabbit = new AminalType
  rabbit.wantLearned("breaststroke")         //蛙泳
}
