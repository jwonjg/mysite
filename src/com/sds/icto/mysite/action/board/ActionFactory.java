package com.sds.icto.mysite.action.board;

import com.sds.icto.mysite.servlet.action.Action;

public class ActionFactory {
	private static ActionFactory instance;
	
	private ActionFactory(){};
	
	public static ActionFactory getInstance() {
		if(instance == null){
			instance = new ActionFactory();
		}
		return instance;
	}
	
	public Action getAction(String action){
		Action a = null;
		if("insert".equals(action)){
			a = new InsertAction();
		}else if("detail".equals(action)){
			a = new DetailAction();
		}else if("delete".equals(action)){
			a = new DeleteAction();
		}else if("updateForm".equals(action)){
			a = new UpdateFormAction();
		}else if("update".equals(action)){
			a = new UpdateAction();
		}else if("comment".equals(action)){
			a = new CommentAction();
		}else if("deleteComment".equals(action)){
			a = new DeleteCommentAction();
		}else if("search".equals(action)){
			a = new SearchAction();
		}else{
			a = new ListAction();
		}
		return a;
	}
}
