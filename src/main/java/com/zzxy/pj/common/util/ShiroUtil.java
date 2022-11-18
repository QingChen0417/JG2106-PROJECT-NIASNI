package com.zzxy.pj.common.util;

import org.apache.shiro.SecurityUtils;

import com.zzxy.pj.sys.entity.SysUser;

public class ShiroUtil {

	/*
	 * 在Shiro中管理登录对象的类型是
	 * Subject
	 */
	
	/**
	 * 获取登录对象
	 * @return
	 */
	public static SysUser getLoginUser() {
		//(SysUser)SecurityUtils.getSubject(): 获取subject实现对象
		//Subject: 管理主体对象即登录对象
		//subject.getPrincipal();获取登录对象
		return (SysUser)SecurityUtils.getSubject().getPrincipal();
	}
	
	/**
	 * 获取登录的用户名
	 * @return
	 */
	public static String getUsername() {
		return getLoginUser().getUsername();
	}
}
