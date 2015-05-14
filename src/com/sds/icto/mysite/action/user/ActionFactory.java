package com.sds.icto.mysite.action.user;

import com.sds.icto.mysite.action.main.IndexAction;
import com.sds.icto.mysite.servlet.action.Action;

public class ActionFactory {
	private static ActionFactory instance;
	
	private ActionFactory() {}
	
	public static ActionFactory getInstance() {
		if(instance == null) {
			instance = new ActionFactory();
		}
		return instance;
	}

	public Action getAction(String action) {
		Action a = null;

		if("joinform".equals(action)){
			a = new JoinFormAction();
		}else if("join".equals(action)){
			a = new JoinAction();
		}else if("joinsuccess".equals(action)){
			a = new JoinSuccessAction();
		}else if("loginform".equals(action)){
			a = new LoginFormAction();
		}else if("login".equals(action)){
			a = new LoginAction();
		}else if("logout".equals(action)){
			a = new LoginOutAction();
		}else if("uinfoform".equals(action)){
			a = new UserInfoFormAction();
		}else if("uinfo".equals(action)){
			a = new UserInfoAction();
		}else if("uinfosuccess".equals(action)){
			a = new UserInfoSuccessAction();
		}else{
			a = new IndexAction();
		}
		return a;
	}
	
	
}
