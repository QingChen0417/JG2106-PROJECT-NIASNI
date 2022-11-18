package com.zzxy.pj.sys.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzxy.pj.common.entity.Pagination;
import com.zzxy.pj.common.util.Assert;
import com.zzxy.pj.sys.dao.SysRoleDao;
import com.zzxy.pj.sys.dao.SysRoleMenuDao;
import com.zzxy.pj.sys.dao.SysUserRoleDao;
import com.zzxy.pj.sys.entity.SysRole;
import com.zzxy.pj.sys.service.SysRoleService;
import com.zzxy.pj.sys.vo.SysRoleMenuVo;

@Service
public class SysRoleServiceImpl implements SysRoleService{
	
	@Autowired
	private SysRoleDao roleDao;
	
	@Autowired
	private SysRoleMenuDao rmDao;
	
	@Autowired
	private SysUserRoleDao urDao;

	@Override
	public Pagination findRole(String name, Integer curPage, Integer pageSize) {
		//1. 验证curPage, pageSize是否有值
		Assert.isEmpty(curPage == null || pageSize == null, "请选择当前页码或每页条数");
		//2. 得到角色的总条数
		Integer count = roleDao.countRole(name);
		//3. 创建分页对象, 算出所有属性
		Pagination pageObj = new Pagination(curPage, count, pageSize);
		Integer start = (pageObj.getCurPage() - 1) * pageObj.getPageSize();
		//4. 根据参数找角色
		List<SysRole> list = roleDao.findRole(name, start, pageObj.getPageSize());
		//5. 验证第四步的结果是否为null
		Assert.isEmpty(list == null || list.size() == 0, "数据或已被删除");
		pageObj.setPageData(list);
		return pageObj;
	}

	@Transactional
	public Integer deleteRoleById(Integer id) {
		//1.验证参数
		Assert.isEmpty(id == null || id == 0, "请选择要删除的数据");
		//2.调用三个dao的与角色相关的删除方法
		Integer n = roleDao.deleteRoleById(id);
		rmDao.deleteMenuByRoleId(id);
		urDao.deleteUserRoleByRoleId(id);
		//3.验证结果
		Assert.isEmpty(n == 0, "角色不存在或已被删除");
		return n;
	}

	public Integer insertRole(SysRole role, Integer[] ids) {
		//1.验证参数
		Assert.isEmpty(role == null || role.getName().equals(""), "请输入角色名称");
		Assert.isEmpty(ids == null || ids.length == 0, "请给角色权限");
		//2.插入角色
		Integer n = roleDao.insertRole(role);
		//3.验证结果
		Assert.isEmpty(n == 0, "角色添加失败");
		//4.插入角色和菜单关系数据
		rmDao.insertRoleMenuData(role.getId(), ids);
		return n;
	}

	public SysRoleMenuVo findRoleMenuIds(Integer id) {
		//验证参数
		Assert.isEmpty(id == null || id == 0, "请选择要修改的数据");
		SysRoleMenuVo srm = roleDao.finRoleMenuIds(id);
		System.out.println(srm);
		Assert.isEmpty(srm == null, "角色不存在或已被删除");
		return srm;
	}

	public Integer updateRoleById(SysRoleMenuVo vo) {
		Assert.isEmpty(vo == null || vo.getId() == null, "请选择要修改的角色!");
		//根据角色id删除角色菜单数据
		rmDao.deleteMenuByRoleId(vo.getId());
		rmDao.insertRoleMenuData(vo.getId(), vo.getMenuIds().toArray(new Integer[] {}));
		Integer n = roleDao.updateRoleById(vo);
		Assert.isEmpty(n == 0, "修改失败或数据已不存在");
		return n;
	}

	public List<SysRole> findAllRole() {
		List<SysRole> list = roleDao.findAllRole();
		return list;
	}
	
	

}
