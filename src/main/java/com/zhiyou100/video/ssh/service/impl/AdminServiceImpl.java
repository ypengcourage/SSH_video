package com.zhiyou100.video.ssh.service.impl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.ssh.dao.AdminDao;
import com.zhiyou100.video.ssh.model.Admin;
import com.zhiyou100.video.ssh.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDao ad;

	@Override
	public Admin findAdmin(DetachedCriteria dc) {
		return ad.findAdmin(dc);
	}

	@Override
	public String listToArray(List<String> li1) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\"");
		for(int i = 0;i<li1.size();i++){
			sb.append(li1.get(i));
			if(i == li1.size()-1){
				break;
			}
			sb.append("\",\"");
		}
		sb.append("\"]");
		/*Object[] arr =  li1.toArray();
		String[] arr1 = (String[]) arr;*/
		return sb.toString();
	}

	@Override
	public String listToArray1(List<Double> li2) {
		Object[] arr =  li2.toArray();
		/*StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0;i<li2.size();i++){
			sb.append(li2.get(i));
			if(i == li2.size()-1){
				break;
			}
			sb.append(",");
		}
		sb.append("]");
		//Double[] arr1 = (Double[]) arr;
*/		return Arrays.toString(arr);
	}
}
