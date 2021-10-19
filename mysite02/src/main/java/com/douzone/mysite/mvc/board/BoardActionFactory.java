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
	}else if("writeform".equals(actionName)){
		action = new WriteFormAction();
	}else if("write".equals(actionName)){
		action = new WriteAction();
	}else if("delete".equals(actionName)){
		action = new DeleteAction();
	}else if("view".equals(actionName)){
		action = new ViewAction();
	}else if("modify".equals(actionName)){
		action = new ModifyAction();
	}else if("modifyform".equals(actionName)){
		action = new ModifyFormAction();
	}else if("update".equals(actionName)){
		action = new UpdateAction();
	}else {
		action = new ListAction();
	}
		return action;
	}

}
