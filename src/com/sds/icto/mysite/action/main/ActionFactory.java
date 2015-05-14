package com.sds.icto.mysite.action.main;

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
		a = new IndexAction();
		return a;
	}
	
	
}
