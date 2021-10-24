package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping({"", "/main"})   // 여러 경로를 한 메서드에 처리하고 싶다면 배열로 경로목록을 지정 ->main이나 아무것도 안적어주면 
	public String index() {
		return "main/index";
	}
}
