package co.kevingomez.jmstemplatedemo;

import java.util.Scanner;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	public Producer(JmsTemplate jmsTemplate) {
		Scanner sc = new Scanner(System.in);
		String text = sc.next();
		while(!text.equalsIgnoreCase("exit")) {
			jmsTemplate.send(s -> s.createTextMessage("Text"));
			text = sc.next();
		}
		sc.close();
	}
}
