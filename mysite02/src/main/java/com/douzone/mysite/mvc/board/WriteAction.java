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

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");	
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		////////////////////////////////////////////////////////////////////
		
		System.out.println("dsfddfsdfsdf" + request.getParameter("reply"));
		Long reply;  // reply = no
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long userNo = authUser.getNo();
		
		// reply(no)의 값이 없으면 첫 글이고 값이 잇으면 댓글로 넘어간다. 글을 안쓰면 글넘버(no)가 없지
		if(request.getParameter("reply") == null || request.getParameter("reply").isEmpty()) {
			System.out.println("reply 없음, 글쓰기 작성");
			
			BoardVo vo = new BoardVo();         
			vo.setTitle(title);
			vo.setContents(content);
			vo.setUserNo(userNo);
			
			new BoardDao().insert(vo);
			
		}else {
			reply = Long.parseLong(request.getParameter("reply"));  //문자로 들어오는 no를 long 타입으로 변경해주기 위해서
			System.out.println("reply 있음, 댓글쓰기 작성");
			System.out.println(authUser.getNo());
			System.out.println(reply);
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(content);
			vo.setUserNo(userNo);
			vo.setNo(reply);
			new BoardDao().insertreply(vo);
			
		}
		
		MvcUtil.redirect(request.getContextPath() + "/board?g=list", request, response);
			
		}
		
		
		
	}


