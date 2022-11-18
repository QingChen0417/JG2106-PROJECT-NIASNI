package com.zzxy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zzxy.pj.sys.entity.SysDept;
import com.zzxy.pj.sys.vo.SysDeptVo;

@Mapper
public interface SysDeptDao {

	/**
	 * 通过id找部门
	 * @param id
	 * @return
	 */
	SysDept findDeptById(Integer id);

	/**
	 * 查找所有部门信息(id, name, parentId)
	 * @return
	 */
	@Select("select id, name, parentId from sys_depts")
	List<SysDept> findallDept();

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
	 * 根据部门名查找部门信息
	 * @param name
	 * @return
	 */
	SysDept findDeptByName(String name);

	/**
	 * 根据部门名和id查找部门信息
	 * @param id
	 * @param name
	 * @return
	 */
	SysDept findDeptByIdName(@Param("id") Integer id, @Param("name") String name);

	/**
	 * 修改部门
	 * @param dept
	 * @return
	 */
	Integer updateDept(SysDept dept);

	/**
	 * 删除部门
	 * @param id
	 * @return
	 */
	Integer deleteDept(Integer id);

	/**
	 * 修改用户部门信息
	 * @param id
	 */
	void changeUserDeptId(Integer id);
	
}
