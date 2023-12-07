package com.example.XyhubHelpdesk.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	public static final String TICKETING_EXCHANGE = "TicketingTool_Data_Exchange";
	public static final String TICKETING_ROUTING_KEY = "TicketingTool_Routing_Key";
	public static final String TICKETING_QUEUE = "TicketingTool_Queue";

	public static final String HELPDESK_EXCHANGE = "HelpDesk_Data_Exchange";
	public static final String HELPDESK_ROUTING_KEY = "HelpDesk_Routing_Key";
	public static final String HELPDESK_QUEUE = "HelpDesk_Queue";

	@Bean
	public Queue ticketingQueue() {
	    return new Queue(TICKETING_QUEUE);
	}

	@Bean
	public Queue helpdeskQueue() {
	    return new Queue(HELPDESK_QUEUE);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}

	@Bean
	public TopicExchange ticketingExchange() {
	    return new TopicExchange(TICKETING_EXCHANGE);
	}

	@Bean
	public TopicExchange helpdeskExchange() {
	    return new TopicExchange(HELPDESK_EXCHANGE);
	}

	@Bean
	public Binding bindingTicketingQueue(TopicExchange ticketingExchange) {
	    return BindingBuilder.bind(ticketingQueue()).to(ticketingExchange).with(TICKETING_ROUTING_KEY);
	}

	@Bean
	public Binding bindingHelpdeskQueue(TopicExchange helpdeskExchange) {
	    return BindingBuilder.bind(helpdeskQueue()).to(helpdeskExchange).with(HELPDESK_ROUTING_KEY);
	}
	
//	@Bean("stockThreadPool")
//    public ThreadPoolTaskExecutor mailThreadPool() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(5); // Set the core pool size as needed
//        executor.setMaxPoolSize(10); // Set the maximum pool size as needed
//        executor.setQueueCapacity(25); // Set the queue capacity as needed
//        return executor;
//    }
	
}
