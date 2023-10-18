package customer.demo.demo.service;
import customer.demo.demo.entity.Customer;
import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomerDataProducer {
    public  void KafkaProducer() {
        List<Customer> ListA = new ArrayList<>();
        ListA.add(new Customer());
        ListA.add(new Customer());
        ListA.add(new Customer());

        // Create and initialize ListB
        List<Customer> ListB = new ArrayList<>();
        ListB.add(new Customer());
        ListB.add(new Customer());
        ListB.add(new Customer());
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        // Produce customer data from List A and List B to the "customer-data" topic
        for (Customer customer : ListA) {
            producer.send(new ProducerRecord<>("customer-data", customer.getFirstName(), customer.toString()));
        }

        for (Customer customer : ListB) {
            producer.send(new ProducerRecord<>("customer-data", customer.getFirstName(), customer.toString()));
        }
        List<Customer> customersInBothLists = new ArrayList<>(ListA);
        customersInBothLists.retainAll(ListB);

        // Produce messages for customers in both lists
        for (Customer customer : customersInBothLists) {
            String customerId = customer.getFirstName();
            String customerData = customer.toString(); // You can serialize the customer data as needed
            producer.send(new ProducerRecord<>("customer-data", customerId, customerData));
        }
        producer.close();
    }
}

