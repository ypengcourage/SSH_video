package com.zhiyou100.video.ssh.utils;

public class Timetransfor {
	
	public static String timeTransforto(Integer time) {
		if(time == null){
			return "00:00:00";
		}
		int a = time/3600;
		String timestr = "";
		if(a>10){
			timestr = timestr+a+":";
		}else{
			timestr = timestr+"0"+a+":";
		}
		int b = (time - a*3600)/60;
		if(b>10){
			timestr = timestr+b+":";
		}else{
			timestr = timestr+"0"+b+":";
		}
		int c = time - a*3600 - 60*((time - a*3600)/60);
		if(c<10){
			timestr = timestr+"0"+c;
		}else{
			timestr = timestr+c;
		}
		return timestr;
	}
}
