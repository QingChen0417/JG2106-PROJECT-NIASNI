package com.zzxy.pj.sys.service.realm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzxy.pj.sys.dao.SysMenuDao;
import com.zzxy.pj.sys.dao.SysRoleMenuDao;
import com.zzxy.pj.sys.dao.SysUserDao;
import com.zzxy.pj.sys.dao.SysUserRoleDao;
import com.zzxy.pj.sys.entity.SysUser;

@Service
public class ShiroUserRealm extends AuthorizingRealm {
	
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private SysUserRoleDao userRoleDao;
	@Autowired
	private SysRoleMenuDao roleMenuDao;
	@Autowired
	private SysMenuDao menuDao;
	
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		//设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}

	//重写授权必要的方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		System.out.println(user);
		Integer userId = user.getId();
		List<Integer> roleIds = userRoleDao.findRoleByUserId(userId);
		if (roleIds == null || roleIds.size() == 0) {
			throw new AuthorizationException();
		}
		List<Integer> menuIds = roleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(new Integer[] {}));
		if (menuIds == null || menuIds.size() == 0) {
			throw new AuthorizationException();
		}
		List<String> permissions = menuDao.findPermissions(menuIds.toArray(new Integer[] {}));
		if (permissions == null || permissions.size() == 0) {
			throw new AuthorizationException();
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		permissions.stream().distinct();
		Set<String> set = new HashSet<>();
		for (String str : permissions) {
			if (str != null && str.length() != 0) {
				set.add(str);
			}
		}
		info.setStringPermissions(set);
		return info;
	}
	
	public static void main(String[] args) {
		String[] arr = {"黄鼠狼", "原玩到到", "六因素", "杨伟"};
		List<String> list = Arrays.asList(arr);
		//遍历	相当于增强for循环
		list.stream().forEach(System.out::println);
		list.stream().filter(e -> e.length() == 3).forEach(System.out::println);
	}

	//重写认证方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//向下造型
		//UsernamePasswordToken封装的是浏览器传过来的用户数据
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		SysUser user = userDao.findUserByName(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		if (user.getValid() == 0) {
			throw new LockedAccountException();
		}
		//处理盐变成ByteSource对象
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user, //身份认真, 保证唯一 
				user.getPassword(), //密码
				credentialsSalt, //盐, 变成ByteSource
				getName());	//realm的名字
		return info;
	}
	
}
