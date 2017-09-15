package com.zhiyou100.video.ssh.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhiyou100.video.ssh.dao.SpeakerDao;
import com.zhiyou100.video.ssh.model.Speaker;

public class SpeakerDaoImpl extends HibernateDaoSupport implements SpeakerDao {

	@Override
	public List<Speaker> findAllSpeaker() {
		// TODO Auto-generated method stub
		return (List<Speaker>) getHibernateTemplate().find("from Speaker");
	}

	@Override
	public Speaker findSpeakerById(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		List<Speaker> list = (List<Speaker>) getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}

	@Override
	public List<Speaker> findSpeakerByPage(DetachedCriteria dc, Integer page) {
		List<Speaker> list = (List<Speaker>) getHibernateTemplate().findByCriteria(dc,(page-1)*5,5);
		return list;
	}

	@Override
	public Integer findSpeakerCount(DetachedCriteria dc) {
		List<Speaker> list = (List<Speaker>) getHibernateTemplate().findByCriteria(dc);
		return list.size();
	}

	@Override
	public void addSpeaker(Speaker speaker) {
		speaker.setInsertTime(new Timestamp(System.currentTimeMillis()));
		getHibernateTemplate().save(speaker);
	}

	@Override
	public void updateSpeaker(Speaker speaker) {
		DetachedCriteria dc = DetachedCriteria.forClass(Speaker.class);
		dc.add(Restrictions.eq("id", speaker.getId()));
		Speaker speaker1 = (Speaker) getHibernateTemplate().findByCriteria(dc).get(0);
		speaker1.setSpeakerDescr(speaker.getSpeakerDescr());
		speaker1.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		speaker1.setSpeakerHeadUrl(speaker.getSpeakerHeadUrl());
		speaker1.setSpeakerJob(speaker.getSpeakerJob());
		speaker1.setSpeakerName(speaker.getSpeakerName());
	}

	@Override
	public void deleteSpeaker(DetachedCriteria dc) {
		List<Speaker> list = (List<Speaker>) getHibernateTemplate().findByCriteria(dc);
		Speaker speaker = list.get(0);
		getHibernateTemplate().delete(speaker);
	}

}
