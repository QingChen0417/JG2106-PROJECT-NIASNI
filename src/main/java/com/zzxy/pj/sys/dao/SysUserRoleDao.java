package com.zzxy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * 用户角色Dao层
 * @author lenovo
 *
 */
@Mapper
public interface SysUserRoleDao {
	
	/**
	 * 通过角色id删除用户角色关系数据
	 * @param roleId
	 * @return
	 */
	@Delete("delete from sys_user_roles where role_id = #{roleId}")
	Integer deleteUserRoleByRoleId(Integer roleId);

	/**
	 * 添加用户角色关系信息
	 * @param id
	 * @param roles
	 */
	Integer insertUserRole(@Param("id") Integer id, @Param("roles") Integer[] roles);

	@Select("select role_id from sys_user_roles where user_id = #{userId}")
	List<Integer> findRoleByUserId(Integer userId);

	/**
	 * 通过用户id删除用户角色关系数据
	 * @param id
	 */
	@Delete("delete from sys_user_roles where user_id = #{id}")
	Integer deleteUserRoleByUserId(Integer id);
}
