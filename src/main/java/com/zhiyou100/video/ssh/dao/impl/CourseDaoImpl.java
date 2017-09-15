package com.zhiyou100.video.ssh.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhiyou100.video.ssh.dao.CourseDao;
import com.zhiyou100.video.ssh.model.Course;
import com.zhiyou100.video.ssh.model.Subject;

public class CourseDaoImpl extends HibernateDaoSupport implements CourseDao {
	@Override
	public List<Course> findCourseByPage(DetachedCriteria dc, Integer page) {
		List<Course> li = (List<Course>) getHibernateTemplate().findByCriteria(dc,(page-1)*5,5);
		return li;
	}

	@Override
	public Integer findcount(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		DetachedCriteria dcc = DetachedCriteria.forClass(Course.class);
		int size = getHibernateTemplate().findByCriteria(dcc).size();
		return size;
	}

	@Override
	public List<Subject> findAllSubject(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return (List<Subject>) getHibernateTemplate().findByCriteria(dc);
	}

	@Override
	public void addCourse(Course course) {
		course.setInsertTime(new Timestamp(System.currentTimeMillis()));
		getHibernateTemplate().save(course);
	}

	@Override
	public Course findCourseById(DetachedCriteria dc) {
		List<Course> list = (List<Course>) getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}

	@Override
	public void updateCourse(Course course) {
		List<Course> list = (List<Course>) getHibernateTemplate().find("from Course where id = ?", course.getId());
		Course course1 = list.get(0);
		course1.setCourseDescr(course.getCourseDescr());
		course1.setCourseName(course.getCourseName());
		course1.setSubject(course.getSubject());
		course1.setUpdateTime(new Timestamp(System.currentTimeMillis()));
	}

	@Override
	public Subject findSubjectById(DetachedCriteria dc) {
		List<Subject> list = (List<Subject>) getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}

	@Override
	public void deleteCrouseById(DetachedCriteria dc) {
		List<Course> list = (List<Course>) getHibernateTemplate().findByCriteria(dc);
		Course course = list.get(0);
		getHibernateTemplate().delete(course);
	}

	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return (List<Course>) getHibernateTemplate().find("from Course");
	}

	@Override
	public Course findCourseById1(Integer course_id) {
		List<Course> list = (List<Course>) getHibernateTemplate().find("from Course where id = ?", course_id);
		return list.get(0);
	}

	@Override
	public List<Course> findCouresBySubject(DetachedCriteria dcc) {
		List<Course> list = (List<Course>) getHibernateTemplate().findByCriteria(dcc);
		return list;
	}


}
