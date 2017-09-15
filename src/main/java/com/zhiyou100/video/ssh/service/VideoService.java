package com.zhiyou100.video.ssh.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhiyou100.video.ssh.model.Video;

public interface VideoService {

	List<Video> findVideoByPage(DetachedCriteria dc, Integer page);

	Integer findVideoCount(DetachedCriteria dc);

	void addVideo(Video video);

	Video findVideoById(DetachedCriteria dc);

	void updateVideo(Video video);

	void deleteVideo(DetachedCriteria dc);

	void deleteAll(DetachedCriteria dc);

	void updateVideoPlayTimes(Integer videoId);

}
