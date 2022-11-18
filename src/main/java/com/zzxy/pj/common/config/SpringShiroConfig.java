package com.zzxy.pj.common.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration//当作配置文件类
public class SpringShiroConfig {

	//@Bean注解会把方法的返回值对象交给容器管理, id为方法名
	/*@Bean
	public SecurityManager securityManager() {
		return new DefaultWebSecurityManager();
	}*/

	@Bean
	public ShiroFilterFactoryBean  shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		//设置登录路径
		shiroFilter.setLoginUrl("/doLoginUI");
		//LinkedHashMap: 有序的
		//用map来存储路径, 是否放过这些路径
		//key表示路径, static开始的路径 value表示放过还是认证
		//value: 表示放过还是认证, value值是固定的几种
		//		 authc表示要认证, user表示要认证, anno表示放过
		Map<String, String> map = new LinkedHashMap<>();
		//放过登录
		//map.put("/doLoginUI", "anon");
		//静态资源都设置为anon
		map.put("/bower_components/**","anon");
		map.put("/build/**","anon");
		map.put("/dist/**","anon");
		map.put("/plugins/**","anon");
		map.put("/user/doLogin","anon");
		//配置退出登录路径
		map.put("/doLoginOut", "logout");
		//除了匿名访问的资源,其它都要认证("authc")后访问
		map.put("/**","user");
		//设置到shiro的过滤器中
		shiroFilter.setFilterChainDefinitionMap(map);
		return shiroFilter;
	}

	@Bean
	public SecurityManager securityManager(Realm realm, CacheManager cacheManager,RememberMeManager rememberMeManager,SessionManager sessionManager) {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		sManager.setCacheManager(cacheManager);
		sManager.setRememberMeManager(rememberMeManager);
		sManager.setSessionManager(sessionManager);
		return sManager;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			SecurityManager securityManager) {
		//类似于切入点
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	
	@Bean
    public ShiroDialect getShiroDialect(){
         return new ShiroDialect();
     }
	
	//CacheManager的方法名不能是cacheManager, 因为框架里面已经有一个了
	//		cacheManager的id不能重复
	@Bean
	public CacheManager shiroCacheManager(){
		//shiro使用的缓存策略: 软引用, 内存满了之后缓存会被回收
		return new MemoryConstrainedCacheManager();
	}
	@Bean
	public RememberMeManager rememberMeManager() {
		CookieRememberMeManager remember = new CookieRememberMeManager();
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setMaxAge(30);//单位秒
		remember.setCookie(cookie);
		return remember;
	}
	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sManager= new DefaultWebSessionManager();
		sManager.setGlobalSessionTimeout(60*60*1000);//毫秒
		return sManager;
	}
}
