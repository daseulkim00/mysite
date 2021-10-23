package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// deletform에서 g=delete, no=글번호, password를 받아옴 
		// String g = request.getParameter("g");  안쓰니깐 지워주면댐
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		// dao랑 vo 객체를 생성 ()
		GuestbookDao guestbookDao = new GuestbookDao();
		//(DELETE메소드 안에서)VO에서NO랑 PASSWORD를 가져와서 VO생성
		GuestbookVo guestbookVo = new GuestbookVo(); 
		guestbookVo.setNo(Long.parseLong(no));
		guestbookVo.setPassword(password);
		
		guestbookDao.delete(guestbookVo);
		
		// 삭제 한뒤 갈곳
		//http://localhost:8080/mysite02
		MvcUtil.redirect(request.getContextPath() + "/guestbook?g=list", request, response);
		
		
		
		
	}

}