package com.zhiyou100.video.ssh.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhiyou100.video.ssh.model.Admin;
import com.zhiyou100.video.ssh.model.Course;
import com.zhiyou100.video.ssh.model.Subject;
import com.zhiyou100.video.ssh.service.CourseService;
import com.zhiyou100.video.ssh.utils.Page;

@Controller("courseAction")
@Scope("prototype")
public class AdminCourseAction  extends ActionSupport implements ModelDriven<Course>{
	@Autowired
	CourseService cs;
	
	private Course course = new Course();
	
	private Integer page;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String list(){
		DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
		page = page == null ? 1 : page;
		List<Course> li = cs.findCourseByPage(dc,page);
		Integer count = cs.findcount(dc);
		Page<Course> ppp = new Page<>();
		ppp.setPage(page);
		ppp.setRows(li);
		ppp.setSize(5);
		ppp.setTotal(count);
		ActionContext.getContext().put("page1",ppp);
		return SUCCESS;
	}
	public String toAdd(){
		DetachedCriteria dc = DetachedCriteria.forClass(Subject.class);
		List<Subject> list = cs.findAllSubject(dc);
		ActionContext.getContext().put("subject",list);
		return "toAdd";
	}
	public String add(){
		//System.out.println(course);
		Integer id = course.getSubject_id();
		Subject subject = new Subject();
		if(id!=null){
			DetachedCriteria dc = DetachedCriteria.forClass(Subject.class);
			dc.add(Restrictions.idEq(id));
			subject = cs.findSubjectById(dc);
		}
		course.setSubject(subject);
		subject.getCourseSet().add(course);
		cs.addCourse(course);
		return "toList";
	}
	public String toUpdate(){
		//System.out.println("-----------"+course.getId());
		DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
		dc.add(Restrictions.idEq(course.getId()));
		Course course1 = cs.findCourseById(dc);
		//System.out.println("-----------"+course1);
		ActionContext.getContext().put("course1",course1);
		DetachedCriteria dcc = DetachedCriteria.forClass(Subject.class);
		List<Subject> list = cs.findAllSubject(dcc);
		ActionContext.getContext().put("subject",list);
		return "toUpdate";
	}
	public String update(){
		Integer id = course.getSubject_id();
		Subject subject = new Subject();
		if(id!=null){
			DetachedCriteria dc = DetachedCriteria.forClass(Subject.class);
			dc.add(Restrictions.idEq(id));
			subject = cs.findSubjectById(dc);
		}
		course.setSubject(subject);
		subject.getCourseSet().add(course);
		cs.updateCourse(course);
		return "toList";
	}
	@Override
	public Course getModel() {
		return course;
	}
}
