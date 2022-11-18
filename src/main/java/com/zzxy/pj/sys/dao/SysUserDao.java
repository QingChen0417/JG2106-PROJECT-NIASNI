package com.zzxy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.zzxy.pj.sys.entity.SysUser;
import com.zzxy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {

	/**
	 * 通过名字找用户总条数
	 * @param username
	 * @return
	 */
	Integer countAllUser(String username);

	/**
	 * 通过名字找用户信息(模糊查询)
	 * @param username
	 * @param start
	 * @param pageSize
	 * @return
	 */
	List<SysUserDeptVo> findUserDeptVo(@Param("username") String username, @Param("start") int start, @Param("pageSize") int pageSize);

	/**
	 * 根据id修改用户状态
	 * @param id 		用户id
	 * @param valid		用户状态
	 * @param string 
	 * @return
	 */
	@Update("update sys_users set valid = #{valid}, modifiedUser = #{modifiedUser} where id = #{id}")
	Integer updateValid(@Param("id")Integer id, @Param("valid")Integer valid,@Param("modifiedUser") String modifiedUser);

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	Integer insertUser(SysUser user);

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	SysUser findUserByName(String username);

	/**
	 * 根据用户id修改用户信息
	 * @param user
	 * @return
	 */
	Integer updateUser(SysUser user);
	
}
