package com.zhiyou100.video.ssh.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhiyou100.video.ssh.model.User;

public class Frontinterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation Invocation) throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("_front_user");
		if(user==null){
			return "success1";
		}else{
			return Invocation.invoke();
		}
	}

}
