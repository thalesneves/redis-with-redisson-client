package com.example.demo.configuration;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

public class TesteThales {

    private RedissonClient redissonClient;

    @Autowired
    public TesteThales(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

}
