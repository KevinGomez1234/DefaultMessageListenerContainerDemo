package co.kevingomez.jmstemplatedemo;

import javax.jms.ConnectionFactory;

import javax.jms.Destination;
import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, @Qualifier("queueTwo") Destination queue)
			throws Exception {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestination(queue);
		return jmsTemplate;
	}

	@Bean
	ConnectionFactory tibjmsConnectionFactory(@Value("${server.url}") String serverUrl,
			@Value("${user.name}") String userName, @Value("${user.password}") String password) throws JMSException {
		TibjmsConnectionFactory connectionFactory = new TibjmsConnectionFactory();
		connectionFactory.setServerUrl(serverUrl);
		connectionFactory.setUserPassword(userName);
		connectionFactory.setUserName(password);
		return connectionFactory;
	}

	@Bean(name = "queueOne")
	Destination queue(@Value("${queue.name}") String queueName) {
		return new TibjmsQueue(queueName);
	}

	@Bean(name = "queueTwo")
	Destination queueTwo(@Value("${queue.name2}") String queueName) {
		return new TibjmsQueue(queueName);
	}

	@Bean(name = "dmlc")
	DefaultMessageListenerContainer defaultMessageListenerContainer(ConnectionFactory connectionFactory,
			@Qualifier("queueOne") Destination queue, MessageListen listen) {
		DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
		dmlc.setConnectionFactory(connectionFactory);
		dmlc.setDestination(queue);
		dmlc.setMessageListener(listen);
		return dmlc;
	}

	@Bean(name = "dmlc2")
	DefaultMessageListenerContainer defaultMessageListenerContainerTwo(ConnectionFactory connectionFactory,
			@Qualifier("queueTwo") Destination queue, MessageListen listen) {
		DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
		dmlc.setConnectionFactory(connectionFactory);
		dmlc.setDestination(queue);
		dmlc.setMessageListener(listen);
		return dmlc;
	}
}
