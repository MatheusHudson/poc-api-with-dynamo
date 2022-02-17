package br.com.poc.usercadastro.dynamo.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties("user-poc.dynamodb")
public class DynamoDbProperties {

    private String awsRegion = "us-east-1";

}