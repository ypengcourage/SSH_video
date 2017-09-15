package com.zhiyou100.video.ssh.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhiyou100.video.ssh.dao.UserDao;
import com.zhiyou100.video.ssh.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public User login(DetachedCriteria dc) {
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public Integer findIdByEmail(DetachedCriteria dc) {
		Integer a = 0;
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		if(list.size()!=0){
			a = list.get(0).getId();
		}
		return a;
	}

	@Override
	public void addUser(User user) {
		getHibernateTemplate().save(user);
	}

	@Override
	public void updateUserProfile(User uu) {
		List<User> list = (List<User>) getHibernateTemplate().find("from User where id  = ?", uu.getId());
		User user = list.get(0);
		user.setNickName(uu.getNickName());
		user.setSex(uu.getSex());
		user.setBirthday(uu.getBirthday());
		user.setProvince(uu.getProvince());
		user.setCity(uu.getCity());
	}

	@Override
	public void updateUserHeadUrl(DetachedCriteria dc, String filename) {
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		User user = list.get(0);
		user.setHeadUrl("/pic/"+filename);
	}

	@Override
	public void updateUserPwd(Integer id, String newPassword) {
		List<User> list = (List<User>) getHibernateTemplate().find("from User where id  = ?", id);
		User user = list.get(0);
		user.setPassword(newPassword);
	}

	@Override
	public void updateUserCapthca(User uu, String captcha) {
		List<User> list = (List<User>) getHibernateTemplate().find("from User where id  = ?", uu.getId());
		User user = list.get(0);
		user.setCaptcha(captcha);
	}

}
