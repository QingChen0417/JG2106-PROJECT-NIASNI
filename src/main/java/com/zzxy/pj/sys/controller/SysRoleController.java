package com.zzxy.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzxy.pj.common.entity.JsonResult;
import com.zzxy.pj.common.entity.Pagination;
import com.zzxy.pj.sys.entity.SysRole;
import com.zzxy.pj.sys.service.SysRoleService;
import com.zzxy.pj.sys.vo.SysRoleMenuVo;

@RestController
@RequestMapping("role")
public class SysRoleController {
	
	@Autowired
	private SysRoleService service;
	
	/**
	 * 通过角色名查找角色
	 * @param name	角色名, 可以模糊查询
	 * @param curPage	当前页
	 * @param pageSize	每页条数
	 * @return
	 */
	@RequestMapping("findRole")
	public JsonResult findRole(String name,@RequestParam(defaultValue="0") Integer curPage,@RequestParam(defaultValue="0") Integer pageSize) {
		Pagination pageObj = service.findRole(name, curPage, pageSize);
		return new JsonResult(pageObj);
	}
	
	/**
	 * 通过id删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteRole")
	public JsonResult deleteRoleById(Integer id) {
		service.deleteRoleById(id);
		return new JsonResult("删除成功");
	}

	/**
	 * 添加角色 (给角色授权)
	 * @param role
	 * @param ids
	 * @return
	 */
	@RequestMapping("saveRole")
	public JsonResult saveRole(SysRole role,@RequestParam("menuIds[]") Integer[] ids) {
		service.insertRole(role, ids);
		return new JsonResult("添加成功");
	}
	
	@RequestMapping("findRoleMenuIdsByRoleId")
	public JsonResult findRoleMenuIds(Integer id) {
		SysRoleMenuVo srm = service.findRoleMenuIds(id);
		return new JsonResult(srm);
	}
	
	@RequestMapping("updateRoleById")
	public JsonResult updateRoleById(SysRoleMenuVo vo) {
		service.updateRoleById(vo);
		return new JsonResult("修改成功");
	}
	
	@RequestMapping("findAllRole")
	public JsonResult findAllRole() {
		List<SysRole> list = service.findAllRole();
		return new JsonResult(list);
	}
	
}
