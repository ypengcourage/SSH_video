package com.zhiyou100.video.ssh.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhiyou100.video.ssh.dao.VideoDao;
import com.zhiyou100.video.ssh.model.Video;
@SuppressWarnings("all")
public class VideoDaoImpl extends HibernateDaoSupport implements VideoDao {


	@Override
	public List<Video> findVideoByPage(DetachedCriteria dc, Integer page) {
		// TODO Auto-generated method stub
		return (List<Video>) getHibernateTemplate().findByCriteria(dc,(page-1)*5,5);
	}

	@Override
	public Integer findVideoCount(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().findByCriteria(dc).size();
	}

	@Override
	public void addVideo(Video video) {
		video.setVideoPlayTimes(0);
		video.setInsertTime(new Timestamp(System.currentTimeMillis()));
		getHibernateTemplate().save(video);
	}

	@Override
	public Video findVideoById(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		List<Video> li = (List<Video>) getHibernateTemplate().findByCriteria(dc);
		return li.get(0);
	}

	@Override
	public void updateVideo(Video video) {
		video.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		getHibernateTemplate().update(video);
	}

	@Override
	public void deleteVideo(DetachedCriteria dc) {
		List<Video> li = (List<Video>) getHibernateTemplate().findByCriteria(dc);
		System.out.println(li);
		Video vv = li.get(0);
		getHibernateTemplate().delete(vv);
		System.out.println(vv);
	}

	@Override
	public void deleteAll(DetachedCriteria dc) {
		List<Video> li = (List<Video>) getHibernateTemplate().findByCriteria(dc);
		getHibernateTemplate().deleteAll(li);
	}

}
