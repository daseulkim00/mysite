package com.douzone.mysite;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class BootInitializer extends SpringBootServletInitializer {
	// 원래는 내장 톰캣을 사용해서 이거 없어도되는데 지금은 외장 톰캣을(war로 만들어서 올려줘야함)로 올려야해서 이걸 만들어줌
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MySiteApplication.class);
	}
	
}
