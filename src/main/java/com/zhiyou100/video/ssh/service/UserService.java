package com.zhiyou100.video.ssh.service;

import org.hibernate.criterion.DetachedCriteria;

import com.zhiyou100.video.ssh.model.User;

public interface UserService {

	User login(DetachedCriteria dc);

	Integer findIdByEmail(DetachedCriteria dc);

	void addUser(User user);

	void updateUserProfile(User uu);

	void updateUserHeadUrl(DetachedCriteria dc, String filename);

	void updateUserPwd(Integer id, String newPassword);

	void updateUserCapthca(User uu, String captcha);

}
