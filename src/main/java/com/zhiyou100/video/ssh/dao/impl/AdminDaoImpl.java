package com.zhiyou100.video.ssh.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhiyou100.video.ssh.dao.AdminDao;
import com.zhiyou100.video.ssh.model.Admin;

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {

	@Override
	public Admin findAdmin(DetachedCriteria dc) {
		List<Admin> list = (List<Admin>) getHibernateTemplate().findByCriteria(dc);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}
	
}
