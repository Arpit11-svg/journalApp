package net.edigest.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.edigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass){
        try{
            Object o = redisTemplate.opsForValue().get(key);
//        Now map incoming JSON into POJO class
            ObjectMapper objectMapper=new ObjectMapper();
            return objectMapper.readValue(o.toString(),entityClass);
        }
        catch (Exception e){
            log.error("Exception ",e);
            return null;
        }
    }
    public void set(String key,Object o,Long ttl){ //ttl=?timeToLive OR ExpiryTime
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            String jsonValue=objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key,jsonValue,ttl, TimeUnit.SECONDS);
        }
        catch (Exception e){
            log.error("Exception ",e);
        }
    }
}
