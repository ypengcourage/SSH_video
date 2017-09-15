package com.zhiyou100.video.ssh.web.action;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhiyou100.video.ssh.model.Course;
import com.zhiyou100.video.ssh.model.Speaker;
import com.zhiyou100.video.ssh.model.Subject;
import com.zhiyou100.video.ssh.model.Video;
import com.zhiyou100.video.ssh.service.CourseService;
import com.zhiyou100.video.ssh.service.SpeakerService;
import com.zhiyou100.video.ssh.service.VideoService;
import com.zhiyou100.video.ssh.utils.Timetransfor;

@Scope("prototype")
@Controller("frontVideoAction")
public class FrontVideoAction extends ActionSupport{
	@Autowired
	CourseService cs;
	@Autowired
	VideoService vs;
	@Autowired
	SpeakerService ss;
	private Integer subjectId;
	private Integer videoId;
	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	
	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String index(){
		DetachedCriteria dc = DetachedCriteria.forClass(Subject.class);
		dc.add(Restrictions.idEq(subjectId));
		Subject subject = cs.findAllSubject(dc).get(0);
		ActionContext.getContext().put("subject", subject);
		return SUCCESS;
	}
	public String data(){
		System.out.println("*-*-*----------*-***************");
		DetachedCriteria dc = DetachedCriteria.forClass(Video.class);
		dc.add(Restrictions.idEq(videoId));
		Video video = vs.findVideoById(dc);
		System.out.println("1"+video);
		Speaker speaker = video.getSpeaker();
		System.out.println("2"+speaker);
		Course course = video.getCourse();
		System.out.println("3"+course);
		Set<Video> videoSet = course.getVideoSet();
		System.out.println("4"+videoSet);
		for (Video video2 : videoSet) {
			video2.setVideoLengthstr(Timetransfor.timeTransforto(video2.getVideoLength()));
		}
		ActionContext.getContext().put("videoList", videoSet);
		ActionContext.getContext().put("video", video);
		ActionContext.getContext().put("course", course);
		ActionContext.getContext().put("speaker", speaker);
		subjectId = course.getSubject().getId();
		System.out.println("*-*-*----------*-***************");
		return "success1";
	}
}
