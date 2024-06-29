package bookagg.api.home.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String BOOKS_QUEUE = "booksQueue";

    @Bean
    public Queue booksQueue() {
        return new Queue(BOOKS_QUEUE, false);
    }

    @Bean public Exchange exchange()
    {
        return new DirectExchange("exchange-name");
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange)
    {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("routing-key")
                .noargs();
    }

}
