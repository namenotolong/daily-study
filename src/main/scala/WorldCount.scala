import org.apache.spark.{SparkConf, SparkContext}

class WorldCount {

}
object WorldCount{
  def main(args: Array[String]): Unit = {
    val fileName = "F:\\data\\data\\test.txt";
    val conf = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    val context = new SparkContext(conf)
    val rdd = context.textFile(fileName)
    var res = "";
    rdd.flatMap(e => e.split(" ")).map((_,1)).reduceByKey(_+_).collect
  }
}
