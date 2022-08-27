package co.kevingomez.jmstemplatedemo;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.tibco.tibjms.TibjmsConnectionFactory;
import com.tibco.tibjms.TibjmsQueue;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, Destination queue) throws Exception {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestination(queue);
		return jmsTemplate;
	}

	@Bean
	ConnectionFactory tibjmsConnectionFactory() throws JMSException {
		TibjmsConnectionFactory connectionFactory = new TibjmsConnectionFactory();
		connectionFactory.setServerUrl("tcp://localhost:7222");
		connectionFactory.setUserPassword("pass");
		connectionFactory.setUserName("kevin");
		return connectionFactory;
	}

	@Bean
	Destination queue() {
		return new TibjmsQueue("sender");
	}

	@Bean
	DefaultMessageListenerContainer defaultMessageListenerContainer(ConnectionFactory connectionFactory,
			Destination queue, MessageListen listen) {
		DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
		dmlc.setConnectionFactory(connectionFactory);
		dmlc.setDestination(queue);
		dmlc.setMessageListener(listen);
		return dmlc;
	}
}
