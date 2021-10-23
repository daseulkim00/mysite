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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		// 로그인 되어 있는지
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}

		// update되야한다
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		Long no = Long.parseLong(request.getParameter("no"));
		
		// 글을 쓴 주인인지 확인
		 //로그인한애 userno   글쓴애 userno  글번호를 넘겨주면 유저번호를 가져와라
		if(authUser.getNo() != dao.findUserNo(no).getNo()) {
			System.out.println("권한없음");
			
			//				http://localhost:8080/mysite02 + /board
			MvcUtil.redirect(request.getContextPath() + "/board", request, response);
			return;
		}
		
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setNo(no);

		dao.update(vo);

		MvcUtil.redirect(request.getContextPath() + "/board?g=list", request, response);

	}

}
