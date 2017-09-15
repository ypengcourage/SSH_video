package com.zhiyou100.video.ssh.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.ssh.dao.UserDao;
import com.zhiyou100.video.ssh.model.User;
import com.zhiyou100.video.ssh.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao ud;

	@Override
	public User login(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return ud.login(dc);
	}

	@Override
	public Integer findIdByEmail(DetachedCriteria dc) {
		
		return ud.findIdByEmail(dc);
	}

	@Override
	public void addUser(User user) {
		ud.addUser(user);
	}

	@Override
	public void updateUserProfile(User uu) {
		ud.updateUserProfile(uu);
		
	}

	@Override
	public void updateUserHeadUrl(DetachedCriteria dc, String filename) {
		ud.updateUserHeadUrl(dc,filename);
	}

	@Override
	public void updateUserPwd(Integer id, String newPassword) {
		ud.updateUserPwd(id,newPassword);
		
	}

	@Override
	public void updateUserCapthca(User uu, String captcha) {
		ud.updateUserCapthca(uu,captcha);
		
	}
}
