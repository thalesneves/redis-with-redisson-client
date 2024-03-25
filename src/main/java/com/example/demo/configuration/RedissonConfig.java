package com.example.demo.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class RedissonConfig {

    @Value("${REDIS_PASSWORD}")
    private String redisPassword;

//    private RedissonClient redissonClient;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379")
                .setPassword(redisPassword);

//        redissonClient = Redisson.create(config);
//        return redissonClient;
        return Redisson.create(config);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(RedissonClient redissonClient) {
        return new RedissonConnectionFactory(redissonClient);
    }

    @Bean
    public TesteThales testeThales(RedissonClient redissonClient) {
        return new TesteThales(redissonClient);
    }

//    public void recreateRedissonClient() {
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://localhost:6379")
//                .setPassword(redisPassword);
//
//        redissonClient = Redisson.create(config);
//
//        System.out.println(redissonClient);
//    }
//
//    public RedissonClient getRedissonClient(String redisPassword) {
//        this.redisPassword = redisPassword;
//
//        if (redissonClient == null || redissonClient.isShutdown()) {
//            recreateRedissonClient();
//        }
//
//        return redissonClient;
//    }

}
