package com.zhiyou100.video.ssh.dao;

import org.hibernate.criterion.DetachedCriteria;

import com.zhiyou100.video.ssh.model.Admin;

public interface AdminDao {

	Admin findAdmin(DetachedCriteria dc);

}
