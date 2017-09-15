package com.zhiyou100.video.ssh.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhiyou100.video.ssh.model.Course;
import com.zhiyou100.video.ssh.model.Video;
import com.zhiyou100.video.ssh.service.AdminService;
import com.zhiyou100.video.ssh.service.CourseService;
@Scope("prototype")
@Controller("statAction")
public class AdminStatAction extends ActionSupport{
	
	
	
	@Autowired
	CourseService cs;
	
	@Autowired
	AdminService as;
	
	
	public String show(){
		List<Course> list = cs.findAllCourse();
		List<String> li1 = new ArrayList<>();
		List<Double> li2 = new ArrayList<>();
		for (Course course : list) {
			if(course.getCourseName() != null){
				li1.add(course.getCourseName());
				Integer s = 0;
				Integer t = 0;
				Set<Video> set = course.getVideoSet();
				for (Video video : set) {
					Integer a = video.getVideoPlayTimes();
					s=s+a;
					t++;
				}
				Double b=(double) (s/t);
				li2.add(b);
			}
		}
		String str1 = as.listToArray(li1);
		String str2 = as.listToArray1(li2);
		ActionContext.getContext().put("courseName",str1);
		ActionContext.getContext().put("times",str2);
		return SUCCESS;
	}
}
