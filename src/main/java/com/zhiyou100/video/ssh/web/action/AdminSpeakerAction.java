package com.zhiyou100.video.ssh.web.action;

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
import com.zhiyou100.video.ssh.model.Speaker;
import com.zhiyou100.video.ssh.service.SpeakerService;
import com.zhiyou100.video.ssh.utils.Page;

@Scope("prototype")
@Controller("speakerAction")
@SuppressWarnings("all")
public class AdminSpeakerAction extends ActionSupport implements ModelDriven<Speaker> {
	@Autowired
	SpeakerService ss;
	
	private Speaker speaker = new Speaker();
	
	private Integer page;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String list(){
		page = page == null ? 1 : page;
		String speakerName = speaker.getSpeakerName();
		String speakerJob = speaker.getSpeakerJob();
		DetachedCriteria dc = DetachedCriteria.forClass(Speaker.class);
		speakerName = speakerName == null ? "":speakerName;
		speakerJob = speakerJob == null ? "":speakerJob;
		if(speakerName!=""){
			dc.add(Restrictions.like("speakerName", speakerName, MatchMode.ANYWHERE));
		}
		if(speakerJob != ""){
			dc.add(Restrictions.like("speakerJob", speakerJob, MatchMode.ANYWHERE));
		}
		Integer count = ss.findSpeakerCount(dc);
		List<Speaker> list = ss.findSpeakerByPage(dc,page);
		Page<Speaker> ppp = new Page<>();
		ppp.setRows(list);
		ppp.setPage(page);
		ppp.setSize(5);
		ppp.setTotal(count);
		ActionContext.getContext().put("page1", ppp);
		ActionContext.getContext().put("speaker_name", speakerName);
		ActionContext.getContext().put("speaker_job", speakerJob);
		return SUCCESS;
	}

	public String toAdd(){
		return "toAdd";
	}
	public String add(){
		ss.addSpeaker(speaker);
		return "toList";
	}
	
	public String toUpdate(){
		Integer id = speaker.getId();
		DetachedCriteria dc = DetachedCriteria.forClass(Speaker.class);
		dc.add(Restrictions.idEq(id));
		Speaker ssss = ss.findSpeakerById(dc);
		ActionContext.getContext().put("speaker1", ssss);
		return "toUpdate";
	}
	public String update(){
		ss.updateSpeaker(speaker);
		return "toList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Speaker getModel() {
		return speaker;
	}
	
}
