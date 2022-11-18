package com.zzxy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zzxy.pj.common.entity.Node;
import com.zzxy.pj.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
	
	/**
	 * 查找所有菜单及父菜单
	 * @return
	 */
	List<Map<String, Object>> findObjects();
	
	/**
	 * 根据父菜单id查找子菜单的数量
	 * @param menuId
	 * @return
	 */
	Integer getCountChildByMenuId(Integer menuId);

	/**
	 * 根据菜单id删除菜单
	 * @param menuId
	 * @return
	 */
	Integer deleteMenuById(Integer menuId);
	
	/**
	 * 查找菜单id, 菜单名和父菜单id
	 * @return
	 */
	List<Node> findZtreeMenuNode();
	
	/**
	 * 插入菜单
	 * @param menu
	 * @return
	 */
	Integer insertMenu(SysMenu menu);

	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	Integer updateMenu(SysMenu menu);
	
	/**
	 * 通过菜单id找所有授权标识
	 * @param menuIds 菜单id
	 * @return
	 */
	List<String> findPermissions(Integer[] menuIds);

	/**
	 * 通过用户id查找所有授权标识
	 * @param userId
	 * @return
	 */
	List<String> findPermissionsByUserId(Integer userId);
}
