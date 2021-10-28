package com.douzone.mysite.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping({"", "/main"})   // 여러 경로를 한 메서드에 처리하고 싶다면 배열로 경로목록을 지정 ->main이나 아무것도 안적어주면 
	public String index() {
		return "main/index";
	}
	
	// jquery
	/*
	 * @RequestMapping("/hello") public void message(HttpServletResponse resp)
	 * throws Exception { resp.setContentType("application/json; charset=UTF-8");
	 * resp.getWriter().print("{\"message\":\"Hello World\"}"); }
	 */
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/hello\") public Object message() throws Exception {
	 * Map<String, Object> map = new HashMap<>(); map.put("message", "Hello World");
	 * 
	 * return map; }
	 */
	
	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		return "안녕";
	}
	
	@ResponseBody
	@RequestMapping("/msg02")
	public Object message02(/*HttpServletResponse resp*/) throws Exception {
		//resp.setContentType("application/json; charset=UTF-8");
		//resp.getWriter().print("{\"message\":\"Hello World\"}");
		
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello World");
		
		return map;
	}
}
