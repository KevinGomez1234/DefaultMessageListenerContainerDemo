package co.kevingomez.jmstemplatedemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;
@Component
public class ConsumerTwo {
	public ConsumerTwo(@Qualifier("dmlc2")DefaultMessageListenerContainer dmlc2) {
		System.out.println("ConsumerTwo");
		dmlc2.start();
	}

}