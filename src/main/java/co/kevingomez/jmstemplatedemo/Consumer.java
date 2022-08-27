package co.kevingomez.jmstemplatedemo;

import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	public Consumer(DefaultMessageListenerContainer dmlc) {
		dmlc.start();
	}

}
