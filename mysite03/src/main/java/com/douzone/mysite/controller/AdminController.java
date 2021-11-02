package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SiteService siteService; 
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ServletContext servletContext;
	
	
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo sitevo = (SiteVo)siteService.getsite();
		model.addAttribute("site", sitevo);
		return "admin/main";
	}
	
	@RequestMapping("/main/update") // @RequestParam("가져올 데이터의 이름")[데이터 타입][가져온 데이터를 담을 변수]               
	public String main(SiteVo site, @RequestParam("file") MultipartFile file) {  
		
		String profile = fileUploadService.restoreImage(file);
		site.setProfile(profile);  //vo에 업로드한 profile을 넣는다
		siteService.update(site); 
		servletContext.setAttribute("site", site);   //main.jsp에서 ${site.welcome } 써서 들고올려고 사용
		return "redirect:/admin";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}


}