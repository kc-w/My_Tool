import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SQLContext}

object Main{

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("spark-sql")
      .setMaster("local")

    val sc = new SparkContext(conf)
    //Spark SQL
    //后续的所有Spark SQL的相关操作都是基于SqlContext
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    //读取csv文件
    val df1 = sqlContext.read
      .option("header", "true")//去除第一行的字段介绍
      .option("InferSchema", "true")
      .format("com.databricks.spark.csv")
      .load("C:\\Users\\Wkc23\\Desktop\\文件夹2\\table1.csv")
    val df2 = sqlContext.read
      .option("header", "true")//去除第一行的字段介绍
      .option("InferSchema", "true")
      .format("com.databricks.spark.csv")
      .load("C:\\Users\\Wkc23\\Desktop\\文件夹2\\table2.csv")
    val df3 = sqlContext.read
      .option("header", "true")//去除第一行的字段介绍
      .option("InferSchema", "true")
      .format("com.databricks.spark.csv")
      .load("C:\\Users\\Wkc23\\Desktop\\文件夹2\\table3.csv")
    val df4 = sqlContext.read
      .option("header", "true")//去除第一行的字段介绍
      .option("InferSchema", "true")
      .format("com.databricks.spark.csv")
      .load("C:\\Users\\Wkc23\\Desktop\\文件夹2\\table4.csv")

    t10(df1)
  }

  //查询学生表中的所有记录的学生姓名、学生性别和学生所在班级列。
  def t1(df: DataFrame): Unit ={
    df.show()
    df.select(df("name"),df("sex"),df("class")).show
  }

  //查询教师所有的单位即不重复的部门列。
  def t2(df: DataFrame): Unit ={
    df.select(df("bumen")).distinct().show()
  }

  //查询成绩表中成绩在60到80之间的所有记录。
  def t3(df: DataFrame): Unit ={
    df.where(df("score") > 60 and df("score") <80).show()
    df.filter(df("score") > 60 and df("score") <80).show()
  }

  //查询成绩表中成绩在60到80之间的所有记录。
  def t4(df: DataFrame): Unit ={
    df.where("score = 88 or score = 86 or score = 85").show()
    df.filter("score = 88 or score = 86 or score = 85").show()
    df.where("score in (85,86,88)").show()
  }

  //以学生所在班级降序查询学生表的所有记录。
  def t5(df: DataFrame): Unit ={
    df.selectExpr("student_id","name","sex","birth","class").sort(df("class").desc).show()
    df.select("*").sort(df("class").desc).show()
    df.sort(df("class").desc).show()
  }

  //查询成绩表中的最高分的学生学号和课程号。
  def t6(df: DataFrame): Unit ={
    val temp=df.agg(Map("score" -> "max")).take(1)(0)(0)
    println(temp)
    df.filter(df("score")===temp).show()
  }

  //查询每门课的平均成绩。
  def t7(df1: DataFrame,df2: DataFrame): Unit ={
    val df=df1.join(df2, df1("id") === df2("class_id"))
    df.select(df("name"),df("score")).groupBy(df("name")).agg("score" -> "avg").show()
  }

  //查询成绩表中至少有5名学生选修的并以3开头的课程的平均分数。
  def t8(df1: DataFrame,df2: DataFrame): Unit ={
    //先对班级id进行分组,获取课程id和出现的次数
    val table1 = df1.groupBy("class_id").count()
    //把出现次数大于或等于5并且以3开头的课程id和出现的次数过滤出来
    val table2 = table1.filter(table1("count") >= 5 and (table1("class_id") like "3%"))
    //得到符合条件的课程id
    val table3 = table2.select(table2("class_id"))
    //将符合条件的id表与课程表连接得到符合条件的课程表
    val table4=df2.join(table3, df2("id") === table3("class_id"))
    //将符合条件的课程表与成绩表连接得到符合条件的成绩表
    val table5=table4.join(df1,table4("id")===df1("class_id"))
    //对符合条件的成绩表进行分组查询出符合条件的课程和平均分
    table5.select(table5("name"),table5("score")).groupBy(table5("name")).agg(("score") -> "avg").show()
  }

  //查询选修某课程的同学人数多于4人的教师姓名。
  def t9(df1: DataFrame,df2: DataFrame,df3: DataFrame): Unit ={
    //先对成绩id进行分组,获取课程id和出现的次数
    val table1 = df2.groupBy("class_id").count()
    //把出现次数大于4的课程id和出现的次数过滤出来
    val table2 = table1.filter(table1("count") >= 4)
    //把符合要求的课程id和课程表连接,查询到教工编号
    val table3 = table2.join(df1,table2("class_id")===df1("id")).select("teacher_id")
    //把查询到的教工编号与教师表连接,查询到含有符合要求的教师名
    val table4 = table3.join(df3,table3("teacher_id")===df3("id")).select("name").show()
  }

  //查询和"李军"同性别并同班的同学姓名。
  def t10(df1: DataFrame): Unit ={
    val table1=df1.filter(df1("name")==="wang").select("sex","class")
    val table2=table1.join(df1,table1("sex")===df1("sex") and table1("class")===df1("class"))
    table2.select("name").filter("name != \"wang\"").show()
  }
}