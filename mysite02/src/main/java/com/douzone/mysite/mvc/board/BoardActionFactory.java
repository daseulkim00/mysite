package com.douzone.mysite.mvc.board;

import java.util.List;

import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		Action action = null;
		
	 if("list".equals(actionName)) {
		action = new ListAction();
	}else if("wirte".equals(actionName)){
		action = new WriteAction();
	}else {
		action = new ListAction();
	}
		return action;
	}

}
