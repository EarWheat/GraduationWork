package demo
import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object StreamingProduceKafka {
  def main(args: Array[String]): Unit = {
    val BROKER_LIST = "localhost:9092"
    val TOPIC = "test"


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


    println("开始生产消息！！！！！！！！！！")
    while(true){
      try {
        var i = 0
        while (i < 8) {
          val line = " This is from Streaming Kafka" + i
          val record = new ProducerRecord[String,String](TOPIC,"key",line)
          producer.send(record)
          i = i + 1
          Thread.sleep(3000)
        }
      }
      catch {
        case e : Exception => println(e)
      }
    }
  }
}
