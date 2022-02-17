package br.com.poc.usercadastro.kafka;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaProducer {

    private final String bootStrapServer;

    private final String schemaRegistryUrl;

    public final org.apache.kafka.clients.producer.KafkaProducer producerRecord;


    public KafkaProducer(@Value("${server.kafka.bootstrap:http://127.0.0.1:9092}")
                                        String bootStrapServer, @Value("${server.schema.bootstrap:http://127.0.0.1:8081}")
                                        String schemaRegistryUrl) {
        this.bootStrapServer = bootStrapServer;
        this.schemaRegistryUrl = schemaRegistryUrl;
        this.producerRecord = createProducer();
    }


    private org.apache.kafka.clients.producer.KafkaProducer createProducer() {

        Properties properties = new Properties();

        //create Producer Properties
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


        //create save Producer
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, "10");
        properties.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);


        return new org.apache.kafka.clients.producer.KafkaProducer(properties);
    }

}
