package com.sds.icto.mysite.action.guestbook;

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
		if("insert".equals(action)){
			a = new InsertAction();
		}else if("deleteform".equals(action)){
			a = new DeleteFormAction();
		}else if("delete".equals(action)){
				a = new DeleteAction();
		}else{
			a = new ListAction();
		}
		return a;
	}
	
	
}
