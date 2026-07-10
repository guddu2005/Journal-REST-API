package com.example.myFirstProject.Service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key , Class<T>entityClass ){
        try{
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString() , entityClass);
        }catch (Exception e){
            log.error("Exception during geting data from Redis " ,e);
            return null;
        }
    }

    public void set(String key , Object  o ,Long ttl ){
        try{
            redisTemplate.opsForValue().set(key , o.toString() ,ttl  , TimeUnit.SECONDS);
            ObjectMapper mapper = new ObjectMapper();
        }catch (Exception e){
            log.error("Exception during setting data from Redis " ,e);
        }
    }

}
