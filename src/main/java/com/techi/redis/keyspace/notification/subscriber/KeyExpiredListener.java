package com.techi.redis.keyspace.notification.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component public class KeyExpiredListener implements MessageListener {

	private final Logger log = LoggerFactory.getLogger(KeyExpiredListener.class);

	@Autowired private RedisTemplate<?, ?> redisTemplate;

	@Override public void onMessage(Message message, byte[] bytes) {
		log.info("message receive time : {}", System.currentTimeMillis()/1000);
		byte[] body = message.getBody();
		byte[] channel = message.getChannel();
		log.info(new String(body));
		log.info(new String(channel));
	}

}
