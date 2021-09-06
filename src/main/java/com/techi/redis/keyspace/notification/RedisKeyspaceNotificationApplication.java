package com.techi.redis.keyspace.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RedisKeyspaceNotificationApplication {

    private static final long twepoch = 1288834974657L;
    private static final long sequenceBits = 17;
    private static final long sequenceMax = 65536;
    private static volatile long lastTimestamp = -1L;
    private static volatile long sequence = 0L;

    public static void main(String[] args) {
        SpringApplication.run(RedisKeyspaceNotificationApplication.class, args);

//        Set<Long> uniqueIds = new HashSet<Long>();
//        long now = System.currentTimeMillis();
//        System.out.println(generateLongId("POS"));
    }

    private static synchronized String generateLongId(String prefix) {
        long timestamp = System.currentTimeMillis();
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) % sequenceMax;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        long id = ((timestamp - twepoch) << sequenceBits) | sequence;
        return prefix + "-" +id;
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
