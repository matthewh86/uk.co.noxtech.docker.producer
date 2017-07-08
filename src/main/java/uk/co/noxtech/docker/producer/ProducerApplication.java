package uk.co.noxtech.docker.producer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProducerApplication {

    private static final Logger LOGGER = LogManager.getLogger(ProducerApplication.class);

    // TODO: create a separate config project
    public static final String QUEUE_NAME = "uk.co.noxtech.docker.queue";

    public static final String EXCHANGE_NAME = "uk.co.noxtech.docker.exchange";

    public static final String SPRING_RABBIT_MQ_HOST_PROP = "spring.rabbitmq.host";

    public static final String RABBIT_MQ_IP = "RABBIT_MQ_IP";

    public static final int INTERVAL = 2000;

    @Autowired
    private ProducerService producerService;

    public static void main(String[] args) {
        System.getProperties().setProperty(SPRING_RABBIT_MQ_HOST_PROP, System.getenv(RABBIT_MQ_IP));
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> producerService.startService();
    }

}
