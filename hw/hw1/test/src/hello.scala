import org.apache.spark.{SparkConf, SparkContext}

object hello {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]")//双线程
    val sc = new SparkContext(conf)
    val textFile = sc.textFile("test.txt")
    val words = textFile.flatMap(line => line.split(" ")).map(word => (word, 1))
    val wordCount = words.reduceByKey((a, b) => a + b)
    wordCount.foreach(println)
  }

}
