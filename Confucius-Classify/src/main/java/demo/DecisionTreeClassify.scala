package demo

import demo.RandomForestClassify.getMetrics
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.tree.model.{DecisionTreeModel, RandomForestModel}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author liuzhaolu
  * @version create_time：2018/12/29 类说明:
  */
object DecisionTreeClassify {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val startTime = System.currentTimeMillis()

    val conf = new SparkConf().setAppName("RandomForest").setMaster("local[6]")
    val sc = new SparkContext(conf)

    val rawData = sc.textFile("data/covtype_data.csv")

    val data = rawData.map { line =>

      val values = line.split(',').map(_.toDouble)

      val featureVector = Vectors.dense(values.init)

      val label = values.last - 1

      LabeledPoint(label, featureVector)

    }

    val splits = data.randomSplit(Array(0.7, 0.15, 0.15))
    val (trainingData, cvData, testData) = (splits(0), splits(1),splits(2))

    val numClasses = 8
    val categoricalFeaturesInfo = Map[Int, Int]()
    val impurity = "entropy"
    val maxDepth = 5
    val maxBins = 32

    val model = DecisionTree.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo,
      impurity, maxDepth, maxBins)


    val metrics = getMetrics(model,testData)

    val precision = metrics.accuracy

    val labelAndPreds = testData.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }

    val testErr = labelAndPreds.filter(r => r._1 == r._2).count.toDouble / testData.count()

    val endTime = System.currentTimeMillis()


    println("========================================================================================")
    //精确度(样本比例)
    println("程序耗时: " + (endTime-startTime)/1000f)

    println("========================================================================================")
    //精确度(样本比例)
    println("精确度: " + precision)
    println("========================================================================================")
    println(s"Test Error = $testErr")
    println("========================================================================================")
    //    println("准确度: " + recall.foreach(println))
    println(metrics.confusionMatrix)
    println("========================================================================================")


    println(model.toDebugString)
    println("========================================================================================")


  }

  def getMetrics(model: DecisionTreeModel, data: RDD[LabeledPoint]): MulticlassMetrics = {
    val predictionsAndLabels = data.map(example => (model.predict(example.features), example.label))
    new MulticlassMetrics(predictionsAndLabels)
  }
}
