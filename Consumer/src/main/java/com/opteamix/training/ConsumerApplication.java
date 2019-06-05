package com.opteamix.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx =  SpringApplication.run(ConsumerApplication.class, args);
		MessageConsumer c = ctx.getBean(MessageConsumer.class);
		c.setUpConsumer();
	}

}
