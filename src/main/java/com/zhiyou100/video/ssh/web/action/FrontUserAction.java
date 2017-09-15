package com.zhiyou100.video.ssh.web.action;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhiyou100.video.ssh.model.Result;
import com.zhiyou100.video.ssh.model.User;
import com.zhiyou100.video.ssh.service.UserService;
import com.zhiyou100.video.ssh.utils.MD5Utils;

@Scope("prototype")
@Controller("userAction")
public class FrontUserAction extends ActionSupport implements ModelDriven<User>{
	@Autowired
	UserService us;
	private User uu = new User();
	
	private File image_file;
	
	private String image_fileFilename;
	
	private String oldPassword;
	private String newPassword;
	private Result result;
	
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public File getImage_file() {
		return image_file;
	}
	public void setImage_file(File image_file) {
		this.image_file = image_file;
	}
	public String getImage_fileFilename() {
		return image_fileFilename;
	}
	public void setImage_fileFilename(String image_fileFilename) {
		this.image_fileFilename = image_fileFilename;
	}
	public String index(){
		Integer id = uu.getId();
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("id", id));
		User user = us.login(dc);
		//System.out.println(user);
		ActionContext.getContext().put("user", user);
		return SUCCESS;
	}
	public String logout(){
		ActionContext.getContext().getSession().remove("_front_user");
		return "success1";
	}
	public String toProfile(){
		Integer id = uu.getId();
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("id", id));
		User user = us.login(dc);
		//System.out.println(user);
		ActionContext.getContext().put("user", user);
		return "toProfile";
	}
	public String profile(){
		//System.out.println(uu);
		us.updateUserProfile(uu);
		Integer id = uu.getId();
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("id", id));
		User user = us.login(dc);
		ActionContext.getContext().put("user", user);
		return SUCCESS;
	}
	public String toAvatar(){
		Integer id = uu.getId();
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("id", id));
		User user = us.login(dc);
		ActionContext.getContext().put("user", user);
		return "toAvatar";
	}
	public String avatar() throws IOException{
		Integer id = uu.getId();
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("id", id));
		String string = FilenameUtils.getExtension(image_fileFilename);
		String filename = UUID.randomUUID().toString().replaceAll("-","")+"."+string;
		FileUtils.copyFile(new File(image_file.getAbsolutePath()), new File("D://upload//"+filename));
		us.updateUserHeadUrl(dc,filename);
		User user = us.login(dc);
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().getSession().put("_front_user", user);
		return SUCCESS;
	}
	
	
	public String toPassword(){
		Integer id = uu.getId();
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("id", id));
		User user = us.login(dc);
		ActionContext.getContext().put("user", user);
		return "toPassword";
	}
	public String password(){
		
		Integer id = uu.getId();
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("id", id));
		User user = us.login(dc);
		String password = user.getPassword();
		oldPassword = MD5Utils.getMD5(oldPassword);
		//System.out.println(oldPassword);
		//System.out.println(password);
		if(password.equals(oldPassword)){
			//System.out.println("*-*-*--------------");
			newPassword = MD5Utils.getMD5(newPassword);
			us.updateUserPwd(id,newPassword);
			ActionContext.getContext().getSession().remove("_front_user");
			return "success1";
		}else{
			ActionContext.getContext().put("message", "密码错误,修改失败");
			ActionContext.getContext().put("user", user);
			return "toPassword";
		}
		
	}
	
	public String toForgetpwd(){
		return "toForgetpwd";
		
	}
	public String forgetpwd(){
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("email",uu.getEmail()));
		User user = us.login(dc);
		if(user.getCaptcha().equals(uu.getCaptcha())){
			ActionContext.getContext().put("user", user);
			return "toRestPwd";
		}else{
			ActionContext.getContext().put("message", "验证码错误,请检查");
			return "toForgetpwd";
		}
		
	}
	
	
	
	public String resetpwd(){
		Integer id = uu.getId();
		String password = MD5Utils.getMD5(uu.getPassword());
		us.updateUserPwd(id, password);
		return "success1";
	}
	
	
	
	
	
	@Override
	public User getModel() {
		return uu;
	}
}
