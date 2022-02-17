# Api and Dynamo
Simple api with dynamo that registers a user and produces a kafka topic.

## About

Esta é uma POC criada com objetivo de criar uma aplicação kafka e realizar uma conteinerização  gerenciada pelo kubernetes utilizando dos recursos oferecidos pelo mesmo.


## Environment Variables

The project has the following environment variables:

* `kafka.topic`
* `server.kafka.bootstrap`
* `server.schema.bootstrap`

## Resources Embedded

Resources needed to start the application, just start docker-compose in the folder .The kafka cluster is not included.
