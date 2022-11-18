package com.zzxy.pj.sys.service;

import java.util.List;
import java.util.Map;

import com.zzxy.pj.common.entity.Node;
import com.zzxy.pj.sys.entity.SysMenu;

public interface SysMenuService {
	
	/**
	 * 找所有菜单及父菜单的名称
	 * @return
	 */
	public List<Map<String, Object>> findObjects();
	
	/**
	 * 根据id删除菜单
	 * @param menuId
	 * @return
	 */
	Integer deleteMenuById(Integer menuId);
	
	List<Node> findZtreeMenuNode();

	/**
	 * 添加菜单
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
	 * 通过用户的id查找所有菜单权限
	 * @param id
	 * @return
	 */
	List<String> findPermissionsByUserId(Integer id);
}
