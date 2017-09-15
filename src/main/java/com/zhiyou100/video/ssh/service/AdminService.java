package com.zhiyou100.video.ssh.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhiyou100.video.ssh.model.Admin;

public interface AdminService {

	Admin findAdmin(DetachedCriteria dc);

	String listToArray(List<String> li1);

	String listToArray1(List<Double> li2);

}
