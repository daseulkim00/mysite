package com.douzone.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration  // config클래스를만들때 달아주면좋다 
@EnableWebMvc  // Message Converter, handler map, View Resolver 같은 기본 기능을 자동으로 설정해 주기 때문에 생생해야 함
public class MvcConfig extends WebMvcConfigurerAdapter { //WebMvcConfigurerAdapter 상속받을때 @EnableWebMvc받아주면좋다
	
	//////// View Resolver
	
	// <!-- ViewResolver -->
	// <bean id="viewResolver"
	//	class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	//	<property name="viewClass"
	//		value="org.springframework.web.servlet.view.JstlView" />
	//	<property name="prefix" value="/WEB-INF/views/" />
	//	<property name="suffix" value=".jsp" />
	// </bean>
	@Bean
	public ViewResolver viewResolver() {
		// id는 보통 인터페이스(같은 인터페이스 있을수도 있으니 조심하기)
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver(); //이거타고올라가면 viewResolver가 나온다
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	/////// Message Converter
	
   //<bean class="org.springframework.http.converter.StringHttpMessageConverter">
	// <property name="supportedMediaTypes">
	//	<list>
	//		<value>text/html; charset=UTF-8</value>
	//	</list>
	// </property>
  //</bean>
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		
		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
		messageConverter.setSupportedMediaTypes(           // 순수 문자열로 바꿔주는것 예를들면 <h1>hello</h1>하면 문자그대로출력안되고 hello가 출력되게 해주는것
			Arrays.asList( 
				new MediaType("text", "html", Charset.forName("utf-8")))
			);       //request와 response에 MediaType을 지정해서 요청받을 때 사용하는 MediaType과 응답할 때 보내주는 MediaType을 지정, 사전에 필요한 타입만 거를 수 있다.
		return messageConverter;
	}
	
//	<bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
//	  <property name="supportedMediaTypes">
//		<list>
//			<value>application/json; charset=UTF-8</value>
//		</list>
//	  </property> 
//   </bean>
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		//객체를 제이슨,xml등 문자열로 변환시켜준다. 지금은 제이슨 
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
			.indentOutput(true)    // 들여쓰기 예쁘게 해주는거                    이거랑 밑에꺼는 그냥 추가해준거
			.dateFormat(new SimpleDateFormat("yyyy-mm-dd"));   // 날짜 포멧
		
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(builder.build());
		messageConverter.setSupportedMediaTypes(
			Arrays.asList(
				new MediaType("application", "json", Charset.forName("utf-8"))
			)
		);
		
		return messageConverter;
	}
	
	@Override  // 메세지 컨버터를 등록해주는 역할 
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());           
		converters.add(mappingJackson2HttpMessageConverter());
	}
	

	// Default Servlet Handler    요건 boot로 가면 사라짐-없어도 잘 돌아감
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
