package com.zzxy.pj.sys.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zzxy.pj.common.entity.Node;
import com.zzxy.pj.common.util.Assert;
import com.zzxy.pj.sys.dao.SysMenuDao;
import com.zzxy.pj.sys.dao.SysRoleMenuDao;
import com.zzxy.pj.sys.entity.SysMenu;
import com.zzxy.pj.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService{

	@Autowired
	private SysMenuDao dao;
	@Autowired
	private SysRoleMenuDao rmDao;
	
	//@RequiredCache
	@Cacheable("menuCache")//使用缓存注解, 指定名字
	public List<Map<String, Object>> findObjects() {
		List<Map<String,Object>> list = dao.findObjects();
		System.out.println("查找菜单");
		Assert.isEmpty(list == null || list.size() == 0, "没有符合条件的菜单");
		return list;
	}

	@CacheEvict(value = "menuCache", allEntries = true)
	public Integer deleteMenuById(Integer menuId) {
		//第一步: 查看当前菜单id的子菜单数量
		Integer count = dao.getCountChildByMenuId(menuId);
		//第二步: 如果子菜单数量大于0则直接抛出异常
		Assert.isEmpty(count > 0, "有子菜单不允许直接删除");
		//第三步: 如果子菜单数量等于0则删除菜单与权限的关系数据
		rmDao.deleteRoleMenuId(menuId);
		//第四步: 最后根据该id删除菜单
		Integer n = dao.deleteMenuById(menuId);
		Assert.isEmpty(n == 0, "该菜单可能已不存在");
		return n;
	}

	public List<Node> findZtreeMenuNode() {
		List<Node> list = dao.findZtreeMenuNode();
		Assert.isEmpty(list == null || list.size() == 0, "菜单不存在!");
		return list;
	}

	@CacheEvict(value = "menuCache", allEntries = true)
	public Integer insertMenu(SysMenu menu) {
		Assert.isEmpty(menu == null || menu.getName() == null, "菜单名称不能为空");
		Integer n = dao.insertMenu(menu);
		Assert.isEmpty(n == 0, "菜单添加失败");
		return n;
	}
	//清空指定key的缓存, allEntries = true清空Cache
	@CacheEvict(value = "menuCache", allEntries = true)
	public Integer updateMenu(SysMenu menu) {
		Assert.isEmpty(menu == null || menu.getId() == null, "请填写数据");
		Integer n = dao.updateMenu(menu);
		Assert.isEmpty(n == 0, "菜单修改失败或数据已不存在");
		return n;
	}

	public List<String> findPermissionsByUserId(Integer userId) {
	 	List<String> list = dao.findPermissionsByUserId(userId);
		return list;
	}

}
