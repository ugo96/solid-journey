val data = Seq(("James ","","Smith","36636","M",3000),("Michael ","Rose","","40288","M",4000),("Robert ","","Williams","42114","M",4000),("Maria ","Anne","Jones","39192","F",4000),("Jen","Mary","Brown","","F",-1))
val columns = Seq("firstname","middlename","lastname","dob","gender","salary")

// What does implicits do?
import spark.sqlContext.implicits._

val df = data.toDF(columns:_*)

// Write DF
df.write.parquet("/tmp/output/people.parquet")

// Read DF
val parqDF = spark.read.parquet("/tmp/output/people.parquet")

// Append df
df.write.mode("append").parquet("/tmp/output/people.parquet")
