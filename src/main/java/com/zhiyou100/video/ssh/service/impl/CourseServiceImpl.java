package com.zhiyou100.video.ssh.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.ssh.dao.CourseDao;
import com.zhiyou100.video.ssh.model.Course;
import com.zhiyou100.video.ssh.model.Subject;
import com.zhiyou100.video.ssh.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseDao cd;

	@Override
	public List<Course> findCourseByPage(DetachedCriteria dc, Integer page) {
		// TODO Auto-generated method stub
		return cd.findCourseByPage(dc,page);
	}

	@Override
	public Integer findcount(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return cd.findcount(dc);
	}

	@Override
	public List<Subject> findAllSubject(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return cd.findAllSubject(dc);
	}

	@Override
	public void addCourse(Course course) {
		cd.addCourse(course);
		
	}



	@Override
	public Course findCourseById(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return cd.findCourseById(dc);
	}

	@Override
	public void updateCourse(Course course) {
		cd.updateCourse(course);
	}

	@Override
	public Subject findSubjectById(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return cd.findSubjectById(dc);
	}

	@Override
	public void deleteCrouseById(DetachedCriteria dc) {
		cd.deleteCrouseById(dc);
	}

	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return cd.findAllCourse();
	}

	@Override
	public Course findCourseById1(Integer course_id) {
		// TODO Auto-generated method stub
		return cd.findCourseById1(course_id);
	}

	@Override
	public List<Course> findCouresBySubject(DetachedCriteria dcc) {
		return cd.findCouresBySubject(dcc);
	}


}
