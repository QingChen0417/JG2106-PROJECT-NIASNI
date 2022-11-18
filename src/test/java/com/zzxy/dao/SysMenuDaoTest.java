package com.zzxy.dao;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zzxy.pj.common.entity.Node;
import com.zzxy.pj.sys.dao.SysMenuDao;
import com.zzxy.pj.sys.dao.SysRoleMenuDao;
import com.zzxy.pj.sys.entity.SysMenu;

@SpringBootTest
public class SysMenuDaoTest {

	@Autowired
	private SysMenuDao dao;
	@Autowired
	private SysRoleMenuDao rmDao;
	
	@Test
	public void findObjectTest() {
		List<Map<String,Object>> list = dao.findObjects();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	@Test
	public void getCountChildByMenuIdTest() {
		Integer n = dao.getCountChildByMenuId(8);
		System.out.println(n);
	}
	
	@Test
	public void findZtreeMenuNodeTest() {
		List<Node> list = dao.findZtreeMenuNode();
		for (Node node : list) {
			System.out.println(node);
		}
	}
	
	@Test
	public void insertMenuTest() {
		Integer n = dao.insertMenu(new SysMenu(null, "宿舍管理", "ss", 1, 200, null, 0, "aaa", null, null, null, null));
		System.out.println(n);
	}
	
	@Test
	public void test01() {
		Integer [] arr = {47, 48};
		List<Integer> list = rmDao.findMenuIdsByRoleIds(arr);
		for (Integer integer : list) {
			System.out.println(integer);
		}
		Integer [] arr2 = {8, 45, 46};
		List<String> list2 = dao.findPermissions(arr2);
		for (String string : list2) {
			System.out.println(string);
		}
	}
}
