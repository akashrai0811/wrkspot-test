import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class CustomerDataProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        // Produce customer data from List A and List B to the "customer-data" topic
        for (Customer customer : listA) {
            producer.send(new ProducerRecord<>("customer-data", customer.getCustomerId(), customer.toString()));
        }

        for (Customer customer : listB) {
            producer.send(new ProducerRecord<>("customer-data", customer.getCustomerId(), customer.toString()));
        }

        producer.close();
    }
}
