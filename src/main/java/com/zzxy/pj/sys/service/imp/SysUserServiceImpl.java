package com.zzxy.pj.sys.service.imp;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.zzxy.pj.common.annotation.RequireLog;
import com.zzxy.pj.common.entity.Pagination;
import com.zzxy.pj.common.util.Assert;
import com.zzxy.pj.common.util.ShiroUtil;
import com.zzxy.pj.sys.dao.SysUserDao;
import com.zzxy.pj.sys.dao.SysUserRoleDao;
import com.zzxy.pj.sys.entity.SysUser;
import com.zzxy.pj.sys.service.SysUserService;
import com.zzxy.pj.sys.vo.SysUserDeptVo;

@Service
//整个类看作是一个事务
//isolation : 指定事务隔离级别
//		未提交读: Isolation.READ_UNCOMMITTED
//		已提交读: Isolation.READ_COMMITTED  oracle默认
//		可重复读:Isolation.REPEATABLE_READ mysql默认
//		串行化:	Isolation.SEERIALZABLE
//timeout : 事务超时, 秒为单位
//rollbackFor : 指定回滚生效的异常
//noRollbackFor : 指定不回滚的异常
//propagation : 指定事务传播机制
//readOnly : 设置是否只读, 默认可以读写
//select * from acc for update : 查询的数据加了行锁
@Transactional(isolation = Isolation.READ_COMMITTED,timeout = 30,rollbackFor = RuntimeException.class)
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private SysUserRoleDao userRoleDao;

	//当类和方法上都有事务注解时, 方法的优先级高
	@RequireLog("查询操作")
	@Transactional(readOnly = true)//只读
	@RequiresPermissions("sys:user:view")//shiro提供的注解
	public Pagination findUserDeptVo(String username, Integer curPage, Integer pageSize) {
		Assert.isEmpty(curPage == null || curPage == 0, "请选择当前页数");
		Assert.isEmpty(pageSize == null || pageSize == 0, "请选择每页条数");
		Integer count = userDao.countAllUser(username);//查找总条数
		Pagination pageObj = new Pagination(curPage, count, pageSize);
		int start = (pageObj.getCurPage() - 1) * pageObj.getPageSize();
		List<SysUserDeptVo> list = userDao.findUserDeptVo(username, start, pageObj.getPageSize());
		pageObj.setPageData(list);
		Assert.isEmpty(list == null || list.size() == 0, "数据不存在或已被删除");
		return pageObj;
	}

	@RequiresPermissions("sys:user:updateValid")
	public Integer updateValid(Integer id, Integer valid) {
		Assert.isEmpty(id == null || id == 0, "请选择用户");
		Assert.isEmpty(valid == null, "操作有误");
		Integer n = userDao.updateValid(id, valid, ShiroUtil.getUsername());
		Assert.isEmpty(n == 0, "修改失败");
		return n;
	}

	@RequireLog("注册用户")
	@RequiresPermissions("sys:user:add")
	public Integer insertUser(SysUser user, Integer [] roles) {
		Assert.isEmpty(user == null || user.getUsername() == null, "请填写用户信息");
		//UUID是util包中的类
		String salt = UUID.randomUUID().toString();
		//加密方式, 原密码, 盐, 加密次数
		SimpleHash sh = new SimpleHash("MD5", user.getPassword(), salt, 1);
		String password = sh.toHex();
		user.setSalt(salt);
		user.setPassword(password);
		SysUser u = userDao.findUserByName(user.getUsername());//查找用户是否存在
		Assert.isEmpty(u != null, "用户名已存在");
		user.setCreatedUser(ShiroUtil.getUsername());
		Integer n = userDao.insertUser(user);
		Assert.isEmpty(n == 0, "添加失败");
		//添加用户角色关系数据
		userRoleDao.insertUserRole(user.getId(), roles);
		return n;
	}

	public List<Integer> findRoleByUserId(Integer userId) {
		Assert.isEmpty(userId == null || userId == 0, "请选择要修改的用户");
		List<Integer> list = userRoleDao.findRoleByUserId(userId);
		return list;
	}

	@RequiresPermissions("sys:user:update")
	public Integer updateUser(SysUser user, Integer[] roleIds) {
		Assert.isEmpty(user == null || user.getId() == 0 || user.getId() == null, "请选择要修改的用户");
		Assert.isEmpty(roleIds == null || roleIds.length == 0, "请选择至少一个角色");
		Integer n = userDao.updateUser(user);
		userRoleDao.deleteUserRoleByUserId(user.getId());
		userRoleDao.insertUserRole(user.getId(), roleIds);
		Assert.isEmpty(n == 0, "修改失败");
		return n;
	}
}
