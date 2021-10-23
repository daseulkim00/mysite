package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDao boardDao = new BoardDao();
		
		BoardVo vo = new BoardVo();
		Long no = Long.parseLong(request.getParameter("no"));
		vo.setNo(no);
		
		//로그인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		// 자기글
		if(authUser.getNo() != boardDao.findUserNo(no).getNo()) {
			System.out.println("권한없음");
			
			//				http://localhost:8080/mysite02 + /board
			MvcUtil.redirect(request.getContextPath() + "/board", request, response);
			return;
		}
		
		boardDao.delete(vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?g=list", request, response);
	}

}
