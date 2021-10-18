package com.douzone.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		encoding =  fConfig.getInitParameter("encoding");
		if(encoding == null) {
			encoding = "UTF-8";
		}
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//request
		
		// System.out.println("UTF-8");
		chain.doFilter(request, response);  //필터끼리 체인으로 연결되어있당
		
		//response
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}



}
