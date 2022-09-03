package co.kevingomez.jmstemplatedemo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class MessageListen implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		String textMessage;
		try {
			System.out.println(message.getJMSDestination());
			textMessage = msg.getText();
			System.out.println("ConsumedE: " + textMessage);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
