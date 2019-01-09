package demo

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.tree.model.RandomForestModel

/**
  * @author liuzhaolu
  * @version create_time：2019/1/9 类说明:
  */
object PredictClassify {
  def main(args: Array[String]): Unit = {

    //屏蔽日志
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val startTime = System.currentTimeMillis()
    val conf = new SparkConf().setAppName("PredictClassify").setMaster("local[6]")
    val sc = new SparkContext(conf)

    val model = RandomForestModel.load(sc, "model/myRandomForestClassificationModel_covtype")

    val input = "2447,53,23,95,57,977,226,185,82,666,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0"

    val vector = Vectors.dense(input.split(',').map(_.toDouble))

    val data = input.split(",").map(_.toDouble);

    val v = Vectors.dense(data);

    println("样例被预测类别: " + model.predict(vector))
  }

}
