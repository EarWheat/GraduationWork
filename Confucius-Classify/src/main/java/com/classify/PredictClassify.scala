package com.classify

import java.util.Properties

import demo.StreamingWorldCount.{createStream, updateFunc}
import kafka.serializer.StringDecoder
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.mllib.tree.model.RandomForestModel
import org.apache.spark.streaming.{Duration, StreamingContext}
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaUtils

/**
  * @author liuzhaolu
  * @version create_time：2019/1/9 类说明:
  */
object PredictClassify {

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setMaster("local[6]").setAppName("PredictClassify")
    val sc = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sc, Duration(5000))
    ssc.checkpoint("file:///usr/local/spark/mycode/kafka/checkpoint") //这里表示把检查点文件写入分布式文件系统HDFS，所以要启动Hadoop
    val topics = Set("test") //我们需要消费的kafka数据的topic
    val kafkaParam = Map(
      "metadata.broker.list" -> "localhost:9092") // kafka的broker list地址

    val model = RandomForestModel.load(sc,"../GraduationWork/Confucius-Classify/src/main/java/model/RandomForestModel_Iris")

    val stream: InputDStream[(String, String)] = createStream(ssc, kafkaParam, topics)
//    stream.map(_._2) // 取出value
//      .flatMap(_.split(",")) // 将字符串使用空格分隔
//      .map(_.toDouble) // 每个单词映射成一个pair
//        .print()

    stream.map(value => {
      val data = value._2
      val vector = Vectors.dense(data.split(",").map(_.toDouble))
      val result = model.predict(vector)
      sendResultStream(result)
      result
    }).print()

    ssc.start() // 真正启动程序
    ssc.awaitTermination() //阻塞等待
  }


  def createStream(scc: StreamingContext, kafkaParam: Map[String, String], topics: Set[String]) = {
    KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](scc, kafkaParam, topics)
  }

  def sendResultStream(result: Double) = {
    val BROKER_LIST = "localhost:9092"
    val TOPIC = "result"


    /**
      * 1、配置属性
      * metadata.broker.list : kafka集群的broker
      * serializer.class : 如何序列化发送消息
      * request.required.acks : 1代表需要broker接收到消息后acknowledgment,默认是0
      * producer.type : 默认就是同步sync
      */
    val props = new Properties()
    props.put("bootstrap.servers",BROKER_LIST)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("request.required.acks", "1")
    props.put("producer.type", "async")


    val producer = new KafkaProducer[String,String](props)

    try {
      val record = new ProducerRecord[String,String](TOPIC,"key",result.toString)
      producer.send(record)
    } catch {
      case e : Exception => println(e)
    }

  }
}
