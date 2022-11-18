package com.zzxy.pj.sys.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzxy.pj.common.annotation.RequireLog;
import com.zzxy.pj.common.entity.JsonResult;
import com.zzxy.pj.common.entity.Pagination;
import com.zzxy.pj.sys.entity.SysUser;
import com.zzxy.pj.sys.service.SysUserService;

@RestController
@RequestMapping("user")
public class SysUserController {
	
	@Autowired
	private SysUserService userService;

	@RequestMapping("findUserDeptVo")
	public JsonResult findUserDeptVo(String username,@RequestParam(defaultValue="1") Integer curPage,@RequestParam(defaultValue="10") Integer pageSize) {
		Pagination pageObj = userService.findUserDeptVo(username, curPage, pageSize);
		return new JsonResult(pageObj);
	}
	
	@RequestMapping("updateValid")
	public JsonResult updateValid(Integer id, Integer valid) {
		userService.updateValid(id, valid);
		return new JsonResult("修改成功");
	}
	
	@RequestMapping("saveUser")
	public JsonResult saveUser(SysUser user, @RequestParam(required = false, value = "roleIds[]") Integer [] roleIds) {
		userService.insertUser(user, roleIds);
		return new JsonResult("添加成功");
	}
	
	@RequestMapping("findRoleByUserId")
	public JsonResult findRoleByUserId(Integer userId) {
		List<Integer> list = userService.findRoleByUserId(userId);
		return new JsonResult(list);
	}
	
	@RequestMapping("updateUser")
	//@RequestParam:加了该注解要求必须传值, required为false时可以不传值
	public JsonResult updateUser(SysUser user, @RequestParam(required = false, value = "roleIds[]") Integer[] roleIds) {
		userService.updateUser(user, roleIds);
		return new JsonResult("修改成功");
	}
	
	@RequestMapping("doLogin")
	@RequireLog("登录操作")
	public JsonResult doLogin(String username, String password, boolean isRememberMe) {
		//当SecurityManager创建对象时在构造方法中设置了Subject对象
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(isRememberMe);
		subject.login(token);
		return new JsonResult("登录成功");
	}
}
