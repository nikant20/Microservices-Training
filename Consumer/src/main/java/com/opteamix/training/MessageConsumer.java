package com.opteamix.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solacesystems.jcsmp.BytesXMLMessage;
import com.solacesystems.jcsmp.InvalidPropertiesException;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPFactory;
import com.solacesystems.jcsmp.JCSMPSession;
import com.solacesystems.jcsmp.SpringJCSMPFactory;
import com.solacesystems.jcsmp.TextMessage;
import com.solacesystems.jcsmp.Topic;
import com.solacesystems.jcsmp.XMLMessageConsumer;
import com.solacesystems.jcsmp.XMLMessageListener;

@Component
public class MessageConsumer {
	@Autowired
	SpringJCSMPFactory springJCSMPFactory;
	
	public void setUpConsumer() throws Exception {
		JCSMPFactory jcsmpFactory = JCSMPFactory.onlyInstance();
		JCSMPSession session = springJCSMPFactory.createSession();
		Topic topic = jcsmpFactory.createTopic("training");
		
		XMLMessageListener listener = new XMLMessageListener() {
			
			@Override
			public void onReceive(BytesXMLMessage arg0) {
				TextMessage msg = (TextMessage) arg0;
				System.out.println("Recieved: "+msg.getText());
				
			}
			
			@Override
			public void onException(JCSMPException arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		XMLMessageConsumer consumer = session.getMessageConsumer(listener);
		session.addSubscription(topic);
		session.connect();
		consumer.start();
	}
}
