package com.zhiyou100.video.ssh.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhiyou100.video.ssh.model.Admin;

public class AdminInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation Invocation) throws Exception {
		Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
		if(admin==null){
			return "success";
		}else{
			return Invocation.invoke();
		}
	}

}
