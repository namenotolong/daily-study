import org.apache.spark.{SparkConf, SparkContext}

class WorldCount {

}
object WorldCount{
  def main(args: Array[String]): Unit = {
    val fileName = "/Users/guandata/Desktop/record.txt";
    val conf = new SparkConf().setMaster("local").setAppName("wordCount")
    val context = new SparkContext(conf)
    val rdd = context.textFile(fileName)
    val value = rdd.flatMap(e => e.split(" ")).map((_, 1)).foreach(println)
  }

  private lazy val a : Int = {
    1 + 2
  }

}
