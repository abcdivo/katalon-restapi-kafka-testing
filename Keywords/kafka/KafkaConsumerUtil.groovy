package kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration

class KafkaConsumerUtil {

    static String consumeMessage() {

        Properties props = new Properties()

        props.put(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            "localhost:9092"
        )

        props.put(
            ConsumerConfig.GROUP_ID_CONFIG,
            "katalon-group-" + System.currentTimeMillis()
        )

        props.put(
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
            "org.apache.kafka.common.serialization.StringDeserializer"
        )

        props.put(
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
            "org.apache.kafka.common.serialization.StringDeserializer"
        )

        props.put(
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
            "earliest"
        )

        KafkaConsumer<String,String> consumer =
                new KafkaConsumer<>(props)

      consumer.subscribe(["orders"])

// Poll pertama untuk join consumer group
	  consumer.poll(Duration.ofSeconds(2))

// Poll kedua untuk membaca data
	  def records = consumer.poll(Duration.ofSeconds(10))

	  println("Records Count = " + records.count())

	  String message = ""

	  records.each {
		  println("Received Message = " + it.value())
		  message = it.value()
	  }

	  println("Final Message = [" + message + "]")

       consumer.close()
       return message
    }
}
