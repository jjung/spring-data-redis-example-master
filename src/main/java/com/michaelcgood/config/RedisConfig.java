package com.michaelcgood.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.michaelcgood.queue.MessagePublisher;
import com.michaelcgood.queue.MessagePublisherImpl;
import com.michaelcgood.queue.MessageSubscriber;

@Configuration
@ComponentScan("com.michaelcgood")
public class RedisConfig {
//
	@Value("${spring.redis.host}")
	String host;
	@Value("${spring.redis.port}")
	int port;
//	@Value("${spring.redis.password}")
//	String password;
	
//
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName(host);
		jedisConFactory.setPort(port);
//		jedisConFactory.setPassword(password);
		
		return jedisConFactory;
    }

//  @Bean
//  LettuceConnectionFactory lettuceConnectionFactory() {
//	  LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory("27.122.227.144", 31379);
//		
//		return lettuceConnectionFactory;
//  }
//	
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//    	 
//    	
//        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//        template.setConnectionFactory(lettuceConnectionFactory());
//        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
//        return template;
//    }
	    @Bean
	    public RedisTemplate<String, Object> redisTemplate() {
	    	 
	    	
	        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	        template.setConnectionFactory(jedisConnectionFactory());
	        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
	        return template;
	    }
	    

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new MessageSubscriber());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisherImpl(redisTemplate(), topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }
}
