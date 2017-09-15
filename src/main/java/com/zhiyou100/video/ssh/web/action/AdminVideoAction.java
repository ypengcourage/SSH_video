package com.zhiyou100.video.ssh.web.action;

import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhiyou100.video.ssh.model.Course;
import com.zhiyou100.video.ssh.model.Speaker;
import com.zhiyou100.video.ssh.model.Video;
import com.zhiyou100.video.ssh.service.CourseService;
import com.zhiyou100.video.ssh.service.SpeakerService;
import com.zhiyou100.video.ssh.service.VideoService;
import com.zhiyou100.video.ssh.utils.Page;

@Scope("prototype")
@Controller("videoAction")
public class AdminVideoAction extends ActionSupport implements ModelDriven<Video>{
	
	@Autowired
	CourseService cs;
	@Autowired
	VideoService vs;
	@Autowired
	SpeakerService ss;
	
	private Video video = new Video();
	
	private Integer [] xuanze;
	
	public Integer[] getXuanze() {
		return xuanze;
	}

	public void setXuanze(Integer[] xuanze) {
		this.xuanze = xuanze;
	}

	private Integer page;
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String list(){
		page = page == null ? 1 : page;
		List<Course> li1 = cs.findAllCourse();
		List<Speaker> li2 = ss.findAllSpeaker();
		String videoTitle = video.getVideoTitle() == null ? "":video.getVideoTitle();
		Integer course_id = video.getCourse_id() == null ? 0:video.getCourse_id();
		Integer speaker_id = video.getSpeaker_id() == null ? 0:video.getSpeaker_id();
		DetachedCriteria dc = DetachedCriteria.forClass(Video.class);
		dc.add(Restrictions.like("videoTitle", videoTitle, MatchMode.ANYWHERE));
		if(course_id!=0){
			dc.add(Restrictions.eq("course.id", course_id));
		}
		if(speaker_id!=0){
			dc.add(Restrictions.eq("speaker.id", speaker_id));
		}
		Integer count = vs.findVideoCount(dc);
		List<Video> li = vs.findVideoByPage(dc,page);
		Page<Video> pp = new Page<>();
		pp.setPage(page);
		pp.setRows(li);
		pp.setSize(5);
		pp.setTotal(count);
		ActionContext.getContext().put("videoTitle", videoTitle);
		ActionContext.getContext().put("course_id", course_id);
		ActionContext.getContext().put("speaker_id", speaker_id);
		ActionContext.getContext().put("page1", pp);
		ActionContext.getContext().put("listCourse", li1);
		ActionContext.getContext().put("listSpeaker", li2);
		return SUCCESS;
	}


	public String toAdd(){
		List<Course> li1 = cs.findAllCourse();
		List<Speaker> li2 = ss.findAllSpeaker();
		ActionContext.getContext().put("listCourse", li1);
		ActionContext.getContext().put("listSpeaker", li2);
		return "toAdd";
	}
	
	public String add(){
		Integer course_id = video.getCourse_id();
		Integer speaker_id = video.getSpeaker_id();
		if(course_id !=null){
			Course course = cs.findCourseById1(course_id);
			video.setCourse(course);
		}
		if(speaker_id != null){
			DetachedCriteria dc = DetachedCriteria.forClass(Speaker.class);
			dc.add(Restrictions.idEq(speaker_id));
			Speaker speaker = ss.findSpeakerById(dc);
			video.setSpeaker(speaker);
		}
		vs.addVideo(video);
		return "toList";
	}
	public String toUpdate(){
		DetachedCriteria dc = DetachedCriteria.forClass(Video.class);
		dc.add(Restrictions.idEq(video.getId()));
		Video vv = vs.findVideoById(dc);
		List<Course> li1 = cs.findAllCourse();
		List<Speaker> li2 = ss.findAllSpeaker();
		ActionContext.getContext().put("listCourse", li1);
		ActionContext.getContext().put("listSpeaker", li2);
		ActionContext.getContext().put("video1", vv);
		return "toUpdate";
	}
	
	public String update(){
		Integer course_id = video.getCourse_id();
		Integer speaker_id = video.getSpeaker_id();
		
		
		DetachedCriteria dc = DetachedCriteria.forClass(Speaker.class);
		dc.add(Restrictions.idEq(speaker_id));
		Speaker speaker = ss.findSpeakerById(dc);
		
		
		Course course = cs.findCourseById1(course_id);
		
		
		video.setCourse(course);
		video.setSpeaker(speaker);
		
		System.out.println(video);
		vs.updateVideo(video);
		return "toList";
	}
	
	public String deleteAll(){
		DetachedCriteria dc = DetachedCriteria.forClass(Video.class);
		dc.add(Restrictions.in("id", xuanze));
		vs.deleteAll(dc);
		
		return "toList";
	}
	
	@Override
	public Video getModel() {
		return video;
	}
}
