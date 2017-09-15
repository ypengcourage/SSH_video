package com.zhiyou100.video.ssh.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.ssh.dao.VideoDao;
import com.zhiyou100.video.ssh.model.Video;
import com.zhiyou100.video.ssh.service.VideoService;
@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	VideoDao vd;

	@Override
	public List<Video> findVideoByPage(DetachedCriteria dc, Integer page) {
		return vd.findVideoByPage(dc,page);
	}

	@Override
	public Integer findVideoCount(DetachedCriteria dc) {
		return vd.findVideoCount(dc);
	}

	@Override
	public void addVideo(Video video) {
		vd.addVideo(video);
	}

	@Override
	public Video findVideoById(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return vd.findVideoById(dc);
	}

	@Override
	public void updateVideo(Video video) {
		vd.updateVideo(video);
		
	}

	@Override
	public void deleteVideo(DetachedCriteria dc) {
		vd.deleteVideo(dc);
	}

	@Override
	public void deleteAll(DetachedCriteria dc) {
		vd.deleteAll(dc);
		
	}

	@Override
	public void updateVideoPlayTimes(Integer videoId) {
		DetachedCriteria dc = DetachedCriteria.forClass(Video.class);
		dc.add(Restrictions.eq("id", videoId));
		Video video = vd.findVideoById(dc);
		video.setVideoPlayTimes(video.getVideoPlayTimes()+1);
		vd.updateVideo(video);
	}
}
