package com.zhiyou100.video.ssh.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhiyou100.video.ssh.model.Course;
import com.zhiyou100.video.ssh.model.Subject;

public interface CourseDao {

	List<Course> findCourseByPage(DetachedCriteria dc, Integer page);

	Integer findcount(DetachedCriteria dc);

	List<Subject> findAllSubject(DetachedCriteria dc);

	void addCourse(Course course);

	Course findCourseById(DetachedCriteria dc);

	void updateCourse(Course course);

	Subject findSubjectById(DetachedCriteria dc);

	void deleteCrouseById(DetachedCriteria dc);

	List<Course> findAllCourse();

	Course findCourseById1(Integer course_id);

	List<Course> findCouresBySubject(DetachedCriteria dcc);


}
