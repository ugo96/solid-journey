# Get docker image
docker pull spark

# Run basic REPL
docker run -it spark:latest /opt/spark/bin/spark-shell 

# Run REPL with file (Saves time writing main method, imports and initialization)
docker run -it spark:latest /opt/spark/bin/spark-shell -i script.scala

# Setup application folder
mkdir newdir &&\
cd newdir &&\
touch build.sbt &&\
mkdir -p src/main/scala &&\
touch src/main/scala/newdir.scala

# First application - Run from folder with build.sbt
# Use Volume mount for Storing JAR and data in /tmp/ for getting it back
sbt package && 
docker run \
    -v .:/tmp \
    -it spark:latest \
    /opt/spark/bin/spark-submit  --class "SimpleApp" /tmp/target/scala-2.12/simple-project_2.12-1.0.jar

# Run from folder with build.sbt
sbt package && 
docker run \
    -v .:/tmp \
    -it spark:latest \
    /opt/spark/bin/spark-submit  --class "ScalaSizer" /tmp/target/scala-2.12/scala-sizer_2.12-1.0.jar

# Run from folder with build.sbt
sbt package && 
docker run \
    -v .:/tmp \
    -it spark:latest \
    /opt/spark/bin/spark-submit  --class "ReadWriteParquet" /tmp/target/scala-2.12/read-write-parquet_2.12-1.0.jar

# INCLUDED JARS ARE REALLY IMPORTANT FOR SPARK SUBMIT
# Use only URLs, actual JARs are not recommended
# All these dependencies are included by default on EMR image.
sbt package &&
docker run \
    -it spark:latest \
    /opt/spark/bin/spark-submit  \
    --jars https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-s3/1.12.262/aws-java-sdk-s3-1.12.262.jar,https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-core/1.12.262/aws-java-sdk-core-1.12.262.jar,https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-aws/3.3.4/hadoop-aws-3.3.4.jar,https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-dynamodb/1.12.262/aws-java-sdk-dynamodb-1.12.262.jar \
    --class "ReadWriteParquetS3" /tmp/target/scala-2.12/read-write-parquet-s3_2.12-1.0.jar

