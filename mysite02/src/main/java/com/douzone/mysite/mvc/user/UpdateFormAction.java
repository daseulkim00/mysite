package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(보안, 인증체크) 보안,인증이들어간건 중복되니깐 나중에 빼 낸다. 게시판 만들때 들어가 있어야한다
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		/////////////////////////////////////////////////////////////
		
		Long no = authUser.getNo();
		UserVo userVo = new UserDao().findByNo(no);
		
		request.setAttribute("userVo", userVo);
		MvcUtil.forward("user/updateform", request, response);

	}

}
