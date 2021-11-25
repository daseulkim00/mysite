package com.douzone.mysite.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.mysite.config.AppConfig;
import com.douzone.mysite.config.WebConfig;

public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	// applicationContext.xml 대신
	@Override
	protected Class<?>[] getRootConfigClasses() { //?는[] 배열에 들어갈 타입을 지정한다.? 가 들어가면 몰라 아무거나 들어가도된다.

		// return  new Class<>[] {String.class}; 스트링 타입의 객체를 넣는다.
		return  new Class<?>[] {AppConfig.class};   
	}
	
	// spring-servlet.xml 대신
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return  new Class<?>[] {WebConfig.class}; 
	}

	@Override
	protected String[] getServletMappings() {   // 매핑url 
  
		return new String[] {"/"};
	}

	
	// 옵션 양키들은안함 
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("UTF-8",false)};
	}

	// 추가적으로 url 이상하게 들어가면 에러띄어줘라
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}
	
}
