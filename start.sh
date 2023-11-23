# Get docker image
docker pull spark

# Run basic REPL
docker run -it spark:latest /opt/spark/bin/spark-shell 

# Run REPL with file (Saves time writing main method, imports and initialization)
docker run -it spark:latest /opt/spark/bin/spark-shell -i script.scala

# First application - Run from folder with build.sbt
# Use Volume mount for Storing JAR and data in /opt/spark/work-dir/
sbt package && 
docker run \
    -v .:/tmp \
    -it spark:latest \
    /opt/spark/bin/spark-submit  --class "SimpleApp" /tmp/target/scala-2.12/simple-project_2.12-1.0.jar

