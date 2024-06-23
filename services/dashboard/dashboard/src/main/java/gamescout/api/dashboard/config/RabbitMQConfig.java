package gamescout.api.dashboard.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String BOOKS_QUEUE = "booksQueue";

    @Bean
    public Queue booksQueue() {
        return new Queue(BOOKS_QUEUE, false);
    }
}
