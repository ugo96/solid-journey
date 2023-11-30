name := "Read Write Parquet S3"

version := "1.0"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "org.apache.spark"             %% "spark-core"           % "3.2.0",
  "org.apache.spark"             %% "spark-sql"            % "3.2.0",
  "com.fasterxml.jackson.core"    % "jackson-databind"     % "2.12.2",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.2",
  "org.apache.hadoop"             % "hadoop-client"        % "3.2.1",
  "org.apache.hadoop"             % "hadoop-aws"           % "3.2.1",
  "com.amazonaws"                 % "aws-java-sdk-s3"      % "1.12.262",
  "com.amazonaws"                 % "aws-java-sdk-core"    % "1.12.262",
  "com.amazonaws" % "aws-java-sdk-dynamodb" % "1.12.262"
)