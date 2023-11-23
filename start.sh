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
# Use Volume mount for Storing JAR and data in /tmp/
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

