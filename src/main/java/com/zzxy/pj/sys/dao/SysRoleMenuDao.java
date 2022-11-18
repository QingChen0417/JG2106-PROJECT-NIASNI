package com.zzxy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 权限和菜单关系dao层
 * @author lenovo
 *
 */
@Mapper
public interface SysRoleMenuDao {

	/**
	 * 通过菜单id删除角色菜单数据
	 * @param menuId 菜单id
	 * @return
	 */
	Integer deleteRoleMenuId(Integer menuId);
	
	/**
	 * 通过角色id删除接受菜单关系数据
	 * @param roleId
	 * @return
	 */
	@Delete("delete from sys_role_menus where role_id = #{roleId}")
	Integer deleteMenuByRoleId(Integer roleId);

	/**
	 * 插入角色和菜单的关系数据
	 * @param roleId	角色id
	 * @param ids	菜单id数组
	 * @return 
	 */
	Integer insertRoleMenuData(@Param("roleId")Integer roleId,@Param("ids")Integer[] ids);
	
	/**
	 * 根据角色id查找菜单id
	 * @param id
	 * @return
	 */
	List<Integer> findMenuIdByRoleId(Integer id);
	
	/**
	 * 通过角色id数组查找所有菜单id
	 * @param roleIds 角色id数组
	 * @return
	 */
	List<Integer> findMenuIdsByRoleIds(Integer[] roleIds);
	
}
