package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardDto;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ------페이징 처리-----------
		
		// 페이징 처리를 위한 변수 선언
		
		int page = 0; 
		
		if(request.getParameter("page") == null || request.getParameter("page").isEmpty()) {
			page = 1;
		}else {
		
		page = Integer.parseInt(request.getParameter("page"));
		}
		int List_size = 5; //한 화면에 표시할 게시물 개수
		int Page_size = 5;
		
		//1. 기본 값
		int totalcnt = new BoardDao().totalcnt(); //총 게시물 갯수
		int pagecnt = (int)Math.ceil(totalcnt/ List_size);
		int blockcnt =(int)Math.ceil(pagecnt/Page_size); // 5개씩 끊어줌
		int currentblock =(int)Math.ceil(page/Page_size);
		
		//2. 셋팅
		if(page > pagecnt) {
			page = pagecnt;
			currentblock =(int)Math.ceil(page/Page_size);
		}
		
		if(page < 1) {
			page = 1;
			currentblock = 1;
		}

		//3. 계산
		int beginPage = currentblock == 0 ? 1 : (currentblock - 1) *Page_size + 1;
										  // (currentblock - 1) *Page_size  = 전 단락의 끝페이지 번호
		int prevPage = (currentblock > 1) ? (currentblock - 1) * Page_size :0;
		
		int nextPage = (currentblock < blockcnt) ? currentblock * Page_size+1 : 0;
		
		int endPage = (nextPage > 0) ? (beginPage - 1 ) + List_size:pagecnt;
		
		int stindex = (page-1)*List_size;
		
		
	 List<BoardDto> list= new BoardDao().findAll(stindex, List_size);
	 	
	 	PageVo pvo = new PageVo();
	 	
	 	pvo.setPage(page);
	 	pvo.setBeginPage(beginPage);
	 	pvo.setBlockcnt(blockcnt);
	 	pvo.setCurrentblock(currentblock);
	 	pvo.setEndPage(endPage);
	 	pvo.setList_size(List_size);
	 	pvo.setNextPage(nextPage);
	 	pvo.setPage_size(Page_size);
	 	pvo.setPagecnt(pagecnt);
	 	pvo.setStindex(stindex);
	 	pvo.setTotalcnt(totalcnt);
	 	pvo.setPrevPage(prevPage);
		
	 	request.setAttribute("pvo", pvo);
		request.setAttribute("list", list);
		MvcUtil.forward("board/list", request, response);

		
		
	}
		
}
