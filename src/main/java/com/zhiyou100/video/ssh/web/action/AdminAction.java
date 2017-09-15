package com.zhiyou100.video.ssh.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhiyou100.video.ssh.model.Admin;
import com.zhiyou100.video.ssh.service.AdminService;
import com.zhiyou100.video.ssh.utils.MD5Utils;
@Scope("prototype")
@Controller("adminAction")
public class AdminAction extends ActionSupport implements ModelDriven<Admin>{
	@Autowired
	AdminService as;
	
	private Admin admin = new Admin();
	
	public String toLogin(){
		return SUCCESS;
	}
	
	public String login(){
		DetachedCriteria dc = DetachedCriteria.forClass(Admin.class);
		dc.add(Restrictions.eq("loginName", admin.getLoginName())).add(Restrictions.eq("loginPwd", MD5Utils.getMD5(admin.getLoginPwd())));
		Admin ad = as.findAdmin(dc);
		if(ad==null){
			addActionError("用户名或密码错误");
			return SUCCESS;
		}else{
			ActionContext.getContext().getSession().put("admin", ad);
			return "success1";
		}
	}
	public String loginout(){
		ActionContext.getContext().getSession().remove("admin");
		return SUCCESS;
	}

	@Override
	public Admin getModel() {
		return admin;
	}
}
