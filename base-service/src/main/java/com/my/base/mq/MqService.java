package com.my.base.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;

public interface MqService {
	public void send(String queue, Object msg);
	
	public void send(String queue, Object msg, ConfirmCallback confirm);
	
	public void onMessage(Object msg);
}
