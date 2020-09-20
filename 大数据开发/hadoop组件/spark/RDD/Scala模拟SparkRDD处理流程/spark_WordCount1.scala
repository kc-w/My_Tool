import org.apache.spark._
object spark_WordCount1 {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
    .setAppName("wordcount")
    //.setMaster("yarn")
    val sc=new SparkContext(conf)
    val textfile=sc.textFile("hdfs://hadoop1:8020/user/hive/warehouse/test.db/table2/000000_0")
    val wordsRDD=textfile.flatMap(_.split(" "))
    val wordCountRDD=wordsRDD.map((_,1))
    val reduceByKeyRDD=wordCountRDD.reduceByKey(_+_)
    val resultRDD=reduceByKeyRDD.collect
    resultRDD.foreach(println)
  }
}
