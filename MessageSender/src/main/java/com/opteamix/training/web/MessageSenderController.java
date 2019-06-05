package com.opteamix.training.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solacesystems.jcsmp.InvalidPropertiesException;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPFactory;
import com.solacesystems.jcsmp.JCSMPSession;
import com.solacesystems.jcsmp.JCSMPStreamingPublishEventHandler;
import com.solacesystems.jcsmp.Session;
import com.solacesystems.jcsmp.SpringJCSMPFactory;
import com.solacesystems.jcsmp.TextMessage;
import com.solacesystems.jcsmp.Topic;
import com.solacesystems.jcsmp.XMLMessageProducer;

@RestController
public class MessageSenderController {

	@Autowired
	SpringJCSMPFactory springJCSMPFactory;

	@GetMapping("/sendmessage")
	public String sendMessage() {
		String response = "Message Couldn't be sent";
		try {
			JCSMPFactory jcsmpFactory = JCSMPFactory.onlyInstance();
			JCSMPSession session = springJCSMPFactory.createSession();
			Topic topic = jcsmpFactory.createTopic("training");
			session.connect();
			XMLMessageProducer producer = session.getMessageProducer(new JCSMPStreamingPublishEventHandler() {

				@Override
				public void responseReceived(String arg0) {
					System.out.println("Message Sent");
				}

				@Override
				public void handleError(String arg0, JCSMPException arg1, long arg2) {
					System.out.println("Error on Broker");
					arg1.printStackTrace();
				}
			});
			TextMessage message = jcsmpFactory.createMessage(TextMessage.class);
			message.setText(" " + new Date().toString());
			producer.send(message, topic);
			response = "Message Sent Successfully!!";
			session.closeSession();

		} catch (Exception e) {
			System.out.println("Error Connecting to solace");
			e.printStackTrace();
		}

		return response;
	}
}
