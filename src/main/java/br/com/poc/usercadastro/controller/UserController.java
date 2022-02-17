package br.com.poc.usercadastro.controller;

import br.com.poc.usercadastro.dto.UserDto;
import br.com.poc.usercadastro.dynamo.model.UserEntity;
import br.com.poc.usercadastro.kafka.KafkaProducer;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private KafkaProducer kafkaProducer;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @Value("${kafka.topic:test_topic}")
    private String topic;

    @PostMapping
    private ResponseEntity<UserDto> createNewUser(@RequestBody UserDto userDto, UriComponentsBuilder uriBuilder) {

        UserEntity userEntity = userDto.toEntity();

        dynamoDBMapper.save(userEntity);
        logger.info("User "+ userDto.getName() + " created in the database");

        kafkaProducer.producerRecord.send(new ProducerRecord<>(topic, UUID.randomUUID().toString(), userDto));
        logger.info("Topic " + topic + " sent successfully!");

        return ResponseEntity.created(uriBuilder.path("/user/{id}").buildAndExpand(userEntity.getUuid()).toUri()).body(userDto);

    }
}
