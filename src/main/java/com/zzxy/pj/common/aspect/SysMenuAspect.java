package com.zzxy.pj.common.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysMenuAspect {
	
	private Map<String, Object> map = new HashMap<>();
	
	@Pointcut("@annotation(com.zzxy.pj.common.annotation.RequiredCache)")
	public void findMenu() {}
	
	@Around("findMenu()")
	public Object test(ProceedingJoinPoint pj) throws Throwable {
		if (map.get("menuData") == null) {
			Object obj = pj.proceed();
			map.put("menuData", obj);
		}
		System.out.println("缓存");
		return map.get("menuData");
	}
}
