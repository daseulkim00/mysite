package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;
import com.douzone.web.util.MvcUtil;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("list".equals(actionName)) {
			action = new ListAction();
		} else if ("write".equals(actionName)) {
			action = new WriteAction(); // 확인눌럿을때 여기로 가야한다.
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction(); // 확인눌럿을때 여기로 가야한다.
		}else if ("delete".equals(actionName)) {
			action = new DeleteAction(); // 확인눌럿을때 여기로 가야한다.
		}else {
			action = new ListAction();
		}

		return action;
	}

}
