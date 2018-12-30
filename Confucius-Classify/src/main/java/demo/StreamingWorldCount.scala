package demo

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Duration, StreamingContext}
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaUtils

/**
  * @author liuzhaolu
  * @version create_time：2018/12/24 类说明:
  */
object StreamingWorldCount {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("kafka-spark-demo")
    val ssc = new StreamingContext(sparkConf, Duration(5000))
    ssc.checkpoint("file:///usr/local/spark/mycode/kafka/checkpoint") //这里表示把检查点文件写入分布式文件系统HDFS，所以要启动Hadoop
    val topics = Set("test") //我们需要消费的kafka数据的topic
    val kafkaParam = Map(
      "metadata.broker.list" -> "localhost:9092") // kafka的broker list地址

    val stream: InputDStream[(String, String)] = createStream(ssc, kafkaParam, topics)
    stream.map(_._2) // 取出value
      .flatMap(_.split(" ")) // 将字符串使用空格分隔
      .map(r => (r, 1)) // 每个单词映射成一个pair
      .updateStateByKey[Int](updateFunc) // 用当前batch的数据区更新已有的数据
      .print() // 打印前10个数据

    ssc.start() // 真正启动程序
    ssc.awaitTermination() //阻塞等待
  }

  val updateFunc = (currentValues: Seq[Int], preValue: Option[Int]) => {
    val curr = currentValues.sum
    val pre = preValue.getOrElse(0)
    Some(curr + pre)
  }

  def createStream(scc: StreamingContext, kafkaParam: Map[String, String], topics: Set[String]) = {
    KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](scc, kafkaParam, topics)
  }
}
