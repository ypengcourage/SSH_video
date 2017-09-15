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
import com.zhiyou100.video.ssh.model.Subject;
import com.zhiyou100.video.ssh.model.Video;
import com.zhiyou100.video.ssh.service.CourseService;
import com.zhiyou100.video.ssh.utils.Timetransfor;

@Scope("prototype")
@Controller("frontCourseAction")
public class FrontCourseAction extends ActionSupport{
	@Autowired
	CourseService cs;
	
	


	private Integer subjectId;
	
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
		DetachedCriteria dcc = DetachedCriteria.forClass(Course.class);
		dcc.add(Restrictions.eq("subject", subject));
		List<Course> li = cs.findCouresBySubject(dcc);
		for (Course course : li) {
			Set<Video> videoSet = course.getVideoSet();
			for (Video video : videoSet) {
				video.setVideoLengthstr(Timetransfor.timeTransforto(video.getVideoLength()));
			}
		}
		ActionContext.getContext().put("courses", li);
		ActionContext.getContext().put("subject", subject);
		return SUCCESS;
	}
}
