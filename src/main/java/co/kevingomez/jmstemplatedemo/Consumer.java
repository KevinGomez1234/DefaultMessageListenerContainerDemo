package co.kevingomez.jmstemplatedemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;
@Component
public class Consumer {
	public Consumer(@Qualifier("dmlc")DefaultMessageListenerContainer dmlc) {
		System.out.println("ConsumerOne");
		dmlc.start();
	}

}
