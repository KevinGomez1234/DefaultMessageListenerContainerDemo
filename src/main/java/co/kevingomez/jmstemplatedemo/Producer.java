package co.kevingomez.jmstemplatedemo;

import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	private JmsTemplate jmsTemplate;
	public Producer(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
		Scanner sc = new Scanner(System.in);
		String text = sc.next();
		while(!text.equalsIgnoreCase("exit")) {
			sendMessage(text);
			text = sc.next();
		}
		sc.close();
	}
	
	public void sendMessage(final String msg) {				
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
			
		});
	}
}
