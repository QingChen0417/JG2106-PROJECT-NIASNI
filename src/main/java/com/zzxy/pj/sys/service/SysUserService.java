package com.zzxy.pj.sys.service;

import java.util.List;

import com.zzxy.pj.common.entity.Pagination;
import com.zzxy.pj.sys.entity.SysUser;

public interface SysUserService {

	Pagination findUserDeptVo(String username, Integer curPage, Integer pageSize);

	/**
	 * 根据id修改用户状态
	 * @param id
	 * @param valid
	 * @return
	 */
	Integer updateValid(Integer id, Integer valid);

	/**
	 * 插入用户
	 * @param user
	 * @param roles
	 * @return
	 */
	Integer insertUser(SysUser user, Integer [] roles);

	/**
	 * 根据用户id查找角色
	 * @param userId
	 * @return
	 */
	List<Integer> findRoleByUserId(Integer userId);

	/**
	 * 通过用户id修改用户信息
	 * @param user
	 * @param roleIds 
	 * @return
	 */
	Integer updateUser(SysUser user, Integer[] roleIds);
}
