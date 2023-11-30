import org.apache.spark.sql.SparkSession

// val df = spark.read.format("parquet").load("/Users/ugo96/stocks-collector/data/")

object ReadWriteParquetS3 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .getOrCreate()
    import spark.sqlContext.implicits._

    // spark.sparkContext.hadoopConfiguration.set("spark.hadoop.fs.s3.impl","") 

    spark.sparkContext.hadoopConfiguration.set("fs.s3a.access.key", "")
    spark.sparkContext.hadoopConfiguration.set("fs.s3a.secret.key", "")
    
    println()
    println()
    println("Hadoop version: " + org.apache.hadoop.util.VersionInfo.getVersion())
    println()
    println()
    
    val data = Seq(
      ("James ", "", "Smith", "36636", "M", 3000),
      ("Michael ", "Rose", "", "40288", "M", 4000),
      ("Robert ", "", "Williams", "42114", "M", 4000),
      ("Maria ", "Anne", "Jones", "39192", "F", 4000),
      ("Jen", "Mary", "Brown", "", "F", -1),
      ("James ", "", "Smith", "36636", "M", 3000),
      ("Michael ", "Rose", "", "40288", "M", 4000),
      ("Robert ", "", "Williams", "42114", "M", 4000),
      ("Maria ", "Anne", "Jones", "39192", "F", 4000),
      ("Jen", "Mary", "Brown", "", "F", -1),
      ("James ", "", "Smith", "36636", "M", 3000),
      ("Michael ", "Rose", "", "40288", "M", 4000),
      ("Robert ", "", "Williams", "42114", "M", 4000),
      ("Maria ", "Anne", "Jones", "39192", "F", 4000),
      ("Jen", "Mary", "Brown", "", "F", -1),
      ("James ", "", "Smith", "36636", "M", 3000),
      ("Michael ", "Rose", "", "40288", "M", 4000),
      ("Robert ", "", "Williams", "42114", "M", 4000),
      ("Maria ", "Anne", "Jones", "39192", "F", 4000),
      ("Jen", "Mary", "Brown", "", "F", -1),
      ("James ", "", "Smith", "36636", "M", 3000),
      ("Michael ", "Rose", "", "40288", "M", 4000),
      ("Robert ", "", "Williams", "42114", "M", 4000),
      ("Maria ", "Anne", "Jones", "39192", "F", 4000),
      ("Jen", "Mary", "Brown", "", "F", -1),
      ("James ", "", "Smith", "36636", "M", 3000),
      ("Michael ", "Rose", "", "40288", "M", 4000),
      ("Robert ", "", "Williams", "42114", "M", 4000),
      ("Maria ", "Anne", "Jones", "39192", "F", 4000),
      ("Jen", "Mary", "Brown", "", "F", -1)
    )

    val columns = Seq("firstname", "middlename", "lastname", "dob", "gender", "salary")


    val df = data.toDF(columns: _*)
    println("Writing initial table")
    df.write.mode("overwrite").parquet("s3a://staging-beta-us-east-1/file.pqt")
    println("Appending initial table")
    df.write.mode("append").parquet("s3a://staging-beta-us-east-1/file.pqt")
  }
}
