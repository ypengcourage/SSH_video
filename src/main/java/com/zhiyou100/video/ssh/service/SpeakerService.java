package com.zhiyou100.video.ssh.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhiyou100.video.ssh.model.Speaker;

public interface SpeakerService {

	List<Speaker> findAllSpeaker();

	Speaker findSpeakerById(DetachedCriteria dc);

	List<Speaker> findSpeakerByPage(DetachedCriteria dc, Integer page);

	Integer findSpeakerCount(DetachedCriteria dc);

	void addSpeaker(Speaker speaker);

	void updateSpeaker(Speaker speaker);

	void deleteSpeaker(DetachedCriteria dc);

}
