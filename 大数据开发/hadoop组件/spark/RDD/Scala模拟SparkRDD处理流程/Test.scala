import org.apache.spark.{SparkConf, SparkContext}

object Test {
  def main(args: Array[String]): Unit = {
    test4()
  }
  def test1(): Unit ={
    val conf=new SparkConf()
      .setAppName("wordcount")
      .setMaster("local")
    val sc=new SparkContext(conf)
    //val counter=sc.accumulator(0,"counter")
    //在spask程序中定义的普通变量,在进行运算时会在spark程序中创建一个副本
    var counter = 0
    val arr=Array(1,2,3,4,5,6)
    var rdd = sc.parallelize(arr)
    rdd.foreach(x => counter += x)
    println("Counter value: " + counter)
  }
  def test2(): Unit ={
    val conf=new SparkConf()
      .setAppName("wordcount")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val arr1=Array(("100","无线电"),("101","哈哈哈"),("102","sss"))
    val arr2=Array(("101",999),("100",666),("102",888))
    var rdd1 = sc.parallelize(arr1)
    var rdd2 = sc.parallelize(arr2)
    rdd1.join(rdd2).foreach(println)
    rdd1.cogroup(rdd2).foreach(println)
  }

  def test3(): Unit ={
    val conf=new SparkConf()
      .setAppName("wordcount")
      .setMaster("local")
    val sc=new SparkContext(conf)
    var counter = 0
    val arr1 = Array(("class1", 85), ("class1", 100), ("class2", 100), ("class2", 85))
    var rdd1 = sc.parallelize(arr1)
    println(rdd1.map(_._1).distinct().count())

  }

  def test4(): Unit ={
    val conf=new SparkConf()
      .setAppName("wordcount")
      .setMaster("local")
    val sc=new SparkContext(conf)
    var counter = 0
    val arr1 = Array(
      (100,"Geneva",22.25),
      (100,"Zurich",42.10),
      (200,"Fribourg",12.40),
      (200,"St.Gallen",8.20),
      (300,"Lucerne",31.60),
      (300,"Basel",16.20)
    )
    var rdd1 = sc.parallelize(arr1)
    rdd1.foreach(x =>{

    })

  }
}
