package com.zzxy.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zzxy.pj.sys.dao.SysRoleDao;
import com.zzxy.pj.sys.entity.SysRole;

@SpringBootTest
public class SysRoleDaoTest {
	
	@Autowired
	private SysRoleDao dao;
	
	@Test
	public void findRoleTest() {
		List<SysRole> list = dao.findRole(null,0, 10);
		System.out.println(list);
	}
	
	@Test
	public void countRoleTest() {
		Integer countRole = dao.countRole("运维");
		System.out.println(countRole);
	}
	
}
