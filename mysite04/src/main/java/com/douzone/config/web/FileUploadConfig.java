package com.douzone.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration  // 해당 클래스에서 빈을 생성하는 것을 명시
@PropertySource("classpath:com/douzone/mysite/config/web/fileupload.properties")
public class FileUploadConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment env; 
	
//	<!-- Multipart Resolver -->
//	<bean id="multipartResolver"
//		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
//		<!-- 최대업로드 가능한 바이트크기 -->
//		<property name="maxUploadSize" value="52428800" />
//		<!-- 디스크에 임시 파일을 생성하기 전에 메모리에 보관할수있는 최대 바이트 크기 -->
//		<!-- property name="maxInMemorySize" value="52428800" / -->
//		<!-- defaultEncoding -->
//		<property name="defaultEncoding" value="utf-8" />
//	</bean>
	
	@Bean
	public MultipartResolver multipartResolver() {   // 이미지 크기등을정해주는메소드
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(env.getProperty("fileupload.maxUploadSize", Long.class));
		multipartResolver.setMaxInMemorySize(env.getProperty("fileupload.maxInMemorySize", Integer.class));
		multipartResolver.setDefaultEncoding(env.getProperty("fileupload.defaultEncoding"));
		
		return multipartResolver;
	}

//	<!-- mvc url-resource mapping -->
//	<mvc:resources mapping="/upload/images/**" location="file:/upload-mysite/" />
	
	@Override  
	public void addResourceHandlers(ResourceHandlerRegistry registry) {  // 접근, 경로같은거를 정해주는메소드
		registry
			.addResourceHandler(env.getProperty("fileupload.resourceMapping"))
			.addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));
	}
	
	
}
