package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardDto;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BoardDao dao = new BoardDao();

		// 로그인되있는지검사
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}

		// 로그인한 사람이 글 주인인지

		// no를 가져와야 하는데 "10"이라서 long 타입으로 변환해주기 위해서 사용
		String parameter = request.getParameter("no");
		// "10" -> 10
		Long no = Long.parseLong(parameter);
		//Long no = Long.parseLong(request.getParameter("no")); 위에2줄과 같은 코드
		
		if (authUser.getNo() != dao.findUserNo(no).getNo()) {
			System.out.println("권한없음");

			// http://localhost:8080/mysite02 + /board
			MvcUtil.redirect(request.getContextPath() + "/board", request, response);
			return;
		}

		// no select
		

		BoardDto dto = new BoardDao().findByNo(no);
		request.setAttribute("dto", dto);

		MvcUtil.forward("board/modifyform", request, response);
	}

}
