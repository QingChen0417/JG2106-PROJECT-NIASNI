package com.zzxy.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzxy.pj.common.entity.JsonResult;
import com.zzxy.pj.sys.entity.SysDept;
import com.zzxy.pj.sys.service.SysDeptService;
import com.zzxy.pj.sys.vo.SysDeptVo;

@RestController
@RequestMapping("dept")
public class SysDeptController {
	
	@Autowired
	private SysDeptService deptService;
	
	/**
	 * 查找Dept(id, name, parentId)
	 * @return
	 */
	@RequestMapping("findDeptZtree")
	public JsonResult findDeptZtree() {
		List<SysDept> list = deptService.findAllDept();
		return new JsonResult(list);
	}
	
	/**
	 * 查找所有部门信息及上级部门名
	 * @return
	 */
	@RequestMapping("findDeptVo")
	public JsonResult findDeptVo() {
		List<SysDeptVo> list = deptService.findDeptVo();
		System.out.println(list);
		return new JsonResult(list);
	}
	
	@RequestMapping("doSaveDept")
	public JsonResult doSaveDept(SysDept dept) {
		deptService.saveDept(dept);
		return new JsonResult("添加成功");
	}
	
	@RequestMapping("doUpdateDept")
	public JsonResult doUpdateDept(SysDept dept) {
		deptService.updateDept(dept);
		return new JsonResult("修改成功");
	}
	
	@RequestMapping("doDeleteDept")
	public JsonResult doDeleteDept(Integer id) {
		deptService.deleteDept(id);
		return new JsonResult("删除成功");
	}
}
