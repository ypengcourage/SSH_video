package com.zhiyou100.video.ssh.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.ssh.dao.SpeakerDao;
import com.zhiyou100.video.ssh.model.Speaker;
import com.zhiyou100.video.ssh.service.SpeakerService;
@Service
public class SpeakerServiceImpl implements SpeakerService {
	@Autowired
	SpeakerDao sd;

	@Override
	public List<Speaker> findAllSpeaker() {
		// TODO Auto-generated method stub
		return sd.findAllSpeaker();
	}

	@Override
	public Speaker findSpeakerById(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return sd.findSpeakerById(dc);
	}

	@Override
	public List<Speaker> findSpeakerByPage(DetachedCriteria dc, Integer page) {
		return sd.findSpeakerByPage(dc,page);
	}

	@Override
	public Integer findSpeakerCount(DetachedCriteria dc) {
		
		return sd.findSpeakerCount(dc);
	}

	@Override
	public void addSpeaker(Speaker speaker) {
		sd.addSpeaker(speaker);
	}

	@Override
	public void updateSpeaker(Speaker speaker) {
		sd.updateSpeaker(speaker);
	}

	@Override
	public void deleteSpeaker(DetachedCriteria dc) {
		sd.deleteSpeaker(dc);
	}
}
