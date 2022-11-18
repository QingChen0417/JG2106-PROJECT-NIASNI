package com.zzxy.pj.sys.service;

import java.util.List;

import com.zzxy.pj.sys.entity.SysDept;
import com.zzxy.pj.sys.vo.SysDeptVo;

public interface SysDeptService {

	/**
	 * 查找所有部门信息
	 * @return
	 */
	List<SysDept> findAllDept();

	/**
	 * 查找所有部门信息及上级部门名
	 * @return
	 */
	List<SysDeptVo> findDeptVo();

	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	Integer saveDept(SysDept dept);

	/**
	 * 修改部门
	 * @param dept
	 * @return
	 */
	Integer updateDept(SysDept dept);

	/**
	 * 删除部门
	 * @param id
	 */
	Integer deleteDept(Integer id);

}
