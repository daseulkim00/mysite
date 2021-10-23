package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		GuestbookVo guestbookVo = new GuestbookVo();
		
		// deletform에서 g=delete, no=글번호, password를 받아옴
		// String g = request.getParameter("g"); 안쓰니깐 지워주면댐
		
		Long no = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");
		
		guestbookVo.setNo(no);
		guestbookVo.setPassword(password);
		
		MvcUtil.forward("guestbook/deleteform", request, response);
		
	}

}
