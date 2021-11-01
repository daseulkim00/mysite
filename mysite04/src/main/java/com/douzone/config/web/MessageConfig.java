package com.douzone.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {
	
	
//	<!-- Custom Message Source -->
//	<bean id="messageSource"
//		class="org.springframework.context.support.ResourceBundleMessageSource">
//		<property name="basenames">
//			<list>
//				<value>messages/messages_ko</value>
//			</list>
//		</property>
//	</bean>
	
	@Bean 
	public MessageSource messageSource() {   // 다국어 지원하기 위한 프로그램
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("com/douzone/mysite/config/web/message_ko");
		messageSource.setDefaultEncoding("utf-8");
		// 언어를 지원해 주는 거라서 파일 전체를 그냥 가져온다. message_ko 자체가 한국말
		
	
		return messageSource;
	}
}
