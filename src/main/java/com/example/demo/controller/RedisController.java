package com.example.demo.controller;

import com.example.demo.configuration.TesteThales;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.WriteRedisConnectionException;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    private RedissonClient redissonClient;

    private TesteThales testeThales;

    @Autowired
    private ApplicationContext applicationContext;

    private int i = 0;

    @Autowired
    public RedisController(RedissonClient redissonClient, TesteThales testeThales) {
        this.redissonClient = redissonClient;
        this.testeThales = testeThales;
    }

    @PostMapping("/inserirNoRedis")
    public String inserirNoRedis(@RequestBody String dados) {
        try {
            redissonClient.getBucket("chave").set(dados);

            return "Dados inseridos com sucesso no Redis.";
        } catch (WriteRedisConnectionException e) {
            String[] pass = {"admin_thales", "admin_jarvis"};

            Config config = new Config();
            config.useSingleServer()
                    .setAddress("redis://localhost:6379")
                    .setPassword(pass[i]);
            i++;

            if (i == 1) i = 0;

            RedissonClient newRedissonClient = Redisson.create(config);

            DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) applicationContext.getAutowireCapableBeanFactory();
            registry.destroySingleton("redissonClient");
            registry.registerSingleton("redissonClient", newRedissonClient);

            return "Tente de novo!";
        }
    }

}
