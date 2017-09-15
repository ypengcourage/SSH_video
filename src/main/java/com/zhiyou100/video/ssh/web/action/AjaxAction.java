package com.zhiyou100.video.ssh.web.action;

import java.util.Random;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhiyou100.video.ssh.model.Course;
import com.zhiyou100.video.ssh.model.Result;
import com.zhiyou100.video.ssh.model.Speaker;
import com.zhiyou100.video.ssh.model.User;
import com.zhiyou100.video.ssh.model.Video;
import com.zhiyou100.video.ssh.service.CourseService;
import com.zhiyou100.video.ssh.service.SpeakerService;
import com.zhiyou100.video.ssh.service.UserService;
import com.zhiyou100.video.ssh.service.VideoService;
import com.zhiyou100.video.ssh.utils.MD5Utils;
import com.zhiyou100.video.ssh.utils.RandomCode;
import com.zhiyou100.video.ssh.utils.SendMailUtil;
@Controller("ajaxAction")
@Scope("prototype")
public class AjaxAction  extends ActionSupport implements ModelDriven<User>{
	@Autowired
	CourseService cs;
	@Autowired
	VideoService vs;
	@Autowired
	SpeakerService ss;
	@Autowired
	UserService us;
	private Integer videoId;
	
	private Result result;
	
	private User user = new User();

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String deleteCourse(){
		DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
		dc.add(Restrictions.idEq(user.getId()));
		cs.deleteCrouseById(dc);
		result = new Result();
		result.setSuccess(true);
		System.out.println(result);
		return SUCCESS;
	}
	public String deleteVideo(){
		System.out.println("***********---------------**************");
		DetachedCriteria dc = DetachedCriteria.forClass(Video.class);
		dc.add(Restrictions.idEq(user.getId()));
		vs.deleteVideo(dc);
		result = new Result();
		result.setSuccess(true);
		System.out.println(result);
		return SUCCESS;
	}
	public String deleteSpeaker(){
		DetachedCriteria dc = DetachedCriteria.forClass(Speaker.class);
		dc.add(Restrictions.idEq(user.getId()));
		ss.deleteSpeaker(dc);
		result = new Result();
		result.setSuccess(true);
		System.out.println(result);
		return SUCCESS;
	}
	public String userLogin(){
		user.setPassword(MD5Utils.getMD5(user.getPassword()));
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("email", user.getEmail())).add(Restrictions.eq("password", user.getPassword()));
		User uu = us.login(dc);
		//System.out.println("*/*/*/-*---------"+uu);
		result = new Result();
		if(uu!=null){
			result.setSuccess(true);
			ActionContext.getContext().getSession().put("_front_user", uu);
		}else{
			result.setMessage("邮箱或密码错误");
		}
		return SUCCESS;
	}
	public String userRegist(){
		user.setPassword(MD5Utils.getMD5(user.getPassword()));
		//System.out.println(user.getEmail());
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("email", user.getEmail()));
		Integer a = us.findIdByEmail(dc);
		//System.out.println(a+"-----");
		result = new Result();
		if(a!=0){
			result.setMessage("邮箱已被注册,你可以去找回密码");
		}else{
			us.addUser(user);
			result.setSuccess(true);
		}
		return SUCCESS;
	}

	
	
	public String sendEmail() throws Exception{
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("email", user.getEmail()));
		User uu = us.login(dc);
		result = new Result();
		if(uu!=null){
			String captcha = RandomCode.getRandomCode()+"";
			uu.setCaptcha(captcha);
			us.updateUserCapthca(uu,captcha);
			SendMailUtil.send(user.getEmail(), "智游集团", "智游集团验证码:"+uu.getCaptcha()+",不要告诉其他人哦!");
			result.setSuccess(true);
		}else{
			result.setMessage("请检查邮箱,该邮箱尚未注册");
		}
		return SUCCESS;
	}
	
	public String state() throws Exception{
		vs.updateVideoPlayTimes(videoId);
		return SUCCESS;
	}
	
	
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
}
