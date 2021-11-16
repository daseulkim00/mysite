package com.douzone.mysite.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;

	
	// select
	@RequestMapping("")
	//파라미터로 Model을 받겠다고 적어놔야 스프링이 넣어줄 수 있다
	public String index(Model model) {
		List<GuestbookVo> list = guestbookService.getMessageList();
		model.addAttribute("list",list);
		return "guestbook/index";
	}
	
	// spa
	
	@RequestMapping("/spa")
	public String spa() {
//		List<GuestbookVo> list = guestbookService.getMessageList();
//		model.addAttribute("list",list);
		return "guestbook/index-spa";
	}
	
	@RequestMapping("/spaadd")
	public JsonResult spaadd(@RequestBody GuestbookVo vo) {
		return JsonResult.success(vo);
	}

	
	// write
	@RequestMapping(value = "/write" , method = RequestMethod.POST)
	public String write(GuestbookVo vo) {
		guestbookService.writeMessage(vo);
		return "redirect:/guestbook";
	}
	
	//delete
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	// @PathVariable - @RequestMapping의 path에 변수명을 입력받기 위한 place holder가 필요함
	public String delete(@PathVariable("no") Long no, Model model ) {
		model.addAttribute("no",no);
		return "/guestbook/delete";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, @RequestParam(defaultValue = "") String password) {
		guestbookService.deleteMessage(no, password);
		return "redirect:/guestbook";
			}
		
}