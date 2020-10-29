package com.huyong.bigdata.test

class ReflectTest(name : String, age : Int) {
  def say(): Unit = {
    println(s"我的名字是$name, 我的年龄是$age")
  }
}
object ReflectTest{
  def main(args: Array[String]): Unit = {
    val ju = scala.reflect.runtime.universe
    //得到一个JavaMirror,用于反射Person.class(获取对应的Mirrors,这里是运行时的)
    val mirror = ju.runtimeMirror(getClass.getClassLoader)
    //得到Person类的Type对象后，得到type的特征值并转为ClassSymbol对象
    val classPerson = ju.typeOf[ReflectTest].typeSymbol.asClass
    //用Mirrors去reflect对应的类,返回一个Mirrors的实例,而该Mirrors装载着对应类的信息
    val classMirror = mirror.reflectClass(classPerson)
    //得到构造器方法
    val constructor = ju.typeOf[ReflectTest].decl(ju.termNames.CONSTRUCTOR).asMethod
    //得到MethodMirror
    val methodMirror = classMirror.reflectConstructor(constructor)
    /**
     * Scala通过反射实例化对象
     */
    val p = methodMirror("test",22)
    println(p.toString)

    /**
     * Scala通过反射调用方法
     */
    val instanceMirror = mirror.reflect(p)
    //得到Method的mirror
    val mysay = ju.typeOf[ReflectTest].decl(ju.TermName("say")).asMethod
    //通过Method的Mirror索取方法
    val mysayHello = instanceMirror.reflectMethod(mysay)
    //调用我们自定义的方法
    mysayHello()
    /**
     * Scala通过反射访问属性
     */
    val nameField = ju.typeOf[ReflectTest].decl(ju.TermName("name")).asTerm
    val name = instanceMirror.reflectField(nameField)
    println("通过反射得到name的值为:"+name.get)
  }
}
