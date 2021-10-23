package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardDto;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no = Long.parseLong(request.getParameter("no"));

		BoardDto dto = new BoardDao().findByNo(no);

		request.setAttribute("dto", dto);

		// 조회수- 따로 파라미터를 넘길필요없다. 자기가 클릭하면 조회수 안 올라감
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		
		if(authUser.getNo() != dao.findUserNo(no).getNo()) {
			
			vo.setNo(no);
			dao.hitupdate(vo);
		}
				
		MvcUtil.forward("board/view", request, response);
	}

}
