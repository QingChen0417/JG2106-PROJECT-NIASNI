package com.zzxy.pj.sys.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzxy.pj.common.util.Assert;
import com.zzxy.pj.sys.dao.SysDeptDao;
import com.zzxy.pj.sys.entity.SysDept;
import com.zzxy.pj.sys.service.SysDeptService;
import com.zzxy.pj.sys.vo.SysDeptVo;

@Service
public class SysDeptServiceImpl implements SysDeptService {
	
	@Autowired
	private SysDeptDao deptDao;

	public List<SysDept> findAllDept() {
		List<SysDept> list = deptDao.findallDept();
		Assert.isEmpty(list == null || list.size() == 0 , "部门信息不存在");
		return list;
	}

	public List<SysDeptVo> findDeptVo() {
		List<SysDeptVo> list = deptDao.findDeptVo();
		Assert.isEmpty(list == null || list.size() == 0 , "部门信息不存在");
		return list;
	}

	public Integer saveDept(SysDept dept) {
		Assert.isEmpty(dept == null || dept.getName() == null, "部门信息不完整");
		SysDept d = deptDao.findDeptByName(dept.getName());
		Assert.isEmpty(d != null, "部门名已存在");
		Integer n = deptDao.saveDept(dept);
		Assert.isEmpty(n == 0, "添加失败");
		return n;
	}

	public Integer updateDept(SysDept dept) {
		Assert.isEmpty(dept == null || dept.getName() == null, "部门信息不完整");
		SysDept d = deptDao.findDeptByIdName(dept.getId(), dept.getName());
		Assert.isEmpty(d != null, "部门名已存在");
		Integer n = deptDao.updateDept(dept);
		Assert.isEmpty(n == 0, "修改失败");
		return null;
	}

	public Integer deleteDept(Integer id) {
		Assert.isEmpty(id == null || id == 0, "请选择要删除的数据");
		Integer n = deptDao.deleteDept(id);
		Assert.isEmpty(n == 0, "删除失败或已被删除");
		deptDao.changeUserDeptId(id);
		return n;
	}

}
