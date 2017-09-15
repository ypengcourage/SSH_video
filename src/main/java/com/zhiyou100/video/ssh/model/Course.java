package com.zhiyou100.video.ssh.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Course {
    private Integer id;

    private Integer subject_id;
    
    private String courseName;

    private String courseDescr;

    private Date insertTime;

    private Date updateTime;

    private Subject subject;

    private Set<Video> videoSet = new HashSet<>();
    
	public Set<Video> getVideoSet() {
		return videoSet;
	}

	public void setVideoSet(Set<Video> videoSet) {
		this.videoSet = videoSet;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescr() {
		return courseDescr;
	}

	public void setCourseDescr(String courseDescr) {
		this.courseDescr = courseDescr;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", subject_id=" + subject_id + ", courseName=" + courseName + ", courseDescr="
				+ courseDescr + ", insertTime=" + insertTime + ", updateTime=" + updateTime + ", subject=" + subject
				+ "]";
	}
	
}