# Spring Boot Cdc and Cqrs Pattern
> Spring Boot Cdc and Cqrs Pattern
>
<img src="https://github.com/susimsek/spring-boot-cdc-and-cqrs-pattern/blob/main/images/spring-boot-cdc-and-cqrs-pattern.png" alt="Spring Boot Cdc and Cqrs Pattern" width="100%" height="100%"/> 

## Prerequisites

* Java 11
* Maven 3.3+
* Docker 19.03+
* Docker Compose 1.25+

## Installation

```sh
sudo chmod +x build.sh
```

```sh
./build.sh
```


```sh
docker-compose up -d 
```

```sh
cd debezium-connector
```

```sh
sudo chmod +x connector.sh
```

```sh
./connector.sh
```

## Installation Using Vagrant

<img src="https://github.com/susimsek/spring-boot-cdc-and-cqrs-pattern/blob/main/images/vagrant-installation.png" alt="Spring Boot Vagrant Installation" width="100%" height="100%"/> 

### Prerequisites

* Vagrant 2.2+
* Virtualbox or Hyperv

```sh
vagrant up
```

```sh
vagrant ssh
```

```sh
cd vagrant/setup
```

```sh
sudo chmod u+x *.sh
```

```sh
./install-prereqs.sh
```

```sh
exit
```

```sh
vagrant ssh
```

```sh
sudo chmod +x build.sh
```

```sh
./build.sh
```


```sh
docker-compose up -d 
```

```sh
cd debezium-connector
```

```sh
sudo chmod +x connector.sh
```

```sh
./connector.sh
```

You can access the SpringDoc Openapi from the following url.

http://localhost:9090/api

You can access the Kafdrop from the following url.

http://localhost:9000

You can access the Kibana from the following url.

http://localhost:5601

## Used Technologies

* Spring Boot 2.4.3
* Mongodb (Replica Mod)
* Elasticsearch
* Kibana
* Fluentd  
* Zookeeper
* Kafka
* Kafdrop
* Debezium  
* Spring Boot Web Flux
* Spring Boot Mongodb Reactive  
* Spring Boot Data Elasticsearch
* Spring Boot Validation
* Spring Boot Log4j2  
* Kafka Log4j Appender
* Spring Boot Aop
* Spring Kafka  
* Guava
* Vavr
* Problem Spring Web Flux
* SpringDoc Openapi Web Flux Core
* SpringDoc Openapi Web Flux Ui
* Spring Boot Actuator
* Maven Jib Plugin
* Maven Clean Plugin
* Maven Enforcer Plugin
* Maven Compiler Plugin
* Mapstruct
* Lombok
* Dev Tools
* Spring Boot Test

## SpringDoc OpenApi

> You can access the SpringDoc Openapi from the following url.

http://localhost:9090/api

<img src="https://github.com/susimsek/spring-boot-cdc-and-cqrs-pattern/blob/main/images/springdoc-openapi.png" alt="SpringDoc Openapi" width="100%" height="100%"/> 