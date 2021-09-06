package com.techi.redis.keyspace.notification.publisher;

import com.techi.redis.keyspace.notification.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;



@RestController
public class publisher {

    private final Logger logger = LoggerFactory.getLogger(publisher.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("publish")
    public String publishMessage(@RequestBody Order order){
        logger.info("message publish time : {}", System.currentTimeMillis()/1000);
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.setex(order.toString(), 10, order.toString());
        return "published !!";
    }
}
